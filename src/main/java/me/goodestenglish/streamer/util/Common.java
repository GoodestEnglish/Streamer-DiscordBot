package me.goodestenglish.streamer.util;

import me.goodestenglish.streamer.Streamer;
import me.goodestenglish.streamer.StreamerConfig;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Common {

    public static String NO_PERMISSIONS = "你沒有權限使用這個指令!";


    public static String NO_PERMISSIONS(Permission... permissions) {
        List<String> p = Arrays.stream(permissions).map(Permission::getName).collect(Collectors.toList());
        return "你必須要擁有 " + String.join(", ", p) + " 權限才能使用這個指令!";
    }

    public static String NO_ROLE(String... roleID) {
        Guild guild = Streamer.getJda().getGuildById(StreamerConfig.GUILD_ID);
        if (guild == null) {
            return "錯誤出現了! guild == null";
        }
        List<String> roleName = new ArrayList<>();
        for (String roleStr : roleID) {
            Role role = guild.getRoleById(roleStr);
            if (role == null) {
                return "錯誤出現了! role == null";
            }
            roleName.add(role.getName());
        }
        return "你必須要擁有 '" + String.join(",", roleName) + "' 身份組才能使用這個指令!";
    }

}
