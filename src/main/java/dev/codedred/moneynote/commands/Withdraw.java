package dev.codedred.moneynote.commands;

import dev.codedred.moneynote.commands.custom.WithdrawAll;
import dev.codedred.moneynote.data.DataManager;
import dev.codedred.moneynote.utils.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Withdraw implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdL, String[] args) {
        DataManager data = DataManager.getInstance();
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Use /mnote create <player> <amount>");
            return true;
        }
        Player player = (Player) sender;

        // No arguments
        if (args.length == 0) {
            ChatUtil.send(player, ChatUtil.ChatEnum.CMD_USAGE);
            return true;
        }
        // Inv full + cannot drop note
        if (player.getInventory().firstEmpty() == -1 && !data.getConfig().getBoolean("drop-note.enabled")) {
            ChatUtil.send(player, ChatUtil.ChatEnum.ERROR_INV_FULL);
            return true;
        }

        switch(args[0].toLowerCase()) {
            case "all":
                WithdrawAll.execute(player, args);
                break;
        }



        return true;
    }
}
