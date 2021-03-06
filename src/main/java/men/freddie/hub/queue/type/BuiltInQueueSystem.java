package men.freddie.hub.queue.type;

import men.freddie.hub.Hub;
import men.freddie.hub.queue.IQueueSystem;
import org.bukkit.entity.Player;

public class BuiltInQueueSystem implements IQueueSystem {

    @Override
    public void addToQueue(Player player, String queue) {
        Hub.getInstance().getQueueManager().getByName(queue).addToQueue(player);
    }

    @Override
    public boolean inQueue(Player player) {
        return Hub.getInstance().getQueueManager().getByPlayer(player) != null;
    }

    @Override
    public String getQueue(Player player) {
        return Hub.getInstance().getQueueManager().getByPlayer(player).getServer();
    }

    @Override
    public int getPosition(Player player) {
        return Hub.getInstance().getQueueManager().getByPlayer(player).getPosition(player);
    }

    @Override
    public int getQueueSize(String queue) {
        return Hub.getInstance().getQueueManager().getByName(queue).getInQueue().size();
    }
}
