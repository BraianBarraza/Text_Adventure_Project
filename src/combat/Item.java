package combat;

public class Item {
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static class HealingItem extends Item {
        private int healingPoints;

        public HealingItem(String name, int healingPoints, String description) {
            super(name, description);
            this.healingPoints = healingPoints;
        }

        public int getHealingPoints() {
            return healingPoints;
        }
    }

    public static class Munition extends Item {
        private int quantity;

        public Munition(String name, int quantity, String description) {
            super(name, description);
            this.quantity = quantity;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    public static class KeyItem extends Item {
        public KeyItem(String name, String description) {
            super(name, description);
        }
    }
}
