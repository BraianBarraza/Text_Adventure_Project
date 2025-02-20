package combat;

public class Weapon extends Item {
    private String name;
    private int damage;
    private String description;

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
