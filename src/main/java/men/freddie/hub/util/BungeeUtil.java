package men.freddie.hub.util;

import lombok.Getter;
import men.freddie.hub.Hub;
import net.minecraft.util.com.google.common.io.ByteArrayDataInput;
import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BungeeUtil implements PluginMessageListener {

    @Getter
    private Map<String, Integer> serverCountMap;

    public BungeeUtil() {
        this.serverCountMap = new HashMap<>();

        Bukkit.getScheduler().runTaskTimerAsynchronously(Hub.getInstance(), () -> {
            if (Bukkit.getOnlinePlayers().size() == 0) return;

            serverCountMap.keySet().forEach(server -> {
                ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
                dataOutput.writeUTF("PlayerCount");
                dataOutput.writeUTF(server);
                ArrayList<Player> clone = new ArrayList<>(Bukkit.getOnlinePlayers());
                clone.get(0).sendPluginMessage(Hub.getInstance(), "BungeeCord", dataOutput.toByteArray());
            });

            ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
            dataOutput.writeUTF("PlayerCount");
            dataOutput.writeUTF("ALL");
            ArrayList<Player> clone = new ArrayList<>(Bukkit.getOnlinePlayers());
            clone.get(0).sendPluginMessage(Hub.getInstance(), "BungeeCord", dataOutput.toByteArray());
        }, 10L, 20L);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) return;
        ByteArrayDataInput dataInput = ByteStreams.newDataInput(message);
        String subChannel = dataInput.readUTF();

        if (subChannel.equals("PlayerCount")) {
            String server = dataInput.readUTF();
            int count = dataInput.readInt();

            if (server == null) return;
            if (serverCountMap.containsKey(server)) serverCountMap.replace(server, count);
            else serverCountMap.put(server, count);
         }
    }

    public void sendToServer(Player player, String server) {
        ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
        dataOutput.writeUTF("Connect");
        dataOutput.writeUTF(server);
        player.sendPluginMessage(Hub.getInstance(), "BungeeCord", dataOutput.toByteArray());
    }
}
