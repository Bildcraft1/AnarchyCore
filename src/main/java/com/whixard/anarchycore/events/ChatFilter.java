package com.whixard.anarchycore.events;

import com.whixard.anarchycore.commands.AnarchyCoreCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

import com.whixard.anarchycore.AnarchyCore;

import java.util.List;

public class ChatFilter implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String prefix = "§c§lAnarchyCore >> §7";
        String message = e.getMessage().toLowerCase();
        Player p = e.getPlayer();
        List<String> blockedWords = AnarchyCore.getPlugin(AnarchyCore.class).getConfig().getStringList("blocked-words");

        if (AnarchyCore.getPlugin(AnarchyCore.class).getConfig().getBoolean("anti-force-op")) {
            if(message.matches("^<.*>.*$")) {
                for(Player player: p.getServer().getOnlinePlayers()) {
                    if(player.hasPermission("anarchycore.staff")) {
                        TextComponent msg = new TextComponent("§cAnarchyCore >> User §7" + p.getName() + " §ctried to force op!");
                        msg.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tp " + p.getName()));
                        player.spigot().sendMessage(msg);
                    }
                }

                AnarchyCoreCommand.addToMap(p.getName(), "force op");
                e.setCancelled(true);
                p.sendMessage(p.getName() + "§7: " + "§a" +  message);
            }
        }

        if (AnarchyCore.getPlugin(AnarchyCore.class).getConfig().getBoolean("anti-log4j")) {
            if (message.matches(".*\\{jndi:ldap://.*}.*")) {
                for(Player player: p.getServer().getOnlinePlayers()) {
                    if(player.hasPermission("anarchycore.staff")) {
                        TextComponent msg = new TextComponent(prefix + "User §7" + p.getName() + " §ctried to Log4Shell!");
                        msg.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tp " + p.getName()));
                        player.spigot().sendMessage(msg);
                    }
                }

                AnarchyCoreCommand.addToMap(p.getName(), "Log4Shell");
                e.setCancelled(true);
                p.sendMessage(p.getName() + "§7: " + "§a" +  message);
            }
        }

        // Create a loop to check if the message contains any of the blocked words
        for (String word : blockedWords) {
            if (message.contains(word)) {
                for (Player player : p.getServer().getOnlinePlayers()) {
                    if (player.hasPermission("anarchycore.staff")) {
                        player.sendMessage(prefix + "§c" + p.getName() + " §7tried to say §c" + message);
                        TextComponent message1 = new TextComponent("§7Click here to mute §c" + p.getName());
                        message1.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/mute " + p.getName()));
                        TextComponent message2 = new TextComponent("§7Click here to ban §c" + p.getName());
                        message2.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/ban " + p.getName()));
                        TextComponent message3 = new TextComponent("§7Click here to kick §c" + p.getName());
                        message3.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/kick " + p.getName()));
                        player.spigot().sendMessage(message1);
                        player.spigot().sendMessage(message2);
                        player.spigot().sendMessage(message3);
                    }
                }
                e.setCancelled(true);
                p.sendMessage(p.getName() + "§7: " + "§a" +  message);
            }
        }

    }
}
