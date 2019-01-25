package rpg_express_0.pkg2;

import static rpg_express_0.pkg2.Utility_Commands.A_ACTION;
import static rpg_express_0.pkg2.Utility_Commands.B_PLACE;
import static rpg_express_0.pkg2.Utility_Commands.C_LEVEL;
import static rpg_express_0.pkg2.Utility_Commands.String_to_int;
import static rpg_express_0.pkg2.Utility_Commands.bolb_food_evolution_list;
import static rpg_express_0.pkg2.Utility_Commands.crafting_recipes_tab;
import static rpg_express_0.pkg2.Utility_Commands.gather_actions_event_chance_tab;
import static rpg_express_0.pkg2.Utility_Commands.gather_actions_npc_items_chance;
import static rpg_express_0.pkg2.Utility_Commands.gather_actions_npc_reaction_chance;
import static rpg_express_0.pkg2.Utility_Commands.gather_actions_place_item_loot;
import static rpg_express_0.pkg2.Utility_Commands.warehouse_item_array;

public class Recipes_Chances_ 
{
    //=============================================================================================================================================================
    //=============================================================================== TABLICA Z SZANS¥ NA NPC, NIC ALBO LOOT DLA RÓ¯NYCH LOKACJI I AKCJI
        //gather_actions_event_chance_tab = new int[A_ACTION][B_PLACE][C_LEVEL][3];
        static void fill_gather_actions_event_chance_tab()
        {
        /*[nr akcji zbieraj¹cej itemy] [ nr miejsca akcji] [poziom miejsca] [nr szansy na event]
        [A][B][C][D]
        [ 0 - look around ]
        [ 1 - Moonshore, 2 - Lemon Beach, 3 - Arcane Forest, 4 - Dark Forest, 5 - Sorri's Swamps]
        [ 0,1,2...] poziomy
        [0 - NPC chance, 1 - NOTHING chance, 2 - LOOT ITEM chance]
        */
        //------------------------------------------------------------- UZUPE£NIANIE TABLICY DLA LOOK_AROUND (A = 0) - string, który jest dzielony na czêœci
            //-------------------
            int A = 0; // A - nr akcji. Zaczynamy od 0 bo to pierwsza akcja i jest ni¹ look_around
            int B = 1; // B - nr miejsca. Zaczynamy od 1 bo 0 to placeholder
            int C = 0; // C - poziom miejsca. Zaczynamy od 0 i idziemy dalej
            int D = 0; // zaczynamy od wklepania wartoœci dla NPC
            //NPC, NOTHING, ITEM LOOT
            
            //npc_chance!nothing_chance!itemloot_chance?....?..@...@...@..#..&
            // (? = next level, @ = next place/action/koñczy pobieranie)
            int string_pos = 0;
            //wstawianie danych do stringa, którego potem program roz³upie i wklepie do tablicy
              //----------- LOOK AROUND
            //----------------------------------- MOONSHORE
       String lista = "0!70!30?" //lv 0
                    + "0!65!35?" //lv 1
                    + "0!60!40?" //lv 2
                    + "0!55!45?" //lv 3
                    + "0!45!55?" //lv 4
                    + "0!30!70@" //lv 5
            //----------------------------------- LEMON BEACH
                    + "25!30!45?" //lv 0
                    + "25!25!50?" //lv 1
                    + "30!20!50?" //lv 2
                    + "30!15!55?" //lv 3
                    + "30!10!60?" //lv 4
                    + "25!10!65@" //lv 5
            //----------------------------------- ARCANE FOREST
                    + "20!20!60?" //lv 0
                    + "25!20!55?" //lv 1
                    + "25!15!60?" //lv 2
                    + "25!13!62?" //lv 3
                    + "30!10!60?" //lv 4
                    + "29!10!61@" //lv 5
            //----------------------------------- DARK FOREST
                    + "50!20!30?" //lv 0
                    + "45!20!35?" //lv 1
                    + "45!18!37?" //lv 2
                    + "43!18!39?" //lv 3
                    + "42!16!42?" //lv 4
                    + "45!15!40@" //lv 5
            //----------------------------------- SORRI'S SWAMPS
                    + "30!30!40?" //lv 0
                    + "28!27!45?" //lv 1
                    + "25!25!50?" //lv 2
                    + "28!22!50?" //lv 3
                    + "25!20!55?" //lv 4
                    + "20!15!65@"; //lv 5
       
            String temp = "";
            
            for(; A< A_ACTION; A++) //przechodzi przez wszystkie akcje
            {
                for(B = 1; B < B_PLACE; B++) //przechodzi przez wszystkie akcje
                {
                    for(C = 0; C < C_LEVEL; C++) //przechodzi przez wszystkie poziomy
                    {
                        D = 0; //zeruje D, aby mo¿na by³o wpisywaæ resztê szans na npc, nothing, item loot
                        //system roz³upywania stringa
                        for(; string_pos < lista.length(); string_pos++)
                        {
                            if(lista.charAt(string_pos) == '!')
                            {
                                gather_actions_event_chance_tab[A][B][C][D]= String_to_int(temp); // wstawia pobran¹ szansê dla okreœlonego pola poziomu etc
                                D++;
                                temp = ""; // zeruje temp
                            }
                            else if( (lista.charAt(string_pos) == '?') || (lista.charAt(string_pos) == '@') )
                            {
                                int temp_int = String_to_int(temp);
                                gather_actions_event_chance_tab[A][B][C][D]= temp_int; // wstawia pobran¹ szansê dla okreœlonego pola poziomu etc
                                temp = ""; // zeruje temp
                                string_pos++; //wychodzimy z pêtli przez break, temu musimy dodaæ jeden znak dalej bo inaczej utkniemy na '?'
                                break; //wychodzi z pêtli i przechodzi do nastêpnego poziomu/miejsca/akcji
                            }
                            else 
                                temp += lista.charAt(string_pos); //jesli nie zakoñczy³o liczby !, to dokleja cyfry dalej
                        }
                    }
                }
            }
        }   
        
