package config;

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
        if (InputHandler.askYesNo("Are you sure you want to exit without saving?")) {
            System.out.println("Exiting game without saving. Goodbye!");
            System.exit(0);
        } else {
            System.out.println("Exit cancelled. Returning to game...");
        }
    }
}
