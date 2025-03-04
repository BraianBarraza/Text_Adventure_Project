package characters;

public class Enemy {

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
