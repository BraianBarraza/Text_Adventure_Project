package config;

import map.Places;
import characters.Player;

/**
 * Hold the current state of the game, including the players data and the location.
 */
public class GameState {
    private Places currentPlace;
    private Player player;

    /**
     * Creates a new GameState with the provided place and player.
     * @param currentPlace the current location
     * @param player the player's character
     */
    public GameState(Places currentPlace, Player player) {
        this.currentPlace = currentPlace;
        this.player = player;
    }

    public Places getCurrentPlace() {
        return currentPlace;
    }

    public void setCurrentPlace(Places currentPlace) {
        this.currentPlace = currentPlace;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
