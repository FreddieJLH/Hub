package men.freddie.hub.queue.type.custom;

import lombok.Data;
import men.freddie.hub.Hub;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Queue {

    private String server;
    private List<Player> inQueue;
    private boolean paused;

    public Queue(String server) {
        this.server = server;
        this.inQueue = new ArrayList<>();
        this.paused = false;
    }

    public void addToQueue(Player player) {
        this.inQueue.forEach(listPlayer -> {
            if (player != listPlayer) {
                if (Hub.getInstance().getPermissionCore().getWeight(player) > Hub.getInstance().getPermissionCore().getWeight(listPlayer))
                    Collections.swap(inQueue, inQueue.indexOf(listPlayer), inQueue.size() - 1);
            }
        });

        player.sendMessage(ChatColor.GREEN + "You have joined the " + server + " queue.");
    }

    public void removeFromQueue(Player player) {
        if (this.inQueue.contains(player)) this.inQueue.remove(player);
        player.sendMessage(ChatColor.RED + "You have left the queue for " + server + ".");
    }

    public void sendFirst() {
        if (inQueue.get(0).isOnline()) {
            inQueue.remove(0);
            Hub.getInstance().getBungeeUtil().sendToServer(inQueue.get(0), server);
        } else inQueue.remove(0);
    }

    public int getPosition(Player player) {
        return inQueue.indexOf(player);
    }
}