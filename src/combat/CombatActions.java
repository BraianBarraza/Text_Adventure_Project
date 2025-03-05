package combat;

import java.util.Random;

import characters.Enemy.Zombie;
import characters.Player;

public class CombatActions {

    public void zombieAttack(Player player, Zombie zombie) {
        if (player.getHp() > 0) {
            Random r = new Random();
            int attackType = r.nextInt(2); // 0: Bite, 1: Claw
            int damage;
            String attackName;
            if (attackType == 0) {
                damage = zombie.getDamage(); // 100% of base damage
                attackName = "Bite";
            } else {
                damage = zombie.getDamage() / 2; // 50% of base damage
                attackName = "Claw";
            }
            int newHp = player.getHp() - damage;
            player.setHp(newHp);
            System.out.println(zombie.getName() + " uses " + attackName + " attack and deals "
                    + damage + " damage. Your HP: " + player.getHp());
        }
    }

    /**
     * Player attack: check if the zombie is alive, check the weapon to use, and if it needs ammo, and do the ammo math.
     *
     * @param player
     * @param zombie
     * @param weapon
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
            // Melee weapons or fists apply base damage directly
            int newHp = zombie.getHp() - weapon.getDamage();
            zombie.setHp(newHp);
            System.out.println("You hit the zombie with " + weapon.getName() + " for "
                    + weapon.getDamage() + " damage. Zombie HP: " + zombie.getHp());
        }
    }

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
                shotType = "Chest shot";
                break;
            default:
                damage = weapon.getDamage() / 2;
                shotType = "Leg shot";
                break;
        }
        int newHp = zombie.getHp() - damage;
        zombie.setHp(newHp);
        System.out.println(shotType + " with " + weapon.getName() + " for "
                + damage + " damage. Zombie HP: " + zombie.getHp());
    }
}
