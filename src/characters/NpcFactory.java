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
                    
                    "it has been 15 minutes since the press conference with the president of the united states ended. this is what he said:\s
                    Barack Obama: “We don't know why, nor who caused this, however our scientists and our intelligence group are doing their best to gather as much information
                    as possible. I will now read the report that has been given to me by the Department of Science:
                    
                    1. The cause of the dead to move is a kind of cell reactivation in the primary motor cortex and basal ganglia,
                    fortunately if you can say so, the reactivation of the cerebellum is partial, therefore their movements are clumsy and lacking in precision.
                    
                    2. The limbic system also shows activity, especially the hypothalamus. However, this is not total, we think thats why the specimens are significantly aggressive,
                    cause they have only retained their most basic instincts, especially eating. Likewise the primary auditory cortex shows not only activation but also a
                    significant evolution so we gotta be quite, *they can hear us pretty good*.
                    
                    3. The Broca and Wernicke areas, as well as the entire frontal lobe, are practically nonfunctional in most subjects. As a result, these subjects show
                    no capacity for speech or reasoning.
                    We believe the virus spreads through the bodily fluids of the infected, so keep as far away from them as possible. If you are attacked by one,
                    the only way to stop them is by destroying their brain or to do a lot of damage to the extremities until they are unable to move.
                    
                    Do not attempt to reason with them they will direct attack you.
                    
                    Finally, may God bless us."
                    ...
                    
                    END OF THE TRANSMISSION.
                    
                    Larry:
                    
                    Ok, thanks for the info Obama ... another black guy who do a good Job... but why was Coronel Cox there with the president?
                    I hate to admit it but I wish I could be there, I could be useful again ... one last time.
                    
                    I should go for my old gun, I think I let it in the *Sleeping room*.
                    
                    Tutorial 2: Yo can press "1. Move" to move around the map, the map is conformed by "Places" and inside of them there are "Rooms" 
                    every one of these has an own name, if you want to go to your Sleeping room just type "room sleeping room"
                    and if you want to go to another Place for example the Garden just type "exit garden". 
                    (UPPERCASE, lowercase and CamelCase are accepted but false sp a ces, misspelling a wodr or us|ng some other symbols are not, so type correctly),
                    And try to check every place around you may find something useful.
                    """, null


    );

    public static final Npc COX = new Npc(
            "Yaesu FT-857D",
            """
            Holy fuck! The old Bill has a Yaesu FT-857D radio!
        
            I think I can use it to establish communication with Washington!...
        
            *tuning emergency frequency ...146.520 MHz...*
            *Static...*
            *Screams...*
        
            *tuning emergency frequency ...14.300 MHz...*
            *Static...*
        
            *Operator: ...in a state of emergency, please tune your radio or television to keep informed. Stay indoors.
            All our units are deployed right now, so wait until they arrive in your sector...*
        
            Larry: Emergency traffic, emergency traffic!
            This is retired soldier Larry Underwood. Do you copy?
        
            *Operator: I read you loud and clear, but right now every single grid in America is in immediate distress.*
        
            Larry: I just need a couple of minutes, soldier. I need to send some traffic to Colonel James O. Cox. Over.
        
            *Operator: I think you mean General O. Cox, and Mr. O. Cox is pretty busy right now, so Over and O—*
        
            Larry: Don't you dare sign off, son. This is retired First Lieutenant Larry Underwood, service number O-960319,
            former commander of the 3rd Platoon, Bravo Company, 1st Marine Division, in Operation Phantom Fury.
            I need to contact Colonel—General—James O. Cox now.
            Tell your superior that a White Torch veteran needs a favor. Over.
        
            ...waiting...
        
            ...waiting...
        
            *Operator: Sir, General Cox is standing by on frequency 17.700 MHz. We have established a stable connection. Over and out.*
        
            *Static...*
            *tuning emergency frequency ...17.700 MHz...*
        
            Larry: This is Larry...
        
            *Cox: Delta-Viking, this is Alpha-Raven. Welcome on board again. I just have to sign a couple of papers and you'll be back in.
            What's your grid? Over.*
        
            Larry: I don’t understand, sir. I just need som—
        
            *Cox: You need an extraction, and I need someone with tactical ground experience. Take it or leave it—I don't have much time.
            There are two others on the list.*
        
            Larry: Latitude 44.8023163, Longitude -68.7850466. Over.
        
            ...Waiting...
        
            *Cox: OK, Castle Rock. Nice place to heal after all you've seen. We found a suitable spot for extraction: the police station roof.
            I'm sending a UH-60 Black Hawk. It's almost 500 miles from D.C. to Castle Rock, so they'll have to make one fuel stop—
            that means it’ll take 4 to 5 hours to reach you. It's now 0030Q; ETA is 0530Q. That gives you enough time.
            Move your ass and ensure the extraction point is clear otherwise, the chopper won't land.
            And congratulations on the promotion, Colonel Underwood you are now on the staff of the American military forces as my assistant.*
        
            Larry: Roger that, sir. I'll be there...
        
            *Cox: One last thing, Larry—don't blame yourself for what those soldiers did to the locals. I saw the images.
            It's not your fault. Move forward now. Over and Out*
            """,
            null
    );

    public static final Npc CARRIE = new Npc(
            "Carrie White",
            """
            Carrie: Oh Larry! I am so glad to see you!
            Every thing went crazy so fast. I was at the Parking Lot cause 2 people were fighting,
            so I scream and call the Police, they came direct out the station, I was in front of the two people a woman and a man
            the man was a guest and didn´t recognize the woman, but I tried to help and everything went suddenly RED
            there was a lot of blood over me and over the floor, I could´t see and the Sheriff Johnny Smith, take me away and bring me to the Reception,
            he lock the door and went back to the parking lot.
            and then I saw it, the woman had bitten the guy and he took almost his whole neck away with one bite, then he bite
            the detective Ralph Anderson she took 2 fingers away with one bite and then bite the agent Holly Gibney direct in the face and eat her nose,
            there was blood all over the place and Johnny was just there doing nothing,
            and I understand it... the crazy woman, was Sarah his wife!. I was in shock and suddenly I just faint.
            
            I think that Jhonny took Sarah to the room number *3*, I saw the blood track he had a *Shotgun* so be careful
            
            oh and I found this in the floor
            
            *Carrie gave you the Police Station Key*
            
            I am so scare Larry...
            """,
            STATIONS_KEY
    );
}
