package men.freddie.hub.permissions.type;

import men.freddie.hub.permissions.IPermissionCore;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultPermissionCore implements IPermissionCore {

    private Permission perms;
    private Chat chat;

    public VaultPermissionCore() {
        this.perms = null;
        this.chat = null;
        setupPermissions();
        setupChat();
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = Bukkit.getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = Bukkit.getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    @Override
    public String getColoredRank(Player player) {
        return perms.getPrimaryGroup(player);
    }

    @Override
    public String getRankPrefix(Player player) {
        return chat.getGroupPrefix(player.getWorld(), getRank(player));
    }

    @Override
    public String getRank(Player player) {
        return perms.getPrimaryGroup(player);
    }
}
