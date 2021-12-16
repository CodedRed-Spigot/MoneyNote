package dev.codedred.moneynote.data;

import dev.codedred.moneynote.MoneyNote;
import org.bukkit.configuration.file.FileConfiguration;

public class DataManager {

    private final static DataManager instance = new DataManager();

    public static DataManager getInstance() {
        return instance;

    }

    private final CustomConfig lang = new CustomConfig(MoneyNote.getPlugin(MoneyNote.class), "language.yml");
    private final CustomConfig config = new CustomConfig(MoneyNote.getPlugin(MoneyNote.class), "config.yml");

    public FileConfiguration getConfig() {
        return config.getConfig();
    }

    public FileConfiguration getLang() {
        return lang.getConfig();
    }

    public void reloadAll() {
        lang.reloadConfig();
        config.reloadConfig();
    }



}