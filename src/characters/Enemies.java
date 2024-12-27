package characters;

import java.util.ArrayList;
import java.util.List;

public class Enemies {

    public static List<Zombie> getZombies() {
        List<Zombie> zombies = new ArrayList<>();
        zombies.add(new Zombie("Zombie", "It is another infected person, it moves slow", 100, 10));
        zombies.add(new Zombie("Infected Dog", "It is an infected Dog! It moves quickly, I can´t run away from it!", 50, 5));
        zombies.add(new Zombie("Police Zombie", "The zombie has a bulletproof vest, I have to shoot to the head", 120, 10));
        zombies.add(new Zombie("Mutation", "It is a mutated Zombie! It is faster and stronger! I have to be careful", 150, 20));
        return zombies;
    }

    public static class Zombie {
        private String name;
        private String description;
        private int hp;
        private int damage;


        public Zombie(String name, String description, int hp, int damage) {
            this.name = name;
            this.description = description;
            this.hp = hp;
            this.damage = damage;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public int getHp() {
            return hp;
        }

        public void setHp(int hp) {
            this.hp = hp;
        }

        public int getDamage() {
            return damage;
        }
    }
}
