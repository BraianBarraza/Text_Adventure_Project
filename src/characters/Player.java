package characters;

import combat.Item;
import combat.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int hp;
    private List<Weapon> inventoryWeapons;
    private List<Item.Munition> inventoryMunition;
    private List<Item.HealingItem> inventoryHealingItems;
    private Weapon currentWeapon;



    public Player(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.inventoryWeapons = new ArrayList<>();
        this.inventoryMunition = new ArrayList<>();
        this.inventoryHealingItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public List<Weapon> getInventoryWeapons() {
        return inventoryWeapons;
    }

    public List<Item.Munition> getInventoryMunition() {
        return inventoryMunition;
    }

    public List<Item.HealingItem> getInventoryHealingItems() {
        return inventoryHealingItems;
    }

    public Weapon getCurrentWeapon(){
        return currentWeapon != null ? currentWeapon : fist;
    }

    public void setCurrentWeapon(Weapon weapon){
        this.currentWeapon = weapon;
        System.out.println("You equiped: " + weapon.getName());
    }

    public void addWeaponToInventory(Weapon weapon){
        inventoryWeapons.add(weapon);
        System.out.println(weapon.getName() + " added to Inventory. Avarage damage: " + weapon.getDamage());
    }

    public void addInventoryMunition(Item.Munition item){
        inventoryMunition.add(item);
        System.out.println("The " + item.getQuantity() + " " + item.getName() + " Were added to your inventory");
    }

    public void addHealingItemToInventory(Item.HealingItem item) {
        inventoryHealingItems.add(item);
        System.out.println("The " + item.getName() + "  with: " + item.getHealingPoints() + " Healing points was added to the inventory");
    }

    public void showInventory(){
        System.out.println("Weapons: ");
        for (Weapon weapon : inventoryWeapons){
            System.out.println("- " + weapon);
        }

        System.out.println("Munition: ");
        for (Item.Munition munition : inventoryMunition){
            System.out.println("- " + munition);
        }

        System.out.println("Healing Items: ");
        for (Item.HealingItem healingItem : inventoryHealingItems){
            System.out.println("- " + healingItem);
        }
    }

    Weapon fist = new Weapon("Fists", 15);
    public int currentDamage(){
        return getCurrentWeapon().getDamage();
    }

}
