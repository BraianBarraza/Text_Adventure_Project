import config.Game;

/**
 * entry point of the Text Adventure game.
 * It creates and starts the Game instance.
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
