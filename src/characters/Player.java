package characters;

import combat.Item;
import combat.Item.HealingItem;
import combat.Item.Munition;
import combat.Item.KeyItem;
import combat.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name = "Larry Underwood";
    private int hp;
    private List<Weapon> inventoryWeapons;
    private List<HealingItem> inventoryHealingItems;
    private List<Munition> inventoryMunition;
    private List<KeyItem> inventoryKeys;

    public Player(String name, int hp) {
        this.hp = hp;
        this.inventoryWeapons = new ArrayList<>();
        this.inventoryHealingItems = new ArrayList<>();
        this.inventoryMunition = new ArrayList<>();
        this.inventoryKeys = new ArrayList<>();
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

    public void addWeaponToInventory(Weapon weapon) {
        inventoryWeapons.add(weapon);
    }

    public List<Weapon> getInventoryWeapons() {
        return inventoryWeapons;
    }

    public void addHealingItem(HealingItem item) {
        inventoryHealingItems.add(item);
    }

    public List<HealingItem> getInventoryHealingItems() {
        return inventoryHealingItems;
    }

    public void addMunition(Munition munition) {
        inventoryMunition.add(munition);
    }

    public List<Munition> getInventoryMunition() {
        return inventoryMunition;
    }

    public void addKeyItem(KeyItem key) {
        inventoryKeys.add(key);
    }

    public List<KeyItem> getInventoryKeys() {
        return inventoryKeys;
    }

    public void showInventory() {
        System.out.println("Weapons:");
        for (Weapon w : inventoryWeapons) {
            System.out.println(" - " + w.getName() + " (DMG: " + w.getDamage() + ")");
        }
        System.out.println("Ammunition:");
        for (Munition m : inventoryMunition) {
            System.out.println(" - " + m.getName() + " (Quantity: " + m.getQuantity() + ")");
        }
        System.out.println("Healing Items:");
        for (HealingItem h : inventoryHealingItems) {
            System.out.println(" - " + h.getName() + " (Heal: " + h.getHealingPoints() + ")");
        }
        System.out.println("Key Items:");
        for (KeyItem k : inventoryKeys) {
            System.out.println(" - " + k.getName());
        }
    }

    public boolean hasAmmo(String ammoName) {
        for (Munition m : inventoryMunition) {
            if (m.getName().equalsIgnoreCase(ammoName) && m.getQuantity() > 0) {
                return true;
            }
        }
        return false;
    }

    public void consumeAmmo(String ammoName) {
        for (int i = 0; i < inventoryMunition.size(); i++) {
            Munition m = inventoryMunition.get(i);
            if (m.getName().equalsIgnoreCase(ammoName) && m.getQuantity() > 0) {
                m.setQuantity(m.getQuantity() - 1);
                if (m.getQuantity() <= 0) {
                    inventoryMunition.remove(i);
                }
                return;
            }
        }
    }
}
