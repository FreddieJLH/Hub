package men.freddie.hub.queue.type.custom;

import lombok.SneakyThrows;
import men.freddie.hub.Hub;

public class QueueThread extends Thread {

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Thread.sleep(750);
            Hub.getInstance().getQueueManager().getQueues().forEach(Queue::sendFirst);
        }
    }
}