package me.goodestenglish.streamer;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import lombok.Getter;
import me.goodestenglish.streamer.command.impl.QueueCommand;
import me.goodestenglish.streamer.database.MongoDB;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Streamer {
    @Getter private static JDA jda;
    @Getter private static EventWaiter waiter;
    @Getter private static MongoDB mongoDB;

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(StreamerConfig.DISCORD_BOT_TOKEN);
        builder.setActivity(Activity.watching("小英文的直播"));
        builder.addEventListeners(getCommandClientBuilder().build());
        jda = builder.build();
        waiter = new EventWaiter();

        mongoDB = new MongoDB();
    }

    public static CommandClientBuilder getCommandClientBuilder() {
        CommandClientBuilder client = new CommandClientBuilder();
        client.useHelpBuilder(false);
        client.setActivity(Activity.playing("觀眾場 | !queue"));
        client.setPrefix("!");
        client.setOwnerId(StreamerConfig.USER_OWNER_ID);
        client.addCommands(
                new QueueCommand()
        );
        return client;
    }

}
