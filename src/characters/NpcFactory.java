package characters;

import combat.Item;

import static combat.ItemsFactory.*;

public class NpcFactory {
    public static final Npc BILL = new Npc(
            "Bill Denbrough",
            """
                    Hello friend, the situation is pretty fucked up, one of those bastard bite me. I couldn´t get away
                    'cause the door to the *Alley* is locked. and I left the Alley Key at home
                    So go to *My House* and look for the *Alleys key* it should be in my sleeping room.
                    *Bill gave you his House Key*
                    
                    Oh! and by the way, you may find some help at my place. My brother Georgie was also here, I gave him the same instructions I just gave you, 
                    good thing he had his copy of the keys with him. 
                    *cough* *cough* *cough*.
                    it's always good to have the help of the assistant chief cop,
                    he's probably already found it... 
                    I'm very sleepy Larry and its pretty cold in here. 
                    Tell Georgie I love him.
                    """, BILLS_KEY
    );

    public static final Npc TV = new Npc(
            "the TV",
            """
                    News:
                    
                    it has been 15 minutes since the press conference with the president of the united states ended. this is what he said:\s
                    Barack Obama: “We don't know why, nor who caused this, however our scientists and our intelligence group are doing their best to gather as much information
                    as possible. I will now read the report that has been given to me by the Department of Science:
                    
                    1. The cause of the dead to move is a kind of cell reactivation in the primary motor cortex and basal ganglia,
                    fortunately if you can say so, the reactivation of the cerebellum is partial, therefore their movements are clumsy and lacking in precision.
                    
                    2. The limbic system also shows activity, especially the hypothalamus. However, this is not total, the specimens are significantly aggressive,
                    they have only retained their most basic instincts, especially eating. Likewise the primary auditory cortex shows not only activation but also a
                    significant evolution.
                    
                    The Broca and Wernicke areas, as well as the entire frontal lobe, are practically nonfunctional in most subjects. As a result, these subjects show
                    no capacity for speech or reasoning.
                    We believe the virus spreads through the bodily fluids of the infected, so keep as far away from them as possible. If you are attacked by one,
                    the only way to stop them is by destroying their brain. Do not attempt to reason with them.
                    
                    Finally, may God bless us.
                    ...
                    END OF THE TRANSMISSION.
                    
                    Ok, thanks for the info Obama ... another black guy who do a good Job...
                    
                    I should go for my old gun, I think I let it in the Sleeping room.
                    
                    Tutorial 2: Yo can press "1. Move" to move around the map, the map is conformed by "Places" and "Rooms" every one of these
                    has an own name, if you want to go to your living room just type "room living room", and if you want to go to another Place for example
                    the Garden just type "exit garden" try to check every place around you may find something useful.""", null


    );

    public static final Npc COX = new Npc(
            " Yaesu FT-857D", """
            fuck old bill has a Yaesu FT-857D radio!
            with this maybe he can establish communication with Washington!....
            *tuning emergency frequency...*
            Operator: we are in a state of emergency please tune your radio or television and stay indoors...
            Larry: This is Lieutenant Larry Underwood, identified by service number O-960319.\s
            """, null
    );

}
