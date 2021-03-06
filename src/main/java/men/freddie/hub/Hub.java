package men.freddie.hub;

import lombok.Getter;
import men.freddie.hub.permissions.IPermissionCore;
import men.freddie.hub.permissions.type.AquaCorePermissionCore;
import men.freddie.hub.permissions.type.StarkPermissionCore;
import men.freddie.hub.permissions.type.VaultPermissionCore;
import men.freddie.hub.permissions.type.ZootPermissionCore;
import men.freddie.hub.tab.Tablist;
import men.freddie.hub.tab.provider.TablistAdapter;
import org.bukkit.plugin.java.JavaPlugin;

public class Hub extends JavaPlugin {

    @Getter private static Hub instance;
    @Getter private IPermissionCore permissionCore;

    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        setupPermissions();
        setupBoards();
    }

    private void setupBoards() {
        // scoreboard

        // tablist
        if (this.getConfig().getBoolean("tablist.enabled")) {
            new Tablist(Hub.getInstance(), new TablistAdapter());
        }
    }

    private void setupPermissions() {
        switch (getConfig().getString("permission-core", "Vault").toLowerCase()) {
            case "stark":
                permissionCore = new StarkPermissionCore();
                break;
            case "aquacore":
                permissionCore = new AquaCorePermissionCore();
                break;
            case "zoot":
                permissionCore = new ZootPermissionCore();
                break;
            default:
                permissionCore = new VaultPermissionCore();
                break;
        }
    }
}
