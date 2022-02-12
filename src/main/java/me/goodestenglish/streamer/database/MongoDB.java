package me.goodestenglish.streamer.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import me.goodestenglish.streamer.Streamer;
import me.goodestenglish.streamer.StreamerConfig;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MongoDB {

    @Getter private MongoClient mongoClient;
    @Getter private MongoDatabase mongoDatabase;
    @Getter private MongoCollection<Document> mongoCollection;
    @Getter private boolean isEnabled;

    public MongoDB() {
        connect();
    }

    public void removePlayerData(String username) {
        mongoCollection.findOneAndDelete(Filters.eq("username", username));
    }

    public CompletableFuture<Boolean> replaceResult(String username, Object document) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                mongoCollection.replaceOne(Filters.eq("username", username), (Document) document, new ReplaceOptions().upsert(true));
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }

    public void connect() {
        try {
            mongoClient = new MongoClient(StreamerConfig.MONGO_HOST);
            mongoDatabase = mongoClient.getDatabase("streamer");
            mongoCollection = mongoDatabase.getCollection("data");
            isEnabled = true;
            System.out.println("成功連接到MongoDB");
        } catch (Exception e) {
            isEnabled = false;
            System.out.println("未能連接到MongoDB");
        }
    }

    public <T> T getData(String username, Class<T> clazz) {
        return clazz.cast(mongoCollection.find(Filters.eq("username", username)).first());
    }

    public <T> T getDataByDiscordID(String ID, Class<T> clazz) {
        return clazz.cast(mongoCollection.find(Filters.eq("discord_id", ID)).first());
    }

    public CompletableFuture<List<RetrievedDocument>> retrievePlayers() {
        List<RetrievedDocument> users = new ArrayList<>();
        return CompletableFuture.supplyAsync(() -> {
            if (Streamer.getMongoDB().getMongoCollection().countDocuments() <= 0) {
                return users;
            }
            for (Document doc : getMongoCollection().find()) {
                users.add(new RetrievedDocument(doc.getString("username"), doc.getString("discord_id"), doc.getLong("save_time")));
            }
            return users;
        });
    }
}
