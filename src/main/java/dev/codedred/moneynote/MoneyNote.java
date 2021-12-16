package dev.codedred.moneynote;

import dev.codedred.moneynote.apis.MoneyAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MoneyNote extends JavaPlugin {

    public MoneyAPI eco;

    @Override
    public void onEnable() {


        if (hasVault()) {
            eco = new MoneyAPI();
            if (!eco.setupEconomy()) {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Disabling MoneyNote!");
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "You must have Vault and an Economy plugin installed!");
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                Bukkit.getPluginManager().disablePlugin(this);
            }
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean hasVault() {
        return Bukkit.getServer().getPluginManager().getPlugin("Vault") != null;
    }

}
