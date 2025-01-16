package combat;

public class Item {

    public static class HealingItem{

        private String name;
        private String description;
        private int healingPoints;


        public HealingItem(String description, int healingPoints, String name) {
            this.name = name;
            this.description = description;
            this.healingPoints = healingPoints;
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

        public int getHealingPoints() {
            return healingPoints;
        }

        public void setHealingPoints(int healingPoints) {
            this.healingPoints = healingPoints;
        }



    }

    public class Munition{
        private String name;
        private String description;
        private int quantity;

        public Munition(String name, String description, int quantity) {
            this.name = name;
            this.description = description;
            this.quantity = quantity;
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

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }



    }

/*
    public void Items{
        Munition pistolMunition = new Munition("9mm", "pistol munition", 10);
        Munition shotgunMunition = new Munition("shotgun shells", "Shotgun Munition, really powerful", 5);

        HealingItem greenHerb = new HealingItem("It can help you with the pain and the infection",25, "Green Herb");
        HealingItem redHerb = new HealingItem("Not as powerful as a green herb", 4, "Red Herb");
        HealingItem mixHerb = new HealingItem("Restore your full healt, it can be obtain mixing a red a green herb", 100, "Mixed Herb");

    }
*/



}
