package men.freddie.hub;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import men.freddie.hub.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public enum HubItems {

    SELECTOR(new ItemBuilder(Material.COMPASS).setName("&4Server Selector").build(), 4);

    @Getter
    private final ItemStack itemStack;
    @Getter
    private final int slot;
}
