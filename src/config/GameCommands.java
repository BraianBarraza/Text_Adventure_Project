package config;

import map.Places;
import map.Places.Room;
import characters.Player;
import combat.Item;
import java.util.Scanner;


public class GameCommands {

    public static Places move(Places currentPlace, Player player) {
        System.out.println("You are at the: " + currentPlace.getPlaceName());
        if (currentPlace.getCurrentRoom() != null) {
            System.out.println("Current Room: " + currentPlace.getCurrentRoom().getName());
        }
        System.out.println(" ");

        if (!currentPlace.getRooms().isEmpty()) {
            System.out.println("You can move to the following rooms: ");
            currentPlace.getRooms().forEach((roomName, room) ->
                    System.out.println("- " + roomName)
            );
        }

        if (!currentPlace.getExits().isEmpty()) {
            System.out.println("You can go to other places through these exits: ");
            currentPlace.getExits().forEach((exitName, exitPlace) ->
                    System.out.println("- " + exitName + " (to " + exitPlace.getPlaceName() + ")")
            );
        }

        System.out.println("To move, type: 'room <name of the room>' or 'exit <name of the place>'");

        String command = new Scanner(System.in).nextLine().toLowerCase();

        if (command.startsWith("room ")) {
            String roomName = command.substring(5).trim();
            if (currentPlace.getRooms().containsKey(roomName)) {
                currentPlace.setCurrentRoom(currentPlace.getRooms().get(roomName));
                System.out.println("You move to " + currentPlace.getCurrentRoom().getName());
            } else {
                System.out.println("Invalid room name. Try again.");
            }
        } else if (command.startsWith("exit ")) {
            String direction = command.substring(5).trim();
            if (currentPlace.getExits().containsKey(direction)) {
                Places next = currentPlace.getExits().get(direction);

                if (next.isLocked()) {
                    boolean hasKey = false;
                    for (Item.KeyItem k : player.getInventoryKeys()) {
                        if (k.getName().equalsIgnoreCase(next.getRequiredKeyName())) {
                            hasKey = true;
                            break;
                        }
                    }
                    if (!hasKey) {
                        System.out.println("This place is locked. You need " + next.getRequiredKeyName());
                        return currentPlace;
                    } else {
                        System.out.println("You unlocked the place with the key.");
                        next.setLocked(false);
                    }
                }
                System.out.println("You move to: " + next.getPlaceName());
                return next;
            } else {
                System.out.println("Invalid exit. You can't leave from here in that direction.");
            }
        } else {
            System.out.println("Invalid command. Please try again.");
        }
        return currentPlace;
    }
}
