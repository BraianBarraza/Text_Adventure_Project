package config;

import map.Places;
import characters.Player;
import combat.Weapon;
import combat.WeaponFactory;
import combat.Item;
import combat.ItemsFactory;
import java.io.*;
import java.util.*;

public class GamePersistence {
    public static void saveGame(GameState state, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("currentPlace=" + state.getCurrentPlace().name());
            writer.println("currentRoom=" + state.getCurrentPlace().getCurrentRoom().getName());
            writer.println("playerHP=" + state.getPlayer().getHp());
            List<Weapon> weapons = state.getPlayer().getInventoryWeapons();
            writer.print("weapons=");
            for (int i = 0; i < weapons.size(); i++) {
                writer.print(weapons.get(i).getName());
                if (i < weapons.size() - 1) {
                    writer.print(",");
                }
            }
            writer.println();
            List<Item.HealingItem> healingItems = state.getPlayer().getInventoryHealingItems();
            writer.print("healingItems=");
            for (int i = 0; i < healingItems.size(); i++) {
                writer.print(healingItems.get(i).getName());
                if (i < healingItems.size() - 1) {
                    writer.print(",");
                }
            }
            writer.println();
            List<Item.Munition> munitions = state.getPlayer().getInventoryMunition();
            writer.print("munition=");
            for (int i = 0; i < munitions.size(); i++) {
                writer.print(munitions.get(i).getName() + ":" + munitions.get(i).getQuantity());
                if (i < munitions.size() - 1) {
                    writer.print(";");
                }
            }
            writer.println();
            List<Item.KeyItem> keys = state.getPlayer().getInventoryKeys();
            writer.print("keys=");
            for (int i = 0; i < keys.size(); i++) {
                writer.print(keys.get(i).getName());
                if (i < keys.size() - 1) {
                    writer.print(",");
                }
            }
            writer.println();
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    public static GameState loadGame(String filename) {
        Map<String, String> data = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    data.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading game: " + e.getMessage());
            return null;
        }
        String currentPlaceStr = data.get("currentPlace");
        String currentRoomStr = data.get("currentRoom");
        int playerHP = Integer.parseInt(data.get("playerHP"));
        Places currentPlace = Places.valueOf(currentPlaceStr);
        currentPlace.setCurrentRoom(currentPlace.getRooms().get(currentRoomStr));
        Player player = new Player("Larry Underwood", playerHP);
        String weaponsStr = data.get("weapons");
        if (weaponsStr != null && !weaponsStr.isEmpty()) {
            String[] weaponNames = weaponsStr.split(",");
            for (String wn : weaponNames) {
                wn = wn.trim();
                if (wn.equalsIgnoreCase("Fist")) {
                    player.addWeaponToInventory(WeaponFactory.FIST);
                } else if (wn.equalsIgnoreCase("Knive") || wn.equalsIgnoreCase("Knife")) {
                    player.addWeaponToInventory(WeaponFactory.KNIFE);
                } else if (wn.equalsIgnoreCase("Pistol")) {
                    player.addWeaponToInventory(WeaponFactory.PISTOL);
                } else if (wn.equalsIgnoreCase("Shotgun")) {
                    player.addWeaponToInventory(WeaponFactory.SHOTGUN);
                }
            }
        }
        String healingStr = data.get("healingItems");
        if (healingStr != null && !healingStr.isEmpty()) {
            String[] healingNames = healingStr.split(",");
            for (String hn : healingNames) {
                hn = hn.trim();
                if (hn.equalsIgnoreCase("Green Herb")) {
                    player.addHealingItem(ItemsFactory.createGreenHerb());
                } else if (hn.equalsIgnoreCase("Red Herb")) {
                    player.addHealingItem(ItemsFactory.createRedHerb());
                } else if (hn.equalsIgnoreCase("Normal Mixed Herb")) {
                    player.addHealingItem(ItemsFactory.createNormalMixedHerb());
                } else if (hn.equalsIgnoreCase("Super Mixed Herb")) {
                    player.addHealingItem(ItemsFactory.createSuperMixedHerb());
                }
            }
        }
        String munitionStr = data.get("munition");
        if (munitionStr != null && !munitionStr.isEmpty()) {
            String[] munitions = munitionStr.split(";");
            for (String m : munitions) {
                String[] parts = m.split(":");
                if (parts.length == 2) {
                    String mName = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    if (mName.equalsIgnoreCase("9mm Bullets box")) {
                        Item.Munition ammo = ItemsFactory.createPistolMunition();
                        ammo.setQuantity(quantity);
                        player.addMunition(ammo);
                    } else if (mName.equalsIgnoreCase("12mm Shells box")) {
                        Item.Munition ammo = ItemsFactory.createShotgunMunition();
                        ammo.setQuantity(quantity);
                        player.addMunition(ammo);
                    }
                }
            }
        }
        String keysStr = data.get("keys");
        if (keysStr != null && !keysStr.isEmpty()) {
            String[] keyNames = keysStr.split(",");
            for (String kn : keyNames) {
                kn = kn.trim();
                if (kn.equalsIgnoreCase("Key to Bill's House")) {
                    player.addKeyItem(ItemsFactory.BILLS_KEY);
                } else if (kn.equalsIgnoreCase("Alleys Key")) {
                    player.addKeyItem(ItemsFactory.ALLEY_KEY);
                } else if (kn.equalsIgnoreCase("Key to Police Station")) {
                    player.addKeyItem(ItemsFactory.STATIONS_KEY);
                }
            }
        }
        return new GameState(currentPlace, player);
    }
}
