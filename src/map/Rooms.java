package map;

public class Rooms {
    private String livingRoom;
    private String sleepingRoom;
    private String kitchen;

    public Rooms(String livingRoom, String sleepingRoom, String kitchen) {
        this.livingRoom = livingRoom;
        this.sleepingRoom = sleepingRoom;
        this.kitchen = kitchen;
    }

    public void setLivingRoom(String livingRoom) {
        this.livingRoom = livingRoom;
    }

    public void setSleepingRoom(String sleepingRoom) {
        this.sleepingRoom = sleepingRoom;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

}
