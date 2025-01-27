package config;

import map.Places.Place;
import characters.Player;
import combat.Item;

public class GameCommands {

    public static Place move(Place currentPlace, Player player) {
        System.out.println("You are at the: " + currentPlace.getPlaceName());

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

        String command = new java.util.Scanner(System.in).nextLine().toLowerCase();

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
                Place next = currentPlace.getExits().get(direction);
                if (next.isLocked()) {
                    boolean hasKey = false;
                    for (Item.KeyItem k : player.getInventoryKeys()) {
                        if (k.getKeyName().equalsIgnoreCase(next.getRequiredKeyName())) {
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
                next.setCurrentRoom(next.getRooms().values().iterator().next());
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
