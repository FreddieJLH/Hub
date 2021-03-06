package men.freddie.hub.queue.type.custom;

import lombok.Getter;
import men.freddie.hub.Hub;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QueueManager {

    @Getter private List<Queue> queues;
    @Getter private QueueThread queueThread;

    public QueueManager() {
        this.queues = new ArrayList<>();
        (this.queueThread = new QueueThread()).start();

        Hub.getInstance().getConfig().getConfigurationSection("servers").getKeys(false).forEach(queue -> queues.add(new Queue(queue)));
    }

    public Queue getByName(String name) {
        for (Queue queue : queues) {
            if (queue.getServer().equalsIgnoreCase(name))
                return queue;
        }
        return null;
    }

    public Queue getByPlayer(Player player) {
        for (Queue queue : queues) {
            if (queue.getInQueue().contains(player))
                return queue;
        }
        return null;
    }
}