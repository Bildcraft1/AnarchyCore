package com.whixard.anarchycore;

import com.whixard.anarchycore.commands.AnarchyCoreCommand;
import com.whixard.anarchycore.events.AntiPluginsDumper;
import com.whixard.anarchycore.events.ChatFilter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class AnarchyCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("AnarchyCore has been enabled!");
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new ChatFilter(), this);
        getServer().getPluginManager().registerEvents(new AntiPluginsDumper(), this);
        Objects.requireNonNull(getCommand("anarchycore")).setExecutor(new AnarchyCoreCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("AnarchyCore has been disabled!");
    }
}
