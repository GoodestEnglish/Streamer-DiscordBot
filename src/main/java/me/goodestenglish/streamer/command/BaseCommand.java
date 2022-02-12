package me.goodestenglish.streamer.command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.goodestenglish.streamer.util.Common;
import me.goodestenglish.streamer.util.MessageBuilder;
import me.goodestenglish.streamer.util.Util;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;

public abstract class BaseCommand extends Command {

    public BaseCommand(String command, String help) {
        this.name = command;
        this.help = help;
    }

    protected abstract Permission[] getPermissions();

    protected abstract String[] getRequiredRoleID();

    @Override
    protected void execute(CommandEvent commandEvent) {
        User user = commandEvent.getAuthor();
        if (getPermissions() != null) {
            if (!Util.hasPermissions(commandEvent.getAuthor(), getPermissions())) {
                commandEvent.reply(MessageBuilder.buildFailedMessage(Common.NO_PERMISSIONS(getPermissions())));
                return;
            }
        }
        if (getRequiredRoleID() != null) {
            if (!Util.hasRole(user, getRequiredRoleID())) {
                commandEvent.reply(MessageBuilder.buildFailedMessage(Common.NO_ROLE(getRequiredRoleID())));
                return;
            }
        }

        String[] args = commandEvent.getArgs().split(" ");

        runExecute(commandEvent, args);
    }

    protected abstract void runExecute(CommandEvent commandEvent, String[] args);
}
