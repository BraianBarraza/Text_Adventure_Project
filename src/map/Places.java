package map;

import java.time.Year;
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
        private String placeInformation;
        private Map<String, Place> exits;
        private Map<String, Room> rooms;
        private boolean playerWasHere;
        private Room currentRoom; // Track the player's current room


        public Place(String placeName, String description, String placeInformation) {
            this.placeName = placeName;
            this.description = description;
            this.placeInformation = placeInformation;
            this.playerWasHere = false;
            this.exits = new HashMap<>();
            this.rooms = new HashMap<>();
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

        public String getPlaceInformation() {
            return placeInformation;
        }

        public void setPlaceInformation(String placeInformation) {
            this.placeInformation = placeInformation;
        }

        public boolean isPlayerWasHere() {
            return playerWasHere;
        }

        public void setPlayerWasHere(boolean playerWasHere) {
            this.playerWasHere = playerWasHere;
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
        Place house = new Place("House", "You are in the house. You can go out to the garden.",
                                "You are inside the House, there are a Living room, a Kitchen and a Sleeping room ... " +
                                "I should take a look around in case I could find something useful");
        house.addRoom("living room", "A cozy place with a sofa and TV. I heard somebody hits door. " +
                        "My Spider-sense tells me that it could be dangerous to open it disarmed");
        house.addRoom("kitchen", "A place with a fridge and a table. I should check if I can find something useful here");
        house.addRoom("sleeping room", "A quiet room with a bed. I should check if I can find something useful here");

        Place garden = new Place("Garden", "You are in the garden. You can go to the house or the street.",
                                "Nothing useful around, just a dead body, it looks kinda like Stanley Uris(The owner from the hotel)," +
                                "... he got cuts in both wrists, I assume he commits suicide. Fuck ... I better keep going");

        Place street = new Place("Street", "You are on the Maple street. There is a crashed truck blocking the way ahead. " +
                                "You can go to the garden, the pub, or Bill's house.",
                                "The fire seems pretty intense I cant go closer to it. Bill should be at the bar" +
                                "I should Speak with him");

        Place billsHouse = new Place("Bill's House", "You are in Bill's house. You can go to Maple Street.", "");
        billsHouse.addRoom("living room", "A cozy place with a sofa and TV.");
        billsHouse.addRoom("Kitchen", "A place with a fridge and a table. I should check if I can find something useful here");
        billsHouse.addRoom("Bill Sleeping room", "A quiet room with a bed. I should check if I can find something useful here");

        Place pub = new Place("Pub", "You are in the pub. You can go to the little alley or Maple Street.", "");
        pub.addRoom("main room", " ");
        pub.addRoom("back of the bar counter", " ");
        pub.addRoom("Toilets", " ");

        Place alley = new Place("Little Alley", "You are in the alley. You can go to the pub or Maple Street.", "");
        pub.addRoom("alley back side", " ");
        pub.addRoom("alley side", " ");

        Place street2 = new Place("Street", "You are in the other side of Maple Street, you can go to " +
                                            "Stanley´s Motel, to Ben Hanscom House or to the Police Station. the end of the road is block with a barricade" +
                                            "I don´t think that I can go further", "");

        //define what is inside the police station
        Place policeStation = new Place("Police Station", "You are in the Police Station ...", " ");
        policeStation.addRoom("entrance"," ");
        policeStation.addRoom("office"," ");//Master key
        policeStation.addRoom("armory"," ");
        policeStation.addRoom("cells"," ");//Add the rope function

        Place motel = new Place ("motel", "You are in Stanley´s Motel, you can go to the Street or to the ", " ");
        motel.addRoom("Lobby", "");
        motel.addRoom("room 1", "");
        motel.addRoom("room 2", "");
        motel.addRoom("room 3", "");
        motel.addRoom("room 3", "");
    motel.addRoom("room 3", "");


        house.connectPlaces("garden", garden, "Living room");
        garden.connectPlaces("house", house, "garden");

        garden.connectPlaces("street", street, "garden");
        street.connectPlaces("garden", garden, "street");

        street.connectPlaces("pub", pub, "street");
        pub.connectPlaces("street", street, "pub");

        street.connectPlaces("bills house", billsHouse, "street");
        billsHouse.connectPlaces("street", street, "bills House");

        pub.connectPlaces("alley", alley, "pub");
        alley.connectPlaces("pub", pub, "alley");

        alley.connectPlaces("street", street2, "alley");
        street2.connectPlaces("alley", alley, "street");

        street2.connectPlaces("police station", policeStation, "street");
        policeStation.connectPlaces("street", street2, "police station");

        street2.connectPlaces("motel", motel, "street");
        motel.connectPlaces("street", street2, "motel");


        return house;
        }

}