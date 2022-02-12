package me.goodestenglish.streamer.util;

import me.goodestenglish.streamer.Streamer;
import me.goodestenglish.streamer.StreamerConfig;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class Util {

    public static String getTime() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone(ZoneId.of("GMT+08:00")));
        return df.format(new Date());
    }

    public static boolean hasPermissions(User user, Permission... permissions) {
        Member member = getGuild().retrieveMember(user).complete();
        if (member == null) {
            return false;
        }
        return member.hasPermission(permissions);
    }

    public static boolean hasRole(User user, String... roleIDs) {
        Member member = getGuild().retrieveMember(user).complete();
        if (member == null) {
            return false;
        }

        for (String roleID : roleIDs) {
            Role selectedRole = getGuild().getRoleById(roleID);
            if (selectedRole == null) {
                return false;
            }
            if (member.getRoles().contains(selectedRole)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasRoleOrHigherRole(User user, String roleID) {
        Member member = getGuild().retrieveMember(user).complete();
        if (member == null) {
            return false;
        }
        Role selectedRole = getGuild().getRoleById(roleID);
        if (selectedRole == null) {
            return false;
        }
        return member.getRoles().stream().anyMatch(role -> role.getPosition() >= selectedRole.getPosition());
    }

    public static Guild getGuild() {
        return Streamer.getJda().getGuildById(StreamerConfig.GUILD_ID);
    }

    public static TextChannel getTextChannelByID(String ID) {
        return getGuild().getTextChannelById(ID);
    }

    public static VoiceChannel getVoiceChannelByID(String ID) {
        return getGuild().getVoiceChannelById(ID);
    }

    public static Role getRoleByID(String ID) {
        return getGuild().getRoleById(ID);
    }

}
