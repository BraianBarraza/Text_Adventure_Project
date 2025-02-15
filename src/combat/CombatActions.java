package combat;

import java.util.Random;
import characters.Enemy.Zombie;
import characters.Player;

public class CombatActions {

    public void zombieAttack(Player player, Zombie zombie) {
        if (player.getHp() > 0) {
            int newHp = player.getHp() - zombie.getDamage();
            player.setHp(newHp);
            System.out.println(zombie.getName() + " attacks you. HP: " + player.getHp());
        }
    }

    public void playerAttack(Player player, Zombie zombie, Weapon weapon) {
        if (zombie.getHp() <= 0) {
            System.out.println("That zombie is already dead.");
            return;
        }

        if (weapon.getName().equalsIgnoreCase("Pistol")) {
            if (!player.hasAmmo("9mm")) {
                System.out.println("No 9mm ammo left. You cannot shoot.");
                return;
            }
            player.consumeAmmo("9mm");
            applyRandomShot(zombie, weapon);
        } else if (weapon.getName().equalsIgnoreCase("Shotgun")) {
            if (!player.hasAmmo("70mm shells")) {
                System.out.println("No 70mm shells left. You cannot shoot.");
                return;
            }
            player.consumeAmmo("70mm shells");
            applyRandomShot(zombie, weapon);
        } else {
            // Melee weapon or fists; just apply base damage
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
                damage = 100;
                shotType = "HEADSHOT!";
                break;
            case 1:
                damage = 70;
                shotType = "Chest shot";
                break;
            default:
                damage = 34;
                shotType = "Leg shot";
                break;
        }
        int newHp = zombie.getHp() - damage;
        zombie.setHp(newHp);
        System.out.println(shotType + " with " + weapon.getName() + " for "
                + damage + " damage. Zombie HP: " + zombie.getHp());
    }
}
