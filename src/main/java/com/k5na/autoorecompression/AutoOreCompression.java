package com.k5na.autoorecompression;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
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

    public boolean invEmpty(Player player) {
        return Arrays.asList(player.getInventory().getStorageContents()).contains(null);
    }

    public boolean removeItem(Player player, Material material, int count) {
        Map<Integer, ? extends ItemStack> ore = player.getInventory().all(material);
        int found = 0;

        for (ItemStack stack : ore.values()) {
            found += stack.getAmount();
        }

        if (count > found) {
            return false;
        }

        player.getInventory().removeItemAnySlot(new ItemStack(material, count));

        player.updateInventory();
        return true;
    }

    @Override
    public void onEnable() {
        pManager.registerEvents(this, this);
        console(ChatColor.YELLOW + "onPlayerPickupItemEvent" + ChatColor.WHITE + " is now enabled!");

        ShapelessRecipe quartz = new ShapelessRecipe(new NamespacedKey(this, NamespacedKey.MINECRAFT), new ItemStack(Material.QUARTZ, 4)).addIngredient(Material.QUARTZ_BLOCK);

        Bukkit.addRecipe(quartz);
        console(ChatColor.YELLOW + "ShapelessRecipe" + ChatColor.WHITE + " is now enabled!");

        console(ChatColor.YELLOW + getFullName() + ChatColor.WHITE + " is now enabled!");

        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventHandler
    public void onPlayerBreakBlockEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Inventory inventory = player.getInventory();

        if (player.isOp()) {
            if (invEmpty(player)) {
                if (removeItem(player, Material.COAL, 9)) {
                    inventory.addItem(new ItemStack(Material.COAL_BLOCK));
                }

                if (removeItem(player, Material.RAW_COPPER, 9)) {
                    inventory.addItem(new ItemStack(Material.RAW_COPPER_BLOCK));
                }

                if (removeItem(player, Material.COPPER_INGOT, 9)) {
                    inventory.addItem(new ItemStack(Material.COPPER_BLOCK));
                }

                if (removeItem(player, Material.RAW_IRON, 9)) {
                    inventory.addItem(new ItemStack(Material.RAW_IRON_BLOCK));
                }

                if (removeItem(player, Material.IRON_NUGGET, 9)) {
                    inventory.addItem(new ItemStack(Material.IRON_INGOT));
                }

                if (removeItem(player, Material.IRON_INGOT, 9)) {
                    inventory.addItem(new ItemStack(Material.IRON_BLOCK));
                }

                if (removeItem(player, Material.LAPIS_LAZULI, 9)) {
                    inventory.addItem(new ItemStack(Material.LAPIS_BLOCK));
                }

                if (removeItem(player, Material.RAW_GOLD, 9)) {
                    inventory.addItem(new ItemStack(Material.RAW_GOLD_BLOCK));
                }

                if (removeItem(player, Material.GOLD_NUGGET, 9)) {
                    inventory.addItem(new ItemStack(Material.GOLD_INGOT));
                }

                if (removeItem(player, Material.GOLD_INGOT, 9)) {
                    inventory.addItem(new ItemStack(Material.GOLD_BLOCK));
                }

                if (removeItem(player, Material.REDSTONE, 9)) {
                    inventory.addItem(new ItemStack(Material.REDSTONE_BLOCK));
                }

                if (removeItem(player, Material.DIAMOND, 9)) {
                    inventory.addItem(new ItemStack(Material.DIAMOND_BLOCK));
                }

                if (removeItem(player, Material.EMERALD, 9)) {
                    inventory.addItem(new ItemStack(Material.EMERALD_BLOCK));
                }

                if (removeItem(player, Material.WHEAT, 9)) {
                    inventory.addItem(new ItemStack(Material.HAY_BLOCK));
                }

                player.updateInventory();
            }
        }
    }
}

