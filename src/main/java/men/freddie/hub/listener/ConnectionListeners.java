package men.freddie.hub.listener;

import men.freddie.hub.HubItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;

public class ConnectionListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setExp(0);
        player.setLevel(0);
        player.setFoodLevel(20);
        player.setHealth(20.0);

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        Arrays.asList(HubItems.values()).forEach(hubItem -> player.getInventory().setItem(hubItem.getSlot(), hubItem.getItemStack()));
        player.updateInventory();
    }
}