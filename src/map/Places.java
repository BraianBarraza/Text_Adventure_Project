package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import characters.EnemiesFactory;
import characters.Npc;
import characters.Enemy.Zombie;
import combat.Item;
import static characters.NpcFactory.*;
import static combat.ItemsFactory.*;
import static combat.WeaponFactory.*;

/**
 * Represents major locations (Places) in the game world, each containing multiple Rooms.
 * Each enum constant holds its own name, description, info text, locked state, and a required key if locked.
 */

public enum Places {

    HOUSE("House",
            "A small house with a garden exit",
            "The House has three rooms: a living room (exit to the front garden), a kitchen, and a sleeping room.\n" +
                    " Try searching them for supplies or checking the TV in the living room for updates.",
            false, null),

    GARAGE("Garage",
            "My bike is here it is a shame that a I cannot use it to escape from here",
            "The Garden has two sections: the side garden (exit to the garage driveway)\n" +
                    " and the front garden (exit to the House's living room). You can also reach the Lower Street from the front garden.",
            false, null),

    GARDEN("Garden",
            "A quiet garden",
            "Front garden, side garden",
            false, null),

    LOWER_MAIN_STREET("Lower Street",
            "The Lower Main Street theres smoke on the horizon. A truck crashed into Ben Mears' house, both now ablaze, filling the air with choking fumes. \n" +
                        "I cant keep going this way, gotta find another. I think theres a way through the Pub",
            "The Main Street is dive in 2 parts the \"The MAIN LOWER STREET\" and the \"The MAIN STREET\" \n" +
                    "The Lower Street has two blocks: block 1 (exits to the Garden and Bill's House) and block 2 (leading to the Pub)\n" +
                    "I live in the Lower Street, in the House next to me live the Beautiful Beverly Marsh but she is in Chester Mill right now, and Bill Denbrough is my front neighbor, \n" +
                    "He works direct in the pub next to his house, I should check if he is Ok", false, null),

    MAIN_STREET("Main Street",
            "A main street with smoke in the other part, now I can keep going",
            "Main Street has two blocks: block 3 (exits to the Alley and the Motel parking lot) and \n" +
                    "block 4 (exit to the Police Station). Danger lurks behind every vehicle",
            false, null),

    BILLS_HOUSE("Bills House",
            "A locked house that belongs to Bill",
            "Bill's House has a living room (exit to block 1 of Lower Street), a kitchen, and a bedroom. \n" +
                    "It's locked, so I'll need the right key to enter",
            true, BILLS_KEY),

    PUB("Pub",
            "A local pub, Bill Denbrough is the owner and the first friend I made in Castle Rock town",
            "The Pub has three rooms: the main room (exit to block 2 of Lower Street), the bar counter (exit to the back alley), \n" +
                    "and toilets",
            false, null),

    ALLEY("Alley",
            "A narrow passage filled with trash and graffitis, there use to be a lot of dog who ate it",
            "The Alley has two parts: the back alley (exit to the pub's bar counter) and the side alley (exit to block 3 of Main Street).",
            true, ALLEY_KEY),

    POLICE_STATION("Police Station",
            "The Police Station, this is the place of the extraction I can hear the Helicopter approaching, I have to hurry up ",
            "The Police Station has four areas: the entrance (exit to block 4 of Main Street), an office, \n" +
                    "an armory, and the roof. I have to clear it for the extraction.",
            true, STATIONS_KEY),

    MOTEL("Motel",
            "The Motel from the town, theres blood all around the place something terrible happened here!",
            "The Motel has a parking lot (exit to block 3 of Main Street), a reception desk, and four rooms (1–4). \n" +
                    "Carrie White may be somewhere inside, needing help.",
            false, null);

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
        GARDEN.addRoom("side garden", "A narrow strip overshadowed by a high fence. The garage driveway is just beyond.");
        GARDEN.addRoom("front garden", "Patchy grass and a crooked mailbox near the street. Despite the chaos, a few flowers still bloom");

        // GARAGE
        GARAGE.addRoom("garage driveway", "A short concrete slope with oil stains marking where a car once stood.");
        GARAGE.addRoom("garage inside", "Stacks of boxes, tools, and half-finished repair projects. The smell of gasoline is intense.");

