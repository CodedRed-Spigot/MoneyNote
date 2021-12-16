package dev.codedred.moneynote.commands.custom;

import dev.codedred.moneynote.MoneyNote;
import dev.codedred.moneynote.utils.ChatUtil;
import org.bukkit.entity.Player;

public class WithdrawAll extends WithdrawProtocol{

    private static final MoneyNote plugin = MoneyNote.getPlugin(MoneyNote.class);

    public static void execute(Player player, String[] args) {
        if (!hasPerm(player))
            return;

        double amount = plugin.eco.getEconomy().getBalance(player);
        if (isNegative(player, amount))
          return;


        double min = getMin(player);
        double max = getMax(player);

        if (amount < min || amount > max ) {
            sender.sendMessage(plugin.format(plugin.getLang().getString("messages.min-max-error")
                    .replace("%min%", Double.toString(min)).replace("%max%", Double.toString(max))));
            return true;
        }
        amount = round(amount, 2);
        if ( plugin.getConfig().getBoolean("sounds.withdraw-sound.enabled") == true) {
            player.playSound(player.getLocation(), Sound.valueOf(plugin.getConfig().getString("sounds.withdraw-sound.sound")), 3.0F, 1.0F);
        }
        if (player.getInventory().firstEmpty() == -1 && plugin.getConfig().getBoolean("drop-note.enabled") == true) {
            for (String message : plugin.getLang().getStringList("messages.withdrawal-item-dropped")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message).replace("%amount%", Double.toString(amount))
                        .replace("%signer%", player.getName()));
            }
            double percent = 0.0;
            if (plugin.getConfig().getBoolean("item.tax.enabled")) {
                if (plugin.getConfig().getDouble("item.tax.percent") > 100.0)
                    percent = 100.0;
                else
                    percent = plugin.getConfig().getDouble("item.tax.percent");
                amount = amount - (amount * (percent / 100));
            }
            plugin.eco.getEconomy().withdrawPlayer(player, amount);
            player.getWorld().dropItemNaturally(player.getLocation(), plugin.currency.getNote(player.getName(), amount));
            return true;
        }
        for (String message : plugin.getLang().getStringList("messages.withdraw")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message).replace("%amount%", Double.toString(amount))
                    .replace("%signer%", player.getName()));
        }
        double percent = 0.0;
        if (plugin.getConfig().getBoolean("item.tax.enabled")) {
            if (plugin.getConfig().getDouble("item.tax.percent") > 100.0)
                percent = 100.0;
            else
                percent = plugin.getConfig().getDouble("item.tax.percent");
            amount = amount - (amount * (percent / 100));
        }
        plugin.eco.getEconomy().withdrawPlayer(player, amount);
        player.getInventory().addItem(plugin.currency.getNote(player.getName(), amount));
        player.updateInventory();
        return true;
    }

}
