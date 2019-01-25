
package rpg_express_0.pkg2;

import java.util.ArrayList;
import static rpg_express_0.pkg2.Utility_Commands.Calculate_next_n;
import static rpg_express_0.pkg2.Utility_Commands.String_to_int;
import static rpg_express_0.pkg2.Utility_Commands.building_array;
import static rpg_express_0.pkg2.Utility_Commands.building_map;
import static rpg_express_0.pkg2.Utility_Commands.entity_item_id_length;
import static rpg_express_0.pkg2.Utility_Commands.item_map;
import static rpg_express_0.pkg2.Utility_Commands.warehouse_item_array;


public class Building 
{
    public Building(String ID, int Class, String Race, double hp_multi, int HP_base, int wood_base, int stone_base, String upgrade_recipes)
    {
        setId(ID);
        setClass(Class);
        setRace(Race);
        setHP(HP_base);
        setMaxHP(HP_base);
        setTab_multi_bases(hp_multi, HP_base, wood_base, stone_base); // + sets HP of the buildings!!
        setUpgrade_Recipes(upgrade_recipes);
    }
    
    public int building_class = -1;
    public String building_id = "bX_00";
    public String building_name = "Test";
    public String building_race = "Race_Test";
    public int[] building_hp = new int[2]; //[0] - max hp accorded to the lvl, [1] - actual hp
    public int building_Lv = 0; //startowe;
    public ArrayList<String> building_upgrade_recipes = new ArrayList<>();
    
    private double building_tab_multiplier_bases [][] = new double[2][3];
    // [0] --- multipliery ----- tab = [0 ---> hp_multiplier // 1  ---> wood_multiplier // 2 ---> stone_multiplier]
    // [1] bazowe staty ------- [0] hp, [1] wood, [2] stone
    
    
    //==================================================================================================================================
    //================================================================================================== FUNKCJE
    //==================================================================================================================================
    
