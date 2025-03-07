package combat;

/**
 * game items with a name and a description.
 * Items can be of different specific types, like healing items, munitions, or key items.
 */
public class Item {
    private String name;
    private String description;

    /**
     * Creates a new Item with the specified name and description.
     * @param name name of the item
     * @param description description of the item
     */
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

    /**
     * A specialized type of Item that heals the player when used.
     */
    public static class HealingItem extends Item {
        private int healingPoints;

        /**
         * Creates a HealingItem with an own name, healing value, and description.
         * @param name the name of the healing item
         * @param healingPoints the amount of HP this item restores
         * @param description a brief description of the healing item
         */
        public HealingItem(String name, int healingPoints, String description) {
            super(name, description);
            this.healingPoints = healingPoints;
        }

        public int getHealingPoints() {
            return healingPoints;
        }
    }

    /**
     * Type of Item that provides ammunition for fire weapons.
     */
    public static class Munition extends Item {
        private int quantity;

        /**
         * Creates a Munition item with an own name, quantity, and description.
         * @param name the name of the ammunition
         * @param quantity the initial quantity of ammunition
         * @param description a description of this munition
         */
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

    /**
     * A type of Item, keys that unlock some places in the game.
     */
    public static class KeyItem extends Item {
        /**
         * Creates a KeyItem with  a name and description.
         * @param name the name of the key
         * @param description a description of what this key unlocks
         */
        public KeyItem(String name, String description) {
            super(name, description);
        }
    }
}
