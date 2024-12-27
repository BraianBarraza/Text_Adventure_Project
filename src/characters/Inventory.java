package characters;

import combat.Items;
import combat.Weapon;
import java.util.Map;

public class Inventory {

    public static class PlayerInventory{
        private Map<String, Weapon> playerWeapons;
        private Map<String, Items> playerItems;

        public PlayerInventory(Map<String, Weapon> playerWeapons, Map<String, Items> playerItems) {
            this.playerWeapons = playerWeapons;
            this.playerItems = playerItems;
        }

        public Map<String, Weapon> getPlayerWeapons() {
            return playerWeapons;
        }

        public void setPlayerWeapons(Map<String, Weapon> playerWeapons) {
            this.playerWeapons = playerWeapons;
        }

        public Map<String, Items> getPlayerItems() {
            return playerItems;
        }

        public void setPlayerItems(Map<String, Items> playerItems) {
            this.playerItems = playerItems;
        }

    }
}
