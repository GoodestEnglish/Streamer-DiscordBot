package me.goodestenglish.streamer.command.impl;

import com.jagrosh.jdautilities.command.CommandEvent;
import me.goodestenglish.streamer.Streamer;
import me.goodestenglish.streamer.StreamerConfig;
import me.goodestenglish.streamer.command.BaseCommand;
import me.goodestenglish.streamer.util.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;

public class QueueCommand extends BaseCommand {
    public QueueCommand() {
        super("queue", "");
    }

    @Override
    protected Permission[] getPermissions() {
        return null;
    }

    @Override
    protected String[] getRequiredRoleID() {
        return StreamerConfig.ONLY_FOR_YT_MEMBERS ? new String[]{StreamerConfig.ROLE_YT_MEMBER, StreamerConfig.ROLE_NITRO_BOOSTER} : null;
    }

    @Override
    protected void runExecute(CommandEvent commandEvent, String[] args) {
        User user = commandEvent.getAuthor();

        if (args.length != 1 || args[0].equals("")) {
            commandEvent.reply(MessageBuilder.buildFailedMessage("指令用法: !queue <玩家遊戲ID>"));
            return;
        }

        if (Streamer.getMongoDB().getDataByDiscordID(user.getId(), Document.class) != null) {
            commandEvent.reply(MessageBuilder.buildFailedMessage("你已經在等候名單中! 不能重新輪候!"));
            return;
        }

        Document document = new Document();
        document.put("username", args[0]);
        document.put("discord_id", user.getId());
        document.put("save_time", System.currentTimeMillis());

        Streamer.getMongoDB().replaceResult(args[0], document).whenComplete((aBoolean, throwable) -> {
            if (aBoolean) {
                commandEvent.reply(MessageBuilder.buildSuccessMessage("成功把'" + args[0] + "'新增到等候名單!"));
            } else {
                commandEvent.reply(MessageBuilder.buildFailedMessage("錯誤出現了! " + throwable.getMessage()));
            }
        });
    }
}
