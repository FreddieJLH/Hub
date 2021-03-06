package men.freddie.hub.queue.type;

import me.joeleoli.portal.shared.queue.Queue;
import men.freddie.hub.queue.IQueueSystem;
import org.bukkit.entity.Player;

public class PortalQueueSystem implements IQueueSystem {

    @Override
    public void addToQueue(Player player, String queue) {
        if (Queue.getByPlayer(player.getUniqueId()) != null) return;
        if (Queue.getByName(queue) == null) return;

        player.performCommand("joinqueue " + queue); // couldn't find the add method lol
    }

    @Override
    public boolean inQueue(Player player) {
        return Queue.getByPlayer(player.getUniqueId()) != null;
    }

    @Override
    public String getQueue(Player player) {
        return Queue.getByPlayer(player.getUniqueId()).getName();
    }

    @Override
    public int getPosition(Player player) {
        return Queue.getByPlayer(player.getUniqueId()).getPosition(player.getUniqueId());
    }

    @Override
    public int getQueueSize(String queue) {
        return Queue.getByName(queue).getPlayers().size();
    }
}
