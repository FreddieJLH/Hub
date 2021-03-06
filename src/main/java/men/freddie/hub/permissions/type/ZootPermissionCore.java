package men.freddie.hub.permissions.type;

import com.minexd.zoot.ZootAPI;
import men.freddie.hub.permissions.IPermissionCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ZootPermissionCore implements IPermissionCore {

    @Override
    public String getColoredRank(Player player) {
        return ZootAPI.getRankOfPlayer(player).getDisplayName();
    }

    @Override
    public String getRankPrefix(Player player) {
        return ZootAPI.getRankOfPlayer(player).getPrefix();
    }

    @Override
    public String getRank(Player player) {
        return ChatColor.stripColor(ZootAPI.getRankOfPlayer(player).getDisplayName());
    }
}
