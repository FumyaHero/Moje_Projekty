package rpg_express_0.pkg2;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import static rpg_express_0.pkg2.Utility_Commands.A_ACTION;
import static rpg_express_0.pkg2.Utility_Commands.String_to_int;
import static rpg_express_0.pkg2.Utility_Commands.BOLB_APPEARANCE_ELEMENTS;
import static rpg_express_0.pkg2.Utility_Commands.B_PLACE;
import static rpg_express_0.pkg2.Utility_Commands.building_array;
import static rpg_express_0.pkg2.Utility_Commands.building_map;
import static rpg_express_0.pkg2.Utility_Commands.createNewBolb;
import static rpg_express_0.pkg2.Utility_Commands.createNewBuilding;
import static rpg_express_0.pkg2.Utility_Commands.createNewItem;
import static rpg_express_0.pkg2.Utility_Commands.createNewPlayer;
import static rpg_express_0.pkg2.Utility_Commands.createNewWarehouse;
import static rpg_express_0.pkg2.Utility_Commands.player_array;
import static rpg_express_0.pkg2.Utility_Commands.player_map;

public class Database_Commands 
{
    public Database_Commands(){}
    
    //ŒCIE¯KI DO STRUMIENI
    //strumienie do bazy graczy - pierwszy do wczytania bazy, drugi do wczytania/zapisania aktualnego stanu graczy
    final private String Str_BASE_Players = "..\\Databases\\rpge_BASE_Players.txt";
    final private String Str_SAVE_Players = "..\\Databases\\rpge_SAVE_Players.txt";
    //strumieñ do wczytania bazy itemków
    final private String Str_BASE_Items = "..\\Databases\\rpge_BASE_Items.txt";
    //strumienie do bazy budynków - pierwszy do wczytania bazy, drugi do wczytania/zapisania aktualnego stanu budynków
    final private String Str_BASE_Buildings = "..\\Databases\\rpge_BASE_Buildings.txt";
    final private String Str_SAVE_Buildings = "..\\Databases\\rpge_SAVE_Buildings.txt";
    
    
    //==============================================================================================================================================================   
    //==============================================================================================================================================================   
    //==============================================================================================================================================================   
    
