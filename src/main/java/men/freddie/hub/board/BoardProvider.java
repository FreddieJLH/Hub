package men.freddie.hub.board;

import men.freddie.hub.Hub;
import men.freddie.hub.util.Chat;
import men.freddie.hub.util.assemble.AssembleAdapter;
import org.bukkit.entity.Player;

import java.util.List;

public class BoardProvider implements AssembleAdapter {

    @Override
    public String getTitle(Player player) {
        return Chat.translate(Hub.getInstance().getConfig().getString("scoreboard.title"));
    }

    @Override
    public List<String> getLines(Player player) {
        return null;
    }
}