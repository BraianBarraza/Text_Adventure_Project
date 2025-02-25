package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import characters.Npc;
import characters.Enemy.Zombie;
import combat.Item;

import static characters.EnemiesFactory.*;
import static characters.NpcFactory.*;
import static combat.ItemsFactory.*;
import static combat.WeaponFactory.*;

public enum Places {

    HOUSE("House", "A small house with a garden exit", "I can watch the News in the Living room, " +
            "and look if I find something useful in the kitchen or in the sleeping room", false, null),
    GARAGE("Garage", "My bike is here it is a shame that a I cannot use it to escape from here","Good that I had some munition here", false, null),
    GARDEN("Garden", "A quiet garden", "Front garden, side garden", false, null),
    LOWER_MAIN_STREET("Lower Street", "A main street with smoke in the distance", "The street is dive in 2 parts the \"The MAIN LOWER STREET\" and the \"The MAIN STREET\" \n" +
            "I live in the Lower Street, in the House next to me live the Beautiful Beverly Marsh but she is in Castle Rock right now, and Bob is my front neighbor, \n" +
            "He works direct in the pub next to his house, a", false, null),
    MAIN_STREET("Main Street", "A main street with smoke in the distance", "", false, null),
    BILLS_HOUSE("Bill's House", "A locked house that belongs to Bill", "", true, BILLS_KEY),
    PUB("Pub", "A local pub that might have supplies", "", false, null),
    ALLEY("Alley", "A small alley leading to another street", "", true, ALLEY_KEY),
    POLICE_STATION("Police Station", "A station that might have some weapon", "", true, STATIONS_KEY),
    MOTEL("Motel", "The Motel from the town, I gotta see if Beverly Marsh and Carrie White are Ok ", "", false, null);

    private final String placeName;
    private final String description;
    private final String placeInformation;
    private boolean locked;
    private Item.KeyItem requiredKeyName;

    private final Map<String, Room> rooms = new HashMap<>();

    private final Map<String, List<Connection>> connectionsByRoom = new HashMap<>();

    private Room currentRoom;

    Places(String placeName, String description, String placeInformation, boolean locked, Item.KeyItem requiredKeyName) {
        this.placeName = placeName;
        this.description = description;
        this.placeInformation = placeInformation;
        this.locked = locked;
        this.requiredKeyName = requiredKeyName;
    }

