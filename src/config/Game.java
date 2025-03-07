package config;

import characters.EnemiesFactory;
import characters.Enemy;
import combat.Item;
import combat.Weapon;
import map.Places;
import characters.Player;
import combat.WeaponFactory;

import java.util.List;

/**
 * Manages how the game runs, including the main menu, gameplay loop, and transitions between states.
 */
public class Game {
    private boolean gameStarted;
    private boolean running;
    private Places currentPlace;
    private Player player;

    /**
     * Initializes and starts the game loop, displays the main menu and reacts to the user input.
     */
    public void startGame() {
        running = true;
        while (running) {
            showMainMenu();
            String choice = InputHandler.getInput().toLowerCase();
            if (choice.equals("1") || choice.equals("start")) {
                startNewGame();
            } else if (choice.equals("2") || choice.equals("load")) {
                GameState state = PauseMenu.menuLoadFileOption();
                if (state != null) {
                    restoreGameState(state);
                }
            } else if ((choice.equals("3") || choice.equals("save")) && gameStarted) {
                PauseMenu.menuSaveGameOption(new GameState(currentPlace, player));
            } else if (choice.equals("4") || choice.equals("exit")) {
                System.out.println("Exiting game.");
                running = false;
            } else if (choice.equals("5") || choice.equals("pause")) {
                showPauseMenu();
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    private void showMainMenu() {
        System.out.println();
        System.out.println("Main Menu");
        System.out.println("1. Start Game");
        if (gameStarted){
            System.out.println("2. Load Game");
        }else{
            System.out.println("2. (Locked) Load Game");
        }
        if (gameStarted) {
            System.out.println("3. Save Game");
        } else {
            System.out.println("3. (Locked) Save Game");
        }
        System.out.println("4. Exit");
        System.out.println("5. Pause Menu");
        System.out.print("Choose an option: ");
    }

    private void startNewGame() {
        gameStarted = true;
        GameStory.intro();
        currentPlace = Places.HOUSE;
        System.out.println(currentPlace.getCurrentRoom());

        player = new Player("Larry Underwood", 100);
        player.addWeaponToInventory(WeaponFactory.FIST);
        gameLoop();
    }

    /**
     * Manages gameplay loop where the player interacts with the world.
     * The loop ends if the player dies or is extracted.
     */
    private void gameLoop() {
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
            String input = InputHandler.getInput().toLowerCase();

            if (input.equals("1") || input.equalsIgnoreCase("move")) {
                currentPlace = GameCommands.move(currentPlace, player);
            } else if (input.equals("2") || input.equalsIgnoreCase("search")) {
                searchRoom(currentPlace, player);
            } else if (input.equals("3") || input.equalsIgnoreCase("status")) {
                showPlayerStatus(currentPlace, player);
            } else if (input.equals("4") || input.equalsIgnoreCase("inventory")) {
                inventoryMenu(player);
            } else if (input.equals("5") || input.equalsIgnoreCase("pause")) {
                showPauseMenu();
            } else {
                System.out.println("Invalid option");
            }
        }

    }

    /**
     * Asks if the player wants to replay after losing or completing the game.
     */
    private void replayOption() {
        if (InputHandler.askYesNo("Do you want to replay?")) {
            startNewGame();
        } else {
            running = false;
        }
    }

    private void showPauseMenu() {
        boolean inPause = true;
        while (inPause) {
            System.out.println("\nPause Menu");
            System.out.println("1. Continue Current Game");
            System.out.println("2. Save Game");
            System.out.println("3. Load Game");
            System.out.println("4. Start New Game");
            System.out.println("5. Exit Without Saving");
            System.out.print("Choose an option: ");
            String option = InputHandler.getInput().toLowerCase();
            switch (option) {
                case "1":
                    PauseMenu.menuContinueCurrentGame();
                    inPause = false;
                    break;
                case "2":
                    PauseMenu.menuSaveGameOption(new GameState(currentPlace, player));
                    break;
                case "3":
                    GameState state = PauseMenu.menuLoadFileOption();
                    if (state != null) {
                        restoreGameState(state);
                    }
                    break;
                case "4":
                    startNewGame();
                    inPause = false;
                    break;
                case "5":
                    PauseMenu.exitWithoutSaving();
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Restores a saved game state into the current session.
     *
     * @param state the loaded GameState
     */
    private void restoreGameState(GameState state) {
        this.currentPlace = state.getCurrentPlace();
        this.player = state.getPlayer();
        System.out.println("Game state restored.");
    }

    /**
     * Searches the current room for items, NPCs, or enemies.
     * Initiates combat if there are enemies in the room.
     *
     * @param currentPlace the current location in the game
     * @param player       Larry
     */
    private void searchRoom(Places currentPlace, Player player) {
        Places.Room r = currentPlace.getCurrentRoom();
        List<Enemy.Zombie> aliveEnemies = new java.util.ArrayList<>();
        for (Enemy.Zombie z : r.getEnemiesInRoom()) {
            if (z.getHp() > 0) {
                aliveEnemies.add(z);
            }
        }
        if (!aliveEnemies.isEmpty()) {
            if (aliveEnemies.size() == 1) {
                System.out.println("\n¡Oh Fuck! " + aliveEnemies.size() + " enemy heard me!");
            } else {
                System.out.println("\n¡Oh Fuck! " + aliveEnemies.size() + " enemies heard me!");
            }
            for (Enemy.Zombie enemy : aliveEnemies) {
                System.out.println(" - " + enemy.getName());
            }
            startCombat(aliveEnemies);
        } else if (!r.getEnemiesInRoom().isEmpty()) {
            System.out.println("\nNothing interesting here, just a dead body and a lot of blood.");
        }

        if (r.getNpc() != null) {
            if (InputHandler.askYesNo(r.getNpc().getName() + " is here. Do you want to interact?")) {
                System.out.println(r.getNpc().getDialogue());
                if (!r.getNpc().hasGivenKey() && r.getNpc().getKey() != null) {
                    Item.KeyItem key = (Item.KeyItem) r.getNpc().getKey();
                    player.addKeyItem(new Item.KeyItem(key.getName(), key.getDescription()));
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
            if (InputHandler.askYesNo("\nIt could be useful. Do you want to save items in inventory?")) {
                List<Item> foundItems = new java.util.ArrayList<>(r.getItemsInRoom());
                for (Item i : foundItems) {
                    player.pickUpItem(i);
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

    /**
     * Initiates and control the combat against one or more enemies.
     *
     * @param enemies the list of enemies in the room
     */
    private void startCombat(List<Enemy.Zombie> enemies) {
        combat.CombatActions actions = new combat.CombatActions();

        while (true) {
            if (player.getHp() <= 0) {
                System.out.println("You are dead...");
                return;
            }
            boolean allDead = true;
            for (Enemy.Zombie z : enemies) {
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
            for (Enemy.Zombie z : enemies) {
                System.out.println(count + ") " + z.getName() + " (HP: " + z.getHp() + ")");
                count++;
            }
            boolean validTurn = false;
            while (!validTurn) {
                System.out.println("Choose an action:");
                System.out.println("a) Attack");
                System.out.println("b) Use Herb");
                String choice = InputHandler.getInput().toLowerCase();
                if (choice.equals("a")) {
                    System.out.println("Which enemy?");
                    String num = InputHandler.getInput();
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
                    Enemy.Zombie target = enemies.get(idx);
                    if (target.getHp() <= 0) {
                        System.out.println("That zombie is already dead.");
                        continue;
                    }
                    Weapon chosenWeapon = chooseWeapon();
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
            for (Enemy.Zombie z : enemies) {
                if (z.getHp() > 0 && player.getHp() > 0) {
                    actions.zombieAttack(player, z);
                    if (player.getHp() <= 0) {
                        return;
                    }
                }
            }
            if (EnemiesFactory.MUTATION.getHp() <= 0) {
                GameStory.epilogue();
                replayOption();
            }
        }
    }

    /**
     * Lets the player select a weapon from their inventory to use in combat.
     *
     * @return the chosen Weapon, or null if invalid input
     */
    private Weapon chooseWeapon() {
        List<Weapon> weapons = player.getInventoryWeapons();
        if (weapons.isEmpty()) {
            System.out.println("You have no weapons.");
            return null;
        }
        System.out.println("Choose a weapon by number:");
        for (int i = 0; i < weapons.size(); i++) {
            Weapon w = weapons.get(i);
            System.out.println((i + 1) + ") " + w.getName() + " (Base Damage: " + w.getDamage() + ")");
        }
        String input = InputHandler.getInput();
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

    /**
     * Shows the player's current status including HP, location, and room information.
     *
     * @param currentPlace the current place in the game
     * @param player       the player's character
     */
    private void showPlayerStatus(Places currentPlace, Player player) {
        System.out.println(player.getName());
        System.out.println("HP: " + player.getHp());
        System.out.println();
        System.out.println("Current place: " + currentPlace.getPlaceName());
        System.out.println("Place info: " + currentPlace.getPlaceInformation());
        System.out.println();
        if (currentPlace.getCurrentRoom() != null) {
            System.out.println("Room: " + currentPlace.getCurrentRoom().getName());
            System.out.println("Room info: " + currentPlace.getCurrentRoom().getDescription());
            System.out.println();
        }
    }

    /**
     * Displays and handles the player's inventory menu.
     *
     * @param player Larry
     */
    private void inventoryMenu(Player player) {
        System.out.println();
        this.player.showInventory();
        System.out.println("Inventory Actions:");
        System.out.println("i) Inventory Info");
        System.out.println();
        System.out.println("a) Use Herb");
        System.out.println("b) Combine Herbs");
        System.out.println("c) Exit");
        System.out.println();
        String choice = InputHandler.getInput().toLowerCase();
        if (choice.equals("a") || choice.equals("use herb") || choice.equals("use")) {
            useHerb();
        } else if (choice.equals("b") || choice.equals("combine herb") || choice.equals("combine")) {
            player.combineHerbs();
        } else if (choice.equals("i") || choice.equals("info")) {
            inventoryInfo();
        }
    }

    /**
     * Uses the first healing item in the inventory.
     */
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

    /**
     * Shows information about the player inventory.
     */
    private void inventoryInfo() {
        System.out.println("Weapons:");
        for (Weapon w : player.getInventoryWeapons()) {
            System.out.println(" - " + w.getName() + " (, DMG: " + w.getDamage()
                    + ", Description: " + w.getDescription() + ")");
        }
        System.out.println("Ammunition:");
        for (Item.Munition m : player.getInventoryMunition()) {
            System.out.println(" - " + m.getName() + " (, Quantity: " + m.getQuantity()
                    + ", Description: " + m.getDescription() + ")");
        }
        System.out.println("Healing Items:");
        for (Item.HealingItem h : player.getInventoryHealingItems()) {
            System.out.println(" - " + h.getName() + " (Heal: " + h.getHealingPoints()
                    + ", Description: " + h.getDescription() + ")");
        }
        System.out.println("Key Items:");
        for (Item.KeyItem k : player.getInventoryKeys()) {
            System.out.println(" - " + k.getName() + ", Description: " + k.getDescription());
        }
    }
}
