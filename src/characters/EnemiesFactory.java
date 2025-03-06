package characters;

public class EnemiesFactory {

    public static final Enemy.Zombie MUTATION = new Enemy.Zombie("Mutation", "It is a mutated Zombie! It is faster and stronger! I have to be careful", 700, 20);

    // Factory method for create new enemies in every place
    public static Enemy.Zombie createZombie() {
        return new Enemy.Zombie("Zombie", "It is another infected person, it moves slow", 80, 10);
    }

    public static Enemy.Zombie createDog() {
        return new Enemy.Zombie("Infected Dog", "It is an infected Dog! It moves quickly, I can´t run away from it!", 50, 5);
    }

    public static Enemy.Zombie createPolice() {
        return new Enemy.Zombie("Police Zombie", "The zombie has a light bulletproof vest, and a cop helmet, this zombie is more resistant!", 120, 15);
    }

}
