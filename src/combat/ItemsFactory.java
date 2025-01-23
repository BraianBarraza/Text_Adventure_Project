package combat;

public class ItemsFactory {

    Item.Munition pistolMunitionBox = new Item.Munition("9mm", "pistol munition", 10);
    Item.Munition shotgunMunitionBox = new Item.Munition("shotgun shells", "Shotgun Munition, really powerful", 5);

    Item.HealingItem greenHerb = new Item.HealingItem("It can help you with the pain and the infection",25, "Green Herb");
    Item.HealingItem redHerb = new Item.HealingItem("Not as powerful as a green herb", 4, "Red Herb");
    Item.HealingItem mixHerb = new Item.HealingItem("Restore your full healt, it can be obtain mixing a red a green herb", 100, "Mixed Herb");
}
