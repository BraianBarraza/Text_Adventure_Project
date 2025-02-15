package combat;

public class ItemsFactory {

    public static final Item.Munition PISTOLMUNITIONBOX = new Item.Munition("9mm", 20, "A 9mm munition Box, Perfect for my Beretta");
    public static final Item.Munition SHOTGUNMUNITIONBOX = new Item.Munition("12mm", 10, "A 12mm Munition Shells box perfect for a Remington");

    public static final Item.HealingItem GREENHERB = new Item.HealingItem("Green Herb",25, "It can help you with the pain and the infection");
    public static final Item.HealingItem REDHERB = new Item.HealingItem("Red Herbb", 4, "Not as powerful as a green her");
    public static final Item.HealingItem MIXHERB = new Item.HealingItem("Mixed Herb", 100, "Restore your full healt, it can be obtain mixing a red a green herb");
}
