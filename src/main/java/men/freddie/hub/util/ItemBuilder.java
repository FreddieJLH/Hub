package men.freddie.hub.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setName(String name) {
        itemMeta.setDisplayName(Chat.translate(name));
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        List<String> lore;

        if (itemMeta.getLore() == null) lore = new ArrayList<>();
        else lore = itemMeta.getLore();

        lore.add(Chat.translate(line));
        return this;
    }


    public ItemStack build() {
        this.itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}