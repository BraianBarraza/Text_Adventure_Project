package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import characters.Enemy;
import characters.Npc;
import characters.Enemy.Zombie;
import combat.Item;


import static combat.ItemsFactory.*;
import static combat.WeaponFactory.*;

/**
 *
 */
public enum Places {

    HOUSE("House", "A small house with a garden exit", "Living room, kitchen, sleeping room", false, null),
    GARDEN("Garden", "A quiet garden", "", false, null),
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
        HOUSE.addRoom("living room", "A simple living room with a window");
        HOUSE.addRoom("kitchen", "A kitchen with a small table");
        HOUSE.addRoom("sleeping room", "A bedroom with a large bed");

        // GARDEN
        GARDEN.addRoom("side garden", "");
        GARDEN.addRoom("front garden", "");

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

        HOUSE.connectPlaces("garden", GARDEN, "living room");
        GARDEN.connectPlaces("house", HOUSE, "front garden");

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


        // HOUSE
        HOUSE.getRooms().get("kitchen").getItemsInRoom().add(PISTOLMUNITIONBOX);
        HOUSE.getRooms().get("kitchen").getItemsInRoom().add(PISTOL);
        HOUSE.getRooms().get("kitchen").getItemsInRoom().add(KNIFE);
        HOUSE.getRooms().get("sleeping room").getItemsInRoom().add(GREENHERB);

        // GARDEN
        GARDEN.getRooms().get("side garden").getEnemiesInRoom().add(
                new Enemy.Zombie("Zombie", "It is another infected person, it moves slow", 100, 10)
        );

        // PUB (bar counter)
        Npc npcBill = new Npc(
                "Bill",
                "Hello friend, the situation is pretty fucked up, one of those bastard bite me. I couldn´t get away "
                        + "'cause the *Alley* door is locked. I left the Alley Key at home. "
                        + "So go to *My House* and look for the *alleys key*. You may find some help. "
                        + "The police assistance was here too, probably he already found it.",
                new Item.KeyItem("Key to Bill's House", "Opens Bill's House")
        );
        PUB.getRooms().get("bar counter").setNpc(npcBill);

        // BILL'S HOUSE
        BILLS_HOUSE.getRooms().get("living room").getEnemiesInRoom().add(
                new Zombie("Cop Zombie", "Fuck! the Sheriff assistant was bitten ! he still has his helmet, this will be dangerous", 100, 10)
        );
        BILLS_HOUSE.getRooms().get("bedroom").getItemsInRoom().add(new Item.KeyItem("Key to Alley", "Opens the Alley"));

        // POLICE STATION
        POLICE_STATION.getRooms().get("entrance").getEnemiesInRoom().add(
                new Zombie("Cop Zombie", "A zombie in uniform", 100, 10)
        );
        POLICE_STATION.getRooms().get("office").getItemsInRoom().add(
                new Item.KeyItem("Key to Police Station", "I could maybe find something useful in the police station")
        );

        // HOUSE (living room)
        HOUSE.getRooms().get("living room").getEnemiesInRoom().add(
                new Zombie("Infected Man", "He tries to break the window!", 100, 10)
        );
    }

    // ======================================
    // Métodos auxiliares para este enum
    // ======================================

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
