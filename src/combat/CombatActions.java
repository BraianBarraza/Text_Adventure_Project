package combat;

import java.util.Random;

import characters.Enemy.Zombie;
import characters.Player;

/**
 * Handles the logic for combat encounters, including attacks for both the player and enemies.
 */
public class CombatActions {

    /**
     * Makes a zombie attack the player, choosing either a bite (100% damage) or a claw (50% damage).
     * @param player the Player being attacked
     * @param zombie the attacking Zombie
     */
    public void zombieAttack(Player player, Zombie zombie) {
        if (player.getHp() > 0) {
            Random r = new Random();
            int attackType = r.nextInt(2); // 0: Bite, 1: Claw
            int damage;
            String attackName;
            if (attackType == 0) {
                damage = zombie.getDamage();
                attackName = "Bite";
            } else {
                damage = zombie.getDamage() / 2;
                attackName = "Claw";
            }
            int newHp = player.getHp() - damage;
            player.setHp(newHp);
            System.out.println();
            System.out.println(zombie.getName() + " uses " + attackName + " attack and deals "
                    + damage + " damage. Your HP: " + player.getHp());
            System.out.println();
        }
    }

    /**
     * Handles the player's attack on a single zombie using the chosen weapon.
     * Verifies if the weapon requires ammunition and consumes it if available.
     * @param player the Player who is attacking
     * @param zombie the Zombie being attacked
     * @param weapon the Weapon chosen for the attack
     */
    public void playerAttack(Player player, Zombie zombie, Weapon weapon) {
        if (zombie.getHp() <= 0) {
            System.out.println("That zombie is already dead.");
            return;
        }

        if (weapon.getName().equalsIgnoreCase("Pistol")) {
            String requiredAmmoName = ItemsFactory.createPistolMunition().getName();
            if (!player.hasAmmo(requiredAmmoName)) {
                System.out.println("No " + requiredAmmoName + " left. You cannot shoot.");
                return;
            }
            player.consumeAmmo(requiredAmmoName);
            applyRandomShot(zombie, weapon);
        } else if (weapon.getName().equalsIgnoreCase("Shotgun")) {
            String requiredAmmoName = ItemsFactory.createShotgunMunition().getName();
            if (!player.hasAmmo(requiredAmmoName)) {
                System.out.println("No " + requiredAmmoName + " left. You cannot shoot.");
                return;
            }
            player.consumeAmmo(requiredAmmoName);
            applyRandomShot(zombie, weapon);
        } else {
            int newHp = zombie.getHp() - weapon.getDamage();
            zombie.setHp(newHp);
            System.out.println("You hit the zombie with " + weapon.getName() + " for "
                    + weapon.getDamage() + " damage. Zombie HP: " + zombie.getHp());
            System.out.println();
        }
    }

    /**
     * Applies a random shot type when using a fire gub (headshot, chest shot, or leg shot) and calculates damage.
     * @param zombie the Zombie being shot
     * @param weapon the fire gun used
     */
    private void applyRandomShot(Zombie zombie, Weapon weapon) {
        Random r = new Random();
        int roll = r.nextInt(3); // 0: head, 1: chest, 2: legs
        int damage;
        String shotType;
        switch (roll) {
            case 0:
                damage = weapon.getDamage() * 2;
                shotType = "HEADSHOT!!!";
                break;
            case 1:
                damage = weapon.getDamage();
                shotType = "Chest shot!";
                break;
            default:
                damage = weapon.getDamage() / 2;
                shotType = "Leg shot";
                break;
        }
        int newHp = zombie.getHp() - damage;
        zombie.setHp(newHp);
        System.out.println();
        System.out.println(shotType + " with " + weapon.getName() + " for "
                + damage + " damage. Zombie HP: " + zombie.getHp());
    }
}
