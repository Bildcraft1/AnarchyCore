package com.whixard.anarchycore.events;

import com.whixard.anarchycore.commands.AnarchyCoreCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.TabCompleteEvent;

public class AntiPluginsDumper implements Listener {
    @EventHandler(priority = org.bukkit.event.EventPriority.HIGHEST)
    public void onPluginsDumper(PlayerCommandPreprocessEvent e) {
        String prefix = "§c§lAnarchyCore >> §7";
        String message = e.getMessage().toLowerCase();
        Player player = e.getPlayer();
        if (player.hasPermission("anarchycore.staff")) {
            return;
        }
        if (message.contains("bukkit:ver") || message.contains("pl") || message.contains("plugins") || message.contains("bukkit:pl") || message.contains("bukkit:plugins") || message.contains("bukkit:version") || message.contains("ver") || message.contains("version")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(prefix + "§cPlugins dumpers are disabled!");
            AnarchyCoreCommand.addToMap(player.getName(), "Plugins dumper");
        }
    }

    @EventHandler(priority = org.bukkit.event.EventPriority.HIGHEST)
    public void onTabComplete(TabCompleteEvent e) {
        String prefix = "§c§lAnarchyCore >> §7";
        String message = e.getBuffer().toLowerCase();
        Player player = e.getSender().getServer().matchPlayer(e.getSender().getName()).get(0);
        if (player.hasPermission("anarchycore.staff")) {
            return;
        }
        if (message.contains("bukkit:ver")) {
            e.setCancelled(true);
            e.getSender().sendMessage(prefix + "§cPlugins command is disabled!");
            AnarchyCoreCommand.addToMap(player.getName(), "Plugins dumper");
        }
    }
}
