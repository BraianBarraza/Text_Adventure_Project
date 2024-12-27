package map;

import java.util.HashMap;
import java.util.Map;

public class Places {

    public static class Room {
        private String name;
        private String description;

        public Room(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return name + ": " + description;
        }
    }

    public static class Place {
        private String placeName;
        private String description;
        private Map<String, Place> exits;
        private Map<String, Room> rooms;
        private boolean playerWasHere;
        private Room currentRoom; // Track the player's current room

        public Place(String placeName, String description) {
            this.placeName = placeName;
            this.description = description;
            this.exits = new HashMap<>();
            this.rooms = new HashMap<>();
            this.playerWasHere = false;
        }

        public boolean isPlayerWasHere() {
            return playerWasHere;
        }

        public void setPlayerWasHere(boolean playerWasHere) {
            this.playerWasHere = playerWasHere;
        }

        public String getPlaceName() {
            return placeName;
        }

        public void setPlaceName(String placeName) {
            this.placeName = placeName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Map<String, Place> getExits() {
            return exits;
        }

        public void connectPlaces(String direction, Place destination, String requiredRoom) {
            exits.put(direction, destination);
            rooms.put(direction, new Room(requiredRoom, "Exit to " + destination.getPlaceName()));
        }

        public void addRoom(String name, String description) {
            rooms.put(name, new Room(name, description));
        }

        public Map<String, Room> getRooms() {
            return rooms;
        }

        public Room getCurrentRoom() {
            return currentRoom;
        }

        public void setCurrentRoom(Room currentRoom) {
            this.currentRoom = currentRoom;
        }

        @Override
        public String toString() {
            StringBuilder info = new StringBuilder(description + "\nRooms: ");
            for (String roomName : rooms.keySet()) {
                info.append(roomName).append(" ");
            }
            info.append("\nExits: ");
            for (String direction : exits.keySet()) {
                info.append(direction).append(" ");
            }
            return info.toString();
        }
    }


    public Place createWorld() {
        Place house = new Place("House", "You are in the house. You can go to the garden.");
        Place garden = new Place("Garden", "You are in the garden. You can go to the house or the street.");
        Place street = new Place("Street", "You are on the Maple street. There is a crashed truck blocking the way ahead. " +
                                           "You can go to the garden, the pub, or Bill's house.");
        Place billsHouse = new Place("Bill's House", "You are in Bill's house. You can go to Maple Street.");
        Place pub = new Place("Pub", "You are in the pub. You can go to the little alley or Maple Street.");
        Place alley = new Place("Little Alley", "You are in the alley. You can go to the pub or Maple Street.");
        Place street2 = new Place("Street", "You are in the other side of Maple Street, you can go to " +
                                            "Stanley´s Motel, to Ben Hanscom House or to the Police Station. the end of the road is block with a barricade" +
                                            "I don´t think that I can go further");
        //define what is inside the police station
        Place policeStation = new Place("Police Station", "You are in the Police Station ...");
        Place motel = new Place ("motel", "You are in Stanley´s Motel, you can go to the Street or to the ");


        house.addRoom("living room", "A cozy place with a sofa and TV.");
        house.addRoom("kitchen", "A place with a fridge and a table.");
        house.addRoom("sleeping room", "A quiet room with a bed.");

        house.connectPlaces("garden", garden, "Living room");
        garden.connectPlaces("house", house, "garden");

//        garden.connectPlaces("street", street);
//        street.connectPlaces("garden", garden);
//
//        street.connectPlaces("pub", pub);
//        pub.connectPlaces("street", street);
//
//        street.connectPlaces("bills house", billsHouse);
//        billsHouse.connectPlaces("street", street);
//
//        pub.connectPlaces("alley", alley);
//        alley.connectPlaces("pub", pub);
//
//        alley.connectPlaces("street", street2);
//        street2.connectPlaces("alley", alley);
//
//        street2.connectPlaces("police station", policeStation);
//        policeStation.connectPlaces("street", street2);
//
//        street2.connectPlaces("motel", motel);
//        motel.connectPlaces("street", street2);





        return house;
        }

}