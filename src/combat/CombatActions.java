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

    public void playerAttack(Player player, Zombie zombie) {
        if (zombie.getHp() <= 0) {
            System.out.println("That zombie is already dead.");
            return;
        }
        int baseDamage = player.currentDamage();
        if (player.getCurrentWeapon().getName().equalsIgnoreCase("Pistol")
                || player.getCurrentWeapon().getName().equalsIgnoreCase("Shotgun")) {
            Random r = new Random();
            int roll = r.nextInt(100);
            if (roll < 33) {
                int dmg = baseDamage + 70;
                zombie.setHp(zombie.getHp() - dmg);
                System.out.println("Headshot! +" + 70 + " damage. Zombie HP: " + zombie.getHp());
            } else if (roll < 66) {
                int dmg = baseDamage + 35;
                zombie.setHp(zombie.getHp() - dmg);
                System.out.println("Chest shot! +" + 35 + " damage. Zombie HP: " + zombie.getHp());
            } else {
                int dmg = baseDamage + 10;
                zombie.setHp(zombie.getHp() - dmg);
                System.out.println("Leg shot! +" + 10 + " damage. Zombie HP: " + zombie.getHp());
            }
        } else {
            int newHp = zombie.getHp() - baseDamage;
            zombie.setHp(newHp);
            System.out.println("You strike the zombie. Zombie HP: " + zombie.getHp());
        }
    }
}
