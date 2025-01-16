package characters;

import combat.Item;
import combat.Weapon;
import java.util.Map;

public class Inventory {

    public static class PlayerInventory{
        private Map<String, Weapon> playerWeapons;
        private Map<String, Item> playerItems;

        public PlayerInventory(Map<String, Weapon> playerWeapons, Map<String, Item> playerItems) {
            this.playerWeapons = playerWeapons;
            this.playerItems = playerItems;
        }

        public Map<String, Weapon> getPlayerWeapons() {
            return playerWeapons;
        }

        public void setPlayerWeapons(Map<String, Weapon> playerWeapons) {
            this.playerWeapons = playerWeapons;
        }

        public Map<String, Item> getPlayerItems() {
            return playerItems;
        }

        public void setPlayerItems(Map<String, Item> playerItems) {
            this.playerItems = playerItems;
        }

    }
}
