package men.freddie.hub.permissions.type;

import me.activated.core.plugin.AquaCoreAPI;
import men.freddie.hub.permissions.IPermissionCore;
import org.bukkit.entity.Player;

public class AquaCorePermissionCore implements IPermissionCore {

    @Override
    public String getColoredRank(Player player) {
        return AquaCoreAPI.INSTANCE.getPlayerRank(player.getUniqueId()).getDisplayName();
    }

    @Override
    public String getRankPrefix(Player player) {
        return AquaCoreAPI.INSTANCE.getPlayerRank(player.getUniqueId()).getPrefix();
    }

    @Override
    public String getRank(Player player) {
        return AquaCoreAPI.INSTANCE.getPlayerRank(player.getUniqueId()).getName();
    }
}
