package config;

import map.Places.Place;

import java.util.Scanner;

public class GameCommands {

    public static Place move(Place currentPlace) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Current place: " + currentPlace.getPlaceName());
            System.out.println("Current room: " + (currentPlace.getCurrentRoom() != null
                    ? currentPlace.getCurrentRoom().getName()
                    : "No specific room"));
            System.out.println(currentPlace);
            System.out.println("Type 'room [name]' to move to a room, or 'exit [place]' to leave:");

            String command = sc.nextLine();

            if (command.startsWith("room")) {
                String roomName = command.substring(5);
                if (currentPlace.getRooms().containsKey(roomName)) {
                    currentPlace.setCurrentRoom(currentPlace.getRooms().get(roomName));
                    System.out.println("Moved to room: " + currentPlace.getCurrentRoom());
                } else {
                    System.out.println("Invalid room name!");
                }
            } else if (command.startsWith("exit ")) {
                String direction = command.substring(5);
                if (currentPlace.getExits().containsKey(direction) &&
                        currentPlace.getRooms().get(direction).equals(currentPlace.getCurrentRoom())) {
                    return currentPlace.getExits().get(direction);
                } else {
                    System.out.println("You can't leave from here!");
                    System.out.println("You have to go to the");
                }
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}
