package men.freddie.hub.queue;

import org.bukkit.entity.Player;

public interface IQueueSystem {

    void addToQueue(Player player, String queue);
    boolean inQueue(Player player);
    String getQueue(Player player);
    int getPosition(Player player);
    int getQueueSize(String queue);
}
