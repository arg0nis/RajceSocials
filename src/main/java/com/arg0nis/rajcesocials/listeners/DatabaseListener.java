package com.arg0nis.rajcesocials.listeners;

import com.arg0nis.rajcesocials.RajceSocials;
import me.arcaniax.hdb.api.DatabaseLoadEvent;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DatabaseListener implements Listener {
    RajceSocials instance = RajceSocials.getInstance();


    @EventHandler
    public void onDatabaseLoad(DatabaseLoadEvent e) {
        instance.setHeadAPI(new HeadDatabaseAPI());
        instance.setSocials();
    }
}