    static {
        // HOUSE
        HOUSE.addRoom("living room", "A simple living room, theres a TV and a couch, and the exit to the front garden");
        HOUSE.addRoom("kitchen", "I can hear somebody in the side garden, I cant see it but I know is there.\n" +
                "I think is one of them, I should check if the old Bill is ok, he should be at the PUB");
        HOUSE.addRoom("sleeping room", "My bedroom, good thing that I save my old weapon here \n" +
                "I should look for the munition that I left in the GARAGE");

        // GARDEN
        GARDEN.addRoom("side garden", "");
        GARDEN.addRoom("front garden", "");

        // GARAGE
        GARAGE.addRoom("garage driveway","");
        GARAGE.addRoom("garage inside","");

        // LOWER STREET
        LOWER_MAIN_STREET.addRoom("block 1", "");
        LOWER_MAIN_STREET.addRoom("block 2", "");

        // PUB
        PUB.addRoom("main room", "A large room with tables and chairs");
        PUB.addRoom("bar counter", "A counter where the bartender stands");
        PUB.addRoom("toilets", "Dimly lit toilets");

        // BILL'S HOUSE (locked)
        BILLS_HOUSE.addRoom("living room", "Bill's living room");
        BILLS_HOUSE.addRoom("kitchen", "Bill's kitchen");
        BILLS_HOUSE.addRoom("bedroom", "Bill's bedroom");
        BILLS_HOUSE.setRequiredKey(BILLS_KEY);

        // ALLEY (locked)
        ALLEY.addRoom("Back alley", "A dead end with garbage");
        ALLEY.addRoom("Side alley", "A narrow way out to the other part of the street");
        ALLEY.setRequiredKey(ALLEY_KEY);

        // MAIN STREET
        MAIN_STREET.addRoom("main street", "The main Street from town street");

        // MOTEL
        MOTEL.addRoom("parking lot", "");
        MOTEL.addRoom("reception", "");
        MOTEL.addRoom("room1", "");
        MOTEL.addRoom("room2", "");
        MOTEL.addRoom("room3", "");
        MOTEL.addRoom("room4", "");

        // POLICE STATION (locked)
        POLICE_STATION.addRoom("entrance", "The station entrance");
        POLICE_STATION.addRoom("office", "An office with papers everywhere");
        POLICE_STATION.addRoom("armory", "An armory that might contain weapons");
        POLICE_STATION.addRoom("roof", "The Police Station roof perfect for some air landing");
        POLICE_STATION.setRequiredKey(STATIONS_KEY);

        //Current room(Initial Room) in every Place
        HOUSE.setCurrentRoom(HOUSE.getRooms().get("living room"));
        GARDEN.setCurrentRoom(GARDEN.getRooms().get("front garden"));
        GARAGE.setCurrentRoom(GARAGE.getRooms().get("garage driveway"));
        LOWER_MAIN_STREET.setCurrentRoom(LOWER_MAIN_STREET.getRooms().get("block 1"));
        BILLS_HOUSE.setCurrentRoom(BILLS_HOUSE.getRooms().get("living room"));
        PUB.setCurrentRoom(PUB.getRooms().get("main room"));
        ALLEY.setCurrentRoom(ALLEY.getRooms().get("Back alley"));
        POLICE_STATION.setCurrentRoom(POLICE_STATION.getRooms().get("entrance"));
        MOTEL.setCurrentRoom(MOTEL.getRooms().get("parking lot"));


        // HOUSE <-> GARDEN
        HOUSE.connectPlaces("living room", GARDEN, "front garden");
        GARDEN.connectPlaces("front garden", HOUSE, "living room");

        // GARDEN <-> GARAGE
        GARDEN.connectPlaces("side garden", GARAGE, "garage driveway");
        GARAGE.connectPlaces("garage driveway", GARDEN, "side garden");

        // GARDEN <-> STREET
        GARDEN.connectPlaces("front garden", LOWER_MAIN_STREET, "block 1");
        LOWER_MAIN_STREET.connectPlaces("block 1", GARDEN, "front garden");

        // STREET <-> PUB
        LOWER_MAIN_STREET.connectPlaces("block 2", PUB, "main room");
        PUB.connectPlaces("main room", LOWER_MAIN_STREET, "block 2");

        // STREET <-> BILL'S HOUSE
        LOWER_MAIN_STREET.connectPlaces("block 1", BILLS_HOUSE, "living room");
        BILLS_HOUSE.connectPlaces("living room", LOWER_MAIN_STREET, "block 1");

        // PUB <-> ALLEY
        PUB.connectPlaces("bar counter", ALLEY, "Back alley");
        ALLEY.connectPlaces("Back alley", PUB, "bar counter");

        // ALLEY <-> POLICE STATION
        ALLEY.connectPlaces("Side alley", POLICE_STATION, "entrance");
        POLICE_STATION.connectPlaces("entrance", ALLEY, "Side alley");

        //MOTEL <-> MAIN_STREET
        MOTEL.connectPlaces("parking lot", MAIN_STREET, "block 1");

        // Setting ITEMS
        HOUSE.getRooms().get("kitchen").getItemsInRoom().add(PISTOL_MUNITION_BOX);
        HOUSE.getRooms().get("kitchen").getItemsInRoom().add(KNIFE);
        HOUSE.getRooms().get("sleeping room").getItemsInRoom().add(PISTOL);
        HOUSE.getRooms().get("sleeping room").getItemsInRoom().add(GREEN_HERB);

        GARAGE.getRooms().get("garage inside").getItemsInRoom().add(PISTOL_MUNITION_BOX);
        GARAGE.getRooms().get("garage inside").getItemsInRoom().add(RED_HERB);

        BILLS_HOUSE.getRooms().get("bedroom").getItemsInRoom().add(BILLS_KEY);

        // Setting ENEMIES
        GARDEN.getRooms().get("side garden").getEnemiesInRoom().add(ZOMBIE);
        LOWER_MAIN_STREET.getRooms().get("block 2").getEnemiesInRoom().add(ZOMBIE);

        PUB.getRooms().get("main room").getEnemiesInRoom().add(ZOMBIE);
        PUB.getRooms().get("main room").getEnemiesInRoom().add(ZOMBIE);
        PUB.getRooms().get("toilets").getEnemiesInRoom().add(ZOMBIE);

        ALLEY.getRooms().get("Back alley").getEnemiesInRoom().add(DOG);
        ALLEY.getRooms().get("Back alley").getEnemiesInRoom().add(DOG);
        ALLEY.getRooms().get("Side alley").getEnemiesInRoom().add(DOG);
        ALLEY.getRooms().get("Side alley").getEnemiesInRoom().add(DOG);
        ALLEY.getRooms().get("Side alley").getEnemiesInRoom().add(DOG);

        BILLS_HOUSE.getRooms().get("living room").getEnemiesInRoom().add(POLICE);
        POLICE_STATION.getRooms().get("entrance").getEnemiesInRoom().add(POLICE);
        POLICE_STATION.getRooms().get("office").getItemsInRoom().add(STATIONS_KEY);

        // Setting NPCs
        HOUSE.getRooms().get("living room").setNpc(TV);
        PUB.getRooms().get("bar counter").setNpc(BILL);
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getDescription() {
        return description;
    }

    public String getPlaceInformation() {
        return placeInformation;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Item.KeyItem getRequiredKey() {
        return requiredKeyName;
    }

    public void setRequiredKey(Item.KeyItem requiredKeyName) {
        this.requiredKeyName = requiredKeyName;
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

    public List<Connection> getConnectionsFrom(String roomName) {
        return connectionsByRoom.getOrDefault(roomName, new ArrayList<>());
    }

    /**
     * Connect two Places, indicating from which room in this Place we can go,
     * and into which room in the destination Place we arrive.
     */
    public void connectPlaces(String fromRoomName, Places destination, String toRoomName) {
        // We create a new connection object
        Connection connection = new Connection(destination, toRoomName);
        // Add it to the list of connections from the specified room
        connectionsByRoom
                .computeIfAbsent(fromRoomName, k -> new ArrayList<>())
                .add(connection);
    }

    /**
     * Adds a Room to this Place
     */
    public void addRoom(String name, String description) {
        rooms.put(name, new Room(name, description));
    }

    /**
     * Inner class to represent a connection from a specific room in this place
     * to another Place (and a specific room in that other Place).
     */
    public static class Connection {
        private Places destinationPlace;
        private String destinationRoomName;

        public Connection(Places destinationPlace, String destinationRoomName) {
            this.destinationPlace = destinationPlace;
            this.destinationRoomName = destinationRoomName;
        }

        public Places getDestinationPlace() {
            return destinationPlace;
        }

        public String getDestinationRoomName() {
            return destinationRoomName;
        }
    }

    public static class Room {
        private String name;
        private String description;
        private List<Item> itemsInRoom;
        private List<Zombie> enemiesInRoom;
        private Npc npc;

        public Room(String name, String description) {
            this.name = name;
            this.description = description;
            this.itemsInRoom = new ArrayList<>();
            this.enemiesInRoom = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public List<Item> getItemsInRoom() {
            return itemsInRoom;
        }

        public List<Zombie> getEnemiesInRoom() {
            return enemiesInRoom;
        }

        public Npc getNpc() {
            return npc;
        }

        public void setNpc(Npc npc) {
            this.npc = npc;
        }

        @Override
        public String toString() {
            return name + ": " + description;
        }
    }
}
