package com.rohan.lowattackcooldown.events;


import com.rohan.lowattackcooldown.commands.LowAttackCooldownCommands;
import com.rohan.lowattackcooldown.commands.LowAttackCooldownGUIS;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;
import org.bukkit.util.Vector;

public class LowAttackCooldownEvents implements Listener {

    public static void createBoard(Player p){
        ScoreboardManager manager= Bukkit.getScoreboardManager();
        Scoreboard b=manager.getNewScoreboard();
        Objective o=b.registerNewObjective("LowAtk","dummy", ChatColor.translateAlternateColorCodes('&',"&a&1<< &2&1Rohan @a@1>>"));
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score s=o.getScore(ChatColor.GREEN+"kills:   "+p.getStatistic(Statistic.PLAYER_KILLS));
        s.setScore(2);
        Score s2=o.getScore(ChatColor.RED+"deaths:   "+p.getStatistic(Statistic.DEATHS));
        s.setScore(1);
        p.setScoreboard(b);
    }
    @EventHandler
    public static void onPlayerRespawn(PlayerRespawnEvent event){
        Player player= event.getPlayer() ;
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 100));


    }


    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event){
        Player player= event.getPlayer() ;
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 100));
        player.sendMessage(ChatColor.GREEN+ "Why hello my fellow miners and crafters, Good times with scar here");




    }
    @EventHandler
    public static void onPlayerAttack(EntityDamageByEntityEvent event){
        if((event.getEntity() instanceof Player && event.getDamager() instanceof Player )){
            if(LowAttackCooldownCommands.hitheal){
                Player player= (Player)(event.getDamager());
                player.sendMessage(ChatColor.GREEN+ "NICE HIT BRO");
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10,1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 10,1));

            }

        }

    }
    @EventHandler
    public static void onPlayerSleep(PlayerBedEnterEvent event){
        World world= event.getPlayer().getWorld();

            if (world.getTime() >= 12000){
                world.setTime(0);
                event.getPlayer().sendMessage(event.getPlayer()+" slept, Good Night and Morning");
            }

        }
    @EventHandler
    public static void onPlayerChangeGamemode(PlayerGameModeChangeEvent event){
            event.getPlayer().sendMessage(event.getPlayer()+" went into gamemode:"+ event.getNewGameMode());
        }
    @EventHandler
    public static void onPlayerRightClick(PlayerInteractEvent event){
        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.WOODEN_HOE)){
            if(event.getAction()==Action.RIGHT_CLICK_AIR)  event.getPlayer().openInventory((new LowAttackCooldownGUIS()).getInventory());

        }
    }
    @EventHandler
    public static void onPlayerRightClickinv(InventoryClickEvent event){
        Player player=(Player) event.getWhoClicked();
        if(event.getClickedInventory() instanceof LowAttackCooldownGUIS) {
            event.setCancelled(true);
            player.getInventory().addItem(new ItemStack(Material.DIAMOND_HELMET));
            player.getInventory().addItem(new ItemStack(Material.DIAMOND_CHESTPLATE));
            player.getInventory().addItem(new ItemStack(Material.DIAMOND_LEGGINGS));
            player.getInventory().addItem(new ItemStack(Material.DIAMOND_BOOTS));
            player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
            player.getInventory().addItem(new ItemStack(Material.DIAMOND_AXE));
            player.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT, 64));

        }
        }
    @EventHandler
    public static void onPlayerRightClickFire(PlayerInteractEvent event){
        Player player=event.getPlayer();
        if(player.getInventory().getItemInMainHand().getType().equals(Material.FIRE_CHARGE)){
            if(event.getAction()== Action.RIGHT_CLICK_AIR) {
                player.launchProjectile(Fireball.class);
                player.getInventory().removeItem(new ItemStack(Material.FIRE_CHARGE));
                player.sendMessage("epic throw");
            }


        }
    }
    @EventHandler
    public static void onPlayerRightClickNamedBow(PlayerInteractEvent event){
        Player player=event.getPlayer();
        if(LowAttackCooldownCommands.guns && player.getInventory().getItemInMainHand().getType().equals(Material.CROSSBOW)){
            if(event.getAction()== Action.RIGHT_CLICK_AIR ) {
                if(player.getInventory().getItemInOffHand().equals(new ItemStack (Material.GUNPOWDER,1))){
                    Arrow bullet=player.launchProjectile(Arrow.class);
                    bullet.setKnockbackStrength(0);
                    bullet.setDamage(5.0);
                    player.sendMessage("pew pew");
                    bullet.setTicksLived(80);

                }
                else{
                    player.sendMessage("you have no ammunition");
                }

            }


        }

    }
    @EventHandler
    public static void onPlayerPickUpArrow(PlayerPickupArrowEvent event){
        Player player=event.getPlayer();
        player.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 1));
        player.getInventory().remove(Material.ARROW);
    }

    @EventHandler
    public static void onPlayerBreakBlock(BlockBreakEvent event){
        if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            event.getPlayer().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(event.getBlock().getType()));
        }
    }
    @EventHandler
    public static void onPlayerPlaceBlock(BlockPlaceEvent event){
        if(event.getBlock().getType().equals(Material.OAK_STAIRS)){


            Minecart c=event.getPlayer().getWorld().spawn(event.getBlock().getLocation().add(0.5,0,0.5), Minecart.class);
            c.setGravity(false);
            c.setInvulnerable(true);
            c.setMaxSpeed(0);
            c.setCustomName("chair");
        }
    }
    @EventHandler
    public static void onPlayerPlaceTntBlock(BlockPlaceEvent event){
        if(event.getBlock().getType().equals(Material.TNT)){
            event.setCancelled(true);
            event.getPlayer().getInventory().removeItem(new ItemStack(Material.TNT,1));
            TNTPrimed t=event.getPlayer().getWorld().spawn(event.getBlock().getLocation().add(0.5,0,0.5),TNTPrimed.class);
            t.setFuseTicks(20);
            t.setIsIncendiary(true);
            t.setGlowing(true);
        }
    }
    @EventHandler
    public static void onPlayerJoinTwo(PlayerJoinEvent event){
        createBoard(event.getPlayer());
        if(LowAttackCooldownCommands.recoursepack){
            event.getPlayer().setResourcePack("https://www.dropbox.com/sh/lv6knrji7s2e53c/AAB0ah8W0t3swu9oMlYmOFQla?dl=1");
        }
    }

}