    //=============================================================================================================================================================
    //=============================================================================== TABLICA NPC REACTION - zawiera info na szansê reakcji NPC 
    //                                                                                                                          - string, który jest dzielony na czêœci
        //gather_actions_npc_reaction_chance = new int[A_ACTION][B_PLACE][C_LEVEL][5];
        static void fill_gather_actions_npc_reaction_chance() 
         {
        //UWAGA! kaŸdy NPC ma przypisane okreœlone miejsce, wyj¹tkiem jest Moonshore - bez NPC
        // Moonshore - NONE, Lemon Beach - Tork, Arcane Forest - Snori, Dark Forest - Darcus, Sorri's Swamps - Gyrio
        //[0] funny,  [1] hide,   [2] loot item,   [3] neutral,   [4] tip
             
            //wstawianie danych do stringa, którego potem program roz³upie i wklepie do tablicy
              //----------- LOOK AROUND
            //----------------------------------- MOONSHORE - BRAK NPC
       String lista = "~" //lv 0
                    + "~" //lv 1
                    + "~"  //lv 2
                    + "~"  //lv 3
                    + "~"  //lv 4
                    + "~"  //lv 5
            //----------------------------------- LEMON BEACH - TORK
                    + "25!30!15!10!20?" //lv 0
                    + "24!31!16!9!20?" //lv 1
                    + "23!31!18!8!20?"  //lv 2
                    + "22!30!20!8!20?"  //lv 3
                    + "20!31!21!9!19?"  //lv 4
                    + "18!32!22!13!15@"  //lv 5
            //----------------------------------- ARCANE FOREST - SNORI
                    + "15!0!24!26!35?" //lv 0
                    + "16!0!25!26!33?" //lv 1
                    + "15!0!26!25!34?"  //lv 2
                    + "14!0!28!25!33?"  //lv 3
                    + "13!0!29!26!32?"  //lv 4
                    + "12!0!30!28!30@"  //lv 5
            //----------------------------------- DARK FOREST - DARCUS
                    + "0!17!19!43!21?" //lv 0
                    + "0!17!21!41!21?" //lv 1
                    + "0!15!23!39!23?"  //lv 2
                    + "0!13!25!38!24?"  //lv 3
                    + "0!12!27!36!25?"  //lv 4
                    + "0!10!29!35!26@"  //lv 5
            //----------------------------------- SORRI'S SWAMPS - GYRIO
                    + "19!11!26!20!24?" //lv 0
                    + "18!10!28!21!23?" //lv 1
                    + "17!11!29!21!22?"  //lv 2
                    + "16!10!30!21!23?"  //lv 3
                    + "16!9!31!20!24?"  //lv 4
                    + "17!8!32!19!24@";  //lv 5
       
            //============== roz³upanie stringa i wstawienie szans do odpowiedniej czêœci tablicy
            int D = 0;
            int string_pos = 0;
            String temp = "";
            
            for(int A = 0; A <A_ACTION; A++) //przechodzi przez wszystkie akcje
            {
                for(int B = 1; B < B_PLACE; B++) //przechodzi przez wszystkie miejsca
                {
                    for(int C = 0; C < C_LEVEL; C++) //przechodzi przez wszystkie poziomy
                    {
                        //system roz³upywania stringa
                        for(; string_pos < lista.length(); string_pos++)
                        {
                            if(lista.charAt(string_pos) == '!')
                            {
                                gather_actions_npc_reaction_chance[A][B][C][D]= String_to_int(temp); // wstawia pobran¹ szansê dla okreœlonego pola poziomu etc
                                D++;
                                temp = ""; // zeruje temp
                            }
                            else if( (lista.charAt(string_pos) == '?') || (lista.charAt(string_pos) == '@') )
                            {
                                gather_actions_npc_reaction_chance[A][B][C][D]= String_to_int(temp); // wstawia pobran¹ szansê dla okreœlonego pola poziomu etc
                                D = 0; //zeruje D, aby mo¿na by³o wpisywaæ resztê szans na pozosta³e itemy
                                temp = ""; // zeruje temp
                                string_pos++; //wychodzimy z pêtli przez break, temu musimy dodaæ jeden znak dalej bo inaczej utkniemy na '?'
                                break; //wychodzi z pêtli i przechodzi do nastêpnego poziomu/miejsca/akcji
                            }
                            else if(lista.charAt(string_pos) == '~')
                            {
                                D = 0; //zeruje D, aby mo¿na by³o wpisywaæ resztê szans na pozosta³e itemy
                                temp = ""; // zeruje temp
                                string_pos++; //wychodzimy z pêtli przez break, temu musimy dodaæ jeden znak dalej bo inaczej utkniemy na '?'
                                break; //wychodzi z pêtli i przechodzi do nastêpnego poziomu/miejsca/akcji
                            }
                            else 
                                temp += lista.charAt(string_pos); //jesli nie zakoñczy³o liczby !, to dokleja cyfry dalej
                        }
                    }
                }
            }
         }
        