    //===========================================
    //------------------------------------ CONSTRUCTOR -------------------
    //===========================================
    private void setId(String ID)
    {
        this.building_id = ID;
        building_map.put(ID, this);
        building_array.add(this);
    }
    //===========================================
    private void setClass(int C)
    {
        this.building_class = C;
        switch(C)
        {
            case 1:
            {
                this.building_name = "Castle";
                break;
            }
        
            case 2:
            {
                this.building_name = "Houses";
                break;
            }
        
            case 3:
            {
                this.building_name = "Warehouse";
                break;
            }
        
            case 4:
            {
                this.building_name = "Blacksmith";
                break;
            }
        
            case 5:
            {
                this.building_name = "Barracks";
                break;
            }
        
            case 6:
            {
                this.building_name = "Marketplace";
                break;
            }
            
            case 7:
            {
                this.building_name = "Bridge";
                break;
            }            
            
            default:
            {
                System.err.println("ERROR: Cannot set Building's name, according to class number! (Building.SetClass)");
            }
        }
    }
    //===========================================
    private void setRace(String R)
    {
        this.building_race = R;
    }
    //===========================================
    private void setTab_multi_bases(double hp_multi, int HP_base, int wood_base, int stone_base)
    {
        //ustawianie bazowych statystyk
        this.building_tab_multiplier_bases[1][0] = HP_base;
        this.building_tab_multiplier_bases[1][1] = wood_base;
        this.building_tab_multiplier_bases[1][2] = stone_base;
        
        //ustawianie multiplierów [0]
        this.building_tab_multiplier_bases[0][0] = hp_multi;
        switch(this.building_race) //ustawia wood/stone multiplier w zale¿noœci od "rasy" budynku
        {
            case "Azgoran":
            {
                this.building_tab_multiplier_bases[0][1] = 1.65; //wood
                this.building_tab_multiplier_bases[0][2] = 1.75; //stone
                break;
            }
            case "Duargian":
            {
                this.building_tab_multiplier_bases[0][1] = 1.8; //wood
                this.building_tab_multiplier_bases[0][2] = 1.6; //stone
                break;
            }
            case "Jessari":
            {
                this.building_tab_multiplier_bases[0][1] = 1.75; //wood
                this.building_tab_multiplier_bases[0][2] = 1.65; //stone
                break;
            }
            case "Nyrion":
            {
                this.building_tab_multiplier_bases[0][1] = 1.6; //wood
                this.building_tab_multiplier_bases[0][2] = 1.8; //stone
                break;
            }
            default:
            {
                System.err.println("ERROR: UNKOWN RACE TYPE! (Building.setTab_multi_bases)");
                break;
            }
        }
        
    }
    //===========================================
    private void setUpgrade_Recipes(String base_string)
    {
        String temp = "";
        
        for(int i = 0; i< base_string.length(); i++)
        {
            if(base_string.charAt(i) == '&')
            {
                this.building_upgrade_recipes.add(temp);
                temp = "";
            }
            else
                temp+= base_string.charAt(i);
        }
    }
    //===========================================
    //------------------------------------ PUBLIC SET COMMANDS -------------------
    //===========================================
    //-------------------------------------------------------------------------------------------------- odejmuje/dodaje do aktualnego HP
    public void addHP(int add_hp)
    {
        if(this.building_hp[1] + add_hp < this.building_hp[0])
            this.building_hp[1] += add_hp;
        else if(this.building_hp[1] + add_hp <= 0)
        {
            System.out.println("WARNING: YOUR BUILDING HAS BEEN DESTROYED! (Building.addHP)");
            this.building_hp[1] = 0; //ustawia aktualne hp na 0, bo budynek jest zniszczony
        }
        else
            this.building_hp[1] = this.building_hp[0]; //ustawia max hp dla aktualnego
    }
    //-------------------------------------------------------------------------------------------------- ustawianie aktualnego HP
    public void setHP(int new_hp)
    {
        this.building_hp[1] = new_hp;
    }
    //-------------------------------------------------------------------------------------------------- ustawianie nowego max HP
    public void setMaxHP(int new_max_hp)
    {
        this.building_hp[0] = new_max_hp;
    }
    //-------------------------------------------------------------------------------------------------- lvl up - aktualizacja hp i wymagañ
    public void LvUp()
    {
        this.building_Lv++;
        //szybka konwersja na int, ¿eby mo¿na by³o wygodniej wpisaæ kod
        int[] old_base = new int[3];
        old_base[0] = (int)this.building_tab_multiplier_bases[1][0];
        old_base[1] = (int)this.building_tab_multiplier_bases[1][1];
        old_base[2] = (int)this.building_tab_multiplier_bases[1][2];
        
        this.building_tab_multiplier_bases[1][0] = Calculate_next_n(old_base[0], this.building_tab_multiplier_bases[0][0]); //nowe hp
        this.building_tab_multiplier_bases[1][0] = Calculate_next_n(old_base[1], this.building_tab_multiplier_bases[0][1]); //nowy wood base
        this.building_tab_multiplier_bases[1][0] = Calculate_next_n(old_base[2], this.building_tab_multiplier_bases[0][2]); //nowy stone base
        
                
        setMaxHP( (int)this.building_tab_multiplier_bases[1][0] );
        setHP( (int)this.building_tab_multiplier_bases[1][0] );
    }
    //-------------------------------------------------------------------------------------------------- ulepszanie budynku do kolejnego poziomu
    public void building_upgrade()
    {
        //sprawdzenie, czy rasa ma wystarczaj¹c¹ iloœæ surowcow na upgrade w warehousie
        
        int req_wood = (int)(this.building_tab_multiplier_bases[1][1]); // wood_base
        int req_stone = (int)(this.building_tab_multiplier_bases[1][2]); // stone_base

        String ware_id = ""; //id warehouse'a dla tej samej rasy co ulepszany budynek
        //szukanie warehouse rasy
        for(int i = 0; i < building_array.size(); i++)
        {
            //jeœli znajdzie warehouse, który ma t¹ sam¹ rasê co ulepszany budynek
            if(  (building_array.get(i).building_class == 3) && (building_array.get(i).building_race.equals(this.building_race))   ) 
            {
                ware_id = building_array.get(i).building_id;
                break;
            }
        }
        ////WA¯NE!! 
        //((Warehouse)building_map.get(ware_id). 

        //wejœcie do warehouse dla tej rasy i podliczenie itemów klasy wood i stone
        //przechodzi przez wszystkie dostêpne itemy, sprawdza ich wartoœæ, czy spe³niaj¹ warunek
        if( (Inv_base_check(1, (int)this.building_tab_multiplier_bases[1][1], ware_id, "CHECK")) && (Inv_base_check(2, (int)this.building_tab_multiplier_bases[1][2], ware_id,"CHECK")) )
        {
            //jeœli rasa ma wystarczaj¹c¹ iloœæ itemów w warehouse, to dla wy¿szych poziomów sprawdza pozosta³e itemy, a dla poziomu 0 usuwa potrzebne itemy z warehouse'a i buduje
            if(this.building_Lv == 0)
            {
                Inv_base_check(1, req_wood, ware_id, "REMOVE"); 
                Inv_base_check(2, req_stone, ware_id,"REMOVE");

                LvUp();
            }
            else
            {
                if(Inv_rest_check(ware_id)) //jesli uda³o siê poprawnie zebraæ resztê itemów potrzebnych do upgrade'u
                {
                    Inv_base_check(1, req_wood, ware_id, "REMOVE"); 
                    Inv_base_check(2, req_stone, ware_id,"REMOVE");
                    LvUp();
                }
            }
                
        }
        else
            System.err.println("ERROR: CANNOT UPGRADE BUILDING - TOO LITTLE RESOURCES IN WAREHOUSE! (Building.building_upgrade)");
        
    }
    //-------------------------------------------------------------------------------------------------- sprawdza, czy warehouse ma wystarczaj¹co bazowych resourców (wood/stone)
    public boolean Inv_base_check(int mode, int amount_needed, String ware_id, String action)
    {
        String base_type = "";
        switch(mode)
        {
            case 1:
            {
                base_type = "wood";
                break;
            }
            
            case 2:
            {
                base_type = "stone";
                break;
            }
            
            default:
            {
                System.out.println("ERROR: UNKNOWN CHECKING MODE! (Building.Inv_base_check)");
                return false;
            }
        }
        
        int suma = 0;
        
        String[][] item_tab = new String[((Warehouse)building_map.get(ware_id)).warehouse_inventory.size()][2]; //tworzy tablicê wielkoœci itemów w warehousie
        load_ware_inv(ware_id, item_tab);
        //=============================================================================================================================================================
        //=============================================================================================================================================================
        //================================================================= SWITCH LATER - CHECK/REMOVAL
        //=============================================================================================================================================================
        //=============================================================================================================================================================
        if(action.equals("CHECK"))//przechodzi przez uzyskane itemy i sprawdza te, które s¹ równe z warunkiem
        {
            for(int i = 0; i < ((Warehouse)building_map.get(ware_id)).warehouse_inventory.size(); i++)
            {
                //jeœli znalaz³o item, to sprawdza, ile tego itemu by³oby potrzebne do uzupe³nienia amount_needed
               if(item_map.get(item_tab[i][0]).item_class.equals(base_type)) 
               {
                   if( suma + String_to_int(item_tab[i][1]) >= amount_needed) //jeœli uda mu siê spe³niæ warunek
                   {
                       return true;                   
                   }
                   else
                       suma += String_to_int(item_tab[i][1]); //dodaje kolejne  iloœci itemów, dopóki s¹ i spróbuje spe³niæ warunek
               }
            }
        }
        else if(action.equals("REMOVE"))//przechodzi przez uzyskane itemy i usuwa odpowiedni¹ iloœæ itemów zgodn¹ z warunkiem
        {
            for(int i = 0; i < ((Warehouse)building_map.get(ware_id)).warehouse_inventory.size(); i++)
            {
                //jeœli znalaz³o item, to sprawdza, ile tego itemu by³oby potrzebne do uzupe³nienia amount_needed
               if(item_map.get(item_tab[i][0]).item_class.equals(base_type)) 
               {
                   if( suma + String_to_int(item_tab[i][1]) >= amount_needed) //jeœli uda mu siê spe³niæ warunek
                   {
                       int reszta = amount_needed - suma;
                       ((Warehouse)building_map.get(ware_id)).removeItem(item_tab[i][0], reszta);
                        //usuwa dodany do sumy przedmiot tylko w takiej iloœci, ile go potrzeba, ¿eby spe³niæ warunek amount_needed                       
                       return true;                   
                   }
                   else
                   {
                       suma += String_to_int(item_tab[i][1]); //dodaje kolejne  iloœci itemów, tym samym usuwaj¹c je z ekwipunku, dopóki s¹ i spróbuje spe³niæ warunek
                       ((Warehouse)building_map.get(ware_id)).removeItem(item_tab[i][0], String_to_int(item_tab[i][1])); //usuwa dodany do sumy przedmiot
                   }
               }
            }
        }
        return false; // jeœli nie uda³o siê spe³niæ warunku iloœci
    }
    //-------------------------------------------------------------------------------------------------- sprawdza, czy warehouse ma pozosta³e itemy potrzebne do upgrade'u budynku
    public void load_ware_inv(String ware_id, String item_tab[][])
    {
        String temp = "";
        //przechodzi przez wszystkie dostêpne itemy, wbija je do tablicy
        for(int i = 0; i < ((Warehouse)building_map.get(ware_id)).warehouse_inventory.size(); i++) //przechodzi przez wszystkie itemy w tablicy
        {
            //przechodzi przez wszystkie znaki w stringu i rozdrabnia je na nazwê itemu i iloœæ
            for(int j = 0; j < ((Warehouse)building_map.get(ware_id)).warehouse_inventory.get(i).length(); j++) 
            {
                if( ((Warehouse)building_map.get(ware_id)).warehouse_inventory.get(i).charAt(j) == '!' ) //koniec itemu
                {
                    item_tab[i][0] = temp;
                    temp = "";                        
                }
                else if( ((Warehouse)building_map.get(ware_id)).warehouse_inventory.get(i).charAt(j) == '?' ) //koniec iloœci
                {
                    item_tab[i][1] = temp;
                    temp = "";
                }
                else
                    temp += ((Warehouse)building_map.get(ware_id)).warehouse_inventory.get(i).charAt(j);
            }
        }
    }
    //-------------------------------------------------------------------------------------------------- sprawdza, czy warehouse ma pozosta³e itemy potrzebne do upgrade'u budynku
    public boolean Inv_rest_check(String ware_id)
    {
        String upgrade_recipe = this.building_upgrade_recipes.get(building_Lv-1); //-1, bo poziom budynku to 1, a na liœcie recipe nr 0
        
        int item_c = 0;
        //przechodzi przez ca³ego stringa i liczy iloœæ itemów
        for(int i = 0; i < upgrade_recipe.length(); i++)
        {
                if(upgrade_recipe.charAt(i) == '!')
                    item_c++;
                else if(upgrade_recipe.charAt(i) == '?')
                {
                    item_c++;
                    break;
                }            
        }
        
        //tworzy tablicê na podstawie wyliczonych przedmiotów
        String[][] item_tab = new String[item_c][2]; // A [X] [ nazwa itemu] -- 0,  B [X][ iloœæ itemu] -- 1
        String temp = "";
        
        boolean number_mode = false;
        for(int i = 0, A = 0; i < upgrade_recipe.length(); i++)
        {
            if(number_mode == false) //jeœli number_mode jest false
            {
                if(upgrade_recipe.charAt(i) == '!')
                {
                    item_tab[A][0] = temp; //wstawia item id
                    A++;
                    temp = "";
                }
                else if(upgrade_recipe.charAt(i) == '?')
                {
                    item_tab[A][0] = temp; //wstawia ostatni item id
                    A = 0;
                    number_mode = true;//ustawia tryb wstawiania liczb
                    temp = "";
                }
                else
                    temp += upgrade_recipe.charAt(i);
            }
            else//jeœli number_mode jest true
            {
                if(upgrade_recipe.charAt(i) == '!')
                {
                    item_tab[A][1] = temp; //wstawia item id
                    A++;
                    temp = "";
                }
                else
                    temp += upgrade_recipe.charAt(i);
            }
        }
        //po skoñczeniu wczytywania listy itemów, wczytuje dostêpne itemy z warehouse'a
        String[][] ware_item_tab = new String[((Warehouse)building_map.get(ware_id)).warehouse_inventory.size()][2]; //tworzy tablicê wielkoœci itemów w warehousie
                
        load_ware_inv(ware_id, ware_item_tab);//wczytuje itemy z warehouse
        
        //sprawdza czy ka¿dy item w warehouse jest w wystarczaj¹cej iloœci do upgrade'u
        
        boolean item_check = false;
        for(int i = 0; i < item_c; i++) // przechodzi przez wszystkie itemy i sprawdza czy s¹ dostêpne
        {
            for(int j = 0; j < ((Warehouse)building_map.get(ware_id)).warehouse_inventory.size(); j++) //przechodzi przez ca³y warehouse i sprawdza, czy jest taki item
            {
                if(item_tab[i][0].equals(ware_item_tab[j][0])) //jeœli znajdzie przedmiot w warehouse, sprawdza jego iloœæ
                {
                    if(String_to_int(item_tab[i][1]) <= String_to_int(ware_item_tab[j][1])) //jeœli w warehouse jest wystarczaj¹co przedmiotu to wychodzi z pêtli i item_check = true dla tego itemu tylko
                    {
                        item_check = true;
                        break;
                    }
                }                
            }
            if(item_check == false) //jeœli item_check jest false, to wychodzi z pêtli i zwraca b³¹d
            {
                System.err.println("ERROR: NOT ENOUGH ITEMS TO PERFORM UPGRADE! (Building.Inv_rest_check.item_check=false)");
                return false;
            }
            item_check = false; //zeruje item_check dla nastêpnego przedmiotu
        }
        //jeœli uda mu siê przejœæ ca³y item check to idzie dalej i usuwa przedmioty z warehouse, które s¹ potrzebne do upgrade'u
        for(int i = 0; i < item_c; i++)
        {
            ((Warehouse)building_map.get(ware_id)).removeItem(item_tab[i][0], String_to_int(item_tab[i][1]));
        }
        return true;
    }
}

