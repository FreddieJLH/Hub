package men.freddie.hub.cosmetic;

public interface ICosmetic {

    String name();
    CosmeticType cosmeticType();
    void onApply();
    void tick();
}