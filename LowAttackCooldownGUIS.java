package com.rohan.lowattackcooldown.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class LowAttackCooldownGUIS implements InventoryHolder {
    private Inventory inv;

    public void createInv(){
        inv= Bukkit.createInventory(null,9, ChatColor.GOLD+"Select Kit");
        ItemStack item= new ItemStack(Material.IRON_SWORD);
        ItemMeta meta =item.getItemMeta();
        //classic kit
        meta.setDisplayName(ChatColor.RED+"Classic kit");
        meta.addEnchant(Enchantment.LUCK,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(Collections.singletonList("click to select kit"));
        item.setItemMeta(meta);
        inv.setItem(0,new ItemStack(Material.IRON_SWORD, 1));



    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
