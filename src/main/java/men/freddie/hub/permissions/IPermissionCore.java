package men.freddie.hub.permissions;

import org.bukkit.entity.Player;

public interface IPermissionCore {

    String getColoredRank(Player player);
    String getRankPrefix(Player player);
    String getRank(Player player);
}
