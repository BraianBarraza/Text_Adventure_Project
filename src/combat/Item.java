package combat;

public class Item {
    public static class HealingItem extends Item {
        private int healingPoints;
        private String name;

        public HealingItem(String name, int healingPoints, String description) {
            super(description);
            this.name = name;
            this.healingPoints = healingPoints;
        }
        public int getHealingPoints() {
            return healingPoints;
        }
        public String getName() {
            return name;
        }
    }

    public static class Munition extends Item {
        private String name;
        private int quantity;

        public Munition(String name, int quantity, String description) {
            super(description);
            this.name = name;
            this.quantity = quantity;
        }
        public String getName() {
            return name;
        }
        public int getQuantity() {
            return quantity;
        }
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    public static class KeyItem extends Item {
        private String name;

        public KeyItem(String name, String description) {
            super(description);
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    private String description;

    public Item(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
