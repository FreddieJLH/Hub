package men.freddie.hub;

import lombok.Getter;
import men.freddie.hub.board.BoardProvider;
import men.freddie.hub.listener.ChatListener;
import men.freddie.hub.listener.ConnectionListeners;
import men.freddie.hub.listener.PreventionListeners;
import men.freddie.hub.permissions.IPermissionCore;
import men.freddie.hub.permissions.type.AquaCorePermissionCore;
import men.freddie.hub.permissions.type.StarkPermissionCore;
import men.freddie.hub.permissions.type.VaultPermissionCore;
import men.freddie.hub.permissions.type.ZootPermissionCore;
import men.freddie.hub.queue.IQueueSystem;
import men.freddie.hub.queue.type.PortalQueueSystem;
import men.freddie.hub.selector.SelectorListeners;
import men.freddie.hub.util.BungeeUtil;
import men.freddie.hub.util.assemble.Assemble;
import men.freddie.hub.util.assemble.AssembleStyle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Hub extends JavaPlugin {

    @Getter private static Hub instance;
    @Getter private IPermissionCore permissionCore;
    @Getter private IQueueSystem queueSystem;
    @Getter private BungeeUtil bungeeUtil;

    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        setupPermissions();
        setupQueueSystem();
        registerListeners();
        this.bungeeUtil = new BungeeUtil();
        setupBoards();

        Bukkit.getWorlds().forEach(world -> world.getEntities().forEach(entity -> {
            if (!(entity instanceof Player)) entity.remove();
        }));
    }

    private void registerListeners() {
        Listener[] listeners = {
                new ChatListener(),
                new SelectorListeners(),
                new ConnectionListeners(),
                new PreventionListeners(),
        };
        Arrays.asList(listeners).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void setupBoards() {
        // scoreboard
        if (getConfig().getBoolean("scoreboard.enabled")) {
            Assemble assemble = new Assemble(this, new BoardProvider());
            assemble.setTicks(2);
            assemble.setAssembleStyle(AssembleStyle.VIPER);
        }
    }

    private void setupQueueSystem() {
        switch (getConfig().getString("queue-system", "Portal").toLowerCase()) {
            case "Portal":
                this.queueSystem = new PortalQueueSystem();
                break;
            default:
                break;
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