    //=============================================================================================================================================================
    //=============================================================================== TABLICA NPC ITEMS CHANCE - zawiera info z jak¹ szans¹ dropi¹ itemy przy spotkaniu NPC
    //                                                                                                                                 - string, który jest dzielony na czêœci
        //gather_actions_npc_items_chances = new int[A_ACTION][B_PLACE][C_LEVEL][4];
        static void fill_gather_actions_npc_items_chance() 
        {
        //akcja/miejsce/poziom = string zawieraj¹cy string zawieraj¹cy loot chance w takiej kolejnoœci, w jakiej zosta³y podane itemy

        //chance!chance!chance!chance? 
        //! = nastêpny chance, ? = przejœcie do kolejnego poziomu, @ = przejœcie do nastêpnego miejsca/akcji/wpisanie ostatniego

        //UWAGA!! KOLEJNOŒÆ ITEMÓW DLA NPC JEST W ACCESIE USTALANA W NASTÊPUJ¥CY SPOSÓB: OD NAJMNIEJSZEGO ITEM CHANCE DO NAJWIÊKSZEGO!, 
        //                                                                                   a jeœli itemy maj¹ taki sam chance, to patrz jeszcze alfabetycznie! (PATRZ POZIOM 0)
           //wstawianie danych do stringa, którego potem program roz³upie i wklepie do tablicy
            //----------- LOOK AROUND
            //----------------------------------- MOONSHORE
       String lista = "~" //lv 0
                    + "~" //lv 1
                    + "~"  //lv 2
                    + "~"  //lv 3
                    + "~"  //lv 4
                    + "~"  //lv 5
            //----------------------------------- LEMON BEACH
                    + "25!20!45!10?" //lv 0
                    + "27!19!43!11?" //lv 1
                    + "28!9!41!12?"  //lv 2
                    + "26!21!40!13?"  //lv 3
                    + "27!22!37!14?"  //lv 4
                    + "28!22!35!15@"  //lv 5
            //----------------------------------- ARCANE FOREST
                    + "15!30!28!27?" //lv 0
                    + "16!29!27!28?" //lv 1
                    + "16!28!27!29?"  //lv 2
                    + "17!28!25!30?"  //lv 3
                    + "18!27!25!30?"  //lv 4
                    + "19!28!24!29@"  //lv 5
            //----------------------------------- DARK FOREST
                    + "15!25!45!15?" //lv 0
                    + "16!24!43!17?" //lv 1
                    + "15!25!42!18?"  //lv 2
                    + "18!22!41!19?"  //lv 3
                    + "18!23!40!19?"  //lv 4
                    + "19!22!39!20@"  //lv 5
            //----------------------------------- SORRI'S SWAMPS
                    + "19!22!40!19?" //lv 0
                    + "18!23!41!18?" //lv 1
                    + "20!23!39!18?"  //lv 2
                    + "22!22!37!19?"  //lv 3
                    + "22!22!36!20?"  //lv 4
                    + "24!24!31!21@";  //lv 5
             
            //============== roz³upanie stringa i wstawienie szans do odpowiedniej czêœci tablicy
            int D = 0;
            int string_pos = 0;
            String temp = "";
            
            for(int A = 0; A <A_ACTION; A++) //przechodzi przez wszystkie akcje
            {
                for(int B = 1; B < B_PLACE; B++) //przechodzi przez wszystkie miejsca
                {
                    for(int C = 0; C < C_LEVEL; C++) //przechodzi przez wszystkie poziomy
                    {
                        //system roz³upywania stringa
                        for(; string_pos < lista.length(); string_pos++)
                        {
                            if(lista.charAt(string_pos) == '!')
                            {
                                gather_actions_npc_items_chance[A][B][C][D]= String_to_int(temp); // wstawia pobran¹ szansê dla okreœlonego pola poziomu etc
                                D++;
                                temp = ""; // zeruje temp
                            }
                            else if( (lista.charAt(string_pos) == '?') || (lista.charAt(string_pos) == '@') )
                            {
                                gather_actions_npc_items_chance[A][B][C][D]= String_to_int(temp); // wstawia pobran¹ szansê dla okreœlonego pola poziomu etc
                                D = 0; //zeruje D, aby mo¿na by³o wpisywaæ resztê szans na pozosta³e itemy
                                temp = ""; // zeruje temp
                                string_pos++; //wychodzimy z pêtli przez break, temu musimy dodaæ jeden znak dalej bo inaczej utkniemy na '?'
                                break; //wychodzi z pêtli i przechodzi do nastêpnego poziomu/miejsca/akcji
                            }
                            else if(lista.charAt(string_pos) == '~') // jeœli trafi na tyldê, to skippuje ca³e miejsce
                            {
                                D = 0; //zeruje D, aby mo¿na by³o wpisywaæ resztê 
                                temp = ""; // zeruje temp
                                string_pos++; //trzeba rêcznie przeskoczyæ do nastêpnego znaku
                                break;
                            }
                            else 
                                temp += lista.charAt(string_pos); //jesli nie zakoñczy³o liczby !, to dokleja cyfry dalej
                        }
                    }
                }
            }
         }
         
