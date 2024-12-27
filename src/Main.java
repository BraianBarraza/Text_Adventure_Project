import characters.Enemies;
import characters.Player;
import combat.CombatActions;
import combat.Weapon;
import config.GameCommands;
import map.Places.Place;

public class Main {

    public static void main(String[] args) {

        Place startingPlace = new map.Places().createWorld();
        Player player = new Player("Player1", 100, new Weapon("Fists", 5));
        CombatActions combat = new CombatActions();

        Place currentPlace = startingPlace;

        while (true) {
            System.out.println("You are at: " + currentPlace.getPlaceName());
            currentPlace = GameCommands.move(currentPlace);

            if (currentPlace.getPlaceName().equals("Street")) {
                Enemies.Zombie zombie = Enemies.getZombies().get(0);
                System.out.println("A " + zombie.getName() + " appears!");
                combat.playerAttack(player, zombie);
                combat.zombieAttack(player, zombie);
            }
        }
    }
}