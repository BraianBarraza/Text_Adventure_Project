package combat;

public class ItemsFactory {

    //MUNITION
    public static final Item.Munition PISTOL_MUNITION_BOX = new Item.Munition("9mm Bullets box", 10, "A 9mm munition Box, Perfect for my Beretta");
    public static final Item.Munition SHOTGUN_MUNITION_BOX = new Item.Munition("12mm Shells box", 5, "A 12mm Munition Shells box perfect for a Remington");

    //HEALING
    public static final Item.HealingItem GREEN_HERB = new Item.HealingItem("Green Herb",30, "It can help you with the pain and the infection");
    public static final Item.HealingItem RED_HERB = new Item.HealingItem("Red Herb", 10, "Not as powerful as a green her but it helps");
    public static final Item.HealingItem MIX_HERB = new Item.HealingItem("Mixed Herb", 80, "Basic mixed herb, restores a good amount of health");
    public static final Item.HealingItem GR_MIX_HERB = new Item.HealingItem("Mixed Herb", 100, "Restore your full health, it can be obtain mixing a red a green herb");

    //KEYS
    public static final Item.KeyItem BILLS_KEY = new Item.KeyItem("Key to Bill's House", "Opens Bill's House");
    public static final Item.KeyItem ALLEY_KEY = new Item.KeyItem("Alleys Key", "Opens the Pub door to the Alley");
    public static final Item.KeyItem STATIONS_KEY = new Item.KeyItem("Key to Police Station", "I could maybe find something useful in the police station");
}
