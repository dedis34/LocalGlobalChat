package me.dedis34.localGlobalChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class ChatHandler implements Listener {
    private final Plugin plugin;
    private final Map<String, Integer> worldRadiusMap;

    public ChatHandler(Plugin plugin, Map<String, Integer> worldRadiusMap) {
        this.plugin = plugin;
        this.worldRadiusMap = worldRadiusMap;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName();

        if (!worldRadiusMap.containsKey(worldName)) {
            return;
        }

        int radius = worldRadiusMap.get(worldName);
        double radiusSq = radius * (double) radius;

        String playerMessage = event.getMessage();
        boolean isGlobal = playerMessage.startsWith("!");
        final String checkedPlayerMessage
                = isGlobal ? playerMessage.substring(1).strip() : playerMessage;

        event.setCancelled(true);

        final String formattedCheckedPlayerMessage;
        if (isGlobal) {
            formattedCheckedPlayerMessage = ChatColor.DARK_GRAY + "["
                    + ChatColor.GREEN + "G"
                    + ChatColor.DARK_GRAY + "] "
                    + ChatColor.WHITE + player.getName()
                    + " " + ChatColor.GREEN + "-> "
                    + ChatColor.WHITE + checkedPlayerMessage;
        } else {
            formattedCheckedPlayerMessage = ChatColor.DARK_GRAY + "["
                    + ChatColor.BLUE + "L"
                    + ChatColor.DARK_GRAY + "] "
                    + ChatColor.WHITE + player.getName()
                    + " " + ChatColor.BLUE + "-> "
                    + ChatColor.WHITE + checkedPlayerMessage;
        }

        Bukkit.getScheduler().runTask(plugin, () -> {
            if (isGlobal) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getWorld().getName().equals(worldName)) {
                        p.sendMessage(formattedCheckedPlayerMessage);
                    }
                }
            } else {
                Location playerLocation = player.getLocation();
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!p.getWorld().getName().equals(worldName)) continue;

                    if (p.equals(player)) {
                        p.sendMessage(formattedCheckedPlayerMessage);
                        continue;
                    }
                    if (p.getLocation().distanceSquared(playerLocation) <= radiusSq) {
                        p.sendMessage(formattedCheckedPlayerMessage);
                    }
                }
            }
        });
    }
}
