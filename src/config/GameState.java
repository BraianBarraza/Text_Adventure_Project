package config;

import map.Places;
import characters.Player;

public class GameState {
    private Places currentPlace;
    private Player player;

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
