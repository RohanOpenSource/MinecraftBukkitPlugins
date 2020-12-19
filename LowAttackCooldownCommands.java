package com.rohan.lowattackcooldown.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class LowAttackCooldownCommands implements CommandExecutor {

    public static boolean hitheal = true;
    public static boolean guns = true;
    public static boolean recoursepack= true;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("heal")) {
            double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue();
            player.setHealth(maxHealth);
            player.sendMessage("you have been healed");

        } else if (cmd.getName().equalsIgnoreCase("feed")) {
            player.setFoodLevel(20);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 10, 100));
            player.sendMessage("you have been fed");
        } else if (cmd.getName().equalsIgnoreCase("hitheal")) {
            if (hitheal) {
                hitheal = false;
                player.sendMessage("hitheal has been disabled");
            } else {
                hitheal = true;
                player.sendMessage("hitheal has been enabled");
            }

        } else if (cmd.getName().equalsIgnoreCase("mlgwater")) {
            player.getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 100));
            player.sendMessage("you have been yeeted");
        } else if (cmd.getName().equalsIgnoreCase("mlgslime")) {
            player.getInventory().addItem(new ItemStack(Material.SLIME_BLOCK));
            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 100));
            player.sendMessage("you have been yeeted");
        } else if (cmd.getName().equalsIgnoreCase("mlghorse")) {
            player.getInventory().addItem(new ItemStack(Material.HORSE_SPAWN_EGG));
            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 100));
            player.sendMessage("you have been yeeted");
        } else if (cmd.getName().equalsIgnoreCase("web")) {
            if (args.length >= 1) {
                try {
                    Player p = Bukkit.getPlayer(args[0]);
                    p.getLocation().getBlock().setType(Material.COBWEB);

                } catch (IllegalArgumentException e) {
                    player.sendMessage("that isn't a valid argument");
                }


            } else {
                player.sendMessage("you need to add the names of the player you want to web");
            }


        } else if (cmd.getName().equalsIgnoreCase("cage")) {
            if (args.length >= 1) {
                try {
                    Player p = Bukkit.getPlayer(args[0]);
                    p.getLocation().subtract(0, 1, 0).getBlock().setType(Material.GLASS);
                    p.getLocation().subtract(1, 0, 0).getBlock().setType(Material.GLASS);
                    p.getLocation().subtract(0, 0, 1).getBlock().setType(Material.GLASS);
                    p.getLocation().subtract(-1, 0, 0).getBlock().setType(Material.GLASS);
                    p.getLocation().subtract(0, 0, -1).getBlock().setType(Material.GLASS);
                    p.getLocation().subtract(1, -1, 0).getBlock().setType(Material.GLASS);
                    p.getLocation().subtract(0, -1, 1).getBlock().setType(Material.GLASS);
                    p.getLocation().subtract(-1, -1, 0).getBlock().setType(Material.GLASS);
                    p.getLocation().subtract(0, -1, -1).getBlock().setType(Material.GLASS);
                    p.getLocation().subtract(0, -2, 0).getBlock().setType(Material.GLASS);


                } catch (IllegalArgumentException e) {
                    player.sendMessage("that isn't a valid argument");
                }


            } else {
                player.sendMessage("you need to add the names of the player you want to trap");
            }


        } else if (cmd.getName().equalsIgnoreCase("void")) {
            if (args.length >= 1) {
                try {
                    Player p = Bukkit.getPlayer(args[0]);
                    for (int j = 0; j < 256; j++) {
                        p.getLocation().subtract(0, j, 0).getBlock().setType(Material.AIR);
                    }


                } catch (IllegalArgumentException e) {
                    player.sendMessage("that isn't a valid argument");
                }


            } else {
                player.sendMessage("you need to add the names of the player you want to void");
            }

        } else if (cmd.getName().equalsIgnoreCase("guns")) {
            if (guns) {
                guns = false;
                player.sendMessage("guns has been disabled");
            } else {
                guns = true;
                player.sendMessage("guns has been enabled");
            }



        }
        else if (cmd.getName().equalsIgnoreCase("customenchant")){
            if(player.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
                player.sendMessage("no items in main hand");
            }
            else{
                try{
                    AttributeModifier modifier=new AttributeModifier(UUID.randomUUID(), "generic.attackdamage", Double.valueOf(args[1]), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND );
                    ItemStack m=player.getInventory().getItemInMainHand();
                    ItemMeta meta= m.getItemMeta();
                    if(meta!=null) meta.addAttributeModifier(Attribute.valueOf(args[0]),modifier);
                    m.setItemMeta(meta);
                    player.sendMessage("Attribute added");

                }
                catch(IllegalArgumentException e){
                    player.sendMessage("invalid arguments");

                }


            }

        }
        else if (cmd.getName().equalsIgnoreCase("pack")) {
            if (recoursepack) {
                recoursepack = false;
                player.sendMessage("recourse pack has been disabled");
            } else {
                recoursepack = true;
                player.sendMessage("recourse pack has been enabled");
            }
        }

        return true;
    }
}