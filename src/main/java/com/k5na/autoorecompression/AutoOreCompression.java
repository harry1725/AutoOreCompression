package com.k5na.autoorecompression;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class AutoOreCompression extends JavaPlugin implements Listener {

    PluginDescriptionFile pdfFile = this.getDescription();
    PluginManager pManager = Bukkit.getPluginManager();

    String pfName = pdfFile.getName() + " v" + pdfFile.getVersion();

    public PluginDescriptionFile getPdfFile() {
        return pdfFile;
    }

    public String getFullName() {
        return pfName;
    }

    public static void console(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

    public boolean checkItem(Player player, Material material, int count) {
        Map<Integer, ? extends ItemStack> items = player.getInventory().all(material);
        int found = 0;

        for (ItemStack stack : items.values()) {
            found += stack.getAmount();
            console("[DEBUG] " + stack + " found in checking!");
        }

        if (count > found) {
            console("[DEBUG] found less than count");
            return false;
        } else {
            console("[DEBUG] found more than count");
            return true;
        }
    }

    public boolean removeItem(Player player, Material material, int count) {
        Map<Integer, ? extends ItemStack> ore = player.getInventory().all(material);
        int found = 0;

        for (ItemStack stack : ore.values()) {
            found += stack.getAmount();
            console("[DEBUG] " + stack + " found in removing!");
        }

        if (count > found) {
            console("[DEBUG] found less than count");
            return false;
        }

        for (Integer index : ore.keySet()) {
            ItemStack stack = ore.get(index);

            int removed = Math.min(count, stack.getAmount());
            count -= removed;

            if (stack.getAmount() == removed) {
                player.getInventory().setItem(index, null);
            }

            if (count <= 0) {
                break;
            }
        }

        player.updateInventory();
        console("[DEBUG] Inventory Updated");
        return true;
    }

    @Override
    public void onEnable() {
        pManager.registerEvents(this, this);
        console(ChatColor.YELLOW + "onPlayerPickupItemEvent" + ChatColor.WHITE + " is now enabled!");

        ShapelessRecipe quartz = new ShapelessRecipe(new NamespacedKey(this, NamespacedKey.MINECRAFT), new ItemStack(Material.QUARTZ, 4)).addIngredient(Material.QUARTZ_BLOCK);
        console(ChatColor.YELLOW + "ShapelessRecipe" + ChatColor.WHITE + " is now enabled!");

        Bukkit.addRecipe(quartz);

        console(ChatColor.YELLOW + getFullName() + ChatColor.WHITE + " is now enabled!");

        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventHandler
    public void onPlayerPickupItemEvent(EntityPickupItemEvent event) {
        Entity entity = event.getEntity();

        if (entity.getType() == EntityType.PLAYER) {
            Player player = (Player) entity;
            Inventory inventory = player.getInventory();

            if (player.isOp()) {
                if (inventory.contains(Material.AIR)) {
                    if (checkItem(player, Material.COAL, 9)) {
                        removeItem(player, Material.COAL, 9);
                        inventory.addItem(new ItemStack(Material.COAL_BLOCK));
                    } else if (checkItem(player, Material.RAW_COPPER, 9)) {
                        removeItem(player, Material.RAW_COPPER, 9);
                        inventory.addItem(new ItemStack(Material.RAW_COPPER_BLOCK));
                    } else if (checkItem(player, Material.COPPER_INGOT, 9)) {
                        removeItem(player, Material.COPPER_INGOT, 9);
                        inventory.addItem(new ItemStack(Material.COPPER_BLOCK));
                    } else if (checkItem(player, Material.RAW_IRON, 9)) {
                        removeItem(player, Material.RAW_IRON, 9);
                        inventory.addItem(new ItemStack(Material.RAW_IRON_BLOCK));
                    } else if (checkItem(player, Material.IRON_NUGGET, 9)) {
                        removeItem(player, Material.IRON_NUGGET, 9);
                        inventory.addItem(new ItemStack(Material.IRON_INGOT));
                    } else if (checkItem(player, Material.IRON_INGOT, 9)) {
                        removeItem(player, Material.IRON_INGOT, 9);
                        inventory.addItem(new ItemStack(Material.IRON_BLOCK));
                    } else if (checkItem(player, Material.LAPIS_LAZULI, 9)) {
                        removeItem(player, Material.LAPIS_LAZULI, 9);
                        inventory.addItem(new ItemStack(Material.LAPIS_BLOCK));
                    } else if (checkItem(player, Material.RAW_GOLD, 9)) {
                        removeItem(player, Material.RAW_GOLD, 9);
                        inventory.addItem(new ItemStack(Material.RAW_GOLD_BLOCK));
                    } else if (checkItem(player, Material.GOLD_NUGGET, 9)) {
                        removeItem(player, Material.GOLD_NUGGET, 9);
                        inventory.addItem(new ItemStack(Material.GOLD_INGOT));
                    } else if (checkItem(player, Material.GOLD_INGOT, 9)) {
                        removeItem(player, Material.GOLD_INGOT, 9);
                        inventory.addItem(new ItemStack(Material.GOLD_BLOCK));
                    } else if (checkItem(player, Material.REDSTONE, 9)) {
                        removeItem(player, Material.REDSTONE, 9);
                        inventory.addItem(new ItemStack(Material.REDSTONE_BLOCK));
                    } else if (checkItem(player, Material.DIAMOND, 9)) {
                        removeItem(player, Material.DIAMOND, 9);
                        inventory.addItem(new ItemStack(Material.DIAMOND_BLOCK));
                    } else if (checkItem(player, Material.EMERALD, 9)) {
                        removeItem(player, Material.EMERALD, 9);
                        inventory.addItem(new ItemStack(Material.EMERALD_BLOCK));
                    }

                    player.updateInventory();
                }
            }
        }
    }
}

