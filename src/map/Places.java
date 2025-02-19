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
    STREET("Street", "A main street with smoke in the distance", "", false, null),
    BILLS_HOUSE("Bill's House", "A locked house that belongs to Bill", "", true, "Key to Bill's House"),
    PUB("Pub", "A local pub that might have supplies", "", false, null),
    ALLEY("Alley", "A small alley leading to another street", "", true, "Key to Alley"),
    POLICE_STATION("Police Station", "A station that might have some weapon", "", true, "Key to Police Station");

    private final String placeName;
    private final String description;
    private final String placeInformation;
    private boolean locked;
    private String requiredKeyName;

    private final Map<String, Room> rooms = new HashMap<>();

    private final Map<String, Places> exits = new HashMap<>();

    private Room currentRoom;

    Places(String placeName, String description, String placeInformation, boolean locked, String requiredKeyName) {
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

        //GARAGE
        GARAGE.addRoom("garage","");

        // STREET
        STREET.addRoom("block 1", "");
        STREET.addRoom("block 2", "");

        // BILL'S HOUSE (locked)
        BILLS_HOUSE.addRoom("living room", "Bill's living room");
        BILLS_HOUSE.addRoom("kitchen", "Bill's kitchen");
        BILLS_HOUSE.addRoom("bedroom", "Bill's bedroom");

        // PUB
        PUB.addRoom("main room", "A large room with tables and chairs");
        PUB.addRoom("bar counter", "A counter where the bartender stands");
        PUB.addRoom("toilets", "Dimly lit toilets");

        // ALLEY (locked)
        ALLEY.addRoom("Back alley", "A dead end with garbage");
        ALLEY.addRoom("Side alley", "A narrow way out to the other part of the street");

        // POLICE STATION (locked)
        POLICE_STATION.addRoom("entrance", "The station entrance");
        POLICE_STATION.addRoom("office", "An office with papers everywhere");
        POLICE_STATION.addRoom("armory", "An armory that might contain weapons");

        //Places Connection
        HOUSE.connectPlaces("garden", GARDEN, "living room");
        GARDEN.connectPlaces("house", HOUSE, "front garden");

        GARDEN.connectPlaces("garage", GARAGE, "garage");
        GARAGE.connectPlaces("garden", GARDEN, "side garden");

        GARDEN.connectPlaces("street", STREET, "front garden");
        STREET.connectPlaces("garden", GARDEN, "block 1");

        STREET.connectPlaces("pub", PUB, "block 2");
        PUB.connectPlaces("street", STREET, "pub");

        STREET.connectPlaces("bills house", BILLS_HOUSE, "street");
        BILLS_HOUSE.connectPlaces("street", STREET, "bills house");

        PUB.connectPlaces("bar alley", ALLEY, "bar counter");
        ALLEY.connectPlaces("pub", PUB, "alley");

        ALLEY.connectPlaces("police station", POLICE_STATION, "alley end");
        POLICE_STATION.connectPlaces("alley", ALLEY, "police station");

        HOUSE.setCurrentRoom(HOUSE.getRooms().get("living room"));
        GARDEN.setCurrentRoom(GARDEN.getRooms().get("front garden"));
        STREET.setCurrentRoom(STREET.getRooms().get("block 1"));
        BILLS_HOUSE.setCurrentRoom(BILLS_HOUSE.getRooms().get("living room"));
        PUB.setCurrentRoom(PUB.getRooms().get("main room"));
        ALLEY.setCurrentRoom(ALLEY.getRooms().get("Back alley"));
        POLICE_STATION.setCurrentRoom(POLICE_STATION.getRooms().get("entrance"));


        //Setting ITEMS
        HOUSE.getRooms().get("kitchen").getItemsInRoom().add(PISTOL_MUNITION_BOX);
        HOUSE.getRooms().get("kitchen").getItemsInRoom().add(KNIFE);
        HOUSE.getRooms().get("sleeping room").getItemsInRoom().add(PISTOL);
        HOUSE.getRooms().get("sleeping room").getItemsInRoom().add(GREEN_HERB);

        GARAGE.getRooms().get("garage").getItemsInRoom().add(PISTOL_MUNITION_BOX);
        GARAGE.getRooms().get("garage").getItemsInRoom().add(RED_HERB);

        BILLS_HOUSE.getRooms().get("bedroom").getItemsInRoom().add(BILLS_KEY);


        //Setting ENEMIES
        GARDEN.getRooms().get("side garden").getEnemiesInRoom().add(ZOMBIE);

        STREET.getRooms().get("block 2").getEnemiesInRoom().add(ZOMBIE);

        BILLS_HOUSE.getRooms().get("living room").getEnemiesInRoom().add(POLICE);

        POLICE_STATION.getRooms().get("entrance").getEnemiesInRoom().add(POLICE);
        POLICE_STATION.getRooms().get("office").getItemsInRoom().add(STATIONS_KEY);

        //Setting NPCs
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

    public String getRequiredKeyName() {
        return requiredKeyName;
    }

    public void setRequiredKeyName(String requiredKeyName) {
        this.requiredKeyName = requiredKeyName;
    }

    public Map<String, Places> getExits() {
        return exits;
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

    /**
     *
     * @param direction
     * @param destination
     * @param roomName
     */
    public void connectPlaces(String direction, Places destination, String roomName) {
        exits.put(direction, destination);

        if (!rooms.containsKey(roomName)) {
            rooms.put(roomName, new Room(roomName, "Exit to " + destination.getPlaceName()));
        }
    }

    /**
     *
     * @param name
     * @param description
     */
    public void addRoom(String name, String description) {
        rooms.put(name, new Room(name, description));
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
