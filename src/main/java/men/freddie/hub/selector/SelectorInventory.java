package men.freddie.hub.selector;

import men.freddie.hub.Hub;
import men.freddie.hub.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;

public class SelectorInventory {

    private Inventory inventory;
    private FileConfiguration config = Hub.getInstance().getConfig();

    public SelectorInventory() {
        this.inventory = Bukkit.createInventory(null, config.getInt("selector.size"), Chat.translate(config.getString("selector.title")));

        config.getConfigurationSection("servers").getKeys(false).forEach(string ->
                inventory.setItem(config.getInt("servers." + string + ".slot"), new SelectorItem(string).getItemStack()));
    }

    public Inventory build() {
        return this.inventory;
    }
}
