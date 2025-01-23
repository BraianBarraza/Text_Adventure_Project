package characters;

import combat.Item;

public class Npc {
    private String name;
    private String dialogue;
    private Item key;
    private boolean hasGivenKey;

    public Npc(String name, String dialogue, Item key) {
        this.name = name;
        this.dialogue = dialogue;
        this.key = key;
        this.hasGivenKey = false;
    }

    public String getName() {
        return name;
    }

    public String getDialogue() {
        return dialogue;
    }

    public Item getKey() {
        return key;
    }

    public boolean hasGivenKey() {
        return hasGivenKey;
    }

    public void giveKey() {
        this.hasGivenKey = true;
    }
}
