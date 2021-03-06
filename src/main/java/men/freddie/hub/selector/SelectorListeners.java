package men.freddie.hub.selector;

import men.freddie.hub.Hub;
import men.freddie.hub.util.Chat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SelectorListeners implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            SelectorInventory selectorInventory = new SelectorInventory();
            player.openInventory(selectorInventory.build());
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getInventory().getTitle().equals(Chat.translate(Hub.getInstance().getConfig().getString("selector.title")))) return;

        event.setCancelled(true);
        if (event.getCurrentItem() == null) return;
        if (!event.getCurrentItem().hasItemMeta()) return;
        if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;

        Hub.getInstance().getQueueSystem().addToQueue((Player) event.getWhoClicked(), ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
    }
}
