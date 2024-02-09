package com.arg0nis.rajcesocials.listeners;

import com.arg0nis.rajcesocials.RajceSocials;
import com.arg0nis.rajcesocials.Social;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class InventoryListener implements Listener {
    RajceSocials instance = RajceSocials.getInstance();
    FileConfiguration config = instance.getConfig();

    @EventHandler
    public void onPlayerInventory(InventoryClickEvent e) {

        if(e.getView().getTitle().equalsIgnoreCase(RajceSocials.translate(config.getString("gui-name")))) {
            e.setCancelled(true);
            ItemStack item = e.getCurrentItem();

            if(item == null)
                return;

            Player p = (Player) e.getWhoClicked();

            ArrayList<Social> socials = instance.getSocials();

            for (Social social : socials) {
                String name = RajceSocials.translate(social.getName());
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
                    p.sendMessage("\n" + RajceSocials.translate(social.getName() + " &r&llink: ") + ChatColor.RESET + social.getLink() + "\n");
                    p.closeInventory();
                    return;
                }
            }
        }
    }
}
