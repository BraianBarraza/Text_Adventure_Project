package config;

import map.Places;
import characters.Player;
import combat.WeaponFactory;
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
                GameState state = PauseMenu.menuLoadFileOption();
                if (state != null) {
                    restoreGameState(state);
                }
            } else if ((choice.equals("3") || choice.equals("save")) && gameStarted) {
                PauseMenu.menuSaveGameOption(new GameState(currentPlace, player));
            } else if (choice.equals("4") || choice.equals("exit")) {
                running = false;
                System.out.println("Exiting game.");
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
        System.out.println("2. Load Game");
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

            if (input.equals("1") || input.equalsIgnoreCase("move")) {
                currentPlace = GameCommands.move(currentPlace, player);
            } else if (input.equals("2") || input.equalsIgnoreCase("search")) {
                GameCommands.searchRoom(currentPlace, player);
            } else if (input.equals("3") || input.equalsIgnoreCase("status")) {
                GameCommands.showPlayerStatus(currentPlace, player);
            } else if (input.equals("4") || input.equalsIgnoreCase("inventory")) {
                GameCommands.inventoryMenu(player);
            } else if (input.equals("5") || input.equalsIgnoreCase("pause")) {
                showPauseMenu();
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

    private void showPauseMenu() {
        Scanner sc = new Scanner(System.in);
        boolean inPause = true;
        while (inPause) {
            System.out.println("\nPause Menu");
            System.out.println("1. Continue Current Game");
            System.out.println("2. Save Game");
            System.out.println("3. Load Game");
            System.out.println("4. Start New Game");
            System.out.println("5. Exit Without Saving");
            System.out.print("Choose an option: ");
            String option = sc.nextLine().toLowerCase();
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
                    PauseMenu.exitWithoutExit();
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void restoreGameState(GameState state) {
        this.currentPlace = state.getCurrentPlace();
        this.player = state.getPlayer();
        System.out.println("Game state restored.");
    }
}
