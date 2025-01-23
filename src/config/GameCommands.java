package config;

import map.Places.Place;
import map.Places.Room;
import characters.Player;
import combat.Item;

public class GameCommands {

    public static Place move(Place currentPlace, Player player) {
        System.out.println("Type: 'room <roomName>' or 'exit <place>'");
        String command = new java.util.Scanner(System.in).nextLine().toLowerCase();
        if (command.startsWith("room ")) {
            String roomName = command.substring(5).trim();
            if (currentPlace.getRooms().containsKey(roomName)) {
                currentPlace.setCurrentRoom(currentPlace.getRooms().get(roomName));
                System.out.println("You move to " + currentPlace.getCurrentRoom().getName());
            } else {
                System.out.println("Invalid room name");
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
                        next.setLocked(false);
                    }
                }
                next.setCurrentRoom(next.getRooms().values().iterator().next());
                return next;
            } else {
                System.out.println("You can't leave from here");
            }
        }
        return currentPlace;
    }
}
