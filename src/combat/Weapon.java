package combat;

public class Weapon extends Item {
    private final String name;
    private final int damage;
    private final String description;

    public Weapon(String name, int damage, String description) {
        super("Weapon", "Description");
        this.name = name;
        this.damage = damage;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public String getDescription() {
        return description;
    }
}
