package characters;

public class EnemiesFactory {
        // Templates for enemies
        public static final Enemy.Zombie ZOMBIE_TEMPLATE = new Enemy.Zombie("Zombie", "It is another infected person, it moves slow", 80, 10);
        public static final Enemy.Zombie DOG_TEMPLATE = new Enemy.Zombie("Infected Dog", "It is an infected Dog! It moves quickly, I can´t run away from it!", 50, 5);
        public static final Enemy.Zombie POLICE_TEMPLATE = new Enemy.Zombie("Police Zombie", "The zombie has a light bulletproof vest, and a cop helmet, this zombie is more resistant!", 120, 10);
        public static final Enemy.Zombie MUTATION_TEMPLATE = new Enemy.Zombie("Mutation", "It is a mutated Zombie! It is faster and stronger! I have to be careful", 500, 20);

        // Factory methods to create new independent enemy instances
        public static Enemy.Zombie createZombie() {
                return new Enemy.Zombie(ZOMBIE_TEMPLATE.getName(), ZOMBIE_TEMPLATE.getDescription(), ZOMBIE_TEMPLATE.getHp(), ZOMBIE_TEMPLATE.getDamage());
        }

        public static Enemy.Zombie createDog() {
                return new Enemy.Zombie(DOG_TEMPLATE.getName(), DOG_TEMPLATE.getDescription(), DOG_TEMPLATE.getHp(), DOG_TEMPLATE.getDamage());
        }

        public static Enemy.Zombie createPolice() {
                return new Enemy.Zombie(POLICE_TEMPLATE.getName(), POLICE_TEMPLATE.getDescription(), POLICE_TEMPLATE.getHp(), POLICE_TEMPLATE.getDamage());
        }

        public static Enemy.Zombie createMutation() {
                return new Enemy.Zombie(MUTATION_TEMPLATE.getName(), MUTATION_TEMPLATE.getDescription(), MUTATION_TEMPLATE.getHp(), MUTATION_TEMPLATE.getDamage());
        }
}
