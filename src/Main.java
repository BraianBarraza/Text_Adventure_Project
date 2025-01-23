import characters.Enemies;
import characters.Player;
import combat.CombatActions;
import combat.Item;
import combat.Weapon;
import config.GameCommands;
import map.Places.Place;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Weapon fist = new Weapon("Fists", 15);
        Weapon knife = new Weapon("Knife", 33);


        Item.HealingItem greenHerb = new Item.HealingItem("It can help you with the pain and the infection",25, "Green Herb");

        Place startingPlace = new map.Places().createWorld();
        Player player = new Player("Player1", 100);

        player.addWeaponToInventory(fist);

        CombatActions combat = new CombatActions();

        Place currentPlace = startingPlace;

        System.out.println("Your inventory:");
        player.showInventory();

        System.out.println("You found a Knife and a Green Herb. Do you want to pick them up? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            player.addWeaponToInventory(knife);
            player.addHealingItemToInventory(greenHerb);
        }

        System.out.println("Your updated inventory:");
        player.showInventory();

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