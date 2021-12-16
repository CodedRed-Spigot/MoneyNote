package dev.codedred.moneynote.utils;

import dev.codedred.moneynote.data.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtil {

    public enum ChatEnum {
        CMD_USAGE,
        CMD_ALL,
        CMD_RANDOM,
        CMD_GIVE,

        ERROR_NO_PERMS,
        ERROR_INV_FULL,
        ERROR_ZERO_BAL,
        ERROR_MIN_MAX_ERROR;


    }

    public static void send(Player player, ChatEnum type) {
        DataManager data = DataManager.getInstance();
        switch (type) {
            case CMD_USAGE:
                for (String msg : data.getLang().getStringList("messages.usage"))
                    player.sendMessage(format(msg));
                break;
            case CMD_ALL:
            case CMD_GIVE:
            case CMD_RANDOM:


            case ERROR_INV_FULL:
            case ERROR_NO_PERMS:
            case ERROR_ZERO_BAL:
            case ERROR_MIN_MAX_ERROR:

        }
    }

    public static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
