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
}
