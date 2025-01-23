package config;

import characters.Npc;
import map.Places;
import map.Places.Place;
import map.Places.Room;
import characters.Player;
import characters.Enemy.Zombie;
import characters.Npc;
import combat.CombatActions;
import combat.Weapon;
import combat.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private boolean gameStarted;
    private boolean running;
    private Place currentPlace;
    private Player player;

    public void startGame() {
        running = true;
        while (running) {
            showMainMenu();
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine().toLowerCase();
            if (choice.equals("1") || choice.equals("start")) {
                startNewGame();
            } else if (choice.equals("2") || choice.equals("load")) {
                System.out.println("Load game not available");
            } else if ((choice.equals("3") || choice.equals("save")) && gameStarted) {
                System.out.println("Save game not available");
            } else if (choice.equals("4") || choice.equals("exit")) {
                System.out.println("Exiting game");
                running = false;
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    private void showMainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Start Game");
        System.out.println("2. Load Game");
        if (gameStarted) {
            System.out.println("3. Save Game");
            System.out.println("4. Exit");
        } else {
            System.out.println("3. (Locked) Save Game");
            System.out.println("4. Exit");
        }
        System.out.print("Choose an option: ");
    }

    private void startNewGame() {
        gameStarted = true;
        System.out.println(GameStory.intro());
        Places places = new Places();
        currentPlace = places.createWorld();
        player = new Player("Player", 100);
        player.addWeaponToInventory(new Weapon("Knife", 15));
        player.addWeaponToInventory(new Weapon("Pistol", 25));
        gameLoop();
    }

    private void gameLoop() {
        Scanner sc = new Scanner(System.in);
        boolean inGame = true;
        while (inGame) {
            if (player.getHp() <= 0) {
                System.out.println("You died. Game Over.");
                inGame = false;
                replayOption();
                break;
            }
            System.out.println("1. Move");
            System.out.println("2. Search");
            System.out.println("3. Player Status");
            System.out.println("4. Inventory");
            System.out.println("5. Pause Menu");
            String input = sc.nextLine();
            if (input.equals("1")) {
                currentPlace = GameCommands.move(currentPlace, player);
            } else if (input.equals("2")) {
                searchRoom();
            } else if (input.equals("3")) {
                showPlayerStatus();
            } else if (input.equals("4")) {
                inventoryMenu();
            } else if (input.equals("5")) {
                inGame = false;
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    private void replayOption() {
        System.out.println("Do you want to replay? (y/n)");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine().toLowerCase();
        if (choice.equals("y")) {
            startNewGame();
        } else {
            running = false;
        }
    }

    private void searchRoom() {
        if (currentPlace.getCurrentRoom() == null) {
            System.out.println("No current room");
            return;
        }
        Room r = currentPlace.getCurrentRoom();
        if (!r.getEnemiesInRoom().isEmpty()) {
            startCombat(r.getEnemiesInRoom());
        }
        if (r.getNpc() != null) {
            Npc npc = r.getNpc();
            System.out.println(npc.getDialogue());
            if (!npc.hasGivenKey() && npc.getKey() != null) {
                player.addKeyItem((Item.KeyItem) npc.getKey());
                npc.giveKey();
            }
        }
        if (!r.getItemsInRoom().isEmpty()) {
            List<Item> foundItems = new ArrayList<>(r.getItemsInRoom());
            for (Item i : foundItems) {
                if (i instanceof Item.HealingItem) {
                    player.addHealingItem((Item.HealingItem) i);
                } else if (i instanceof Item.Munition) {
                    player.addMunition((Item.Munition) i);
                } else if (i instanceof Item.KeyItem) {
                    player.addKeyItem((Item.KeyItem) i);
                }
                r.getItemsInRoom().remove(i);
            }
        }
    }

    private void startCombat(List<Zombie> enemies) {
        CombatActions actions = new CombatActions();
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (player.getHp() <= 0) {
                System.out.println("You died.");
                return;
            }
            boolean allDead = true;
            for (Zombie z : enemies) {
                if (z.getHp() > 0) {
                    allDead = false;
                    break;
                }
            }
            if (allDead) {
                System.out.println("All enemies are dead.");
                return;
            }
            System.out.println("Your HP: " + player.getHp());
            int count = 1;
            for (Zombie z : enemies) {
                System.out.println(count + ") " + z.getName() + " (HP: " + z.getHp() + ")");
                count++;
            }
            System.out.println("a) Attack");
            System.out.println("b) Use Herb");
            String choice = sc.nextLine().toLowerCase();
            if (choice.equals("a")) {
                System.out.println("Which enemy?");
                String num = sc.nextLine();
                int idx;
                try {
                    idx = Integer.parseInt(num) - 1;
                } catch (Exception e) {
                    System.out.println("Invalid choice");
                    continue;
                }
                if (idx >= 0 && idx < enemies.size()) {
                    Zombie target = enemies.get(idx);
                    if (target.getHp() > 0) {
                        actions.playerAttack(player, target);
                    } else {
                        System.out.println("Already dead");
                    }
                } else {
                    System.out.println("Invalid choice");
                    continue;
                }
            } else if (choice.equals("b")) {
                useHerb();
            }
            for (Zombie z : enemies) {
                if (z.getHp() > 0) {
                    actions.zombieAttack(player, z);
                    if (player.getHp() <= 0) {
                        return;
                    }
                }
            }
        }
    }

    private void showPlayerStatus() {
        System.out.println("Player HP: " + player.getHp());
        System.out.println("Current place: " + currentPlace.getPlaceName());
        if (currentPlace.getCurrentRoom() != null) {
            System.out.println("Room: " + currentPlace.getCurrentRoom().getName());
            System.out.println("Room info: " + currentPlace.getCurrentRoom().getDescription());
        }
    }

    private void inventoryMenu() {
        Scanner sc = new Scanner(System.in);
        player.showInventory();
        System.out.println("a) Equip Weapon");
        System.out.println("b) Use Herb");
        System.out.println("c) Combine Herbs");
        System.out.println("d) Exit");
        String choice = sc.nextLine().toLowerCase();
        if (choice.equals("a")) {
            equipWeapon();
        } else if (choice.equals("b")) {
            useHerb();
        } else if (choice.equals("c")) {
            combineHerbs();
        }
    }

    private void equipWeapon() {
        if (player.getInventoryWeapons().isEmpty()) {
            System.out.println("No weapons available");
            return;
        }
        System.out.println("Choose a weapon by number:");
        int i = 1;
        for (Weapon w : player.getInventoryWeapons()) {
            System.out.println(i + ") " + w.getName());
            i++;
        }
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        try {
            int idx = Integer.parseInt(input) - 1;
            if (idx >= 0 && idx < player.getInventoryWeapons().size()) {
                player.setCurrentWeapon(player.getInventoryWeapons().get(idx));
            }
        } catch (Exception e) {
            System.out.println("Invalid");
        }
    }

    private void useHerb() {
        if (!player.getInventoryHealingItems().isEmpty()) {
            int heal = player.getInventoryHealingItems().get(0).getHealingPoints();
            player.setHp(Math.min(player.getHp() + heal, 100));
            System.out.println("Used " + player.getInventoryHealingItems().get(0).getName() + " +" + heal + " HP");
            player.getInventoryHealingItems().remove(0);
        } else {
            System.out.println("No healing items");
        }
    }

    private void combineHerbs() {
        int greenCount = 0;
        int redCount = 0;
        for (Item.HealingItem h : player.getInventoryHealingItems()) {
            if (h.getName().equalsIgnoreCase("Green Herb")) {
                greenCount++;
            } else if (h.getName().equalsIgnoreCase("Red Herb")) {
                redCount++;
            }
        }
        if (greenCount >= 2) {
            removeHerb("Green Herb");
            removeHerb("Green Herb");
            player.addHealingItem(new Item.HealingItem("Mixed 2 Greens", 70, "Mixed Herb"));
            System.out.println("You created a Mixed Herb (70 HP)");
        } else if (redCount >= 2) {
            removeHerb("Red Herb");
            removeHerb("Red Herb");
            player.addHealingItem(new Item.HealingItem("Mixed 2 Reds", 70, "Mixed Herb"));
            System.out.println("You created a Mixed Herb (70 HP)");
        } else if (greenCount >= 1 && redCount >= 1) {
            removeHerb("Green Herb");
            removeHerb("Red Herb");
            player.addHealingItem(new Item.HealingItem("Mixed Red+Green", 100, "Mixed Herb"));
            System.out.println("You created a Mixed Herb (100 HP)");
        } else {
            System.out.println("Not enough herbs to combine");
        }
    }

    private void removeHerb(String herbName) {
        for (int i = 0; i < player.getInventoryHealingItems().size(); i++) {
            if (player.getInventoryHealingItems().get(i).getName().equalsIgnoreCase(herbName)) {
                player.getInventoryHealingItems().remove(i);
                return;
            }
        }
    }
}
