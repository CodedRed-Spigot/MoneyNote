package dev.codedred.moneynote.commands.custom;

import dev.codedred.moneynote.MoneyNote;
import dev.codedred.moneynote.utils.ChatUtil;
import org.bukkit.entity.Player;

public class WithdrawProtocol {

    private static final MoneyNote plugin = MoneyNote.getPlugin(MoneyNote.class);

    static boolean hasPerm(Player player) {
        if (!(player.hasPermission("mn.all"))) {
            ChatUtil.send(player, ChatUtil.ChatEnum.ERROR_NO_PERMS);
            return false;
        }
        return true;
    }


    static boolean isNegative(Player player, double amount) {
        if (amount < 0) {
            ChatUtil.send(player, ChatUtil.ChatEnum.ERROR_ZERO_BAL);
            return true;
        }
        return false;
    }


    static double getMin(Player player) {
        if (plugin.getConfig().getBoolean("use-perm-based-notes")) {
            for (String perm : plugin.getConfig().getConfigurationSection("permission").getKeys(false)) {
                if (player.hasPermission("mn." + perm))
                    return plugin.getConfig().getDouble("permission." + perm + ".min-note-amount");
            }
        }
        return plugin.getConfig().getDouble("min-note-amount");
    }

    static double getMax(Player player) {
        if (plugin.getConfig().getBoolean("use-perm-based-notes")) {
            for (String perm : plugin.getConfig().getConfigurationSection("permission").getKeys(false)) {
                if (player.hasPermission("mn." + perm))
                    return plugin.getConfig().getDouble("permission." + perm + ".max-note-amount");
            }
        }
        return plugin.getConfig().getDouble("max-note-amount");
    }
}