//====================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================

class Warehouse extends Building 
{
    public Warehouse(String ID, int Class, String Race, double hp_multi, int HP_base, int wood_base, int stone_base, double cap_multi, int capacity, String upgrade_recipes)
    {
        super(ID, Class, Race, hp_multi, HP_base, wood_base, stone_base, upgrade_recipes);//to ju¿ siê wywo³uje w klasie wy¿ej
        setCapacity(capacity);
        setCap_multi(cap_multi);
    }
    /* BASE FROM BUILDING
    public int building_class = -1;
    public String building_id = "bX_00";
    public String building_name = "Test";
    public String building_race = "Race_Test";
    public int[] building_hp = new int[2]; //[0] - current max hp accorded to the lvl, [1] - actual hp
    public int building_Lv = 0; //startowe;
    
    private double building_tab_multiplier_bases [][] = new double[2][3];
    // [0] --- multipliery ----- tab = [0 ---> hp_multiplier // 1  ---> wood_multiplier // 2 ---> stone_multiplier]
    // [1] bazowe staty ------- [0] hp, [1] wood, [2] stone*/
    public int capacity = -1;
    public double cap_multi = -1;
    public ArrayList<String> warehouse_inventory = new ArrayList<>(); // [id_itemu1]![id_itemu2]?[ilosc_itemu1]![ilosc_itemu2]! //patrz crafting recipes
    
    
    //===========================================
    //------------------------------------ CONSTRUCTOR -------------------
    //===========================================
    private void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }
    //===========================================
    private void setCap_multi(double cap_multi)
    {
        this.cap_multi = cap_multi;
    }
    //===========================================
    //===========================================
    //------------------------------------ PUBLIC COMMANDS -------------------
    //===========================================
    //-------------------------------------------------------------------------------------------------- wstawianie itemu do ekwipunku z zabezpieczeniem
    public boolean add_item(String Item_ID, int amount)
    {
        if(amount > 0)
        {   
            if(amount > this.capacity) //zabezpieczenie przed prze³adowaniem
            {
                //zabezpieczenie przed wrzucaniem œmieci
                if(warehouse_item_check(Item_ID))
                {
                    String sample = "";
                    String amount_string = "";
                    int int_amount_string = 0;
                    int new_amount = 0;
                    String sample_temp ="";
                    String string_temp ="";
                    //wzór itemu w liœcie: i0_001!20?

                    for(int i=0; i< this.warehouse_inventory.size(); i++)
                    {
                        sample_temp = "";
                        sample = this.warehouse_inventory.get(i);

                        for(int j=0; j<entity_item_id_length; j++) //pobiera e0_000 z listy przedmiotów w ekwipunku i zapisuje jako sample_temp
                            sample_temp += sample.charAt(j);

                        if(sample_temp.equals(Item_ID) ) //jeœli znajdzie poszukiwany przedmiot
                        {
                            for(int k = entity_item_id_length+1; k< sample.length(); k++) //na entity_item_id_length-tym miejscu jest '!', dlatego zaczynamy od entity_item_id_length+1 miejsca
                            {
                                if(sample.charAt(k) == '?') //jeœli znajdzie pytajnik, konwertuje liczbê - aktualn¹ iloœæ przedmiotu
                                {
                                    //sprawdza, czy po dodaniu aktualnej iloœci itemu nie przekroczy capacity
                                    if(int_amount_string + amount <= this.capacity)
                                    {
                                        int_amount_string = Integer.parseInt(amount_string);
                                        new_amount = amount + int_amount_string;

                                        string_temp += Item_ID+"!"+new_amount+"?";

                                        this.warehouse_inventory.remove(i);//usuwa starego stringa z nieaktualnymi danymi
                                        this.warehouse_inventory.add(string_temp); //wstawia nowego strina z aktualnymi danymi
                                        System.out.println("** Item has been updated succesfully. **"); //=========================================== CHECKING STUFF
                                        return true;
                                    }
                                    else
                                    {
                                        System.err.println("ERROR: The amount of item you want to put into the warehouse and actual amount of item are greater than warehouse's capacity! (Warehouse.add_item)");
                                        return false;
                                    }
                                }
                                amount_string += sample.charAt(k); //mieli liczbê póki mo¿e
                            }
                        }
                    }
                    //===================================================
                    String temp = Item_ID+"!"+amount+"?"; //nie znalaz³ itemu, wiêc go wstawia do listy teraz
                    this.warehouse_inventory.add(temp);
                    System.out.println("** Item has been added succesfully. **"); //=============================================================== CHECKING STUFF
                    return true;
                }
                else
                {
                    System.err.println("ERROR: You can't add this item here! (Warehouse.add_item)");
                    return false;
                } 
            }
            else
            {
                System.err.println("ERROR: The amount of item you want to put into the warehouse is greater than entire warehouse's capacity! (Warehouse.add_item)");
                return false;
            }
        }
        else
        {
            System.err.println("ERROR: You can't add less than 1 piece of item! (Warehouse.add_item)");
            return false;
        }
    }
    //-------------------------------------------------------------------------------------------------- wyœwietlenie/pobranie informacji ile przedmiotu znajduje siê w ekwipunku
    public int showItemAmount(String item_id)
    {
        String sample = "";
        String amount_string = "";
        int int_amount_string = 0;
        String sample_temp ="";
        String string_temp ="";
        
        for(int i=0; i< this.warehouse_inventory.size(); i++)
        {
            sample = this.warehouse_inventory.get(i);
            for(int j=0; j<entity_item_id_length; j++) //pobiera e0_000 z listy przedmiotów w ekwipunku i zapisuje jako sample_temp
                sample_temp += sample.charAt(j);
            for(int j=0; j<entity_item_id_length; j++) //pobiera e0_000 z item_id i zapisuje jako string_temp
                string_temp+=item_id.charAt(j);
            if(sample_temp.equals(string_temp) ) //jeœli znajdzie poszukiwany przedmiot
            {
                for(int k = entity_item_id_length+1; k< sample.length(); k++) //na 6-tym miejscu jest '!', dlatego zaczynamy od 7-go miejsca
                {
                    if(sample.charAt(k) == '?') //jeœli znajdzie pytajnik, konwertuje liczbê - aktualn¹ iloœæ przedmiotu
                    {
                        int_amount_string = Integer.parseInt(amount_string);
                        return int_amount_string;
                        
                    }
                    amount_string += sample.charAt(k); //mieli liczbê póki mo¿e
                }
            }
        }
        System.err.println("ERROR: Cannot return item amount - There's no such item! (Warehouse.showItemAmount)");
        return -1;
    }
    //-------------------------------------------------------------------------------------------------- usuwanie itemu z ekwipunku
    public boolean removeItem(String Item_ID, int amount)
    {
        String sample = "";
        String amount_string = "";
        int int_amount_string = 0;
        int new_amount = 0;
        String sample_temp ="";
        String string_temp ="";
        
        for(int j=0; j<entity_item_id_length; j++) //pobiera e0_000 z Item_ID i zapisuje jako string_temp
                string_temp+=Item_ID.charAt(j);
        
        for(int i=0; i< this.warehouse_inventory.size(); i++)
        {
            sample = this.warehouse_inventory.get(i);
            for(int j=0; j<entity_item_id_length; j++) //pobiera e0_000 z listy przedmiotów w ekwipunku i zapisuje jako sample_temp
                sample_temp += sample.charAt(j);
            
            if(sample_temp.equals(string_temp) ) //jeœli znajdzie poszukiwany przedmiot
            {
                for(int k = entity_item_id_length+1; k< sample.length(); k++) //na 6-tym miejscu jest '!', dlatego zaczynamy od 7-go miejsca
                {
                    if(sample.charAt(k) == '?') //jeœli znajdzie pytajnik, konwertuje liczbê - aktualn¹ iloœæ przedmiotu
                    {
                        int_amount_string = Integer.parseInt(amount_string);
                        if((new_amount = int_amount_string - amount) >0) // jeœli zostanie jeszcze troche itemu
                        {
                            string_temp += "!"+new_amount+"?";
                            this.warehouse_inventory.remove(i);//usuwa starego stringa z nieaktualnymi danymi
                            this.warehouse_inventory.add(string_temp); //wstawia nowego strina z aktualnymi danymi
                            System.out.println("** Item has been substracted succesfully. **"); //=========================================================================================================== CHECKING STUFF
                            return true;
                        }
                        else if((new_amount = int_amount_string - amount) == 0) // jeœli zostanie jeszcze troche itemu
                        {
                            this.warehouse_inventory.remove(i);//usuwa starego stringa z nieaktualnymi danymi i nie wstawia nowego bo zosta³o 0, czyli nic
                            System.out.println("** Item has been removed succesfully. **"); //============================================================================================================== CHECKING STUFF
                            return true;
                        }
                        else if((new_amount = int_amount_string - amount) < 0) // jeœli zostanie jeszcze troche itemu
                        {
                            //informuje, ¿e nie uda³o siê usun¹æ, bo nie ma tyle itemu
                            System.err.println("ERROR: The item has not been removed - there's too little of it! (Warehouse.removeItem)");
                            return false;
                        }
                        
                    }
                    amount_string += sample.charAt(k); //mieli liczbê póki mo¿e
                }
            }
            else
                sample_temp = "";
        }
        System.err.println("ERROR: The item has not been removed - There's no such item! (Warehouse.removeItem)");
        return false;
        
    }
    //-------------------------------------------------------------------------------------------------- pokazuje ca³y dostêpny ekwipunek warehouse'a
    public void showInventory()
    {
        System.out.println("========================================================================");
        System.out.println( this.building_race + "'s Warehouse: ");
        if(this.warehouse_inventory.size() > 0) //sprawdza, czy jest coœ w ekwipunku
        {   
            String item = "";
            String item_amount_string = "";
            String item_id = "";            
            
            for(int i=0; i< this.warehouse_inventory.size(); i++)
            {
                //---zeruje wszystko!
                item_amount_string = "";
                item_id = "";
                
                item = this.warehouse_inventory.get(i);
                
                for(int j=0; j<entity_item_id_length; j++) //pobiera e0_000 z listy przedmiotów w ekwipunku i zapisuje jako sample_temp
                    item_id += item.charAt(j);

                for(int k = entity_item_id_length+1; k< item.length(); k++) //na entity_item_id_length - w tym miejscu jest '!', dlatego zaczynamy od entity_item_id_length+1 miejsca
                {
                    if(item.charAt(k) == '?') //jeœli znajdzie pytajnik, oznacza to koniec sklejania liczby- aktualn¹ iloœæ przedmiotu
                        break; // wychodzi z pêtli
                    else
                        item_amount_string += item.charAt(k); //dokleja cyfry do liczby póki mo¿e
                }
                //------ po przeliczeniu ca³ego itemku oraz jego iloœci, wypisuje go.
                System.out.println("* Item name: " + item_map.get(item_id).item_name + ", Class: " + item_map.get(item_id).item_class + ", Amount: " + item_amount_string + 
                                                                                                                    ", Description: " + item_map.get(item_id).item_description);
            }
        }
        else
        {
            System.out.println("EMPTY!");
        }
        System.out.println("========================================================================");
    }
    //-------------------------------------------------------------------------------------------------- sprawdza, czy item jest na liœcie dozwolonych przedmiotów do dodania do warehouse
    public boolean warehouse_item_check(String Item_ID)
    {
        for (String sample : warehouse_item_array) 
        {
            if(sample.equals(Item_ID))
                return true;
        }
        return false;   
    }
}