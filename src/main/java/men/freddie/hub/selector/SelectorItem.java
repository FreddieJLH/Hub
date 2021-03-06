package men.freddie.hub.selector;

import lombok.RequiredArgsConstructor;
import men.freddie.hub.Hub;
import men.freddie.hub.util.Chat;
import men.freddie.hub.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class SelectorItem {

    private final String name;

    private FileConfiguration config = Hub.getInstance().getConfig();

    public ItemStack getItemStack() {
        ItemBuilder itemBuilder = new ItemBuilder(Material.valueOf(config.getString("servers." + name + ".display-item")));

        config.getStringList("servers." + name + ".lore").forEach(loreLine -> itemBuilder.addLoreLine(Chat.translate(loreLine)));

        itemBuilder.setName(Chat.translate(config.getString("servers." + name + ".display-name")));

        return itemBuilder.build();
    }
}