        // LOWER STREET
        LOWER_MAIN_STREET.addRoom("block 1", "block 1/4 The pavement is cracked, and smoke curls in the air from nearby fires.");
        LOWER_MAIN_STREET.addRoom("block 2", "block 2/4 its pretty hot here I am almost in front of the fire");

        // PUB
        PUB.addRoom("main room", "A large room with tables and chairs, theres chaos all around, it looks like there was a fight here");
        PUB.addRoom("bar counter", "A counter where the bartender stands, bottles smashed all around. the sour smell of alcohol rises here");
        PUB.addRoom("toilets", "Dimly lit toilets, the mirrors are broken theres watter and blood in the floor");

        // BILL'S HOUSE (locked)
        BILLS_HOUSE.addRoom("living room", "Bill's living room, somebody was here. the table is broken and someone threw everything to the floor!");
        BILLS_HOUSE.addRoom("kitchen", "Bill's kitchen, A small table stands overturned, and the fridge door hangs open, may be was Georgi");
        BILLS_HOUSE.addRoom("bedroom", "Bill's bedroom, A flickering radio on the nightstand emits static and faint voices.\n" +
                "may be Bill used it to communicate with his little brother, fuck they were so close");

        // ALLEY (locked)
        ALLEY.addRoom("back alley", "A dead end with a lot of garbage, there are dead rats all around, something ate them.");
        ALLEY.addRoom("side alley", "A narrow way out to the other part of the street, more dead rats and blood \n" +
                "I can smell dead bodies all around, theres no air in here");

        // MAIN STREET
        MAIN_STREET.addRoom("block 3", "block 3/4 here is the truck on fire again, I can see the body of the driver from here");
        MAIN_STREET.addRoom("block 4", "block 4/4 there was a big crash at the avenue, it is impossible to go out of this street");

        // MOTEL
        MOTEL.addRoom("parking lot", "A lot of blood all around here, there was a carnage");
        MOTEL.addRoom("reception", "A dusty reception desk with a bell. The neon light flickers overhead my head");
        MOTEL.addRoom("room 1", "Blood all around");
        MOTEL.addRoom("room 2", "Blood all around");
        MOTEL.addRoom("room 3", "The Sheriff took his wife Sarah inside here, probably he tried to kill her but he didnt, \n" +
                "what a terrible situation");
        MOTEL.addRoom("room 4", "Blood all around");

        // POLICE STATION (locked)
        POLICE_STATION.addRoom("entrance", "The station entrance");
        POLICE_STATION.addRoom("office", "An office with papers everywhere, they went out so suddenly and let everything made a mess");
        POLICE_STATION.addRoom("armory", "They took all the guns but theres still some munition around");
        POLICE_STATION.addRoom("roof", "The Police Station roof perfect for some air landing");


        // Current room (Initial Room)
        HOUSE.setCurrentRoom(HOUSE.getRooms().get("living room"));

        // Connections between places
        HOUSE.connectPlaces("living room", GARDEN, "front garden");
        GARDEN.connectPlaces("front garden", HOUSE, "living room");

        GARDEN.connectPlaces("side garden", GARAGE, "garage driveway");
        GARAGE.connectPlaces("garage driveway", GARDEN, "side garden");

        GARDEN.connectPlaces("front garden", LOWER_MAIN_STREET, "block 1");
        LOWER_MAIN_STREET.connectPlaces("block 1", GARDEN, "front garden");

        LOWER_MAIN_STREET.connectPlaces("block 2", PUB, "main room");
        PUB.connectPlaces("main room", LOWER_MAIN_STREET, "block 2");

        LOWER_MAIN_STREET.connectPlaces("block 1", BILLS_HOUSE, "living room");
        BILLS_HOUSE.connectPlaces("living room", LOWER_MAIN_STREET, "block 1");

        PUB.connectPlaces("bar counter", ALLEY, "back alley");
        ALLEY.connectPlaces("back alley", PUB, "bar counter");

        ALLEY.connectPlaces("side alley", MAIN_STREET, "block 3");
        MAIN_STREET.connectPlaces("block 3", ALLEY, "side alley");

        MAIN_STREET.connectPlaces("block 3", MOTEL, "parking lot");
        MOTEL.connectPlaces("parking lot", MAIN_STREET, "block 3");

        MAIN_STREET.connectPlaces("block 4", POLICE_STATION, "entrance");
        POLICE_STATION.connectPlaces("entrance", MAIN_STREET, "block 4");

