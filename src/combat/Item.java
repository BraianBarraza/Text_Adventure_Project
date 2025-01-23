package combat;

public class Item {

    public static class HealingItem extends Item {
        private String name;
        private String description;
        private int healingPoints;

        public HealingItem(String description, int healingPoints, String name) {
            this.name = name;
            this.description = description;
            this.healingPoints = healingPoints;
        }

        public String getName() {
            return name;
        }

        public int getHealingPoints() {
            return healingPoints;
        }
    }

    public static class Munition extends Item {
        private String name;
        private String description;
        private int quantity;

        public Munition(String name, String description, int quantity) {
            this.name = name;
            this.description = description;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    public static class KeyItem extends Item {
        private String keyName;
        private String description;

        public KeyItem(String keyName, String description) {
            this.keyName = keyName;
            this.description = description;
        }

        public String getKeyName() {
            return keyName;
        }
    }
}
