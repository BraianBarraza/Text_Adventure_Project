package characters;

import combat.Weapon;

import java.util.Map;

public class Player {
    private String name;
    private int hp;
    private Inventory inventory;

    public Player(String name, int hp, Inventory inventory) {
        this.name = name;
        this.hp = hp;
        this.inventory = inventory;
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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "Player: " + name + " | HP: " + hp + " | Weapon: " + weapon;
    }
}