        // Setting ITEMS
        HOUSE.getRooms().get("kitchen").getItemsInRoom().add(combat.ItemsFactory.createPistolMunition());
        HOUSE.getRooms().get("kitchen").getItemsInRoom().add(KNIFE);
        HOUSE.getRooms().get("sleeping room").getItemsInRoom().add(PISTOL);
        HOUSE.getRooms().get("sleeping room").getItemsInRoom().add(combat.ItemsFactory.createGreenHerb());

        GARAGE.getRooms().get("garage driveway").getItemsInRoom().add(combat.ItemsFactory.createRedHerb());
        GARAGE.getRooms().get("garage inside").getItemsInRoom().add(combat.ItemsFactory.createPistolMunition());

        LOWER_MAIN_STREET.getRooms().get("block 2").getItemsInRoom().add(combat.ItemsFactory.createGreenHerb());

        PUB.getRooms().get("toilets").getItemsInRoom().add(combat.ItemsFactory.createGreenHerb());

        BILLS_HOUSE.getRooms().get("living room").getItemsInRoom().add(ALLEY_KEY);
        BILLS_HOUSE.getRooms().get("living room").getItemsInRoom().add(combat.ItemsFactory.createPistolMunition());
        BILLS_HOUSE.getRooms().get("kitchen").getItemsInRoom().add(combat.ItemsFactory.createGreenHerb());

        ALLEY.getRooms().get("back alley").getItemsInRoom().add(combat.ItemsFactory.createRedHerb());

        MAIN_STREET.getRooms().get("block 3").getItemsInRoom().add(combat.ItemsFactory.createPistolMunition());
        MAIN_STREET.getRooms().get("block 4").getItemsInRoom().add(combat.ItemsFactory.createPistolMunition());

        MOTEL.getRooms().get("room 1").getItemsInRoom().add(combat.ItemsFactory.createPistolMunition());
        MOTEL.getRooms().get("room 2").getItemsInRoom().add(combat.ItemsFactory.createGreenHerb());
        MOTEL.getRooms().get("room 2").getItemsInRoom().add(combat.ItemsFactory.createGreenHerb());
        MOTEL.getRooms().get("room 3").getItemsInRoom().add(SHOTGUN);
        MOTEL.getRooms().get("room 3").getItemsInRoom().add(combat.ItemsFactory.createShotgunMunition());
        MOTEL.getRooms().get("room 4").getItemsInRoom().add(combat.ItemsFactory.createRedHerb());

        POLICE_STATION.getRooms().get("entrance").getItemsInRoom().add(combat.ItemsFactory.createRedHerb());
        POLICE_STATION.getRooms().get("entrance").getItemsInRoom().add(combat.ItemsFactory.createRedHerb());
        POLICE_STATION.getRooms().get("office").getItemsInRoom().add(combat.ItemsFactory.createGreenHerb());
        POLICE_STATION.getRooms().get("office").getItemsInRoom().add(combat.ItemsFactory.createShotgunMunition());
        POLICE_STATION.getRooms().get("armory").getItemsInRoom().add(combat.ItemsFactory.createShotgunMunition());
        POLICE_STATION.getRooms().get("armory").getItemsInRoom().add(combat.ItemsFactory.createShotgunMunition());
        POLICE_STATION.getRooms().get("armory").getItemsInRoom().add(combat.ItemsFactory.createPistolMunition());
        POLICE_STATION.getRooms().get("armory").getItemsInRoom().add(combat.ItemsFactory.createPistolMunition());

