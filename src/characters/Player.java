package characters;

import combat.Item;
import combat.ItemsFactory;
import combat.Weapon;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the player's character, including inventory and health management.
 */
public class Player {
    private String name;
    private int hp;
    private List<Weapon> inventoryWeapons;
    private List<Item.HealingItem> inventoryHealingItems;
    private List<Item.Munition> inventoryMunition;
    private List<Item.KeyItem> inventoryKeys;

    /**
     * Creates the Character that the player will control.
     * @param name the character's name
     * @param hp initial health points
     */
    public Player(String name, int hp) {
        this.name = name;
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

    /**
     * Adds a Weapon to the player's weapon inventory.
     * @param weapon the Weapon to add
     */
    public void addWeaponToInventory(Weapon weapon) {
        inventoryWeapons.add(weapon);
    }

    /**
     * @return the list of Weapons in the inventory
     */
    public List<Weapon> getInventoryWeapons() {
        return inventoryWeapons;
    }

    /**
     * Adds a HealingItem to the player's healing item inventory.
     * @param item the HealingItem to add
     */
    public void addHealingItem(Item.HealingItem item) {
        inventoryHealingItems.add(item);
    }

    /**
     * @return the list of HealingItems in the inventory
     */
    public List<Item.HealingItem> getInventoryHealingItems() {
        return inventoryHealingItems;
    }

    /**
     * Adds a Munition item to the player's ammunition inventory.
     * @param munition the Munition item to add
     */
    public void addMunition(Item.Munition munition) {
        inventoryMunition.add(munition);
    }

    /**
     * @return the list of Munition items in the inventory
     */
    public List<Item.Munition> getInventoryMunition() {
        return inventoryMunition;
    }

    /**
     * Adds a KeyItem to the player's key inventory.
     * @param key the KeyItem to add
     */
    public void addKeyItem(Item.KeyItem key) {
        inventoryKeys.add(key);
    }

    /**
     * @return the list of KeyItems in the inventory
     */
    public List<Item.KeyItem> getInventoryKeys() {
        return inventoryKeys;
    }

    public void showInventory() {
        System.out.println("Weapons:");
        for (Weapon w : inventoryWeapons) {
            System.out.println(" - " + w.getName() + " (DMG: " + w.getDamage() + ")");
        }
        System.out.println("Ammunition:");
        for (Item.Munition m : inventoryMunition) {
            System.out.println(" - " + m.getName() + " (Quantity: " + m.getQuantity() + ")");
        }
        System.out.println("Healing Items:");
        for (Item.HealingItem h : inventoryHealingItems) {
            System.out.println(" - " + h.getName() + " (Heal: " + h.getHealingPoints() + ")");
        }
        System.out.println("Key Items:");
        for (Item.KeyItem k : inventoryKeys) {
            System.out.println(" - " + k.getName());
        }
    }

    /**
     * Checks if the player has munition available.
     * @param ammoName the name of the ammunition item
     * @return true if the ammunition exits and has > 0, if not, false
     */
    public boolean hasAmmo(String ammoName) {
        for (Item.Munition m : inventoryMunition) {
            if (m.getName().equalsIgnoreCase(ammoName) && m.getQuantity() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * remove one unit of the ammunition that the player had use if exits.
     * @param ammoName the name of the ammunition item
     */
    public void consumeAmmo(String ammoName) {
        for (int i = 0; i < inventoryMunition.size(); i++) {
            Item.Munition m = inventoryMunition.get(i);
            if (m.getName().equalsIgnoreCase(ammoName) && m.getQuantity() > 0) {
                m.setQuantity(m.getQuantity() - 1);
                if (m.getQuantity() <= 0) {
                    inventoryMunition.remove(i);
                }
                return;
            }
        }
    }

    /**
     * Picks up an Item and add it to the inventory category (Weapon, HealingItem, Munition, KeyItem).
     * @param item the Item to pick up
     */
    public void pickUpItem(Item item) {
        if (item instanceof Item.HealingItem) {
            Item.HealingItem healingItem = (Item.HealingItem) item;
            addHealingItem(healingItem);
        } else if (item instanceof Item.Munition) {
            Item.Munition ammo = (Item.Munition) item;
            addMunition(ammo);
        } else if (item instanceof Item.KeyItem) {
            Item.KeyItem key = (Item.KeyItem) item;
            addKeyItem(key);
        } else if (item instanceof Weapon) {
            Weapon w = (Weapon) item;
            addWeaponToInventory(w);
        }
    }

    /**
     * Attempts to combine herbs in the player's inventory to create a new healing items.
     */
    public void combineHerbs() {
        int greenCount = 0;
        int redCount = 0;
        for (Item.HealingItem h : inventoryHealingItems) {
            if (h.getName().equalsIgnoreCase("Green Herb")) {
                greenCount++;
            } else if (h.getName().equalsIgnoreCase("Red Herb")) {
                redCount++;
            }
        }
        if (greenCount >= 1 && redCount >= 1) {
            removeHerb("Green Herb");
            removeHerb("Red Herb");
            addHealingItem(ItemsFactory.createSuperMixedHerb());
            System.out.println("You create a: " + ItemsFactory.createSuperMixedHerb().getName()
                    + " HP: " + ItemsFactory.createSuperMixedHerb().getHealingPoints());
        } else if (redCount >= 2) {
            removeHerb("Red Herb");
            removeHerb("Red Herb");
            addHealingItem(ItemsFactory.createNormalMixedHerb());
            System.out.println("You create a :" + ItemsFactory.createNormalMixedHerb().getName()
                    + " HP: " + ItemsFactory.createNormalMixedHerb().getHealingPoints());
        } else if (greenCount >= 2) {
            removeHerb("Green Herb");
            removeHerb("Green Herb");
            addHealingItem(ItemsFactory.createNormalMixedHerb());
            System.out.println("You create a :" + ItemsFactory.createNormalMixedHerb().getName()
                    + " HP: " + ItemsFactory.createNormalMixedHerb().getHealingPoints());
        } else {
            System.out.println("Not enough herbs to combine");
        }
    }

    /**
     * Removes one herb of the specified name from the player's inventory, if present.
     * @param herbName the name of the herb to remove
     */
    private void removeHerb(String herbName) {
        for (int i = 0; i < inventoryHealingItems.size(); i++) {
            if (inventoryHealingItems.get(i).getName().equalsIgnoreCase(herbName)) {
                inventoryHealingItems.remove(i);
                return;
            }
        }
    }
}
