package com.arg0nis.rajcesocials;

import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Social {

    private final String link;
    private final String name;
    private ItemStack head;

    public Social(HeadDatabaseAPI api, String configSection) {

        RajceSocials instance = RajceSocials.getInstance();
        FileConfiguration config = instance.getConfig();

        link = config.getConfigurationSection(configSection).getString("link");
        String headID = config.getConfigurationSection(configSection).getString("headID");
        name = config.getConfigurationSection(configSection).getString("name");

        try {
            head = api.getItemHead(headID);
        } catch (NullPointerException nullPointerException) {
            instance.getLogger().info("Could not find the head you were looking for");
            head = api.getItemHead("9248");
        }

        ItemMeta headMeta = head.getItemMeta();
        headMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        head.setItemMeta(headMeta);
    }

    public ItemStack getHead() {
        return head;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }
}
