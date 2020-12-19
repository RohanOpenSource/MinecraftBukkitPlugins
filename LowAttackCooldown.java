package com.rohan.lowattackcooldown;

import com.rohan.lowattackcooldown.commands.LowAttackCooldownCommands;
import com.rohan.lowattackcooldown.events.LowAttackCooldownEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

public class LowAttackCooldown extends JavaPlugin {
    @Override
    public void onEnable(){
        LowAttackCooldownCommands commands=new LowAttackCooldownCommands();
        getServer().getPluginManager().registerEvents(new LowAttackCooldownEvents(), this);
        getCommand("heal").setExecutor(commands);
        getCommand("feed").setExecutor(commands);
        getCommand("hitheal").setExecutor(commands);
        getCommand("mlgwater").setExecutor(commands);
        getCommand("mlgslime").setExecutor(commands);
        getCommand("mlghorse").setExecutor(commands);
        getCommand("web").setExecutor(commands);
        getCommand("cage").setExecutor(commands);
        getCommand("void").setExecutor(commands);
        getCommand("guns").setExecutor(commands);
        getCommand("customenchant").setExecutor(commands);
        getCommand("pack").setExecutor(commands);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"[LowAttackCooldown]: hi nerds");
        if(!Bukkit.getOnlinePlayers().isEmpty()){
            for(Player online  :  Bukkit.getOnlinePlayers()){
                LowAttackCooldownEvents.createBoard(online);
            }
        }


    }
    @Override
    public void onDisable(){
        getServer().getConsoleSender().sendMessage(ChatColor.RED+"[LowAttackCooldown]: later nerds");


    }
}
