package config;

import map.Places;
import characters.Player;
import combat.Item;
import java.util.List;
import java.util.Scanner;

public class GameCommands {

    public static Places move(Places currentPlace, Player player) {
        System.out.println("You are at the: " + currentPlace.getPlaceName());
        if (currentPlace.getCurrentRoom() != null) {
            System.out.println("Current Room: " + currentPlace.getCurrentRoom().getName());
        }
        System.out.println(" ");

        String currentRoomName = currentPlace.getCurrentRoom().getName();
        if (!currentPlace.getRooms().isEmpty()) {
            System.out.println("You can move to the following rooms: ");
            currentPlace.getRooms().forEach((roomName, room) -> {
                if (!roomName.equalsIgnoreCase(currentRoomName)){
                System.out.println("- " + roomName);
                }
            });


        }

        List<Places.Connection> possibleConnections =
                currentPlace.getConnectionsFrom(currentPlace.getCurrentRoom().getName());

        if (!possibleConnections.isEmpty()) {
            System.out.println("You can exit to other places from this room: ");
            for (Places.Connection c : possibleConnections) {
                System.out.println("- " + c.getDestinationPlace().getPlaceName()
                        + " (arriving in: " + c.getDestinationRoomName() + ")");
            }
        }

        System.out.println("\nTo move, type: 'room <name of the room>' or 'exit <name of the place>'\n");
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
            String placeToGo = command.substring(5).trim();

            Places.Connection selectedConnection = null;
            for (Places.Connection c : possibleConnections) {
                if (c.getDestinationPlace().getPlaceName().equalsIgnoreCase(placeToGo)) {
                    selectedConnection = c;
                    break;
                }
            }

            if (selectedConnection == null) {
                System.out.println("Invalid exit. You can't leave from here to " + placeToGo + ".");
            } else {
                Places next = selectedConnection.getDestinationPlace();

                if (next.isLocked()) {
                    boolean hasKey = false;
                    Item.KeyItem neededKey = next.getRequiredKey();
                    for (Item.KeyItem k : player.getInventoryKeys()) {
                        if (k == neededKey) {
                            hasKey = true;
                            break;
                        }
                    }
                    if (!hasKey) {
                        System.out.println("This place is locked. You need " + neededKey.getName());
                        return currentPlace;
                    } else {
                        System.out.println("Place Unlocked, You use the: " + neededKey.getName());
                        next.setLocked(false);
                    }
                }

                System.out.println("You move to: " + next.getPlaceName());
                currentPlace = next;
                currentPlace.setCurrentRoom(
                        currentPlace.getRooms().get(selectedConnection.getDestinationRoomName())
                );
            }

        } else {
            System.out.println("Invalid command. Please try again.");
        }

        return currentPlace;
    }
}
