package com.whixard.anarchycore.commands;

import com.whixard.anarchycore.AnarchyCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AnarchyCoreCommand implements CommandExecutor {

    static Map<String, String> exploitMap = new HashMap<>();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//...

    public static void addToMap(String name, String type) {
        Date date = new Date();
        String formattedDate = dateFormat.format(date);
        exploitMap.put(name, type + " §7at §c" + formattedDate);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = "§c§lAnarchyCore >> §7";


        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("anarchycore.staff")) {
                if (args.length == 0) {
                    player.sendMessage("§7Running §cAnarchyCore §7v1.0.0 by §cWhiXard");
                    player.sendMessage("\n");
                    player.sendMessage("§4§l> §c/anarchycore reload §8- §7Reloads the config");
                    player.sendMessage("§4§l> §c/anarchycore help §8- §7Shows this help menu");
                    player.sendMessage("§4§l> §c/anarchycore logs §8- §7Shows the exploit record");
                    return true;
                }


                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        player.sendMessage(prefix + "§aAnarchyCore has been reloaded!");
                        try  {
                            AnarchyCore.getPlugin(AnarchyCore.class).reloadConfig();
                        } catch (CommandException e) {
                            player.sendMessage("§cAnarchyCore failed to reload! Check your config.yml for errors!");
                            e.printStackTrace();
                        }
                        return true;
                    }

                    if (args[0].equalsIgnoreCase("logs")) {
                        if (exploitMap.isEmpty()) {
                            player.sendMessage(prefix + "§aNo logs to show!");
                            return true;
                        }
                        for (Map.Entry<String, String> entry : exploitMap.entrySet()) {
                            player.sendMessage("§c" + entry.getKey() + " §7tried to use §c" + entry.getValue());
                        }
                        return true;
                    }

                    if(args[0].equalsIgnoreCase("help")) {
                        player.sendMessage("§7Running §cAnarchyCore §7v1.0.0 by §cWhiXard");
                        player.sendMessage("\n");
                        player.sendMessage("§4§l> §c/anarchycore reload §8- §7Reloads the config");
                        player.sendMessage("§4§l> §c/anarchycore help §8- §7Shows this help menu");
                        player.sendMessage("§4§l> §c/anarchycore logs §8- §7Shows the exploit record");
                        return true;
                    }


                    player.sendMessage(prefix + "§cInvalid arguments! Type /anarchycore help for help!");
                    return true;
                }

                if (args.length > 2) {
                    player.sendMessage(prefix + "§cInvalid arguments! Type /anarchycore help for help!");
                    return true;
                }
            }
        }
        else {
            throw new CommandException("You must be a player to use this command!");
        }
        return true;
    }
}
