package combat;

import characters.Enemies.Zombie;
import characters.Inventory;
import characters.Player;

public class CombatActions {

    public void zombieAttack(Player player, Zombie zombie) {
        if (player.getHp() > 0) {
            int newHp = player.getHp() - zombie.getDamage();
            player.setHp(newHp);
            System.out.println("The " + zombie.getName() + " is attacking you! current HP: " + player.getHp());
        } else {
            System.out.println("You are dead!");
        }
    }

    public void playerAttack(Player player, Zombie zombie) {
        if (zombie.getHp() > 0) {
            int newHp = zombie.getHp() - player.currentDamage();
            zombie.setHp(newHp);
            System.out.println("You attack the zombie! The zombie still have HP: " + zombie.getHp());
        } else {
            System.out.println("You kill teh zombie!, that was close...");
        }
    }
}