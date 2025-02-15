package characters;

public class EnemiesFactory {
        public static final Enemy.Zombie ZOMBIE = new Enemy.Zombie("Zombie", "It is another infected person, it moves slow", 100, 10);
        public static final Enemy.Zombie DOG = new Enemy.Zombie("Infected Dog", "It is an infected Dog! It moves quickly, I can´t run away from it!", 50, 5);
        public static final Enemy.Zombie POLICE = new Enemy.Zombie("Police Zombie", "The zombie has a bulletproof vest, I have to shoot to the head", 120, 10);
        public static final Enemy.Zombie MUTATION = new Enemy.Zombie("Mutation", "It is a mutated Zombie! It is faster and stronger! I have to be careful", 150, 20);
}
