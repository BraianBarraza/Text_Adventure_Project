package config;

import java.util.Scanner;

public class PauseMenu {

    public static void menuContinueCurrentGame() {
        System.out.println("Resuming current game...");
    }

    public static void menuSaveGameOption(GameState state) {
        GamePersistence.saveGame(state, "gamestate.txt");
    }

    public static GameState menuLoadFileOption() {
        return GamePersistence.loadGame("gamestate.txt");
    }

    public static void exitWithoutExit() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Are you sure you want to exit without saving? (Y/N): ");
        String response = sc.nextLine().trim().toLowerCase();
        if (response.equals("y")) {
            System.out.println("Exiting game without saving. Goodbye!");
            System.exit(0);
        } else {
            System.out.println("Exit cancelled. Returning to game...");
        }
    }
}