    //=============================================================================================================================================================
    //=============================================================================== TABLICA Stringów z zawartoœci¹ co mo¿e dropiæ w jakim rejonie, oraz jakie itemy dropi¹ NPC
    //                                                                                                          - UWAGA! Rêcznie wstawiana!
        //gather_actions_place_item_loot = new String[A_ACTION][B_PLACE][2];
        static void fill_gather_actions_place_item_loot()
        {
        /*[nr akcji zbieraj¹cej itemy] [ nr miejsca akcji][ [0] String z lootem miejsca, [1] String z lootem NPC dla miejsca]
         //UWAGA!! KOLEJNOŒÆ ITEMÓW DLA NPC JEST W ACCESIE USTALANA W NASTÊPUJ¥CY SPOSÓB: OD NAJMNIEJSZEGO ITEM CHANCE DO NAJWIÊKSZEGO!, 
         //                                                                                   a jeœli itemy maj¹ taki sam chance, to patrz jeszcze alfabetycznie! (PATRZ POZIOM 0)
        [A][B]
        [ 0 - look around ]
        [ 1 - Moonshore, 2 - Lemon Beach, 3 - Arcane Forest, 4 - Dark Forest, 5 - Sorri's Swamps]
        WZÓR STRINGA:
         "i0_001!.....i0_00n&" - jeden string dla jednego miejsca
         ! == przejœcie do nastêpnego itemu
         & == koniec ostatniego itemu
        */
         //------------------------------------------------------------- UZUPE£NIANIE TABLICY 
            int A = 0; //startowa akcja look_around
            int B = 1; //startowe miejcse Moonshore
            
            //---------------- LOOK_AROUND
            //Moonshore
            //place
            gather_actions_place_item_loot[A][B][0] = 
                                                  "i0_001!"
                                                + "i0_003!"
                                                + "i0_004!"
                                                + "i0_005!"
                                                + "i0_006!"
                                                + "i0_007!"
                                                + "i0_008!"
                    
                                                + "i1_001!"
                                                + "i1_002!"
                                                + "i1_003!"
                                                + "i1_004!"; 
            
            //npc
            gather_actions_place_item_loot[A][B][1] = 
                                                  ""; //BRAK ITEMÓW NPC DLA TEGO POZIOMU
            
            B++; // przechodzi do kolejnego miejsca
            //Lemon Beach
            //place
            gather_actions_place_item_loot[A][B][0] = 
                                                  "i0_001!"
                                                + "i0_002!"
                                                + "i0_003!"
                                                + "i0_004!"
                                                + "i0_005!"
                                                + "i0_006!"
                                                + "i0_007!"
                                                + "i0_008!"
                                                + "i0_009!"
                    
                                                + "i2_001!"
                                                + "i2_002!"
                                                + "i2_003!"
                                                + "i2_004!"
                                                + "i2_005!"; 
            
            //npc
            gather_actions_place_item_loot[A][B][1] = 
                                                  "i2_001!"
                                                + "i2_005!"
                                                + "i2_002!"
                                                + "i2_004!";
            
            B++; // przechodzi do kolejnego miejsca
            //Arcane Forest
            //place
            gather_actions_place_item_loot[A][B][0] = 
                                                  "i0_001!"
                                                + "i0_004!"
                                                + "i0_010!"
                                                + "i0_013!"
                                                + "i0_015!"
                    
                                                + "i3_001!"
                                                + "i3_002!"
                                                + "i3_003!"
                                                + "i3_004!"
                                                + "i3_005!"
                                                + "i3_006!"
                                                + "i3_007!"
                                                + "i3_008!"
                                                + "i3_009!"
                                                + "i3_010!"
                                                + "i3_011&";
            
            //npc
            gather_actions_place_item_loot[A][B][1] = 
                                                  "i3_009!"
                                                + "i3_004!"
                                                + "i3_005!"
                                                + "i3_003!";
            
            B++; // przechodzi do kolejnego miejsca
            //Dark Forest
            //place
            gather_actions_place_item_loot[A][B][0] = 
                                                  "i0_001!"
                                                + "i0_002!"
                                                + "i0_004!"
                                                + "i0_009!"
                                                + "i0_010!"
                                                + "i0_011!"
                                                + "i0_012!"
                                                + "i0_013!"
                                                + "i0_014!"
                                                + "i0_015!"
                    
                                                + "i4_001!"
                                                + "i4_002!"
                                                + "i4_003!"
                                                + "i4_004!";
            
            //npc
            gather_actions_place_item_loot[A][B][1] = 
                                                  "i4_004!"
                                                + "i4_001!"
                                                + "i4_002!"
                                                + "i4_003!";
            
            B++; // przechodzi do kolejnego miejsca
            //Sorri's Swamps
            //place
            gather_actions_place_item_loot[A][B][0] = 
                                                  "i0_004!"
                                                + "i0_009!"
                                                + "i0_011!"
                                                + "i0_012!"
                                                + "i0_013!"
                                                + "i0_014!"
                                                + "i0_015!"
                    
                                                + "i5_001!"
                                                + "i5_002!"
                                                + "i5_003!"
                                                + "i5_004!"
                                                + "i5_005!"
                                                + "i5_006!"
                                                + "i5_007!"
                                                + "i5_008!"
                                                + "i5_009!";
            
            //npc
            gather_actions_place_item_loot[A][B][1] = 
                                                  "i5_007!"
                                                + "i5_006!"
                                                + "i5_008!"
                                                + "i5_004!";
            
        }
        