    //----------------------------------------------------------------------------------------------------------------------------- wczytanie plików i ustawienie strumieniów dla bazy danych
    //----------------------------------------------------------------------- wczytanie podstawowych danych i wbicie ich do programu za pomoc¹ simple factories
    public boolean Create_Bases()
    {
        RandomAccessFile Write_Player_Base = null;
        RandomAccessFile Write_Item_Base = null;
        RandomAccessFile Write_Building_Base = null;
        //dodanie reszty zapisów baz póŸniej
        
        //wyzerowanie wskaŸnika strumieniów z bazowymi danymi do zapisania
        try
        {
            Write_Player_Base = new RandomAccessFile(this.Str_BASE_Players, "rw");
            Write_Player_Base.seek(0);
            Write_Item_Base = new RandomAccessFile(this.Str_BASE_Items, "rw");
            Write_Item_Base.seek(0);
            Write_Building_Base = new RandomAccessFile(this.Str_BASE_Buildings, "rw");
            Write_Building_Base.seek(0);
        }
        catch(Exception e)
        {
            System.err.println("ERROR: Cannot set the pointer at the beggining of the document(s)! (Database_Commands.Create_Bases.seek(0) )");
            return false;
           
        }
        
        //jeœli siê uda³o, zapisuje bazowe dane
        //=====================================================================================================================================================
        //=====================================================================================================================================================
        //================================================================================== PLAYERS
        //=====================================================================================================================================================
        //=====================================================================================================================================================
        
        try
        {
            //================================================== AZGORANS
            Write_Player_Base.writeUTF("e1_01!Fumya?Azgoran&"); 
            
            //================================================== DUARGIAN
            Write_Player_Base.writeUTF("e2_01!Maxioldo?Duargian&"); 
            
            
            //================================================== JESSARI
            Write_Player_Base.writeUTF("e3_01!Orc?Jessari&"); 
            
            
            //================================================== NYRION
            Write_Player_Base.writeUTF("e4_01!Spring?Nyrion&"); 
            
            
            Write_Player_Base.close();
        }
        catch(Exception e)
        {
            System.err.println("ERROR: Something went wrong with writing down (Or closing) BASE_Players file! (Database_Commands.Create_Bases.WriteUTF)");
            return false;
        }
        
        //=====================================================================================================================================================
        //=====================================================================================================================================================
        //================================================================================== ITEMS
        //=====================================================================================================================================================
        //=====================================================================================================================================================
        try
        {
            
            /*
            BASE:
            item_id[item_name]item_class^description*recipe*
            */
            
            //====================================================================================================================================================================
            //============================================================================================================================================ NEUTRAL
            //====================================================================================================================================================================
            
            Write_Item_Base.writeUTF("i0_001[Simple Stone]stone^Just a stone, there's nothing unusual about it"
                    + "*"
                        + "66!23!5!10?5!5!6!6!6!6@"     //moonshore
                        + "56!20!2!5?15!15!11!8!8!8@"   //lemon beach
                        + "52!18!5!9?14!9!9!7!5!2@"     //arcane forest
                        + "62!22!3!7?15!13!12!12!10!9@" //dark forest
                        + "~"                           //sorri's swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i0_002[Duargian Wood]wood^Few pieces of Duargian Wood. Some people say it's Duargian's Best Friend, since they make almost everything out of it!"
                    + "*"
                        + "~"                 // Moonshore
                        + "71!25!6!9?13!14!14!14!14!15@"        // Lemon Beach
                        + "~"                // Arcane Forest
                        + "82!29!2!6?6!7!7!8!8!9@"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
                       
            Write_Item_Base.writeUTF("i0_003[Simple Sand]craft^A little bit of a sand. Azgoran kids like to make mud pies from it."
                    + "*"                             
                        + "85!30!5!9?15!15!14!14!14!14@"                 // Moonshore
                        + "81!28!3!10?8!8!8!8!9!7@"        // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"               // Sorri's Swamps
                    + "*");              
                        
            Write_Item_Base.writeUTF("i0_004[Feather]craft^A feather. Looks like it used to belong to some Azgoran. Before it fell off, of course."
                    + "*"
                        + "90!31!2!4?6!7!7!7!8!10@"                 // Moonshore
                        + "83!29!3!5?4!4!5!6!4!4@"        // Lemon Beach
                        + "74!26!2!4?7!7!6!7!7!7@"                // Arcane Forest
                        + "86!30!3!8?13!13!11!11!11!11@"                // Dark Forest
                        + "86!30!1!4?3!3!4!5!7!8!"              // Sorri's Swamps
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_005[Fishbone]craft^Small, sharp bone. The previous owner must have been a bit unlucky."
                    + "*"     
                        + "88!31!2!5?10!9!10!10!11!12@"                 // Moonshore
                        + "83!29!3!4?5!5!6!6!6!5@"        // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_006[Fishscale]bolb_food^Pretty, scale lost by Elder Tun fish. You can see rainbow when the sun shines on it."
                    + "*"
                        + "126!44!1!2?2!4!3!3!4!4@"                 // Moonshore
                        + "115!40!1!2?2!3!3!3!3!4@"        // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_007[Seaweed]craft^desc"
                    + "*"
                        + "124!43!1!4?5!5!5!5!5!0@"                 // Moonshore
                        + "112!39!2!4?6!6!6!7!7!5@"        // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_008[Fish Fin]bolb_food^Small, fresh fish fin. Bolbs love sushi, but they're not allowed to eat too much of it!"
                    + "*"
                                      + "126!44!1!2?2!4!3!3!4!4@"                 // Moonshore
                                      + "115!40!1!2?2!3!3!3!3!3@"        // Lemon Beach
                                      + "~"                // Arcane Forest
                                      + "~"                // Dark Forest
                                      + "~"              // Sorri's Swamps
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_009[Bat Wing]bolb_food^Some people think that they can turn their bolbs into Batman this way."
                    + "*"
                        + "~"                 // Moonshore
                        + "115!40!1!2?2!2!3!3!2!4@"        // Lemon Beach
                        + "~"                // Arcane Forest
                        + "126!44!1!3?2!2!3!3!3!5@"                // Dark Forest
                        + "119!42!1!3?2!3!3!3!3!3&"              // Sorri's Swamps
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_010[Longrass]craft^Small bunch of long grass. Great for crafting."
                    + "*"               
                        + "~"                 // Moonshore
                        + "~"        // Lemon Beach
                        + "74!26!3!6?6!7!8!5!6!6@"                // Arcane Forest
                        + "88!31!2!7?10!12!12!12!12!12@"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_011[Black Leaf]craft^Leaf taken by Corruption. Nyrions like to craft traps with it."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "86!30!2!4?14!14!14!14!13!13@"                // Dark Forest
                        + "86!30!2!6?4!6!6!7!7!7!"              // Sorri's Swamps
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_012[Lizard Claw]craft^Spooky lizard claw. It's small, but really tough."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "91!32!1!4?4!5!5!5!5!5@"                // Dark Forest
                        + "85!30!1!3?5!5!5!5!5!5!"              // Sorri's Swamps
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_013[Lizard Paw]bolb_food^Small, scaled paw. Someone took the \"Break a leg\" too seriously."
                    + "*"
                      + "~"                 // Moonshore
                      + "~"                  // Lemon Beach
                      + "104!36!1!3?2!2!3!2!3!3@"                // Arcane Forest
                      + "126!44!1!3?2!2!3!3!4!4@"                // Dark Forest
                      + "119!42!1!3?2!2!3!3!3!3!"              // Sorri's Swamps
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_014[Lizard Tail]bolb_food^A scaled tail. Looks like someone was attempting to escape."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "126!44!1!2?2!2!3!4!4!4@"                // Dark Forest
                        + "119!42!1!2?2!2!2!3!3!3!"              // Sorri's Swamps
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_015[Bolb Egg]craft^An egg. Looks like it's ready to hatch any minute now!"
                    + "*" 
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "76!27!1!4?1!1!1!1!1!2@"                // Arcane Forest
                        + "93!33!1!4?1!1!1!1!3!3@"                // Dark Forest
                        + "87!30!1!5?1!1!1!1!3!3!"             // Sorri's Swamps
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_016[Bolb Silk]craft^The softest silk in the entire Garanta island. Use it with caution."
                    + "*"                    
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_017[Bolb Fur]craft^Perfect decoration to armours and presents!"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_018[Bolb Scales]craft^Don't mind the fishy smell, those scales belonged to Bolb for real."
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_019[Bolb Powder]craft^Some colorful powder. No, you're NOT allowed to sniff it!"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_020[Medium Quality Torch]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_021[Low Quality Brick]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_022[Medium Quality Brick]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_023[High Quality Brick]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_024[Low Quality Plank]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_025[Medium Quality Plank]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_026[High Quality Plank]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_027[Low Quality Fabric]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_028[Medium Quality Fabric]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_029[Low Quality Torch]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_030[Decorative Spikes]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_031[High Quality Torch]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_032[Low Quality Rune]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_033[Medium Quality Rune]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_034[High Quality Rune]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_035[Mana Torch]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_036[Glass]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_037[Low Quality Rope]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_038[Medium Quality Rope]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_039[High Quality Rope]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_040[Decorative Gems]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_041[High Quality Fabric]craft^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_042[Rainbow Dust]bolb_food^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_043[Small Bolb Nest]consumable^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
            Write_Item_Base.writeUTF("i0_044[Big Mana Crystal]mana^desc"
                    + "*"
                    + "-"
                    + "*"); 
                        
             //====================================================================================================================================================================
            //============================================================================================================================================ MOONSHORE -> 1
            //====================================================================================================================================================================
            
            Write_Item_Base.writeUTF("i1_001[Moonstone]gem^A beautiful gem with white and grey spots on it. There's a legend that says it glows during the fullmoon."
                    + "*"
                        + "116!41!2!3?4!5!5!5!5!5@"      // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i1_002[Milk Stone]stone^It's just a white stone, don't try to milk it."
                    + "*"
                        + "58!20!5!9?26!20!20!20!20!19@"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"             // Sorri's Swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i1_003[Crystal Sand]craft^Some people think that this is not a sand, but rather snow, due to its bright color."
                    + "*"
                        + "80!28!4!11?24!24!24!24!20!18@"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i1_004[White Dust]bolb_food^Messy Bolb food. What can be funnier than white powder?"
                    + "*" 
                        + "127!44!1!2?1!2!3!3!3!3@"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
            
            //====================================================================================================================================================================
            //============================================================================================================================================ LEMON BEACH -> 2
            //====================================================================================================================================================================
            
            Write_Item_Base.writeUTF("i2_001[Letone]gem^This gem turns orange if you leave it in sunny place for too long."
                    + "*"
                        + "~"                // Moonshore
                        + "106!37!2!3?4!3!3!4!4!4@"        // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i2_002[Sour Stone]stone^Yellowish stone. Tastes like lemon."
                    + "*" 
                        + "~"                 // Moonshore
                        + "54!19!5!12?20!18!18!17!19!19@"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"             // Sorri's Swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i2_003[Golden Sand]craft^Looks like Simple Sand, but it's different - it's golden."
                    + "*"                    
                        + "~"                 // Moonshore
                        + "76!27!4!8?16!16!16!16!16!17@"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*");
            
            Write_Item_Base.writeUTF("i2_004[Yellow Dust]bolb_food^Dusty Bolb food that tastes like lemon powder."
                    + "*" 
                        + "~"                 // Moonshore
                        + "116!41!1!2?1!1!1!2!2!2@"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i2_005[Oyster]bolb_food^An oyster - seafood at its finest."
                    + "*"
                        + "~"                 // Moonshore
                        + "115!40!1!3?2!2!3!3!3!3@"                  // Lemon Beach
                        + "0!~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
            
            //====================================================================================================================================================================
            //============================================================================================================================================ ARCANE FOREST -> 3
            //====================================================================================================================================================================
            
            Write_Item_Base.writeUTF("i3_001[Small Mana Crystal]mana^Piece of crystalized mana. Small. But works."
                    + "*"
                        + "~"               // Moonshore
                        + "~"                // Lemon Beach
                        + "69!24!3!7?16!16!16!16!12!11@"        // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps    
                    + "*"); 
            
            Write_Item_Base.writeUTF("i3_002[Arcane Wood]wood^Blueish wood. Smells like mana."
                    + "*"
                        + "~"               // Moonshore
                        + "~"                // Lemon Beach
                        + "61!21!5!9?18!17!16!13!13!13@"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps 
                    + "*"); 
            
            Write_Item_Base.writeUTF("i3_003[Skyheart]gem^Heart of the sky enclosed in the gem."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "95!33!2!3?4!3!3!3!3!5@"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i3_004[Medium Mana Crystal]mana^Bigger piece of crystalized mana. It can power up things for more than 1 hour."
                    + "*" 
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "74!26!1!5?5!6!6!6!8!8@"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i3_005[Runic Stone]craft^Stone filled with magic. It's ready to be engraved with runes."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "55!19!1!6?3!3!5!4!6!7@"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i3_006[Mana Leaf]craft^Big, glowing leaf. Juicy!"
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "73!26!4!7?8!11!11!12!12!12@"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i3_007[Blue Dust]bolb_food^After eating this kind of dust, Bolbs are more likely to blend with water."
                    + "*" 
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "104!36!1!3?1!1!2!2!2!2@"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i3_008[Mana Fruit]bolb_food^Edible piece of mana. Tastes like blueberries with mint."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "104!36!2!6?2!2!2!2!2!2@"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i3_009[Green Dust]bolb_food^Green powder, tasting like grass."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "104!36!1!2?1!1!1!1!2!2@"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i3_010[Bunny Paw]bolb_food^Fluffy paw. Feeling lucky?"
                    + "*" 
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "104!36!1!3?2!2!2!2!3!3@"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*"); 
            
            Write_Item_Base.writeUTF("i3_011[Mossy Stone]stone^Looks like Simple Stone, but with moss. Simple Stone's older brother."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "53!19!4!7?10!12!9!17!15!15@"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "~"             // Sorri's Swamps
                    + "*"); 
                        
            //====================================================================================================================================================================
            //============================================================================================================================================ DARK FOREST -> 4
            //====================================================================================================================================================================
            
            
            Write_Item_Base.writeUTF("i4_001[Crystalized Devil]gem^One of the most valuable and dangerous gems. You DO NOT WANT to stay close to the Corrupted Fields with that gem in your pocket, trust me."
                    + "*"
                        + "~"               // Moonshore
                        + "~"                // Lemon Beach
                        + "~"                // Arcane Forest
                        + "116!41!2!3?4!4!4!3!3!3@"        // Dark Forest
                        + "~"              // Sorri's Swamps   
                    + "*");
            
            Write_Item_Base.writeUTF("i4_002[Dark Wood]wood^Wood perfectly blending with the darkness. Watch out where you step!"
                    + "*"               
                        + "~"               // Moonshore
                        + "~"                // Lemon Beach
                        + "~"                // Arcane Forest
                        + "73!26!3!6?25!22!22!20!2!18@"       // Dark Forest
                        + "~"              // Sorri's Swamps  
                    + "*");
            
            Write_Item_Base.writeUTF("i4_003[Translucent Berry]bolb_food^This berry is nothing but Bolb's aphrodisiac."
                    + "*" 
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "127!44!1!4?1!2!2!2!2!2@"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*");
            
            Write_Item_Base.writeUTF("i4_004[Black Dust]bolb_food^Dust dark as night. Emo Bolbs' favourite."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "127!44!1!3?1!1!1!2!2!2@"                // Dark Forest
                        + "~"              // Sorri's Swamps
                    + "*");
            
            //====================================================================================================================================================================
            //============================================================================================================================================ SORRI'S SWAMPS -> 5
            //====================================================================================================================================================================
                        
            Write_Item_Base.writeUTF("i5_001[Pale Wood]wood^Whiteish wood. Shame it can't sunbathe."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "67!23!3!8?26!24!23!20!17!15&"    // Sorri's Swamps  
                    + "*");
            
            Write_Item_Base.writeUTF("i5_002[Bleen]gem^Crimson gem. Some people say it's bleeding during the Blood Moon."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "109!38!2!4?4!4!4!4!4!5&"              // Sorri's Swamps
                    + "*");
            
            Write_Item_Base.writeUTF("i5_003[Obsidian Stone]stone^A dark blue stone. No, you don't need diamond pickaxe to mine it."
                    + "*" 
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "58!20!3!7?14!14!12!11!11!11&"              // Sorri's Swamps
                    + "*");
                        
            Write_Item_Base.writeUTF("i5_004[Red Dust]bolb_food^There's nothing \"redder\" than the red itself."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "119!42!1!3?1!1!1!2!2!2&"              // Sorri's Swamps
                    + "*");
            
            Write_Item_Base.writeUTF("i5_005[Dark Sand]craft^This sand changed its color due to Corruption. Not because it likes dark humour."
                    + "*" 
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "79!28!5!7?16!14!15!14!12!12&"              // Sorri's Swamps
                    + "*");
            
            Write_Item_Base.writeUTF("i5_006[Crimson Spike]bolb_food^Red, pointy thing. It can be turned into pretty dangerous weapon."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "85!30!3!5?6!6!6!8!8!9&"              // Sorri's Swamps
                    + "*");
                        
            Write_Item_Base.writeUTF("i5_007[Blue Peach]bolb_food^Bolb's favourite. Do not mistake with Princess Peach."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "87!30!1!3?2!3!3!3!3!3&"              // Sorri's Swamps
                    + "*");
            
            Write_Item_Base.writeUTF("i5_008[Glowing  Darkleaf]craft^Long, glowing leaf. It can be a great nightlamp."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "84!29!3!5?8!8!8!8!8!7&"              // Sorri's Swamps
                    + "*");
            
            Write_Item_Base.writeUTF("i5_009[Blue Peach Leaf]bolb_food^What's better than Blue Peach? Blue Peach Leaf, of course! ..It sounded funnier in my head."
                    + "*"
                        + "~"                 // Moonshore
                        + "~"                  // Lemon Beach
                        + "~"                // Arcane Forest
                        + "~"                // Dark Forest
                        + "117!41!3!4?4!4!4!3!4!4!"              // Sorri's Swamps
                    + "*");
                        
            
            
            
            
            Write_Item_Base.close();
        }
        catch(Exception e)
        {
            System.err.println("ERROR: Something went wrong with writing down (Or closing) BASE_Items file! (Database_Commands.Create_Bases.WriteUTF)");
            return false;
        }
        
        //=====================================================================================================================================================
        //=====================================================================================================================================================
        //================================================================================== BUILDINGS
        //=====================================================================================================================================================
        //=====================================================================================================================================================
        try
        {
            /*
            BUILDING
                ID[ Class] Race& hp_multi! HP_base? wood_base# stone_base@* upgrade_recipes*
                
            WAREHOUSE
                ID[ Class] Race& hp_multi! HP_base? wood_base# stone_base@ cap_multi$ capacity% * upgrade_recipes*
            
            */
            //------------------------------------------------------------------------- AZGORAN BUILDINGS
            Write_Building_Base.writeUTF("bA_01[1]Azgoran"      //CASTLE
                + "&"
                    + "2.8!"
                    + "1500?"
                    + "2500#"
                    + "5000@"
                + "*"
                    + "i0_021!i0_027!i0_035!i0_036?150!50!30!50!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bA_02[2]Azgoran" //HOUSES
                + "&"
                    + "2.55!"
                    + "200?"
                    + "200#"
                    + "400@"
                + "*"
                    + "i0_021!i0_035!i0_036?70!20!30!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bA_03[3]Azgoran" // WAREHOUSE
                + "&"
                    + "2.75!"
                    + "800?"
                    + "1500#"
                    + "2500@"
                    + "2.35$"
                    + "3000%"
                + "*"
                    + "i0_021!i0_024!i0_035!?450!250!50!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bA_04[4]Azgoran"  //BLACKSMITH
                + "&"
                    + "2.6!"
                    + "350?"
                    + "150#"
                    + "550@"
                + "*"
                    + "i0_021!i0_032!i0_035?80!15!35!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bA_05[5]Azgoran"  //BARRACKS
                + "&"
                    + "2.6!"
                    + "500?"
                    + "1000#"
                    + "1500@"
                + "*"
                    + "i0_021!i0_035!i0_030!i0_036!i0_027?350!20!10!20!30!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bA_06[6]Azgoran"  //MARKETPLACE
                + "&"
                    + "2.3!"
                    + "300?"
                    + "500#"
                    + "150@"
                + "*"
                    + "i0_024!i0_027?250!300!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
            
            //------------------------------------------------------------------------- DUARGIAN BUILDINGS
            Write_Building_Base.writeUTF("bD_01[1]Duargian"      //CASTLE
                + "&"
                    + "2.65!"
                    + "1500?"
                    + "4000#"
                    + "3500@"
                + "*"
                    + "i0_024!i0_029!i0_036?200!30!30!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bD_02[2]Duargian" //HOUSES
                + "&"
                    + "2.5!"
                    + "180?"
                    + "450#"
                    + "100@"
                + "*"
                    + "i0_024!i0_029!i0_036?90!10!10!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bD_03[3]Duargian" // WAREHOUSE
                + "&"
                    + "2.8!"
                    + "800?"
                    + "2600#"
                    + "1500@"
                    + "2.35$"
                    + "3000%"
                + "*"
                    + "i0_021!i0_024!i0_029?200!500!70!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bD_04[4]Duargian"  //BLACKSMITH
                + "&"
                    + "2.65!"
                    + "350?"
                    + "100#"
                    + "600@"
                + "*"
                    + "i0_024!i0_032!i0_029?90!30!15!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bD_05[5]Duargian"  //BARRACKS
                + "&"
                    + "2.75!"
                    + "600?"
                    + "2000#"
                    + "1000@"
                + "*"
                    + "i0_024!i0_029!i0_030!i0_036!i0_027?250!25!50!10!20!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bD_06[6]Duargian"  //MARKETPLACE
                + "&"
                    + "2.4!"
                    + "200?"
                    + "400#"
                    + "50@"
                + "*"
                    + "i0_024!i0_027?300!150!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
            
            //------------------------------------------------------------------------- JESSARI BUILDINGS
            Write_Building_Base.writeUTF("bJ_01[1]Jessari"      //CASTLE
                + "&"
                    + "2.55!"
                    + "1500?"
                    + "5500#"
                    + "2000@"
                + "*"
                    + "i0_024!i0_037!i0_035!i0_036?150!50!30!40!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bJ_02[2]Jessari" //HOUSES
                + "&"
                    + "2.8!"
                    + "180?"
                    + "400#"
                    + "100@"
                + "*"
                    + "i0_024!i0_035!i0_036?80!20!20!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bJ_03[3]Jessari" // WAREHOUSE
                + "&"
                    + "2.6!"
                    + "770?"
                    + "2000#"
                    + "750@"
                    + "2.4$"
                    + "2750%"
                + "*"
                    + "i0_024!i0_037!i0_035?600!100!60!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bJ_04[4]Jessari"  //BLACKSMITH
                + "&"
                    + "2.4!"
                    + "340?"
                    + "200#"
                    + "350@"
                + "*"
                    + "i0_024!i0_032!i0_035?40!50!15!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bJ_05[5]Jessari"  //BARRACKS
                + "&"
                    + "2.75!"
                    + "625?"
                    + "2125#"
                    + "1250@"
                + "*"
                    + "i0_024!i0_035!i0_040!i0_036!i0_027?200!30!20!10!40!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bJ_06[6]Jessari"  //MARKETPLACE
                + "&"
                    + "2.1!"
                    + "230?"
                    + "450#"
                    + "50@"
                + "*"
                    + "i0_024!i0_027?250!250!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
            
            //------------------------------------------------------------------------- NYRION BUILDINGS
            Write_Building_Base.writeUTF("bN_01[1]Nyrion"      //CASTLE
                + "&"
                    + "2.75!"
                    + "1500?"
                    + "4000#"
                    + "3500@"
                + "*"
                    + "i0_021!i0_024!i0_037!i0_029!i0_036?200!100!50!70!20!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bN_02[2]Nyrion" //HOUSES
                + "&"
                    + "2.5!"
                    + "170?"
                    + "300#"
                    + "100@"
                + "*"
                    + "i0_021!i0_029!i0_036?60!40!10!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bN_03[3]Nyrion" // WAREHOUSE
                + "&"
                    + "2.6!"
                    + "725?"
                    + "1000#"
                    + "1500@"
                    + "2.15$"
                    + "3200%"
                + "*"
                    + "i0_021!i0_024!i0_029?300!300!100!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bN_04[4]Nyrion"  //BLACKSMITH
                + "&"
                    + "2.35!"
                    + "400?"
                    + "200#"
                    + "650@"
                + "*"
                    + "i0_021!i0_032!i0_029?65!25!30!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bN_05[5]Nyrion"  //BARRACKS
                + "&"
                    + "2.8!"
                    + "625?"
                    + "1250#"
                    + "2125@"
                + "*"
                    + "i0_021!i0_029!i0_030!i0_036!i0_027?200!40!50!10!10!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bN_06[6]Nyrion"  //MARKETPLACE
                + "&"
                    + "2.0!"
                    + "260?"
                    + "150#"
                    + "400@"
                + "*"
                    + "i0_021!i0_027?400!50!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
                        
            Write_Building_Base.writeUTF("bN_07[7]Nyrion"  //MARKETPLACE
                + "&"
                    + "2.4!"
                    + "1000?"
                    + "1500#"
                    + "3000@"
                + "*"
                    + "i0_021!i0_037!i0_032?400!200!150!" //upgrade lv 1
                    + "&" 
                    + "" //upgrade lv 2
                    + "&" 
                    + "" //upgrade lv 3
                    + "&"
                + "*");
            
            Write_Building_Base.close();
        }
        catch(Exception e)
        {
            System.err.println("ERROR: Something went wrong with writing down (Or closing) BASE_Buildings file! (Database_Commands.Create_Bases.WriteUTF)");
            return false;
        }
        
        //jeœil wszystko siê uda³o, zwraca true
        return true;
    }
    //----------------------------------------------------------------------- wczytanie podstawowych danych i wbicie ich do programu za pomoc¹ simple factories
    public boolean Load_Bases()
    {
        
        RandomAccessFile BASE_Players = null;
        RandomAccessFile BASE_Items = null;
        RandomAccessFile BASE_Buildings =  null;
        //wyzerowanie wskaŸnika strumieniów z bazowymi danymi
        try
        {
            BASE_Players = new RandomAccessFile(this.Str_BASE_Players, "rw");
            BASE_Players.seek(0);
            BASE_Items = new RandomAccessFile(this.Str_BASE_Items, "rw");
            BASE_Items.seek(0);
            BASE_Buildings = new RandomAccessFile(this.Str_BASE_Buildings, "rw");
            BASE_Buildings.seek(0);
            
        }
        catch(Exception e)
        {
            System.err.println("ERROR: Cannot set the pointer at the beggining of the document(s). Database_Commands.Load_Bases.BASE.seek(0) )");
            return false;
        }
        
        //---------------------------------------------------------------------------------------------------------------
        //============================================================================= PLAYERS
        //---------------------------------------------------------------------------------------------------------------
        try//sprawdzanie, czy odczyt siê powiód³, a jeœli nie, to dlaczego
        {
            String line = "";
            String temp = "";
            
            String PT_ID = "";
            String PT_Name = "";
            String PT_Race = "";
            
            //jeœli dobrze wczyta³o bazê playerów:
            while(1==1) //ma czytaæ ca³y czas i wychodzi z pliku dopiero wtedy, kiedy wyskoczy wyj¹tek EOF
            {
                line = BASE_Players.readUTF();
                for(int i=0; i<line.length();i++)//!! readbyte nie dzia³a
                {
                    switch(line.charAt(i)) 
                    {
                        case '!': //wstawianie ID
                        {
                            PT_ID = temp;
                            temp = "";
                            break;
                        }

                        case '?': // wstawianie Name
                        {
                            PT_Name = temp;
                            temp = "";
                            break;
                        }

                        case '&':
                        {
                            PT_Race = temp;
                            temp = "";
                            break;
                        }

                        default:
                        {
                            temp += line.charAt(i);
                            break;
                        }
                    }
                }
                //Po wczytaniu podstawowych danych, tworzy gracza - gracz automatycznie sam siê wstawia do arraya i mapy
                createNewPlayer(PT_ID, PT_Name, PT_Race);
            }
        }
        catch(EOFException e){}

        catch(IOException e)
        {
            System.err.println("ERROR: IO Exception --- BASE_Players file! (Database_Commands.Load_Bases.BASE_Players)");
            return false;
        }

        catch(Exception e)
        {
            System.err.println("ERROR: Something's wrong!! (Database_Commands.Load_Bases.BASE_Players)");
            return false;
        }
        //---------------------------------------------------------------------------------------------------------------
        //============================================================================= ITEMS
        //---------------------------------------------------------------------------------------------------------------
        try//sprawdzanie, czy odczyt siê powiód³, a jeœli nie, to dlaczego
        {
            String line = "";
            String temp = "";
            boolean mode = false;
            
            String IT_ID = "";
            String IT_Name = "";
            String IT_Class = "";
            String IT_Desc = "";
            String IT_Tab_Chances = "";
            
            
            //jeœli dobrze wczyta³o bazê playerów:
            while(1==1) //ma czytaæ ca³y czas i wychodzi z pliku dopiero wtedy, kiedy wyskoczy wyj¹tek EOF
            {
                line = BASE_Items.readUTF();
                for(int i=0; i<line.length();i++)//!! readbyte nie dzia³a
                {
                    if(mode == false) //jeœli mode jest false, to wczytuje id, nazwê, klasê i opis, a potem przechodzi do mode = true
                    {
                        switch(line.charAt(i)) 
                        {
                            case '[': //wstawianie ID
                            {
                                IT_ID = temp;
                                temp = "";
                                break;
                            }

                            case ']': // wstawianie Name
                            {
                                IT_Name = temp;
                                temp = "";
                                break;
                            }

                            case '^':
                            {
                                IT_Class = temp;
                                temp = "";
                                break;
                            }

                            case '*':
                            {
                                IT_Desc = temp;
                                temp = "";
                                mode = true; //w³¹cza tryb wczytywania item_chance
                                break;
                            }

                            default:
                            {
                                temp += line.charAt(i);
                                break;
                            }
                        }
                    }
                    else//jeœli mode jest true, to wczytuje liczby
                    {
                        switch(line.charAt(i)) 
                        {
                            case '*'://dopóki nie znajdzie koñca item_chances
                            {
                                IT_Tab_Chances = temp;
                                temp = "";
                                mode = false; //w³¹cza tryb wczytywania item_chance
                                break;
                            }

                            default:
                            {
                                temp += line.charAt(i);
                                break;
                            }
                        }
                    }
                }
                //Po wczytaniu podstawowych danych, tworzy item - item automatycznie sam siê wstawia do arraya i mapy
                createNewItem(IT_ID, IT_Name, IT_Class, IT_Desc, IT_Tab_Chances);
            }
        }
        catch(EOFException e){}

        catch(IOException e)
        {
            System.err.println("ERROR: IO Exception --- BASE_Items file! (Database_Commands.Load_Bases.BASE_Items)");
            return false;
        }

        catch(Exception e)
        {
            System.err.println("ERROR: Something's wrong!! (Database_Commands.Load_Bases.BASE_Items)");
            return false;
        }
        
        
        //---------------------------------------------------------------------------------------------------------------
        //============================================================================= BUILDINGS
        //---------------------------------------------------------------------------------------------------------------
        try//sprawdzanie, czy odczyt siê powiód³, a jeœli nie, to dlaczego
        {
            String line = "";
            String temp = "";
            boolean mode = false;
            
            String BUIL_ID = "";
            int BUIL_Class = -1;
            String BUIL_Race = "";
            double BUIL_hp_multi = -1;
            int BUIL_HP_base = -1;
            int BUIL_wood_base = -1;
            int BUIL_stone_base = -1;
            double BUIL_cap_multi = -1; //WAREHOUSE ONLY!
            int BUIL_capacity = -1; //WAREHOUSE ONLY!
            String BUIL_upgrade_recipes = "";
            //jeœli dobrze wczyta³o bazê:
            while(1==1) //ma czytaæ ca³y czas i wychodzi z pliku dopiero wtedy, kiedy wyskoczy wyj¹tek EOF
            {
                line = BASE_Buildings.readUTF();
                for(int i=0; i<line.length();i++)//!! readbyte nie dzia³a
                {
                    if(mode == false) //jeœli mode jest false, to wczytuje id, klasê, rasê, hp_multi, HP_base, wood_base i stone_base, SPRAWDZA, CZY TO WAREHOUSE, a potem przechodzi do mode = true
                    {
                        switch(line.charAt(i)) 
                        {
                            case '[': //wstawianie ID
                            {
                                BUIL_ID = temp;
                                temp = "";
                                break;
                            }

                            case ']': // wstawianie Class
                            {
                                BUIL_Class = String_to_int(temp);
                                temp = "";
                                break;
                            }

                            case '&': // wstawianie Race
                            {
                                BUIL_Race = temp;
                                temp = "";
                                break;
                            }
                            
                            case '!': // wstawianie hp_multi
                            {
                                BUIL_hp_multi = Double.valueOf(temp);
                                temp = "";
                                break;
                            }
                            
                            case '?': // wstawianie hp_base
                            {
                                BUIL_HP_base = String_to_int(temp);
                                temp = "";
                                break;
                            }
                            
                            case '#': // wstawianie wood_base
                            {
                                BUIL_wood_base = String_to_int(temp);
                                temp = "";
                                break;
                            }
                            
                            case '@': // wstawianie stone_base
                            {
                                BUIL_stone_base = String_to_int(temp);
                                temp = "";
                                break;
                            }
                            
                            case '$': // wstawianie cap_multi //WAREHOUSE ONLY!
                            {
                                BUIL_cap_multi = Double.valueOf(temp);
                                temp = "";
                                break;
                            }
                            
                            case '%': // wstawianie capacity //WAREHOUSE ONLY!
                            {
                                BUIL_capacity = String_to_int(temp);
                                temp = "";
                                break;
                            }
                            
                            case '*':
                            {
                                temp = "";
                                mode = true; //w³¹cza tryb wczytywania upgrade_recipes
                                break;
                            }

                            default:
                            {
                                temp += line.charAt(i);
                                break;
                            }
                        }
                    }
                    else//jeœli mode jest true, to wczytuje upgrade_recipes
                    {
                        switch(line.charAt(i)) 
                        {
                            case '*'://dopóki nie znajdzie koñca upgrade_recipes
                            {
                                BUIL_upgrade_recipes = temp;
                                temp = "";
                                mode = false; //wy³¹cza tryb wczytywania upgrade_recipes
                                break;
                            }

                            default:
                            {
                                temp += line.charAt(i);
                                break;
                            }
                        }
                    }
                }
                //Po wczytaniu podstawowych danych, tworzy bolba - bolb automatycznie sam siê wstawia do arraya
                if(BUIL_Class != 3) //jeœli obiekt nie jest Warehouse
                    createNewBuilding(BUIL_ID, BUIL_Class, BUIL_Race, BUIL_hp_multi, BUIL_HP_base, BUIL_wood_base, BUIL_stone_base, BUIL_upgrade_recipes);
                else //jeœli obiekt jest Warehouse
                    createNewWarehouse(BUIL_ID, BUIL_Class, BUIL_Race, BUIL_hp_multi, BUIL_HP_base, BUIL_wood_base, BUIL_stone_base, BUIL_cap_multi, BUIL_capacity, BUIL_upgrade_recipes);
            }
        }
        catch(EOFException e){}

        catch(IOException e)
        {
            System.err.println("ERROR: IO Exception --- BASE_Buildings file! (Database_Commands.Load_Bases.BASE_Buildings)");
            return false;
        }

        catch(Exception e)
        {
            System.err.println("ERROR: Something's wrong!! (Database_Commands.Load_Bases.BASE_Buildings)");
            return false;
        }
        
        
        //-------------------------------------- zamykanie baz
        try
        {
            BASE_Players.close();
            BASE_Items.close();
            BASE_Buildings.close();
        }
        catch(IOException e)
        {
            System.err.println("ERROR: Something's wrong with closing BASE_* file(s)! (Database_Commands.Load_Bases.BASE_*.close() )");
            return false;
        }
        
        //jeœli wszystko dobrze posz³o
        return true;
    }
    //---------------------------------------------------- zapisanie danych graczy i ich bolbów
    public boolean Save_Players()
    {
        
        RandomAccessFile SAVE_Players = null;
        //wyzerowanie wskaŸnika strumieniów z bazowymi danymi
        try
        {
            SAVE_Players = new RandomAccessFile(this.Str_SAVE_Players, "rw");
            SAVE_Players.seek(0);
            
        }
        catch(Exception e)
        {
            System.err.println("ERROR: Cannot set the pointer at the beggining of the document(s). Database_Commands.Save_Players.SAVE.seek(0) )");
            return false;
        }
        //---------------------------------------------------------------------------------------------------------------
        //============================================================================= PLAYERS
        //---------------------------------------------------------------------------------------------------------------
        /*
            SZABLON ZAPISU:
            player_id?quest!tokens!influence_points!crafting_pts0!crafting_pts1!*GATHER_ACTIONS*INVENTORY[BOLBLIST]
            ] ---> koniec trybu bolba oraz koniec wczytywania gracza
        
            SZABLON ZAPISU GATHER_ACTIONS:
                0/0!0/0!0/0!0/0!0/0!@
                / ---> zapis poziomu,  ! ---> zapis xp,  @ ---> przejœcie do nastêpnej akcji
                
            SZABLON ZAPISU INVENTORY:
                * jeœli jest pusty, to piszemy po prostu     -
                * jesli nie jest pusty:
                    item_id!amount?
                    ! ---> zapis id itemu,  ? ---> zapis iloœci itemu oraz wstawienie go do ekwipunku gracza
        
            SZABLON ZAPISU BOLBLIST:
                * jeœli jest pusty, to piszemy po prostu     -
                * jesli nie jest pusty:
                    owner_id!name?gender{tab_app_0!tab_app_1!tab_app_2!tab_app_3!tab_app_4!tab_app_5!tab_app_6!}age#breeding_cd$hunger%brushing&@
                    @ ---> przejœcie do nastêpnego bolba
        */
        try//sprawdzanie, czy zapis siê powiód³, a jeœli nie, to dlaczego
        {
            String temp = "";
            
            
            for(int i = 0; i < player_array.size(); i++) //Ma zapisywaæ graczy do pliku SAVE tak d³ugo, a¿ nie skoñczy siê lista
            {
                String gather_actions = "";
                String inventory = "";
                String bolblist = "";
                
                temp = player_array.get(i).entity_id 
                        + "?"
                    + String.valueOf(player_array.get(i).entity_quest)
                        + "!"
                    + String.valueOf(player_array.get(i).entity_tokens)
                        + "!"
                    + String.valueOf(player_array.get(i).entity_influencePoints)
                        + "!"
                    + String.valueOf(player_array.get(i).craftingPoints[0])
                        + "!"
                    + String.valueOf(player_array.get(i).craftingPoints[1])
                        + "!"
                        + "*"; //GATHER ACTIONS_MODE
                            for(int A = 0; A < A_ACTION; A++)
                            {
                                for(int B = 1; B < B_PLACE; B++)
                                {
                                    for(int C = 0; C < 2; C++)
                                    {
                                        gather_actions += String.valueOf(player_array.get(i).entity_gather_actions_Lv_Tab[A][B][C]);
                                        if(C == 0)
                                            gather_actions += "/"; //zapis poziomu
                                        else
                                            gather_actions += "!"; // zapis xp
                                    }
                                }
                                gather_actions += "@"; //przejœcie do nastêpnej akcji
                            }
                temp += gather_actions 
                        + "*"; //INVENTORY MODE
                            //sprawdzenie, czy gracz ma pusty ekwipunek:
                            if(player_array.get(i).entity_inventory.size() == 0)
                                inventory = "~";
                            else //jeœli nie ma:
                            {
                                for(int z = 0; z < player_array.get(i).entity_inventory.size(); z++)
                               inventory += player_array.get(i).entity_inventory.get(z);   
                            }
                temp += inventory
                        + "["; //BOLBLIST MODE
                            //sprawdzenie, czy gracz ma jakieœ bolby
                            if(player_array.get(i).entity_bolblist.size() == 0)
                                bolblist = "~";
                            else //jeœli nie ma:
                            {
                                for(int z = 0; z < player_array.get(i).entity_bolblist.size(); z++)
                                {
                                    bolblist += player_array.get(i).entity_bolblist.get(z).bolb_name
                                            + "?"
                                        + String.valueOf(player_array.get(i).entity_bolblist.get(z).bolb_gender)
                                            + "["; //BOLB_APPEARANCE_TAB MODE
                                            for(int x = 0; x < BOLB_APPEARANCE_ELEMENTS; x++)
                                            {
                                                bolblist += String.valueOf(player_array.get(i).entity_bolblist.get(z).bolb_appearance[x])
                                                    + "!"; 
                                            }
                                    bolblist += String.valueOf(player_array.get(i).entity_bolblist.get(z).bolb_age)
                                            + "#"
                                        + String.valueOf(player_array.get(i).entity_bolblist.get(z).bolb_breeding_cd)
                                            + "$"
                                        + String.valueOf(player_array.get(i).entity_bolblist.get(z).bolb_brushing)
                                            + "&"
                                            + "@"; // przejœcie do nastêpnego bolba
                                }
                            }
                temp += bolblist; // + "]"; to na kiedyœ, jakbym coœ jeszcze chcia³a dodaæ do save'a
                
                //po utworzeniu ca³ego zapisu dla jednego gracza zostaje on wprowadzony do plik
                SAVE_Players.writeUTF(temp);
            }
        }
        catch(EOFException e){}

        catch(IOException e)
        {
            System.err.println("ERROR: IO Exception --- SAVE_Players file! (Database_Commands.Save_Players)");
            return false;
        }

        catch(Exception e)
        {
            System.err.println("ERROR: Something's wrong!! (Database_Commands.Save_Players)");
            return false;
        }
        
        
        //-------------------------------------- zamykanie baz
        try
        {
            SAVE_Players.close();
        }
        catch(IOException e)
        {
            System.err.println("ERROR: Something's wrong with closing SAVE_Players file! (Database_Commands.Save_Players.close() )");
            return false;
        }
        
        //jeœli wszystko dobrze posz³o
        return true;
    }    
    //---------------------------------------------------- wczytanie danych graczy i zaktualizowanie ich oraz u¿ycie simple factories(dla bolbów)
    public boolean Load_Players()
    {
        
        RandomAccessFile BASE_Players = null;
        //wyzerowanie wskaŸnika strumieniów z bazowymi danymi
        try
        {
            BASE_Players = new RandomAccessFile(this.Str_SAVE_Players, "rw");
            BASE_Players.seek(0);
            
        }
        catch(Exception e)
        {
            System.err.println("ERROR: Cannot set the pointer at the beggining of the document(s). Database_Commands.Load_Players.seek(0) )");
            return false;
        }
        
        //---------------------------------------------------------------------------------------------------------------
        //============================================================================= PLAYERS
        //---------------------------------------------------------------------------------------------------------------
        try//sprawdzanie, czy odczyt siê powiód³, a jeœli nie, to dlaczego
        {
            String line = "";
            String temp = "";
            
            //..napisaæ kod, który wczyta aktualne dane gracza i je zaktualizuje
            String id = "";
            String item_id = "";
            
            int mode = 0;
            int inmode = 0;
            int A = 0; //akcje
            int B = 1; //miejsca
            int C = 0; //lvl/xp
            
            String name = "";
            boolean gender = false;
            int [] tab = new int[BOLB_APPEARANCE_ELEMENTS];
            int z = 0;
            int age = -1;
            int hunger = -1;
            int breeding_cd = -1;
            boolean brushing = false;
            
            //jeœli dobrze wczyta³o bazê playerów:
            while(1==1) //ma czytaæ ca³y czas i wychodzi z pliku dopiero wtedy, kiedy wyskoczy wyj¹tek EOF
            {
                line = BASE_Players.readUTF();
                id = "";
                mode = 0;
                inmode = 0;
                
                for(int i=0; i<line.length();i++)//!! readbyte nie dzia³a
                {
                    if(mode == 0)//wczytywanie podstawowych statystyk/stanu postaci
                    {
                        if(line.charAt(i) == '?') //wczytywanie id gracza
                        {
                            id = temp;
                            temp = "";
                        }
                        else if(line.charAt(i) == '*')
                        {
                            temp = "";
                            mode++; //przejœcie do trybu wczytywania gather_actions
                            inmode = 0;
                        }
                        else if(line.charAt(i) == '!')
                        {
                            switch (inmode) 
                            {
                                case 0://wczytywanie stanu Questa
                                {
                                    player_map.get(id).entity_quest = String_to_int(temp);
                                    inmode++; //przejœcie do nastêpnego trybu
                                    temp = "";
                                    break;
                                }
                                    
                                case 1://wczytywanie iloœci tokenów
                                {
                                    player_map.get(id).entity_tokens = String_to_int(temp);
                                    inmode++; // przejœcie do nastêpnego trybu
                                    temp = "";
                                    break;
                                }
                                                                
                                case 2://wczytywanie influence points
                                {
                                    player_map.get(id).entity_influencePoints = String_to_int(temp);
                                    inmode++; // przejœcie do nastêpnego trybu
                                    temp = "";
                                    break;
                                }
                                
                                case 3://wczytywanie poziomu craftingu
                                {
                                    player_map.get(id).craftingPoints[0] = String_to_int(temp);
                                    inmode++; // przejœcie do nastêpnego trybu
                                    temp = "";
                                    break;
                                }

                                case 4://wczytywanie xp craftingu
                                {
                                    player_map.get(id).craftingPoints[1] = String_to_int(temp);
                                    inmode++; // przejœcie do nastêpnego trybu
                                    temp = "";
                                    break;
                                }
                                
                                default:
                                {
                                    System.err.println("ERROR: unknown case for switch(mode), where mode == 0! (Database_Commands.Load_Players)");
                                    break;
                                }
                            }
                        }
                        else //doklejanie znaków
                            temp += line.charAt(i);
                    }
                    else if(mode == 1)//wczytywanie gather_actions lv/xp
                    {
                        if(line.charAt(i) == '/')//zapis poziomu
                        {
                            player_map.get(id).entity_gather_actions_Lv_Tab[A][B][C] = String_to_int(temp);
                            C++; //przejœcie do wpisania xp
                            temp = "";
                        }
                        else if(line.charAt(i) == '!')
                        {
                            player_map.get(id).entity_gather_actions_Lv_Tab[A][B][C] = String_to_int(temp);
                            C--; //przejœcie do wpisania lv
                            B++; //przejœcie do nastêpnej lokacji
                            temp = "";
                        }
                        else if(line.charAt(i) == '@')
                        {
                            A++; //przejœcie do nastêpnej akcji
                            B = 1; // ustawianie pierwszej lokacji dla kolejnej akcji
                            C = 0; //przejœcie do wpisania lv
                            temp = "";
                        }
                        else if(line.charAt(i) == '*')
                        {
                            mode++; //przejœcie do trybu wpisywania inventory
                            A = 0; //zerowanie
                            B = 1;
                            C = 0;
                            temp = "";
                        }
                        else
                            temp += line.charAt(i);
                    }
                    else if(mode == 2)//tryb wczytywania inventory
                    {
                        if(line.charAt(i) == '~')
                        {
                            mode++; //przechodzi do kolejnego trybu, bo ekwipunek gracza jest pusty
                            i++; //skippuje znak specjalny dla wczytywania bolbów
                        }
                        else if(line.charAt(i) == '!')//wczytywanie id itemu
                        {
                            item_id = temp;
                            temp = "";
                        }
                        else if(line.charAt(i) == '?')//wczytuje iloœæ itemu i wstawia do go ekwipunku gracza
                        {
                            int amount = String_to_int(temp);
                            player_map.get(id).addItem(item_id, amount );
                            temp = "";
                        }
                        else if(line.charAt(i) == '[')//przejœcie do trybu wczytywania bolbów
                        {
                            mode++;
                            temp = "";
                        }
                        else
                            temp += line.charAt(i); 
                    }
                    else if(mode == 3)//tryb wczytywania bolbów
                    {
                        if(line.charAt(i) == '~')
                            break; //nie ma bolbów, wychodzi z pêtli?
                        else if(line.charAt(i) == '?')//wczytuje nazwê bolba
                        {
                            name = temp;
                            temp = "";
                        }
                        else if(line.charAt(i) == '[')//wczytuje p³eæ bolba
                        {
                            gender = Boolean.valueOf(temp);
                            temp = "";
                        }
                        
                        else if(line.charAt(i) == '!')//wczytuje dane wygl¹du bolba
                        {
                            tab[z] = String_to_int(temp);
                            z++;
                            temp = "";
                        }
                        
                        else if(line.charAt(i) == '#')//wczytuje wiek bolba
                        {
                            age = String_to_int(temp);
                            temp = "";
                        }
                        
                        else if(line.charAt(i) == '$')//wczytuje breeding_cd
                        {
                            breeding_cd = String_to_int(temp);
                            temp = "";
                        }
                        
                        else if(line.charAt(i) == '%')//wczytuje hunger
                        {
                            hunger = String_to_int(temp);
                            temp = "";
                        }
                        
                        else if(line.charAt(i) == '&')//wczytuje brushing
                        {
                            brushing = Boolean.valueOf(temp);
                            temp = "";
                        }
                        
                        else if(line.charAt(i) == '@')//tworzy bolba i daje go graczowi
                        {//createNewBolb(String owner_id, String name, boolean gender, int[] tab_app) 
                            createNewBolb(id, name, gender, tab);//tworzy bolba
                            //aktualizuje bolba
                            int nr_bolb = player_map.get(id).entity_bolblist.size() -1;
                            player_map.get(id).entity_bolblist.get(nr_bolb).setAge(age);
                            player_map.get(id).entity_bolblist.get(nr_bolb).setBreedingcd(breeding_cd);
                            player_map.get(id).entity_bolblist.get(nr_bolb).setHunger(hunger);
                            player_map.get(id).entity_bolblist.get(nr_bolb).setBrushing(brushing);
                            
                            //zeruje wszystko dla nastêpnego bolba
                            name = "";
                            gender = false;
                            for(int g = 0; g < tab.length; g++)
                                tab[g] = 0;
                            z = 0;
                            age = -1;
                            hunger = -1;
                            breeding_cd = -1;
                            brushing = false;
                        }
                        
                        else
                            temp += line.charAt(i); 
                    }
                }
            }
        }
        catch(EOFException e){}

        catch(IOException e)
        {
            System.err.println("ERROR: IO Exception --- BASE_Players file! (Database_Commands.Load_Players)");
            return false;
        }

        catch(Exception e)
        {
            System.err.println("ERROR: Something's wrong!! (Database_Commands.Load_Players)");
            return false;
        }
        
        //-------------------------------------- zamykanie baz
        try
        {
            BASE_Players.close();
        }
        catch(IOException e)
        {
            System.err.println("ERROR: Something's wrong with closing SAVE_Players file! (Database_Commands.Load_Players.close() )");
            return false;
        }
        
        //jeœli wszystko dobrze posz³o
        return true;
    }
    //---------------------------------------------------- zapisanie danych budynków
    public boolean Save_Buildings()
    {
        
        RandomAccessFile SAVE_Buildings = null;
        //wyzerowanie wskaŸnika strumieniów z bazowymi danymi
        try
        {
            SAVE_Buildings = new RandomAccessFile(this.Str_SAVE_Buildings, "rw");
            SAVE_Buildings.seek(0);
            
        }
        catch(Exception e)
        {
            System.err.println("ERROR: Cannot set the pointer at the beggining of the document(s). Database_Commands.Save_Buildings.SAVE.seek(0) )");
            return false;
        }
        //---------------------------------------------------------------------------------------------------------------
        //============================================================================= BUILDINGS
        //---------------------------------------------------------------------------------------------------------------
        /*
            SZABLON ZAPISU:
            buillding_id?building_lv!building_actual_hp*INVENTORY
                
            SZABLON ZAPISU INVENTORY:
                * tylko dla warehouse! jeœli jest pusty to nic nie piszemy
                * jesli nie jest pusty:
                    item_id!amount?
                    ! ---> zapis id itemu,  ? ---> zapis iloœci itemu oraz wstawienie go do inventory budynku
        */
        try//sprawdzanie, czy zapis siê powiód³, a jeœli nie, to dlaczego
        {
            String temp = "";            
            
            for(int i = 0; i < building_array.size(); i++) //Ma zapisywaæ budynki do pliku SAVE tak d³ugo, a¿ nie skoñczy siê lista
            {
                String inventory = "";
                
                temp = building_array.get(i).building_id
                        + "?"
                    + String.valueOf(building_array.get(i).building_Lv)
                        + "!"
                    + String.valueOf(building_array.get(i).building_hp[1])
                        + "*"; //INVENTORY MODE
                            //sprawdzenie, czy budynek jest warehousem
                            if(building_array.get(i).building_class == 3)
                            {
                                if( ((Warehouse)building_array.get(i)).warehouse_inventory.size() != 0) // sprawdza, czy inventory nie jest pusty
                                {
                                    for(int z = 0; z < ((Warehouse)building_array.get(i)).warehouse_inventory.size(); z++)
                                        inventory += ((Warehouse)building_array.get(i)).warehouse_inventory.get(z);   
                                }
                            }
                temp += inventory;
                //po utworzeniu ca³ego zapisu dla jednego budynku zostaje on wprowadzony do pliku
                SAVE_Buildings.writeUTF(temp);
            }
        }
        catch(EOFException e){}

        catch(IOException e)
        {
            System.err.println("ERROR: IO Exception --- SAVE_Buildings file! (Database_Commands.Save_Buildings)");
            return false;
        }

        catch(Exception e)
        {
            System.err.println("ERROR: Something's wrong!! (Database_Commands.Save_Buildings)");
            return false;
        }
        
        
        //-------------------------------------- zamykanie baz
        try
        {
            SAVE_Buildings.close();
        }
        catch(IOException e)
        {
            System.err.println("ERROR: Something's wrong with closing SAVE_Buildings file! (Database_Commands.Save_Buildings.close() )");
            return false;
        }
        
        //jeœli wszystko dobrze posz³o
        return true;
    }    
    //---------------------------------------------------- wczytanie danych budynków i zaktualizowanie ich
    public boolean Load_Buildings()
    {
        
        RandomAccessFile BASE_Buildings = null;
        //wyzerowanie wskaŸnika strumieniów z bazowymi danymi
        try
        {
            BASE_Buildings = new RandomAccessFile(this.Str_SAVE_Buildings, "rw");
            BASE_Buildings.seek(0);
            
        }
        catch(Exception e)
        {
            System.err.println("ERROR: Cannot set the pointer at the beggining of the document(s). Database_Commands.Load_Buildings.seek(0) )");
            return false;
        }
        
        //---------------------------------------------------------------------------------------------------------------
        //============================================================================= PLAYERS
        //---------------------------------------------------------------------------------------------------------------
        try//sprawdzanie, czy odczyt siê powiód³, a jeœli nie, to dlaczego
        {
            String line = "";
            String temp = "";
            
            String id = "";
            String item_id = "";
            boolean item_mode = false;
            
            //jeœli dobrze wczyta³o bazê budynków:
            while(1==1) //ma czytaæ ca³y czas i wychodzi z pliku dopiero wtedy, kiedy wyskoczy wyj¹tek EOF
            {
                line = BASE_Buildings.readUTF();
                id = "";
                item_mode = false;
                
                for(int i=0; i<line.length();i++)//!! readbyte nie dzia³a
                {
                    if(item_mode == false)//jeœli tryb wczytywania itemków jest false
                    {
                        if(line.charAt(i) == '?') //wczytywanie id budynku
                        {
                            id = temp;
                            temp = "";
                        }
                        else if(line.charAt(i) == '!') //wczytywanie aktualnego poziomu budynku
                        {
                            for(int x = 0; x < String_to_int(temp); x++) //jeœli budynek ma poziom x, to komputer lvl-upuje budynek x razy, do osi¹gniêcia x poziomu
                                building_map.get(id).LvUp();
                            temp = "";
                        }
                        else if(line.charAt(i) == '*') //wczytywanie aktualnej iloœci HP budynku
                        {
                            building_map.get(id).setHP(String_to_int(temp));
                            temp = "";
                            item_mode = true; //w³¹cza tryb wczytywania itemków
                        }
                        else
                            temp += line.charAt(i);
                    }
                    else //jeœli tryb wczytywania itemków jest true
                    {
                        //jeœli budynek to warehouse i inventory nie jest pusty
                        if(building_map.get(id).building_class == 3)
                        {
                            if(line.charAt(i) == '!')//wczytywanie id itemu
                            {
                                item_id = temp;
                                temp = "";
                            }
                            else if(line.charAt(i) == '?')//wczytuje iloœæ itemu i wstawia do go ekwipunku warehouse'a
                            {
                                int amount = String_to_int(temp);
                                ((Warehouse)building_map.get(id)).add_item(item_id, amount );
                                temp = "";
                            }
                            else
                                temp += line.charAt(i); 
                        }
                    }
                }
            }
        }
        catch(EOFException e){}

        catch(IOException e)
        {
            System.err.println("ERROR: IO Exception --- BASE_Buildings file! (Database_Commands.Load_Buildings)");
            return false;
        }

        catch(Exception e)
        {
            System.err.println("ERROR: Something's wrong!! (Database_Commands.Load_Buildings)");
            return false;
        }
        
        //-------------------------------------- zamykanie baz
        try
        {
            BASE_Buildings.close();
        }
        catch(IOException e)
        {
            System.err.println("ERROR: Something's wrong with closing BASE_Buildings file! (Database_Commands.Load_Buildings.close() )");
            return false;
        }
        
        //jeœli wszystko dobrze posz³o
        return true;
    }
}
