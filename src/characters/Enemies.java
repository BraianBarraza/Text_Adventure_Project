package characters;

public class Enemies {

    public static class zombiesCollection{

        Zombies basicZombie = new Zombies("Zombie", "It is another infected person, it moves slow", 100, 10);
        Zombies dogZombie = new Zombies("Infected Dog", "It is an infected Dog! It moves pretty quick I can´t run away from it!", 50, 5);
        Zombies policeZombie = new Zombies("Police Zombie", "The zombie has a bulletproof vest, I have to shot to the head", 120, 10);
        Zombies mutatedZombie = new Zombies("Mutation", "It is a mutated Zombie! it is faster and stronger! i have to be careful", 120, 20);

    }

    public static class Zombies {
        private String name;
        private String description;
        private int hp;
        private int damage;

        public Zombies(String name, String description, int hp, int damage) {
            this.name = name;
            this.description = description;
            this.hp = hp;
            this.damage = damage;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public void setDamage(int damage) {
            this.damage = damage;
        }
    }
}
