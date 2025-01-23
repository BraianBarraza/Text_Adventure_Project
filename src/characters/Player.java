package characters;

import combat.Item;
import combat.Weapon;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int hp;
    private List<Weapon> inventoryWeapons;
    private List<Item.HealingItem> inventoryHealingItems;
    private List<Item.Munition> inventoryMunition;
    private List<Item.KeyItem> inventoryKeys;
    private Weapon currentWeapon;
    private Weapon fist;

    public Player(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.inventoryWeapons = new ArrayList<>();
        this.inventoryHealingItems = new ArrayList<>();
        this.inventoryMunition = new ArrayList<>();
        this.inventoryKeys = new ArrayList<>();
        this.fist = new Weapon("Fists", 10);
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon != null ? currentWeapon : fist;
    }

    public void setCurrentWeapon(Weapon weapon) {
        this.currentWeapon = weapon;
        System.out.println("You equipped " + weapon.getName());
    }

    public List<Weapon> getInventoryWeapons() {
        return inventoryWeapons;
    }

    public List<Item.HealingItem> getInventoryHealingItems() {
        return inventoryHealingItems;
    }

    public List<Item.Munition> getInventoryMunition() {
        return inventoryMunition;
    }

    public List<Item.KeyItem> getInventoryKeys() {
        return inventoryKeys;
    }

    public void addWeaponToInventory(Weapon weapon) {
        inventoryWeapons.add(weapon);
        System.out.println(weapon.getName() + " added to inventory");
    }

    public void addHealingItem(Item.HealingItem item) {
        inventoryHealingItems.add(item);
        System.out.println(item.getName() + " added to inventory");
    }

    public void addMunition(Item.Munition m) {
        inventoryMunition.add(m);
        System.out.println(m.getName() + " added to inventory");
    }

    public void addKeyItem(Item.KeyItem k) {
        inventoryKeys.add(k);
        System.out.println(k.getKeyName() + " key added to inventory");
    }

    public void showInventory() {
        System.out.println("Weapons:");
        for (Weapon w : inventoryWeapons) {
            System.out.println("- " + w);
        }
        System.out.println("Healing Items:");
        for (Item.HealingItem h : inventoryHealingItems) {
            System.out.println("- " + h.getName() + " (" + h.getHealingPoints() + ")");
        }
        System.out.println("Keys:");
        for (Item.KeyItem k : inventoryKeys) {
            System.out.println("- " + k.getKeyName());
        }
        System.out.println("Munition:");
        for (Item.Munition mm : inventoryMunition) {
            System.out.println("- " + mm.getName() + " x" + mm.getQuantity());
        }
    }

    public int currentDamage() {
        return getCurrentWeapon().getDamage();
    }
}
