package rpg_express_0.pkg2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;



public class Utility_Commands 
{
    
    //===========================================
    //------------------------------------ PUBLIC UTILITIES COMMANDS -------------------
    //===========================================
    //===================== VERY IMPORTANT STATIC VARIABLES!!
    public static Map<String, Entity> player_map = new HashMap<>(); //mapa dla identyfikacji graczy
    public static Map<String, Building> building_map = new HashMap<>(); //mapa dla identyfikacji budynków
    public static Map<String, Item> item_map = new HashMap<>(); //mapa dla identyfikacji itemów
    public static Map<String, Bolb> bolb_map = new HashMap<>(); //mapa dla identyfikacji bolbów
    
    public static ArrayList<Entity> player_array = new ArrayList<>();
    public static ArrayList<Building> building_array = new ArrayList<>();
    public static ArrayList<Item> item_array = new ArrayList<>();
    public static ArrayList<Bolb> bolb_array = new ArrayList<>();
    
    
    static final int entity_item_id_length = 6; //aktualna d³ugoœæ ID itemów
    static final int A_ACTION = 1;
    static final int B_PLACE = 6;
    static final int C_LEVEL = 6;
    static final int BOLB_APPEARANCE_ELEMENTS = 7;
    static final int BOLB_COLORS_AMOUNT = 10;
    
    //------------------------- PLAYER
    public static Entity createNewPlayer(String ID, String Name, String Race)
    {
        return new Entity(ID, Name, Race);
    }    
    //------------------------- ITEM
    public static Item createNewItem(String ID, String Name, String Class, String Description, String item_chance_tab)
    {
        return new Item(ID, Name, Class, Description, item_chance_tab);
    }
    //------------------------- BOLB
    public static Bolb createNewBolb(String owner_id, String name, boolean gender, int[] tab_app) 
    {
       // 0 - wings, 1 - horns, 2 - skin, 3 - tail, 4 - legs, 5 - primary_col, 6 - secondary_col 
       return new Bolb(owner_id, name, gender, tab_app[0], tab_app[1], tab_app[2], tab_app[3], tab_app[4], tab_app[5], tab_app[6] );
    }
    //------------------------- BUILDING
    public static Building createNewBuilding(String ID, int Class, String Race, double hp_multi, int HP_base, int wood_base, int stone_base, String upgrade_recipes)
    {
        return new Building(ID, Class, Race, hp_multi, HP_base, wood_base, stone_base, upgrade_recipes);
    }
    //------------------------- WAREHOUSE
    public static Warehouse createNewWarehouse(String ID, int Class, String Race, double hp_multi, int HP_base, int wood_base, int stone_base, double cap_multi, int capacity, String upgrade_recipes)
    {
        return new Warehouse(ID, Class, Race, hp_multi, HP_base, wood_base, stone_base, cap_multi, capacity, upgrade_recipes);
    }
        
     
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    //=============================================================================================================================================================
    //=============================================================================== TABLICA Z SZANS¥ NA NPC, NIC ALBO LOOT DLA RÓ¯NYCH LOKACJI I AKCJI
        static public int[][][][] gather_actions_event_chance_tab = new int[A_ACTION][B_PLACE][C_LEVEL][3];
        //---------------------------------------------> fill_gather_actions_event_chance_tab()
        
        /*[nr akcji zbieraj¹cej itemy] [ nr miejsca akcji] [poziom miejsca] [nr szansy na event]
        [ 0 - look around ]
        [ 1 - Moonshore, 2 - Lemon Beach, 3 - Arcane Forest, 4 - Dark Forest, 5 - Sorri's Swamps]
        [ 0,1,2...] poziomy
        [0 - NPC chance, 1 - NOTHING chance, 2 - LOOT ITEM chance] */        
        
    //=============================================================================================================================================================
    //=============================================================================== TABLICA NPC REACTION - zawiera info na szansê reakcji NPC 
        static public int[][][][] gather_actions_npc_reaction_chance = new int[A_ACTION][B_PLACE][C_LEVEL][5];
        //---------------------------------------------> fill_gather_actions_npc_reaction_chance() 