        // Setting ENEMIES
        GARDEN.getRooms().get("front garden").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());
        GARDEN.getRooms().get("side garden").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());

        LOWER_MAIN_STREET.getRooms().get("block 2").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());

        PUB.getRooms().get("main room").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());
        PUB.getRooms().get("main room").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());
        PUB.getRooms().get("toilets").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());

        BILLS_HOUSE.getRooms().get("living room").getEnemiesInRoom().add(characters.EnemiesFactory.createPolice());

        ALLEY.getRooms().get("back alley").getEnemiesInRoom().add(characters.EnemiesFactory.createDog());
        ALLEY.getRooms().get("back alley").getEnemiesInRoom().add(characters.EnemiesFactory.createDog());
        ALLEY.getRooms().get("side alley").getEnemiesInRoom().add(characters.EnemiesFactory.createDog());
        ALLEY.getRooms().get("side alley").getEnemiesInRoom().add(characters.EnemiesFactory.createDog());
        ALLEY.getRooms().get("side alley").getEnemiesInRoom().add(characters.EnemiesFactory.createDog());

        MAIN_STREET.getRooms().get("block 3").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());
        MAIN_STREET.getRooms().get("block 3").getEnemiesInRoom().add(characters.EnemiesFactory.createDog());

        MAIN_STREET.getRooms().get("block 4").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());
        MAIN_STREET.getRooms().get("block 4").getEnemiesInRoom().add(characters.EnemiesFactory.createPolice());

        //Motel Parking lot
        MOTEL.getRooms().get("parking lot").getEnemiesInRoom().add(characters.EnemiesFactory.createPolice());
        MOTEL.getRooms().get("parking lot").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());
        MOTEL.getRooms().get("parking lot").getEnemiesInRoom().add(characters.EnemiesFactory.createDog());
        //Motel rooms
        MOTEL.getRooms().get("room 1").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());
        MOTEL.getRooms().get("room 1").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());
        MOTEL.getRooms().get("room 2").getEnemiesInRoom().add(characters.EnemiesFactory.createDog());
        MOTEL.getRooms().get("room 2").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());
        MOTEL.getRooms().get("room 3").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());
        MOTEL.getRooms().get("room 3").getEnemiesInRoom().add(characters.EnemiesFactory.createPolice());
        MOTEL.getRooms().get("room 4").getEnemiesInRoom().add(characters.EnemiesFactory.createPolice());
        MOTEL.getRooms().get("room 4").getEnemiesInRoom().add(characters.EnemiesFactory.createPolice());
        MOTEL.getRooms().get("room 4").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());


        POLICE_STATION.getRooms().get("entrance").getEnemiesInRoom().add(characters.EnemiesFactory.createPolice());
        POLICE_STATION.getRooms().get("entrance").getEnemiesInRoom().add(characters.EnemiesFactory.createPolice());
        POLICE_STATION.getRooms().get("office").getEnemiesInRoom().add(characters.EnemiesFactory.createPolice());
        POLICE_STATION.getRooms().get("office").getEnemiesInRoom().add(characters.EnemiesFactory.createZombie());
        //Final Fight
        POLICE_STATION.getRooms().get("roof").getEnemiesInRoom().add(EnemiesFactory.MUTATION);

        // Setting NPCs
        HOUSE.getRooms().get("living room").setNpc(TV);
        PUB.getRooms().get("bar counter").setNpc(BILL);
        BILLS_HOUSE.getRooms().get("bedroom").setNpc(COX);
        MOTEL.getRooms().get("reception").setNpc(CARRIE);
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
     * collect the list of connections leading from a specific room to other places and rooms.
     * @param roomName the name of the room in this place
     * @return a list of Connection objects
     */
    public List<Connection> getConnectionsFrom(String roomName) {
        return connectionsByRoom.getOrDefault(roomName, new ArrayList<>());
    }

    /**
     * Connect two Places, indicating from which room in this Place we can go,
     * and into which room in the destination Place.
     */
    public void connectPlaces(String fromRoomName, Places destination, String toRoomName) {
        Connection connection = new Connection(destination, toRoomName);
        connectionsByRoom
                .computeIfAbsent(fromRoomName, k -> new ArrayList<>())
                .add(connection);
    }

    /**
     * Adds a Room to a Place
     */
    public void addRoom(String name, String description) {
        rooms.put(name, new Room(name, description));
    }

    /**
     * represents a connection from a specific room in this place
     * to another Place (and a specific room in that other Place).
     */
    public static class Connection {
        private Places destinationPlace;
        private String destinationRoomName;

        public Connection(Places destinationPlace, String destinationRoomName) {
            this.destinationPlace = destinationPlace;
            this.destinationRoomName = destinationRoomName;
        }
        /**
         * @return the target place
         */
        public Places getDestinationPlace() {
            return destinationPlace;
        }

        /**
         * @return the name of the target room in the destination place
         */
        public String getDestinationRoomName() {
            return destinationRoomName;
        }
    }

    /**
     * A Room represents a subdivision of a Place where items, enemies, and NPCs can be added.
     */
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
