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
    public static Map<String, Building> building_map = new HashMap<>(); //mapa dla identyfikacji budynk�w
    public static Map<String, Item> item_map = new HashMap<>(); //mapa dla identyfikacji item�w
    public static Map<String, Bolb> bolb_map = new HashMap<>(); //mapa dla identyfikacji bolb�w
    
    public static ArrayList<Entity> player_array = new ArrayList<>();
    public static ArrayList<Building> building_array = new ArrayList<>();
    public static ArrayList<Item> item_array = new ArrayList<>();
    public static ArrayList<Bolb> bolb_array = new ArrayList<>();
    
    
    static final int entity_item_id_length = 6; //aktualna d�ugo�� ID item�w
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
    //=============================================================================== TABLICA Z SZANS� NA NPC, NIC ALBO LOOT DLA RӯNYCH LOKACJI I AKCJI
        static public int[][][][] gather_actions_event_chance_tab = new int[A_ACTION][B_PLACE][C_LEVEL][3];
        //---------------------------------------------> fill_gather_actions_event_chance_tab()
        
        /*[nr akcji zbieraj�cej itemy] [ nr miejsca akcji] [poziom miejsca] [nr szansy na event]
        [ 0 - look around ]
        [ 1 - Moonshore, 2 - Lemon Beach, 3 - Arcane Forest, 4 - Dark Forest, 5 - Sorri's Swamps]
        [ 0,1,2...] poziomy
        [0 - NPC chance, 1 - NOTHING chance, 2 - LOOT ITEM chance] */        
        
    //=============================================================================================================================================================
    //=============================================================================== TABLICA NPC REACTION - zawiera info na szans� reakcji NPC 
        static public int[][][][] gather_actions_npc_reaction_chance = new int[A_ACTION][B_PLACE][C_LEVEL][5];
        //---------------------------------------------> fill_gather_actions_npc_reaction_chance() 

        //UWAGA! ka�dy NPC ma przypisane okre�lone miejsce, wyj�tkiem jest Moonshore - bez NPC
        // Moonshore - NONE, Lemon Beach - Tork, Arcane Forest - Snori, Dark Forest - Darcus, Sorri's Swamps - Gyrio
        //[0] funny,  [1] hide,   [2] loot item,   [3] neutral,   [4] tip
        
    //=============================================================================================================================================================
    //=============================================================================== TABLICA NPC ITEMS CHANCE - zawiera info z jak� szans� dropi� itemy przy spotkaniu NPC
        static public int[][][][] gather_actions_npc_items_chance = new int[A_ACTION][B_PLACE][C_LEVEL][4];
        //---------------------------------------------> fill_gather_actions_npc_items_chance()

        //akcja/miejsce/poziom = string zawieraj�cy string zawieraj�cy loot chance w takiej kolejno�ci, w jakiej zosta�y podane itemy
        //chance!chance!chance!chance? 
        //! = nast�pny chance, ? = przej�cie do kolejnego poziomu, @ = przej�cie do nast�pnego miejsca/akcji/wpisanie ostatniego

        //UWAGA!! KOLEJNO�� ITEM�W DLA NPC JEST W ACCESIE USTALANA W NAST�PUJ�CY SPOS�B: OD NAJMNIEJSZEGO ITEM CHANCE DO NAJWI�KSZEGO!, 
        //             a je�li itemy maj� taki sam chance, to patrz jeszcze alfabetycznie! (PATRZ POZIOM 0)
         
    //=============================================================================================================================================================
    //============================= UWAGA! R�cznie wstawiana! ======================= TABLICA String�w z zawarto�ci� co mo�e dropi� w jakim rejonie, oraz jakie itemy dropi� NPC
        static public String[][][] gather_actions_place_item_loot = new String[A_ACTION][B_PLACE][2];
        //---------------------------------------------> fill_gather_actions_place_item_loot()
        
        /*[nr akcji zbieraj�cej itemy] [ nr miejsca akcji][ [0] String z lootem miejsca, [1] String z lootem NPC dla miejsca]
         //UWAGA!! KOLEJNO�� ITEM�W DLA NPC JEST W ACCESIE USTALANA W NAST�PUJ�CY SPOS�B: OD NAJMNIEJSZEGO ITEM CHANCE DO NAJWI�KSZEGO!, 
         //                                                                                   a je�li itemy maj� taki sam chance, to patrz jeszcze alfabetycznie! (PATRZ POZIOM 0)
        [A][B]
        [ 0 - look around ]
        [ 1 - Moonshore, 2 - Lemon Beach, 3 - Arcane Forest, 4 - Dark Forest, 5 - Sorri's Swamps]
        WZ�R STRINGA:
         "i0_001!.....i0_00n&" - jeden string dla jednego miejsca
         ! == przej�cie do nast�pnego itemu
         & == koniec ostatniego itemu */
        
    //=============================================================================================================================================================
    //=============================================================================== TABLICA String�w z informacjami do crafting recipes
        static public ArrayList<String> crafting_recipes_tab = new ArrayList<>();
        //---------------------------------------------> fill_crafting_recipes_tab()
        
        /*  A - Azgoran only
            D - Duargian only 
            J - Jessari only
            N - Nyrion only
            E - Everyone can craft it!
            WZ�R STRINGA:
             "i0_001!0?5$200#A@i0_002!i0_003?2!5!" + "&"
             crafted_id ! lv ? amount $ xp # kto craftuje @ item1 ! item2 ? amount1 ! amount2 ! & (& oznacza next item recipe!)   */
        
    //=============================================================================================================================================================
    //=============================================================================== TABLICA String�w z informacjami, kt�re itemy mo�na wsadzi� do Warehouse
        static public ArrayList<String> warehouse_item_array = new ArrayList<>();
        //---------------------------------------------> fill_warehouse_items()
        
    //=============================================================================================================================================================
    //=============================================================================== LISTA String�w z zawarto�ci� co robi jaki przedmiot po nakarmieniu nim Bolba
    //------------------------------------------------------------------------------------------------------------------------------------- rozdziela du�y String na cz�ci
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
            - zwyk�y bolb food: [id_itemu]?^
                                        i0_007?^
            - specjalny bolb_food:[id_itemu]?%[nr efektu specjalnego]?
                                        i5_007?%1?
        
            @ OZNACZA PRZEJ�CIE DO NAST�PNEGO ITEMU!
            ~ OZNACZA PRZEJ�CIE DO NAST�PNEJ KATEGORII!   */

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
        //OLD int wynik = roll.nextInt(max) + min; //UWAGA! JE�LI B�DZIE WYWALA� B��DY, TO DO MAX WSTAWI� +1!!!
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
    //----------------------------------------------------- rolluje jedno z podanych wydarze� (szanse 0-99), po czym zwraca nr wylosowanego wydarzenia wg kolejno�ci podawania szans
    public static int look_around_roll_event(int...tab)//NPC, NOTHING, ITEM (FOR NOW)
    {
        // 0 -- NPC, 1 -- NOTHING, 2 -- ITEM
        int roll = Roll(0,99);
        int suma = 0;
        int i = 0; //pocz�tkowe i
        
        for(; i < tab.length; i++)
        {
            if(tab[i] == 0) //je�li roll chance aktualnej rzeczy jest jest r�wny 0, to pomijamy j� i jedziemy dalej
                i++;
            else
            { // w przeciwnym wypadku sprawdzamy, czy liczba jest w zakresie, etc.
                if((roll>= suma) && (roll<suma+tab[i])) //sprawdza, czy roll mie�ci si� w zakresie losowanego eventu
                    return i;
                else
                    suma += tab[i];
            }
        }
        return -1; //zwraca -1, je�li co� posz�o nie tak
    }
    //=====================================================================================================================================================================
    //========================================================================================================================================== Loot_Item
    //---------------------------------------------------------------------------------------------------- generuje, jaki item i w jakiej ilo�ci zosta� zlootowany
    public static String Loot_Item(int action, int place, int lv, String base_string, String mode)
    {
        int item_count = 0;
        String temp = "";
        //przechodzi przez p�tle, �eby policzy� ile jest item�w w stringu
        for(int i = 0; i<base_string.length(); i++)
        {
            if(base_string.charAt(i) == '!')//je�li znajdzie przej�cie do nast�pnego itemu, najpierw zlicza pierwszy item
                item_count++;
        }
        //po zliczeniu item�w, tworzy tablic� dok�adn� liczbie item�w
        String tab[] = new String[item_count];
        
        //ponownie przechodzi przez tablic�, tym razem rozk�adaj�c du�ego stringa na ma�e, aka id item�w
        for(int i = 0, j = 0; i<base_string.length(); i++)
        {
            if(base_string.charAt(i) == '!')//je�li znajdzie przej�cie do nast�pnego itemu, najpierw wstawia do tablicy sklejone id itemu 
            {
                tab[j] = temp;//wstawia id itemu do tablicy
                j++;
                temp = ""; //zeruje temp dla nast�pnego stringa
            }
            else//je�li nie ma przej�cia do nast�pnego itemu, dokleja do aktualnego tempa reszt� id aktualnego itemu
                temp += base_string.charAt(i);
        }        
        
        int suma = 0; //potrzebne p�niej do robienia zakres�w dla item�w
        int roll = Roll(0,99); //rolluje, jaki przedmiot zostanie znaleziony z puli przedmiot�w
                
        if(mode == "NPC_LOOT") //------------- przeszukiwanie itemu wg NPC ITEM LOOT chances
        {
            for(int i=0; i< tab.length; i++)
            {
                if((roll>= suma) && (roll<suma+gather_actions_npc_items_chance[action][place][lv][i])) //sprawdza, czy roll mie�ci si� w 
                        //-------------------------------------------------------------------------------------- zakresie(szansy dropni�cia) pierwszego itemu w tablicy
                {
                    return item_map.get(tab[i]).item_id; //zwraca ID itemu, kt�ry zosta� wyrollowany jako loot
                }
                else
                    suma += gather_actions_npc_items_chance[action][place][lv][i];
            }
            System.out.println("ERROR: Couldn't match the loot to the drop chances!");
            return "";
        }
        else //------------- zwyk�e przeszukiwanie itemu wg chances
        {
            for(int i=0; i< tab.length; i++)
            {
                if((roll>= suma) && (roll<suma+item_map.get(tab[i]).item_gather_actions_chance_Tab[action][place][lv])) //sprawdza, czy roll mie�ci si� w 
                        //-------------------------------------------------------------------------------------- zakresie(szansy dropni�cia) pierwszego itemu w tablicy
                {
                    return item_map.get(tab[i]).item_id; //zwraca ID itemu, kt�ry zosta� wyrollowany jako loot
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
    //---------------------------------------------------------------------------------------------------- generuje ilo�� zdobytego itemu
    public static int Roll_Item_Amount(String id, int action, int place, int lv) //zwraca ilo�� wydropionego itemu 
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
    //---------------------------------------------------------------- generuje ilo�� itemu dla okre�lonego poziomu DLA DOWOLNEGO ITEMU i zwraca tablic� zawieraj�c� min i max itemu
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
          //WZ�R NA amount min dla n ------- min n = max n-1
          //WZ�R NA amount max dla n ------- max n = min n *1.75
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
    //---------------------------------------------------------------- generuje XP dla okre�lonego poziomu DLA DOWOLNEGO ITEMU i zwraca tablic� zawieraj�c� XP i XP_range
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
           //WZ�R NA LV n = (( (100-place chance) + (100 - item chance) * item_xp_multiplier) * place lv )*1.5
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
    //---------------------------------------------------------------- generuje xp range, drop chance i amount range dla ka�dego poziomu miejsca, w kt�rym mo�na znale�� przedmiot
    public static void show_item_amount_chance_xp_properties(String item_id)
    {
        //napisz p�tl� przechodz�c� przez wszystkie miejsca, i kt�ra sprawdza, czy wgl mo�na znale�� ten item w tym miejscu
    }
    //BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
    //BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB BOLB UTILITY COMMANDS BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
    //=====================================================================================================================================================================
    //============================================================================================================ find_bolb_owner
    //---------------------------------------------------- przeszukuje list� graczy i por�wnje z id ownera bolba. Kiedy znajdzie takie samo id, zwraca pozycj� w player_array
    public static int find_bolb_owner(String owner_id)
    {
        for(int i = 0; i < player_array.size(); i++)
        {
            if(owner_id.equals(player_array.get(i).entity_id)) //je�li znalaz�o w�a�ciciela, to zwraca jego numer
                return i;
        }
        return -1; //je�li co� posz�o nie tak
    }
    //=====================================================================================================================================================================
    //============================================================================================================ player_ADD_bolb
    //--------------------------------------------------------------------------------------------- dodaje wybranego Bolba do listy bolb�w gracza
    public static void player_ADD_bolb(String player_id, String bolb_id)// X PSUJE CA�Y PROGRAM!!
    {
        player_map.get(player_id).entity_bolblist.add((bolb_map.get(bolb_id)));
    }   
    //=====================================================================================================================================================================
    //============================================================================================================ player_REMOVE_bolb
    //--------------------------------------------------------------------------------------------- usuwa wybranego Bolba z listy bolb�w gracza PERMAMENTNIE!!
    public static void player_REMOVE_bolb(String player_id, String bolb_id)
    {
        player_map.get(player_id).entity_bolblist.remove((bolb_map.get(bolb_id)));
        bolb_map.get(bolb_id).bolb_owner_id = "TO_REMOVE";
    }
    //=====================================================================================================================================================================
    //============================================================================================================ UPDATE_B_brushing
    //--------------------------------------------------------------------------------------------- aktualizuje status czesania bolb�w na nowy dzie�
    public static void UPDATE_B_brushing()
    {
        for(int i = 0; i < bolb_array.size(); i++)
        {
            bolb_array.get(i).bolb_brushing = false; //zeruje dla ka�dego bolba czesanie na nowy dzie�
        }
    }
    
}