    //=============================================================================================================================================================
    //=============================================================================== TABLICA Stringów z informacjami do crafting recipes
    //                                                                                                                          - string, który jest dzielony na czêœci
        //crafting_recipes_tab = new ArrayList<>();
        static void fill_crafting_recipes_tab()
        {
        /*
        A - Azgoran only
        D - Duargian only 
        J - Jessari only
        N - Nyrion only
        E - Everyone can craft it!
        WZÓR STRINGA:
         "i0_001!0?5$200#A@i0_002!i0_003?2!5!" + "&"
         crafted_id ! lv ? amount $ xp # kto craftuje @ item1 ! item2 ? amount1 ! amount2 ! & (& oznacza next item recipe!)         
        */
         //------------------------------------------------------------- UZUPE£NIANIE TABLICY 
            String lista =""
                    /// azgoran only
                    + "i0_022!2?1$100#A@i0_021!i1_002!i1_003?1!2!3!"           //Medium Quality Brick
                    + "&"
                    + "i0_023!4?1$230#A@i0_022!i2_002!i2_003?2!3!5!"           //High Quality Brick
                    + "&"
                    + "i0_035!1?1$100#A@i0_029!i0_044?1!1!"           //Mana Torch
                    + "&"
                    + "i3_004!2?1$130#A@i3_001!i3_006?2!1!"            //Medium Mana Crystal
                    + "&"
                    /// duargian only
                    + "i0_025!2?1$140#D@i0_024!i4_002!i0_016?1!2!2!"            //Medium Quality Plank
                    + "&"
                    + "i0_026!4?1$250#D@i0_025!i3_002!i0_016?2!3!3!"            //High Quality Plank
                    + "&"
                    + "i0_036!1?1$50#D@i1_003!i0_003?2!1!"            //Glass
                    + "&"
                    + "i0_038!2?1$130#D@i0_037!i0_010!i0_019?1!3!2!"            //Medium Quality Rope
                    + "&"
                    /// jessari only
                    + "i0_028!2?1$155#J@i0_027!i0_005!i0_018?1!1!2!"            //Medium Quality Fabric
                    + "&"
                    + "i0_041!4?1$280#J@i0_028!i0_005!i0_040!i0_018?2!1!1!3!"            //High Quality Fabric
                    + "&"
                    + "i0_040!4?7$200#J@i1_001!i2_001!i3_003!i4_001!i5_002?1!1!1!1!1!"            //Decorative Gems
                    + "&"
                    + "i0_033!2?1$155#J@i0_032!i3_004!i4_001?1!1!1!"            //Medium Quality Rune
                    + "&"
                    + "i0_044!3?1$175#J@i3_004!i3_006!i5_008?2!2!1!"            //Big Mana Crystal
                    + "&"
                    /// nyrion only
                    + "i0_020!2?1$150#N@i0_029!i5_001!i5_008?1!2!2!"            //Medium Quality Torch
                    + "&"
                    + "i0_031!4?!$265#N@i0_020!i5_001!i5_004?2!2!1!"            //High Quality Torch
                    + "&"
                    + "i0_030!2?5$155#N@i5_006!i5_001!i5_002?5!2!1"            //Decorative Spikes
                    + "&"
                    + "i0_034!4?1$280#N@i0_033!i0_044!i3_003?2!2!1!"            //High Quality Rune
                    + "&"
                    + "i0_039!4?1$270#N@i0_038!i0_017!i0_012?2!3!1!"            //High Quality Rope
                    + "&"
                    /// EVERYONE 
                    + "i0_024!0?1$15#E@i0_002?2!"            //Low Quality Plank
                    + "&"
                    + "i0_021!0?1$25#E@i0_001!i0_003?1!2!"            //Low Quality Brick
                    + "&"
                    + "i0_027!0?1$45#E@i0_011!i0_005!i0_010?1!1!2!"            //Low Quality Fabric
                    + "&"
                    + "i0_042!1?1$140#E@i2_004!i5_004!i3_009!i3_007?1!1!1!1!"            //Rainbow Dust (*)
                    + "&"
                    + "i0_032!0?1$65#E@i3_005!i3_001!i1_001?2!1!1!"            //Low Quality Rune
                    + "&"
                    + "i3_001!0?1$40#E@i5_008!i0_011!i0_012?2!1!1!"            //Small Mana Crystal
                    + "&"
                    + "i0_037!0?1$25#E@i0_010!i0_004?2!3!"            //Low Quality Rope
                    + "&"
                    + "i0_043!0?1$100#E@i0_015!i0_004!i0_011?1!8!2!"            //Small Bolb Nest
                    + "&"
                    + "i0_029!0?1$30#E@i0_002!i0_004!i0_010?1!1!2!"            //Low Quality Torch
                    + "";
            
            //pêtla ciachaj¹ca stringa na czêœci i wsadzaj¹ca go do listy
            String temp = "";
            for(int i = 0; i<lista.length(); i++)
            {
                if(lista.charAt(i) == '&')
                {
                    crafting_recipes_tab.add(temp);
                    temp = "";
                }
                else
                    temp += lista.charAt(i);
            }
        }
        
