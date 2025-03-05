package combat;

public class ItemsFactory {

    //MUNITION Items Factory
    public static Item.Munition createPistolMunition() {
        return new Item.Munition("9mm Bullets box", 10, "A 9mm munition Box, Perfect for my Beretta");
    }

    public static Item.Munition createShotgunMunition() {
        return new Item.Munition("12mm Shells box", 5, "A 12mm Munition Shells box perfect for a Remington");
    }

    //HEALING Items Factory
    public static Item.HealingItem createGreenHerb() {
        return new Item.HealingItem("Green Herb", 25, "A Green Herb that can help me with the pain and the infection");
    }

    public static Item.HealingItem createRedHerb() {
        return new Item.HealingItem("Red Herb", 25, "A Red Herb that can help me with the pain and the infection");

    }

    public static Item.HealingItem createNormalMixedHerb() {
        return new Item.HealingItem("Normal Mixed Herb", 75, "A combination of both herbs, the effect is stronger");
    }

    public static Item.HealingItem createSuperMixedHerb() {
        return new Item.HealingItem("Super Mixed Herb", 100, "The perfect herbs combination, it is even stronger a combine G+G or R+R");
    }

    //KEYS
    public static final Item.KeyItem BILLS_KEY = new Item.KeyItem("Key to Bill's House", "Opens Bill's House");
    public static final Item.KeyItem ALLEY_KEY = new Item.KeyItem("Alleys Key", "Opens the Pub door to the Alley");
    public static final Item.KeyItem STATIONS_KEY = new Item.KeyItem("Key to Police Station", "I could maybe find something useful in the police station");
}
