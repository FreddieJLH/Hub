package men.freddie.hub.board;

import men.freddie.hub.Hub;
import men.freddie.hub.util.Chat;
import men.freddie.hub.util.assemble.AssembleAdapter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BoardProvider implements AssembleAdapter {

    @Override
    public String getTitle(Player player) {
        return Chat.translate(Hub.getInstance().getConfig().getString("scoreboard.title"));
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> lines = new ArrayList<>();

        lines.add(Chat.translate(Hub.getInstance().getConfig().getString("scoreboard.bar")));

        Hub.getInstance().getConfig().getStringList("scoreboard.lines").forEach(line ->
                lines.add(Chat.translate(line
                        .replaceAll("%onlineCount%", String.valueOf(Hub.getInstance().getBungeeUtil().getServerCountMap().get("ALL")))
                        .replaceAll("%coloredRank%", Hub.getInstance().getPermissionCore().getColoredRank(player))
                        .replaceAll("%rank%", Hub.getInstance().getPermissionCore().getRank(player)))));

        if (Hub.getInstance().getQueueSystem().inQueue(player)) {
            Hub.getInstance().getConfig().getStringList("scoreboard.queue-lines").forEach(line ->
                    lines.add(line
                            .replaceAll("%server%", Hub.getInstance().getQueueSystem().getQueue(player))
                            .replaceAll("%position%", String.valueOf(Hub.getInstance().getQueueSystem().getPosition(player)))
                            .replaceAll("%queueSize%", String.valueOf(Hub.getInstance().getQueueSystem().getQueueSize(Hub.getInstance().getQueueSystem().getQueue(player))))));
        }

        if (Hub.getInstance().getConfig().getBoolean("scoreboard.footer.enabled")) {
            lines.add("");
            lines.add(Chat.translate(Hub.getInstance().getConfig().getString("scoreboard.footer.link")));
        }

        lines.add(Chat.translate(Hub.getInstance().getConfig().getString("scoreboard.bar")));

        return lines;
    }
}