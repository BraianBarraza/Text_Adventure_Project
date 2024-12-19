package map;

import characters.Enemies;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Places {

        Place house = new Place("House", "You are in the House, you can go out to the garden",null);
        Place garden = new Place("Garden", "You are in the Garden, you can go to the House or to the Street", null);

        Place street = new Place("Street", "You are in the Street, you can go to the Garden, to the Pub or to Bills House", null);
        Place billHouse = new Place("Bill´s House", "You are at Bills House, you can go to Maple Street", null);
        Place pub = new Place("Bill Dembrow´s Pub", "You are in the Pub, You can go to the Maple Street or to the Alley ", null);
        Place alley = new Place("Bill Dembrow´s Pub", "You are in the Alley, You can go to the Maple Street or to the Bill Dembrow´s Pub ", null);


    public static class Place {

        private String name;
        private String description;
        private Map<String, Place> exit = new HashMap<>();

        /**
         *
         * @param name
         * @param description
         * @param exit
         */
        public Place(String name, String description,Map<String, Place> exit) {
            this.name = name;
            this.description = description;
            this.exit = (exit != null) ? exit : new HashMap<>();
        }

        public String getDescription(String description) {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, Place> getExit() {
            return exit;
        }

        public void setExit(Map<String, Place> exit) {
            this.exit = exit;
        }
    }

    public String readDescription(){
        Scanner newScanner = new Scanner(System.in);

        String placeDescription = newScanner.nextLine();
        if(placeDescription.equals("PD")){
            String actualPlaceDescription = "" ;
            System.out.println(actualPlaceDescription);
        }
        return "no description";
    }

}
