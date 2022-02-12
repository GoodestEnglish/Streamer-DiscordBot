package me.goodestenglish.streamer.util;

import me.goodestenglish.streamer.Streamer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class MessageBuilder {

    public static MessageEmbed buildFailedMessage(String description) {
        return buildFailedMessage("出現錯誤!", description);
    }

    public static MessageEmbed buildFailedMessage(String header, String description) {
        return new EmbedBuilder()
                .setColor(Color.RED)
                .setAuthor(header)
                .setDescription(description)
                .setFooter(Util.getTime())
                .build();
    }

    public static MessageEmbed buildSuccessMessage(String description) {
        return new EmbedBuilder()
                .setColor(Color.GREEN)
                .setAuthor("Emilia", null, Streamer.getJda().getSelfUser().getAvatarUrl())
                .setDescription(description)
                .setFooter(Util.getTime())
                .build();
    }

    public static MessageEmbed buildSuccessMessage(String header, String description) {
        return new EmbedBuilder()
                .setColor(Color.GREEN)
                .setAuthor(header)
                .setDescription(description)
                .setFooter(Util.getTime())
                .build();
    }

    public static <T> MessageEmbed buildNullMessage(Class<T> clazz) {
        return buildFailedMessage("無法成功運行! " + clazz.getName() + " == null");
    }

    public static MessageEmbed buildMessage(String header, String description) {
        return new EmbedBuilder()
                .setColor(Color.YELLOW)
                .setAuthor(header)
                .setDescription(description)
                .setFooter(Util.getTime())
                .build();
    }

}