        //UWAGA! kaŸdy NPC ma przypisane okreœlone miejsce, wyj¹tkiem jest Moonshore - bez NPC
        // Moonshore - NONE, Lemon Beach - Tork, Arcane Forest - Snori, Dark Forest - Darcus, Sorri's Swamps - Gyrio
        //[0] funny,  [1] hide,   [2] loot item,   [3] neutral,   [4] tip
        
    //=============================================================================================================================================================
    //=============================================================================== TABLICA NPC ITEMS CHANCE - zawiera info z jak¹ szans¹ dropi¹ itemy przy spotkaniu NPC
        static public int[][][][] gather_actions_npc_items_chance = new int[A_ACTION][B_PLACE][C_LEVEL][4];
        //---------------------------------------------> fill_gather_actions_npc_items_chance()

        //akcja/miejsce/poziom = string zawieraj¹cy string zawieraj¹cy loot chance w takiej kolejnoœci, w jakiej zosta³y podane itemy
        //chance!chance!chance!chance? 
        //! = nastêpny chance, ? = przejœcie do kolejnego poziomu, @ = przejœcie do nastêpnego miejsca/akcji/wpisanie ostatniego

        //UWAGA!! KOLEJNOŒÆ ITEMÓW DLA NPC JEST W ACCESIE USTALANA W NASTÊPUJ¥CY SPOSÓB: OD NAJMNIEJSZEGO ITEM CHANCE DO NAJWIÊKSZEGO!, 
        //             a jeœli itemy maj¹ taki sam chance, to patrz jeszcze alfabetycznie! (PATRZ POZIOM 0)
         
    //=============================================================================================================================================================
    //============================= UWAGA! Rêcznie wstawiana! ======================= TABLICA Stringów z zawartoœci¹ co mo¿e dropiæ w jakim rejonie, oraz jakie itemy dropi¹ NPC
        static public String[][][] gather_actions_place_item_loot = new String[A_ACTION][B_PLACE][2];
        //---------------------------------------------> fill_gather_actions_place_item_loot()
        
        /*[nr akcji zbieraj¹cej itemy] [ nr miejsca akcji][ [0] String z lootem miejsca, [1] String z lootem NPC dla miejsca]
         //UWAGA!! KOLEJNOŒÆ ITEMÓW DLA NPC JEST W ACCESIE USTALANA W NASTÊPUJ¥CY SPOSÓB: OD NAJMNIEJSZEGO ITEM CHANCE DO NAJWIÊKSZEGO!, 
         //                                                                                   a jeœli itemy maj¹ taki sam chance, to patrz jeszcze alfabetycznie! (PATRZ POZIOM 0)
        [A][B]
        [ 0 - look around ]
        [ 1 - Moonshore, 2 - Lemon Beach, 3 - Arcane Forest, 4 - Dark Forest, 5 - Sorri's Swamps]
        WZÓR STRINGA:
         "i0_001!.....i0_00n&" - jeden string dla jednego miejsca
         ! == przejœcie do nastêpnego itemu
         & == koniec ostatniego itemu */
        
    //=============================================================================================================================================================
    //=============================================================================== TABLICA Stringów z informacjami do crafting recipes
        static public ArrayList<String> crafting_recipes_tab = new ArrayList<>();
        //---------------------------------------------> fill_crafting_recipes_tab()
        
        /*  A - Azgoran only
            D - Duargian only 
            J - Jessari only
            N - Nyrion only
            E - Everyone can craft it!
            WZÓR STRINGA:
             "i0_001!0?5$200#A@i0_002!i0_003?2!5!" + "&"
             crafted_id ! lv ? amount $ xp # kto craftuje @ item1 ! item2 ? amount1 ! amount2 ! & (& oznacza next item recipe!)   */
        
    //=============================================================================================================================================================
    //=============================================================================== TABLICA Stringów z informacjami, które itemy mo¿na wsadziæ do Warehouse
        static public ArrayList<String> warehouse_item_array = new ArrayList<>();
        //---------------------------------------------> fill_warehouse_items()
        
