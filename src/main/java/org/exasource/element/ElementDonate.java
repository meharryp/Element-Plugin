package org.exasource.element;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.exasource.element.commands.PingCommand;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class ElementDonate extends JavaPlugin {
    private final Logger console = getLogger();
    private final FileConfiguration config = getConfig();

    @Override
    public void onLoad() {
        // Setup default config options
        config.addDefault("installed", false);

        // Save config
        config.options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onEnable() {
        // Check if we should load
        if (!config.getBoolean("installed")) {
            console.log(Level.WARNING, "Not loading, please generate a config in the web interface");
            getPluginLoader().disablePlugin(this);
            return;
        }

        registerCommands();

        console.log(Level.INFO, "Loaded successfully");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        getCommand("ping").setExecutor(new PingCommand());
    }
}