    //=============================================================================================================================================================
    //=============================================================================== TABLICA Stringów z informacjami, które itemy mo¿na wsadziæ do Warehouse
    //   
        //warehouse_item_array = new ArrayList<>();
        static void fill_warehouse_items()
        {
            //LISTA ITEMKÓW DLA WAREHOUSE
            String lista = ""
                    + "i0_021&"    //low quality brick
                    + "i0_024&"    //low quality plank
                    + "i0_032&"    //low quality rune
                    + "i0_037&"    //low quality rope
                    + "i0_029&"    //low quality torch
                    + "i0_027&"    //low quality fabric
                    + "i0_022&"    //medium quality brick
                    + "i0_025&"    //medium quality plank
                    + "i0_033&"    //medium quality rune
                    + "i0_038&"    //medium quality rope
                    + "i0_020&"    //medium quality torch
                    + "i0_028&"    //medium quality fabric
                    + "i0_023&"    //high quality brick
                    + "i0_026&"    //high quality plank
                    + "i0_034&"    //high quality rune
                    + "i0_039&"    //high quality rope
                    + "i0_031&"    //high quality torch
                    + "i0_041&"    //high quality fabric
                    + "i3_002&"    //arcane wood
                    + "i0_002&"    //duargian wood
                    + "i5_001&"    //pale wood
                    + "i4_002&"    //dark wood
                    + "i1_002&"    //milk stone
                    + "i3_011&"    //mossy stone
                    + "i5_003&"    //obsidian stone
                    + "i0_001&"    //simple stone
                    + "i2_002&"    //sour stone
                    + "i0_035&"    //mana torch
                    + "i0_036&"    //glass
                    + "i0_040&"    //decorative gems
                    + "i0_030&"    //decorative spikes
                    + "";

            String temp = "";
            //wczytywanie itemków do listy
            for(int i = 0; i < lista.length(); i++)
            {
                if(lista.charAt(i)== '&')
                {
                    warehouse_item_array.add(temp);
                    temp = "";
                }
                else
                    temp += lista.charAt(i);
            }
        }
        
