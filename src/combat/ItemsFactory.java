package combat;

public class ItemsFactory {

    //MUNITION
    public static final Item.Munition PISTOL_MUNITION_BOX = new Item.Munition("9mm Bullets box", 10, "A 9mm munition Box, Perfect for my Beretta");
    public static final Item.Munition SHOTGUN_MUNITION_BOX = new Item.Munition("12mm Shells box", 5, "A 12mm Munition Shells box perfect for a Remington");

    //HEALING
    public static final Item.HealingItem GREEN_HERB = new Item.HealingItem("Green Herb",25, "It can help you with the pain and the infection");
    public static final Item.HealingItem RED_HERB = new Item.HealingItem("Red Herb", 4, "Not as powerful as a green her");
    public static final Item.HealingItem MIX_HERB = new Item.HealingItem("Mixed Herb", 100, "Restore your full healt, it can be obtain mixing a red a green herb");

    //KEYS
    public static final Item.KeyItem BILLS_KEY = new Item.KeyItem("Key to Bill's House", "Opens Bill's House");
    public static final Item.KeyItem ALLEY_KEY = new Item.KeyItem("Key to Bill's House", "Opens Bill's House");
    public static final Item.KeyItem STATIONS_KEY = new Item.KeyItem("Key to Police Station", "I could maybe find something useful in the police station");
}
