package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import characters.Npc;
import characters.Enemy.Zombie;
import combat.Item;

public class Places {

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

    public static class Place {
        private String placeName;
        private String description;
        private String placeInformation;
        private Map<String, Place> exits;
        private Map<String, Room> rooms;
        private boolean locked;
        private String requiredKeyName;
        private Room currentRoom;

        public Place(String placeName, String description, String placeInformation) {
            this.placeName = placeName;
            this.description = description;
            this.placeInformation = placeInformation;
            this.exits = new HashMap<>();
            this.rooms = new HashMap<>();
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

        public Map<String, Place> getExits() {
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

        public void connectPlaces(String direction, Place destination, String roomName) {
            exits.put(direction, destination);
            rooms.put(direction, new Room(roomName, "Exit to " + destination.getPlaceName()));
        }

        public void addRoom(String name, String description) {
            rooms.put(name, new Room(name, description));
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
        Place house = new Place("House", "A small house with a garden exit", "Living room, kitchen, sleeping room");
        house.addRoom("living room", "A simple living room with a window");
        house.addRoom("kitchen", "A kitchen with a small table");
        house.addRoom("sleeping room", "A bedroom with a large bed");

        Place garden = new Place("Garden", "A quiet garden", "");
        Place street = new Place("Street", "A main street with smoke in the distance", "");
        Place billsHouse = new Place("Bill's House", "A locked house that belongs to Bill", "");
        billsHouse.setLocked(true);
        billsHouse.setRequiredKeyName("Key to Bill's House");
        billsHouse.addRoom("living room", "Bill's living room");
        billsHouse.addRoom("kitchen", "Bill's kitchen");
        billsHouse.addRoom("bedroom", "Bill's bedroom");

        Place pub = new Place("Pub", "A local pub that might have supplies", "");
        pub.addRoom("main room", "A large room with tables and chairs");
        pub.addRoom("bar counter", "A counter where the bartender stands");
        pub.addRoom("toilets", "Dimly lit toilets");

        Place alley = new Place("Alley", "A small alley leading to another street", "");
        alley.setLocked(true);
        alley.setRequiredKeyName("Key to Alley");
        alley.addRoom("alley entrance", "A narrow entrance");
        alley.addRoom("alley end", "A dead end with garbage");

        Place policeStation = new Place("Police Station", "A station that might have weapons", "");
        policeStation.setLocked(true);
        policeStation.setRequiredKeyName("Key to Police Station");
        policeStation.addRoom("entrance", "The station entrance");
        policeStation.addRoom("office", "An office with papers everywhere");
        policeStation.addRoom("armory", "An armory that might contain weapons");

        house.connectPlaces("garden", garden, "living room");
        garden.connectPlaces("house", house, "garden");
        garden.connectPlaces("street", street, "garden");
        street.connectPlaces("garden", garden, "street");
        street.connectPlaces("pub", pub, "street");
        pub.connectPlaces("street", street, "pub");
        street.connectPlaces("bills house", billsHouse, "street");
        billsHouse.connectPlaces("street", street, "bills house");
        pub.connectPlaces("bar alley", alley, "bar counter");
        alley.connectPlaces("pub", pub, "alley");
        alley.connectPlaces("police station", policeStation, "alley end");
        policeStation.connectPlaces("alley", alley, "police station");

        house.setCurrentRoom(house.getRooms().get("living room"));

        house.getRooms().get("kitchen").getItemsInRoom().add(new Item.Munition("9mm", "Pistol munition", 10));
        house.getRooms().get("sleeping room").getItemsInRoom().add(new Item.HealingItem("Small med", 20, "Green Herb"));

        pub.getRooms().get("bar counter").setNpc(new characters.Npc(
                "Bill",
                "Hello friend, the situation is pretty fucked up, one of those bastard bite me. I couldnt get away 'couse the *Alley* door is locked. I let the Key at home.",
                new Item.KeyItem("Key to Bill's House", "Opens Bill's House")
        ));

        billsHouse.getRooms().get("bedroom").getItemsInRoom().add(new Item.KeyItem("Key to Alley", "Opens the Alley"));
        policeStation.getRooms().get("entrance").getEnemiesInRoom().add(
                new characters.Enemy.Zombie("Cop Zombie", "A zombie in uniform", 100, 10)
        );
        policeStation.getRooms().get("office").getItemsInRoom().add(new Item.KeyItem("Key to Police Station", "I could maybe find something useful in the police station"));

        house.getRooms().get("living room").getEnemiesInRoom().add(
                new characters.Enemy.Zombie("Infected Man", "He tries to break the window!", 100, 10)
        );

        return house;
    }
}