    //=============================================================================================================================================================
    //=============================================================================== LISTA Stringów z zawartoœci¹ co robi jaki przedmiot po nakarmieniu nim Bolba
    //------------------------------------------------------------------------------------------------------------------------------------- rozdziela du¿y String na czêœci
        //bolb_food_evolution_list = new ArrayList<>();
        static void fill_bolb_food_evolution_list()
        {
            /*  WZORY:
            - zmiania jednej kategorii: [id_itemu]?#[nr kategorii]?[elem_1]!..![elem_3]!
                                        i0_006?#2?0!25!75!
            - zmiania wielu/wszystkich kategorii: [id_itemu]?*[el.1_kat.0]![el.2_kat.0]![el.3_kat.0]?...?[el.1_kat.6]![el.2_kat.6]![el.3_kat.6]?
                                        i3_008?*"             // * - MANA FRUIT
                                                + "7!7!7?"            // 0 WINGS - 7/7/7
                                                + "7!7!7?"            // 1 HORNS - 7/7/7
                                                + "5!5!6?"            // 2 SKIN - 5/5/6
                                                + "7!7!7?"            // 3 TAIL - 7/7/7
                                                + "7!7!7?"            // 4 LEGS - 7/7/7
            - zwyk³y bolb food: [id_itemu]?^
                                        i0_007?^
            - specjalny bolb_food:[id_itemu]?%[nr efektu specjalnego]?
                                        i5_007?%1?
        
            @ OZNACZA PRZEJŒCIE DO NASTÊPNEGO ITEMU!
            ~ OZNACZA PRZEJŒCIE DO NASTÊPNEJ KATEGORII!
        */
            String lista = ""
                    + "i0_006?#2?0!25!75!"             // # - FISHSCALE - 2 - 0/25/75
                    + "@"
                    
                    + "i0_007?^"             // ^ - SEAWEED - SIMPLE
                    + "@"
                    
                    + "i0_008?#3?0!0!100!"             // # - FISH FIN - 3 - 0/0/100
                    + "@"
                    
                    + "i0_009?#0?50!25!25!"             // # - BAT WING - 0 - 50/25/25
                    + "@"
                    
                    + "i0_013?#4?100!0!0!"             // # - LIZARD PAW - 4 - 100/0/0
                    + "@"
                    
                    + "i0_014?#3?60!40!0!"             // # - LIZARD TAIL - 3 - 60/40/0
                    + "@"
                    
                    + "i1_004?#5?0!"             // # - WHITE DUST - 5/6 - COLOR 0
                    + "@"
                    
                    + "i2_004?#5?1!"             // # - YELLOW DUST - 5/6 - COLOR 1
                    + "@"
                    
                    + "i2_005?#4?0!0!100!"             // # - OYSTER - 4 - 0/0/100
                    + "@"
                    
                    + "i3_007?#5?8!"             // # - BLUE DUST - 5/6 - COLOR 8
                    + "@"
                    
                    + "i3_008?*"             // * - MANA FRUIT
                                + "7!7!7?"            // 0 WINGS - 7/7/7
                                + "7!7!7?"            // 1 HORNS - 7/7/7
                                + "5!5!6?"            // 2 SKIN - 5/5/6
                                + "7!7!7?"            // 3 TAIL - 7/7/7
                                + "7!7!7?"            // 4 LEGS - 7/7/7
                    + "@"
                    
                    + "i3_009?#5?2!"             // # - GREEN DUST - 5/6 - COLOR 2
                    + "@"
                    
                    + "i3_010?#4?0!100!0!"             // # - BUNNY PAW - 4 - 0/100/0
                    + "@"
                    
                    + "i4_003?%0?"             // % - TRANLUCENT BERRY - SPECIAL ITEM - 0
                    + "@"
                    
                    + "i4_004?#5?9!"             // # - BLACK DUST - 5/6 - COLOR 9
                    + "@"
                    
                    + "i5_004?#5?7"             // # - RED DUST - 5/6 - COLOR 7
                    + "@"
                    
                    + "i5_006?#1?55!20!25!"             // # - CRIMSON SPIKE - 1 - 55/20/25
                    + "@"
                    
                    + "i5_007?%1?"             // % - BLUE PEACH
                    + "@"
                    
                    + "i5_009?^"            // ^ - BLUE PEACH LEAF
                    + "@"
                    
                    + "i0_042?#5?10!"             // # - RAINBOW DUST - 5/6 - COLOR 10
                    + "@";
            
            String temp = "";
            
            for(int i =0; i < lista.length(); i++)
            {
                if(lista.charAt(i) == '@') //jeœli trafi na znak przejscia do nastêpnego itemu, wstawia string z poprzedniego itemu do tablicy
                {
                    bolb_food_evolution_list.add(temp);
                    temp = ""; //czyœci stringa dla nastêpnego itemu
                }
                else
                    temp += lista.charAt(i);
            }
        }
}
