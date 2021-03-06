package men.freddie.hub.permissions.type;

import men.freddie.hub.permissions.IPermissionCore;
import net.evilblock.stark.Stark;
import net.evilblock.stark.profile.BukkitProfile;
import org.bukkit.entity.Player;

public class StarkPermissionCore implements IPermissionCore {

    @Override
    public String getColoredRank(Player player) {
        return getProfile(player).getDisplayName();
    }

    @Override
    public String getRankPrefix(Player player) {
        return getProfile(player).getRank().getPrefix();
    }

    @Override
    public String getRank(Player player) {
        return getProfile(player).getRank().getId();
    }

    @Override
    public int getWeight(Player player) {
        return getProfile(player).getRank().getDisplayOrder();
    }

    public BukkitProfile getProfile(Player player) {
        return Stark.getInstance().getCore().getProfileHandler().getByUUID(player.getUniqueId());
    }
}
