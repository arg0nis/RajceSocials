package com.arg0nis.rajcesocials.commands;

import com.arg0nis.rajcesocials.RajceSocials;
import com.arg0nis.rajcesocials.Social;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Socials implements CommandExecutor {
    RajceSocials instance = RajceSocials.getInstance();
    ArrayList<Social> socials = instance.getSocials();
    FileConfiguration config = instance.getConfig();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command!");
            return false;
        }

        Player p = (Player) commandSender;
        String guiName = config.getString("gui-name");

        Inventory inv = createSocialsMenu(p, RajceSocials.translate(guiName));
        p.openInventory(inv);

        return true;
    }

    public Inventory createSocialsMenu(Player p, String name) {
       Inventory inventory = Bukkit.createInventory(p, 5*9, name);

       // Set heads for socials
        int index = 0;
        for(int i = 19; i <= 25; i+=2 ) {
            ItemStack head = socials.get(index).getHead();
            inventory.setItem(i, head);
            index++;
        }

       ItemStack redBorder = new ItemStack(Material.RED_STAINED_GLASS_PANE);
       ItemStack brownBorder = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
       ItemMeta borderMeta = redBorder.getItemMeta();

       // Set this so ItemStack has no name
       borderMeta.setDisplayName(ChatColor.GOLD+"");
       redBorder.setItemMeta(borderMeta);
       brownBorder.setItemMeta(borderMeta);

       // Set row borders
       for(int i = 0; i < 9; i++) {
           inventory.setItem(i, redBorder);
       }
        for(int i = 36; i < 45; i++) {
            inventory.setItem(i, redBorder);
        }

       return inventory;
    }
}