    //=============================================================================================================================================================
    //=============================================================================== LISTA Stringów z zawartoœci¹ co robi jaki przedmiot po nakarmieniu nim Bolba
    //------------------------------------------------------------------------------------------------------------------------------------- rozdziela du¿y String na czêœci
        static public ArrayList<String> bolb_food_evolution_list = new ArrayList<>();
        //---------------------------------------------> fill_bolb_food_evolution_list()
        
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
            ~ OZNACZA PRZEJŒCIE DO NASTÊPNEJ KATEGORII!   */

    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX    
    //=====================================================================================================================================================================
    //========================================================================================================================================== Show_player_array
    public static void Show_player_array() 
    {
        System.out.println("========================================================================");
        for(int i = 0; i < player_array.size(); i++)
            System.out.println(i + ". ID: " + player_array.get(i).entity_id + ", Name: " + player_array.get(i).entity_name + ", Race: " + player_array.get(i).entity_race);
    }
    //=====================================================================================================================================================================
    //========================================================================================================================================== Show_item_array
    public static void Show_item_array() 
    {
        System.out.println("========================================================================");   
        for(int i = 0; i < item_array.size(); i++)
            System.out.println(i + ". ID: " + item_array.get(i).item_id + ", Name: " + item_array.get(i).item_name + ", Class: " + item_array.get(i).item_class);
    }        
    //=====================================================================================================================================================================
    //========================================================================================================================================== Roll
    public static int Roll(int min, int max)
    {
        //OLD int wynik = roll.nextInt(max) + min; //UWAGA! JEŒLI BÊDZIE WYWALAÆ B£ÊDY, TO DO MAX WSTAWIÆ +1!!!
        Random roll = new Random();
        
        int wynik = 0;
        do
            wynik = roll.nextInt(max) + min;
        while( (wynik > max) || (min > wynik));
        
        return wynik;
    }
    //=====================================================================================================================================================================
    //========================================================================================================================================== String_to_int
    public static int String_to_int(String string)
    {
        int number_to_return = Integer.parseInt(string);
        return number_to_return;
    }
    //=====================================================================================================================================================================
    //========================================================================================================================================== Calculate_next_n
    public static int Calculate_next_n(int base, double ratio)
    {
        int wynik = (int)((double)(base) * ratio);
        return wynik;
    }    
    //=====================================================================================================================================================================
    //========================================================================================================================================== look_around_roll_event
    //----------------------------------------------------- rolluje jedno z podanych wydarzeñ (szanse 0-99), po czym zwraca nr wylosowanego wydarzenia wg kolejnoœci podawania szans
    public static int look_around_roll_event(int...tab)//NPC, NOTHING, ITEM (FOR NOW)
    {
        // 0 -- NPC, 1 -- NOTHING, 2 -- ITEM
        int roll = Roll(0,99);
        int suma = 0;
        int i = 0; //pocz¹tkowe i
        
        for(; i < tab.length; i++)
        {
            if(tab[i] == 0) //jeœli roll chance aktualnej rzeczy jest jest równy 0, to pomijamy j¹ i jedziemy dalej
                i++;
            else
            { // w przeciwnym wypadku sprawdzamy, czy liczba jest w zakresie, etc.
                if((roll>= suma) && (roll<suma+tab[i])) //sprawdza, czy roll mieœci siê w zakresie losowanego eventu
                    return i;
                else
                    suma += tab[i];
            }
        }
        return -1; //zwraca -1, jeœli coœ posz³o nie tak
    }
    //=====================================================================================================================================================================
    //========================================================================================================================================== Loot_Item
    //---------------------------------------------------------------------------------------------------- generuje, jaki item i w jakiej iloœci zosta³ zlootowany
    public static String Loot_Item(int action, int place, int lv, String base_string, String mode)
    {
        int item_count = 0;
        String temp = "";
        //przechodzi przez pêtle, ¿eby policzyæ ile jest itemów w stringu
        for(int i = 0; i<base_string.length(); i++)
        {
            if(base_string.charAt(i) == '!')//jeœli znajdzie przejœcie do nastêpnego itemu, najpierw zlicza pierwszy item
                item_count++;
        }
        //po zliczeniu itemów, tworzy tablicê dok³adn¹ liczbie itemów
        String tab[] = new String[item_count];
        
        //ponownie przechodzi przez tablicê, tym razem rozk³adaj¹c du¿ego stringa na ma³e, aka id itemów
        for(int i = 0, j = 0; i<base_string.length(); i++)
        {
            if(base_string.charAt(i) == '!')//jeœli znajdzie przejœcie do nastêpnego itemu, najpierw wstawia do tablicy sklejone id itemu 
            {
                tab[j] = temp;//wstawia id itemu do tablicy
                j++;
                temp = ""; //zeruje temp dla nastêpnego stringa
            }
            else//jeœli nie ma przejœcia do nastêpnego itemu, dokleja do aktualnego tempa resztê id aktualnego itemu
                temp += base_string.charAt(i);
        }        
        
        int suma = 0; //potrzebne póŸniej do robienia zakresów dla itemów
        int roll = Roll(0,99); //rolluje, jaki przedmiot zostanie znaleziony z puli przedmiotów
                
        if(mode == "NPC_LOOT") //------------- przeszukiwanie itemu wg NPC ITEM LOOT chances
        {
            for(int i=0; i< tab.length; i++)
            {
                if((roll>= suma) && (roll<suma+gather_actions_npc_items_chance[action][place][lv][i])) //sprawdza, czy roll mieœci siê w 
                        //-------------------------------------------------------------------------------------- zakresie(szansy dropniêcia) pierwszego itemu w tablicy
                {
                    return item_map.get(tab[i]).item_id; //zwraca ID itemu, który zosta³ wyrollowany jako loot
                }
                else
                    suma += gather_actions_npc_items_chance[action][place][lv][i];
            }
            System.out.println("ERROR: Couldn't match the loot to the drop chances!");
            return "";
        }
        else //------------- zwyk³e przeszukiwanie itemu wg chances
        {
            for(int i=0; i< tab.length; i++)
            {
                if((roll>= suma) && (roll<suma+item_map.get(tab[i]).item_gather_actions_chance_Tab[action][place][lv])) //sprawdza, czy roll mieœci siê w 
                        //-------------------------------------------------------------------------------------- zakresie(szansy dropniêcia) pierwszego itemu w tablicy
                {
                    return item_map.get(tab[i]).item_id; //zwraca ID itemu, który zosta³ wyrollowany jako loot
                }
                else
                    suma += item_map.get(tab[i]).item_gather_actions_chance_Tab[action][place][lv];
            }
            System.out.println("ERROR: Couldn't match the loot to the drop chances!");
            return "";
        }
    }
    //=====================================================================================================================================================================
    //========================================================================================================================================== Roll_Item_Amount
    //---------------------------------------------------------------------------------------------------- generuje iloœæ zdobytego itemu
    public static int Roll_Item_Amount(String id, int action, int place, int lv) //zwraca iloœæ wydropionego itemu 
    {
        //min/max
        int[] amount_tab = new int[2];
        amount_tab = generate_item_amount(action,place,lv, id);
        
        int wynik = Roll(amount_tab[0],amount_tab[1]);
        System.out.println("You've found "+item_map.get(id).item_name+"["+item_map.get(id).item_class+"], amount: "+wynik+". ");
        return wynik;
    }
    //=====================================================================================================================================================================
    //========================================================================================================================================== generate_item_amount
    //---------------------------------------------------------------- generuje iloœæ itemu dla okreœlonego poziomu DLA DOWOLNEGO ITEMU i zwraca tablicê zawieraj¹c¹ min i max itemu
    public static int[] generate_item_amount(int action, int place, int lv, String item_id)
    {
        //ITEM min/max
        int[] tab = new int[2];
        double min_temp = 0;
        double max_temp = 0;
        
       if(lv == 0)
       {
           tab[0] = item_map.get(item_id).item_gather_actions_Lv_XP_amount_Tab[action][place][2];
           tab[1] = item_map.get(item_id).item_gather_actions_Lv_XP_amount_Tab[action][place][3];
       }
       else
       {
          //WZÓR NA amount min dla n ------- min n = max n-1
          //WZÓR NA amount max dla n ------- max n = min n *1.75
          //przygotowanie max_temp poprzedniego poziomu do zainicjowana min temp aktualnego poziomu
          max_temp = item_map.get(item_id).item_gather_actions_Lv_XP_amount_Tab[action][place][3];
           
          for(int i =0; i<lv; i++)
          {
            min_temp = max_temp;
            max_temp = min_temp*1.75;
          }
                    
          tab[0] = (int)(Math.round(min_temp));
          tab[1] = (int)(Math.round(max_temp)); 
       } 
        return tab;
    }
    //=====================================================================================================================================================================
    //========================================================================================================================================== generate_XP
    //---------------------------------------------------------------- generuje XP dla okreœlonego poziomu DLA DOWOLNEGO ITEMU i zwraca tablicê zawieraj¹c¹ XP i XP_range
    public static int[] generate_XP(int action, int place, int lv, String item_id)
    {
        //XP/XP_range
        int[] tab = new int[2];
        double xp_temp = 0;
        double range_temp = 0;
        
       if(lv == 0)
       {
           tab[0] = item_map.get(item_id).item_gather_actions_Lv_XP_amount_Tab[action][place][0];
           tab[1] = item_map.get(item_id).item_gather_actions_Lv_XP_amount_Tab[action][place][1];
       }
       else
       {
           //WZÓR NA LV n = (( (100-place chance) + (100 - item chance) * item_xp_multiplier) * place lv )*1.5
          xp_temp = ( (double)(100 - gather_actions_event_chance_tab[action][place][lv][2]) + (double)(100 - item_map.get(item_id).item_gather_actions_chance_Tab[action][place][lv])) 
                  *(double)(item_map.get(item_id).item_xp_multi)*1.5*(double)(lv);
          //XP RANGE = 35% XP
          range_temp = 0.35*xp_temp;
          
          tab[0] = (int)(Math.round(xp_temp));
          tab[1] = (int)(Math.round(range_temp)); 
       }
        return tab;
    }   
    //=====================================================================================================================================================================
    //========================================================================================================================================== show_item_amount_chance_xp_properties
    //---------------------------------------------------------------- generuje xp range, drop chance i amount range dla ka¿dego poziomu miejsca, w którym mo¿na znaleŸæ przedmiot
    public static void show_item_amount_chance_xp_properties(String item_id)
    {
        //napisz pêtlê przechodz¹c¹ przez wszystkie miejsca, i która sprawdza, czy wgl mo¿na znaleŸæ ten item w tym miejscu
    }
    //BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
    //BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB BOLB UTILITY COMMANDS BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
    //=====================================================================================================================================================================
    //============================================================================================================ find_bolb_owner
    //---------------------------------------------------- przeszukuje listê graczy i porównje z id ownera bolba. Kiedy znajdzie takie samo id, zwraca pozycjê w player_array
    public static int find_bolb_owner(String owner_id)
    {
        for(int i = 0; i < player_array.size(); i++)
        {
            if(owner_id.equals(player_array.get(i).entity_id)) //jeœli znalaz³o w³aœciciela, to zwraca jego numer
                return i;
        }
        return -1; //jeœli coœ posz³o nie tak
    }
    //=====================================================================================================================================================================
    //============================================================================================================ player_ADD_bolb
    //--------------------------------------------------------------------------------------------- dodaje wybranego Bolba do listy bolbów gracza
    public static void player_ADD_bolb(String player_id, String bolb_id)// X PSUJE CA£Y PROGRAM!!
    {
        player_map.get(player_id).entity_bolblist.add((bolb_map.get(bolb_id)));
    }   
    //=====================================================================================================================================================================
    //============================================================================================================ player_REMOVE_bolb
    //--------------------------------------------------------------------------------------------- usuwa wybranego Bolba z listy bolbów gracza PERMAMENTNIE!!
    public static void player_REMOVE_bolb(String player_id, String bolb_id)
    {
        player_map.get(player_id).entity_bolblist.remove((bolb_map.get(bolb_id)));
        bolb_map.get(bolb_id).bolb_owner_id = "TO_REMOVE";
    }
    //=====================================================================================================================================================================
    //============================================================================================================ UPDATE_B_brushing
    //--------------------------------------------------------------------------------------------- aktualizuje status czesania bolbów na nowy dzieñ
    public static void UPDATE_B_brushing()
    {
        for(int i = 0; i < bolb_array.size(); i++)
        {
            bolb_array.get(i).bolb_brushing = false; //zeruje dla ka¿dego bolba czesanie na nowy dzieñ
        }
    }
    
}
