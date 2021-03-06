package men.freddie.hub.listener;

import men.freddie.hub.Hub;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setFormat(ChatColor.translateAlternateColorCodes('&', Hub.getInstance().getConfig().getString("message.chat-format")
            .replaceAll("%prefix%", Hub.getInstance().getPermissionCore().getRankPrefix(event.getPlayer())
            .replaceAll("%player%", event.getPlayer().getName())
            .replaceAll("%message%", event.getMessage()))));
    }
}
