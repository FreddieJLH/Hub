package men.freddie.hub.queue.type;

import men.freddie.hub.queue.IQueueSystem;
import org.bukkit.entity.Player;

public class BuiltInQueueSystem implements IQueueSystem {

    @Override
    public void addToQueue(Player player, String queue) {

    }

    @Override
    public boolean inQueue(Player player) {
        return false;
    }

    @Override
    public String getQueue(Player player) {
        return null;
    }

    @Override
    public int getPosition(Player player) {
        return 0;
    }

    @Override
    public int getQueueSize(String queue) {
        return 0;
    }
}
