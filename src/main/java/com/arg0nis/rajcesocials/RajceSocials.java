package com.arg0nis.rajcesocials;

import com.arg0nis.rajcesocials.commands.Socials;
import com.arg0nis.rajcesocials.listeners.DatabaseListener;
import com.arg0nis.rajcesocials.listeners.InventoryListener;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class RajceSocials extends JavaPlugin {
    private static RajceSocials instance;
    private HeadDatabaseAPI headAPI;
    private final ArrayList<Social> socials = new ArrayList<>(4);

    @Override
    public void onEnable() {
        loadPlugin();
        if(headAPI != null)
            setSocials();
    }
    @Override
    public void onDisable() {

    }

    public static RajceSocials getInstance() {
       return instance;
    }

    public void setSocials() {
        socials.add(new Social(headAPI, "tiktok"));
        socials.add(new Social(headAPI, "store"));
        socials.add(new Social(headAPI, "youtube"));
        socials.add(new Social(headAPI,"instagram"));
    }

    private void loadPlugin() {
        instance = this;
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new DatabaseListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);

        getLogger().info("Plugin " + this.getName() + " was loaded successfully!");

        getCommand("socials").setExecutor(new Socials());
    }

    public ArrayList<Social> getSocials() {
        return socials;
    }

    public void setHeadAPI(HeadDatabaseAPI headAPI) {
        this.headAPI = headAPI;
    }

    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
