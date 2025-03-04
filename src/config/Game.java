package config;

import map.Places;
import characters.Player;
import combat.Item;
import java.util.List;
import java.util.Scanner;

public class Game {
    private boolean gameStarted;
    private boolean running;
    private Places currentPlace;
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
        System.out.println(" ");
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
        GameStory.intro();

        currentPlace = Places.HOUSE;
        System.out.println(currentPlace.getCurrentRoom());

        player = new Player("Player", 100);

        player.addWeaponToInventory(combat.WeaponFactory.FIST);

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
            System.out.println("what would you like to do?");
            String input = sc.nextLine();

            if (input.equals("1")|| input.equalsIgnoreCase("move")) {
                currentPlace = GameCommands.move(currentPlace, player);
            } else if (input.equals("2")||input.equalsIgnoreCase("search")) {
                searchRoom();
            } else if (input.equals("3")||input.equalsIgnoreCase("status")) {
                showPlayerStatus();
            } else if (input.equals("4")||input.equalsIgnoreCase("inventory")) {
                inventoryMenu();
            } else if (input.equals("5")||input.equalsIgnoreCase("pause")) {
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
        Places.Room r = currentPlace.getCurrentRoom();
        Scanner sc = new Scanner(System.in);

        if (!r.getEnemiesInRoom().isEmpty()) {
            System.out.println("\n¡Oh Fuck! " + r.getEnemiesInRoom().size() + " enemies heard me!");

            for (characters.Enemy.Zombie enemy : r.getEnemiesInRoom()) {
                System.out.println(" - " + enemy.getName());
            }

            startCombat(r.getEnemiesInRoom());
        }

        if (r.getNpc() != null) {
            System.out.println(r.getNpc().getName() + " is here");
            System.out.println("Do you want to interact ? y/n");
            String response = sc.nextLine();

            while (!response.equalsIgnoreCase("y") && (!response.equalsIgnoreCase("n"))) {
                System.out.println("Invalid Answer. Do you want to interact ? y/n");
                response = sc.nextLine();
            }
            if (response.equalsIgnoreCase("y")){
                System.out.println(r.getNpc().getDialogue());
                if (!r.getNpc().hasGivenKey() && r.getNpc().getKey() != null) {
                    player.addKeyItem((Item.KeyItem) r.getNpc().getKey());
                    r.getNpc().giveKey();
                }
            } else {
                System.out.println("I should keep moving then...");
            }
        }

        if (!r.getItemsInRoom().isEmpty()) {
            System.out.println("\nNice, I found:");

            for (Item item : r.getItemsInRoom()) {
                System.out.println(" - " + item.getName());
            }

            System.out.println("\nIt could be useful, should keep it.");
            System.out.print("Do you want to save items in inventory? (Y/N): ");

            String response = sc.nextLine().trim().toLowerCase();

            while (!response.equals("y") && !response.equals("n")) {
                System.out.print("Invalid response. Please enter Y or N: ");
                response = sc.nextLine().trim().toLowerCase();
            }

            if (response.equals("y")) {
                List<Item> foundItems = new java.util.ArrayList<>(r.getItemsInRoom());

                for (Item i : foundItems) {
                    if (i instanceof Item.HealingItem) {
                        player.addHealingItem((Item.HealingItem) i);
                    } else if (i instanceof Item.Munition) {
                        player.addMunition((Item.Munition) i);
                    } else if (i instanceof Item.KeyItem) {
                        player.addKeyItem((Item.KeyItem) i);
                    } else if (i instanceof combat.Weapon) {
                        player.addWeaponToInventory((combat.Weapon) i);
                    }
                    r.getItemsInRoom().remove(i);
                }

                System.out.println("\nAll items have been stored in your inventory.");
            } else {
                System.out.println("\nYou decided to leave the items in the room.");
            }
        } else {
            System.out.println("\nThere are no items in this room.");
        }
    }

    private void startCombat(List<characters.Enemy.Zombie> enemies) {
        combat.CombatActions actions = new combat.CombatActions();
        Scanner sc = new Scanner(System.in);

        while (true) {
            if (player.getHp() <= 0) {
                System.out.println("You are dead...");
                return;
            }
            boolean allDead = true;
            for (characters.Enemy.Zombie z : enemies) {
                if (z.getHp() > 0) {
                    allDead = false;
                    break;
                }
            }
            if (allDead) {
                System.out.println("I killed them. I better keep moving");
                return;
            }

            System.out.println("Your HP: " + player.getHp());
            int count = 1;
            for (characters.Enemy.Zombie z : enemies) {
                System.out.println(count + ") " + z.getName() + " (HP: " + z.getHp() + ")");
                count++;
            }

            boolean validTurn = false;
            while (!validTurn) {
                System.out.println("Choose an action:");
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
                        System.out.println("Invalid choice.");
                        continue;
                    }
                    if (idx < 0 || idx >= enemies.size()) {
                        System.out.println("Invalid choice.");
                        continue;
                    }
                    characters.Enemy.Zombie target = enemies.get(idx);
                    if (target.getHp() <= 0) {
                        System.out.println("That zombie is already dead.");
                        continue;
                    }
                    combat.Weapon chosenWeapon = chooseWeapon();
                    if (chosenWeapon != null) {
                        actions.playerAttack(player, target, chosenWeapon);
                        validTurn = true;
                    } else {
                        System.out.println("No valid weapon chosen.");
                    }
                } else if (choice.equals("b")) {
                    useHerb();
                    validTurn = true;
                } else {
                    System.out.println("Invalid action. Please choose a valid option.");
                }
            }

            // After a valid player turn, zombies attack if still alive
            for (characters.Enemy.Zombie z : enemies) {
                if (z.getHp() > 0 && player.getHp() > 0) {
                    actions.zombieAttack(player, z);
                    if (player.getHp() <= 0) {
                        return;
                    }
                }
            }
        }
    }

    private combat.Weapon chooseWeapon() {
        Scanner sc = new Scanner(System.in);
        java.util.List<combat.Weapon> weapons = player.getInventoryWeapons();
        if (weapons.isEmpty()) {
            System.out.println("You have no weapons.");
            return null;
        }

        System.out.println("Choose a weapon by number:");
        for (int i = 0; i < weapons.size(); i++) {
            combat.Weapon w = weapons.get(i);
            System.out.println((i + 1) + ") " + w.getName() + " (Base Damage: " + w.getDamage() + ")");
        }

        String input = sc.nextLine();
        try {
            int idx = Integer.parseInt(input) - 1;
            if (idx >= 0 && idx < weapons.size()) {
                return weapons.get(idx);
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
        }
        return null;
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
        System.out.println("a) Use Herb");
        System.out.println("b) Combine Herbs");
        System.out.println("c) Exit");
        String choice = sc.nextLine().toLowerCase();
        if (choice.equals("a")) {
            useHerb();
        } else if (choice.equals("b")) {
            combineHerbs();
        }
    }

    private void useHerb() {
        if (!player.getInventoryHealingItems().isEmpty()) {
            int heal = player.getInventoryHealingItems().get(0).getHealingPoints();
            player.setHp(Math.min(player.getHp() + heal, 100));
            System.out.println("Used " + player.getInventoryHealingItems().get(0).getName()
                    + " +" + heal + " HP");
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
