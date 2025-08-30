package me.dedis34.localGlobalChat;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public final class LocalGlobalChat extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Map<String, Integer> worldRadiusMap = new HashMap<>();

        ConfigurationSection worldsSection = getConfig().getConfigurationSection("worlds");
        if (worldsSection != null) {
            for (String worldName : worldsSection.getKeys(false)) {
                int radius = worldsSection.getInt(worldName + ".radius", 250);
                worldRadiusMap.put(worldName, radius);
                getLogger().info("Loaded world " + worldName + " with radius " + radius);
            }
        }

        getServer().getPluginManager().registerEvents(new ChatHandler(this, worldRadiusMap), this);

        getLogger().info("LocalGlobalChat enabled!");

    }

    @Override
    public void onDisable() {
        getLogger().info("LocalGlobalChat disabled!");
    }
}
