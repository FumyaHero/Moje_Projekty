package rpg_express_0.pkg2;

import static rpg_express_0.pkg2.Utility_Commands.Roll; //importuje komendê Roll


import java.util.ArrayList;
import static rpg_express_0.pkg2.Utility_Commands.A_ACTION;
import static rpg_express_0.pkg2.Utility_Commands.B_PLACE;
import static rpg_express_0.pkg2.Utility_Commands.Loot_Item;
import static rpg_express_0.pkg2.Utility_Commands.Roll_Item_Amount;
import static rpg_express_0.pkg2.Utility_Commands.String_to_int;
import static rpg_express_0.pkg2.Utility_Commands.building_array;
import static rpg_express_0.pkg2.Utility_Commands.building_map;
import static rpg_express_0.pkg2.Utility_Commands.crafting_recipes_tab;
import static rpg_express_0.pkg2.Utility_Commands.entity_item_id_length;
import static rpg_express_0.pkg2.Utility_Commands.gather_actions_event_chance_tab;
import static rpg_express_0.pkg2.Utility_Commands.gather_actions_npc_reaction_chance;
import static rpg_express_0.pkg2.Utility_Commands.gather_actions_place_item_loot;
import static rpg_express_0.pkg2.Utility_Commands.generate_XP;
import static rpg_express_0.pkg2.Utility_Commands.item_map;
import static rpg_express_0.pkg2.Utility_Commands.look_around_roll_event;
import static rpg_express_0.pkg2.Utility_Commands.player_array;
import static rpg_express_0.pkg2.Utility_Commands.player_map;


public class Entity 
{
    public Entity(String ID, String N, String R)
    {
        setId(ID);
        setName(N);
        setRace(R);
    }
    //==================================================================================================================================
    //================================================================================================== ZMIENNE
    //==================================================================================================================================
    public String entity_id = "e0_000";
    public String entity_name = "Test";
    public String entity_race = "Race_Test";
    public int entity_quest = -1; //startowe -1 oznacza, ¿e nie ma ¿adnego questa! ============================ WIP WIP WIP WIP WIP WIP WIP???
    public int entity_tokens = 2; //startowe
    public int entity_influencePoints = 1; //startowe
    /*NEW*/ public int[] craftingPoints = new int[2]; //0 --> lvl, 1 --> exp
    ArrayList<String> entity_inventory = new ArrayList<>(); // [id_itemu]![ilosc_itemu]? -------------> ? oznacza koniec liczby np. i1_01!12?
    ArrayList<Bolb> entity_bolblist = new ArrayList<>();
    //tab[A][B][D]
    public int[][][] entity_gather_actions_Lv_Tab = new int
                    [A_ACTION] //[numer akcji zbieraj¹cej itemy]
                    [B_PLACE] //o 1 wiêcej dla [nazwa miejsca akcji] ni¿ potrzeba bo 0 jest placeholderem
                    [2]; //[poziom miejsca ->[0]/ XP miejsca ->[1] ]
    
    /*tab [nazwa akcji zbieraj¹cej itemy -> 0 - ???][nazwa miejsca dla akcji -> 1-5][poziom miejsca/xp miejsca]
    nazwy zbieraj¹cych akcji:
        * 0 --> look_around
        (wip)
    nazwy miejsc dla akcji:
        * 0 --> placeholder
        * 1 --> Moonshore
        * 2 --> Lemon Beach
        * 3 --> Arcane Forest
        * 4 --> Dark Forest
        * 5 --> Sorri's Swamps (limited reach only!)
    trzeci rz¹d:
        * 0 --> poziom wybranego miejsca z wybranej akcji
        * 1 --> exp wybranego miejsca z wybranej akcji
    */
    

    
    //wstawiæ listê Stringów jako statystyki postaci - FAR FUTURE!!
    

    //==================================================================================================================================
    //================================================================================================== FUNKCJE
    //==================================================================================================================================
    
    //===========================================
    //------------------------------------ CONSTRUCTOR -------------------
    //===========================================
    
    private void setId(String ID)
    {
        this.entity_id = ID;
        
        player_map.put(ID, this); //sam siê wstawia do mapy
        player_array.add(this); //sam siê wstawia do array
    }
    //===========================================
    private void setName(String N)
    {
        this.entity_name = N;
    }
    //===========================================
    private void setRace(String R)
    {
        this.entity_race = R;
    }
  
    //===========================================
    //------------------------------------ PUBLIC SET COMMANDS -------------------
    //===========================================
    public void setTokens(int T)
    {
        if(T >=0)
            this.entity_tokens = T;
        else
            System.err.println("ERROR: You can set tokens below 0! (Entity.setTokens)");
    }
    //===========================================
    public void setInfluencePoints(int P)
    {
        if(P >= 0)
            this.entity_influencePoints = P;
        else
            System.err.println("WARNING: Your Influence Points are below 0! (Entity.setInfluencePoints)");
    }
    //===========================================
    public void setLv(int action_number, int place, int Lv)
    {
        if(Lv >= 0)
        {
            //[nazwa akcji zbieraj¹cej itemy][nazwa miejsca dla akcji -> 1-4][poziom miejsca/xp miejsca]
            this.entity_gather_actions_Lv_Tab[action_number][place][0] = Lv;
        }
        else
            System.err.println("ERROR: You can't set level below 0! (Entity.setLv)");
    }
    //===========================================
    public void setXP(int action_number, int place, int XP)
    {
        if(XP >= 0)
        {
            //[nazwa akcji zbieraj¹cej itemy][nazwa miejsca dla akcji -> 1-4][poziom miejsca/xp miejsca]
            this.entity_gather_actions_Lv_Tab[action_number][place][1] = XP;
        }
        else
            System.err.println("ERROR: You can't set XP less than 0! (Entity.setXP)");
    }
    
    
    //===========================================
    //------------------------------------ PUBLIC ADD COMMANDS -------------------
    //===========================================
    public void addTokens(int T)
    {
        if(this.entity_tokens + T <0)
            System.err.println("ERROR: Can't take anymore tokens. The action has been cancelled. (Entity.addTokens)"); // CZY TO JEST TUTAJ WGL POTRZEBNE??
        else
            this.entity_tokens += T;
    }
    //===========================================
    public void addInfluencePoints(int P)
    {
        this.entity_influencePoints += P;
        if(this.entity_influencePoints <0)
            System.err.println("WARNING: Your Influence Points are below 0! (Entity.addInfluencePoints)");
    }
    //===========================================
    public void addLv(int action_number, int place, int Lv)
    {
        if(this.entity_gather_actions_Lv_Tab[action_number][place][0] + Lv >= 0)
        {
            //[nazwa akcji zbieraj¹cej itemy][nazwa miejsca dla akcji -> 1-4][poziom miejsca/xp miejsca]
            this.entity_gather_actions_Lv_Tab[action_number][place][0] += Lv;
        }
        else
            System.err.println("ERROR: You can't make level below 0! (Entity.addLv)");
    }
    //===========================================
    public void addXP(int action_number, int place, int XP)
    {
        if(this.entity_gather_actions_Lv_Tab[action_number][place][1] + XP >= 0)
        {
            //[nazwa akcji zbieraj¹cej itemy][nazwa miejsca dla akcji -> 1-4][poziom miejsca/xp miejsca]
            this.entity_gather_actions_Lv_Tab[action_number][place][1] += XP;
        }
        else
            System.err.println("ERROR: You can't make XP less than 0! (Entity.addXP)");
    }
    //===========================================
    public boolean addItem(String Item_ID, int amount)
    {
        if(amount > 0)
        {   
            String sample = "";
            String amount_string = "";
            int int_amount_string = 0;
            int new_amount = 0;
            String sample_temp ="";
            String string_temp ="";
            
            
            for(int i=0; i< this.entity_inventory.size(); i++)
            {
                sample_temp = "";
                sample = this.entity_inventory.get(i);
                
                for(int j=0; j<entity_item_id_length; j++) //pobiera e0_000 z listy przedmiotów w ekwipunku i zapisuje jako sample_temp
                    sample_temp += sample.charAt(j);

                if(sample_temp.equals(Item_ID) ) //jeœli znajdzie poszukiwany przedmiot
                {
                    for(int k = entity_item_id_length+1; k< sample.length(); k++) //na entity_item_id_length-tym miejscu jest '!', dlatego zaczynamy od entity_item_id_length+1 miejsca
                    {
                        if(sample.charAt(k) == '?') //jeœli znajdzie pytajnik, konwertuje liczbê - aktualn¹ iloœæ przedmiotu
                        {
                            int_amount_string = Integer.parseInt(amount_string);
                            new_amount = amount + int_amount_string;
                            
                            string_temp += Item_ID+"!"+new_amount+"?";
                            
                            this.entity_inventory.remove(i);//usuwa starego stringa z nieaktualnymi danymi
                            this.entity_inventory.add(string_temp); //wstawia nowego strina z aktualnymi danymi
                            System.out.println("** Item has been updated succesfully. **"); //========================================================================================================== CHECKING STUFF
                            return true;
                        }
                        amount_string += sample.charAt(k); //mieli liczbê póki mo¿e
                    }
                }
            }
            //===================================================
            String temp = Item_ID+"!"+amount+"?"; //nie znalaz³ itemu, wiêc go wstawia do listy teraz
            this.entity_inventory.add(temp);
            System.out.println("** Item has been added succesfully. **"); //============================================================================================================================== CHECKING STUFF
            return true;
        }
        else
        {
            System.err.println("ERROR: You can't add less than 1 piece of item! (Entity.addItem)");
            return false;
        }
    }
        //=======================================
    public int showItemAmount(String item_id)
    {
        String sample = "";
        String amount_string = "";
        int int_amount_string = 0;
        String sample_temp ="";
        String string_temp ="";
        
        for(int i=0; i< this.entity_inventory.size(); i++)
        {
            sample = this.entity_inventory.get(i);
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
        System.err.println("ERROR: Cannot return item amount - There's no such item! (Entity.showItemAmount)");
        return -1;
    }
        //=======================================
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
        
        for(int i=0; i< this.entity_inventory.size(); i++)
        {
            sample = this.entity_inventory.get(i);
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
                            this.entity_inventory.remove(i);//usuwa starego stringa z nieaktualnymi danymi
                            this.entity_inventory.add(string_temp); //wstawia nowego strina z aktualnymi danymi
                            System.out.println("** Item has been substracted succesfully. **"); //=========================================================================================================== CHECKING STUFF
                            return true;
                        }
                        else if((new_amount = int_amount_string - amount) == 0) // jeœli zostanie jeszcze troche itemu
                        {
                            this.entity_inventory.remove(i);//usuwa starego stringa z nieaktualnymi danymi i nie wstawia nowego bo zosta³o 0, czyli nic
                            System.out.println("** Item has been removed succesfully. **"); //============================================================================================================== CHECKING STUFF
                            return true;
                        }
                        else if((new_amount = int_amount_string - amount) < 0) // jeœli zostanie jeszcze troche itemu
                        {
                            //informuje, ¿e nie uda³o siê usun¹æ, bo nie ma tyle itemu
                            System.err.println("ERROR: The item has not been removed - there's too little of it! (Entity.removeItem)");
                            return false;
                        }
                        
                    }
                    amount_string += sample.charAt(k); //mieli liczbê póki mo¿e
                }
            }
            else
                string_temp = "";
        }
        System.err.println("ERROR: The item has not been removed - There's no such item! (Entity.removeItem)");
        return false;
        
    }
    
    //===========================================
    //------------------------------------ PUBLIC SHOW COMMANDS COMMANDS -------------------
    //===========================================
    public void showEntity()
    {
        System.out.println("========================================================================");
        System.out.println( this.entity_name + ", the " + this.entity_race);
        System.out.println( "Current amount of tokens: " + this.entity_tokens );
        System.out.println( "Current amount of Influence Points: " + this.entity_influencePoints );
        System.out.println("========================================================================");
    }
        //===========================================
    public void showInventory()
    {
        System.out.println("========================================================================");
        System.out.println( this.entity_name + "'s inventory: ");
        if(this.entity_inventory.size() > 0) //sprawdza, czy jest coœ w ekwipunku
        {   
            String item = "";
            String item_amount_string = "";
            String item_id = "";            
            
            for(int i=0; i< this.entity_inventory.size(); i++)
            {
                //---zeruje wszystko!
                item_amount_string = "";
                item_id = "";
                
                item = this.entity_inventory.get(i);
                
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
    
    
    //===========================================
    //------------------------------------ PUBLIC ACTION COMMANDS -------------------
    //===========================================
    public void action_Lv_up(int action_number, int place, String mode)
    {
        int lv = -1;
        switch(mode)
        {
            case "gather_actions":
            {
                //[nazwa akcji zbieraj¹cej itemy -> 0 - ???][nazwa miejsca dla akcji -> 1-4][poziom miejsca/xp miejsca]
                this.entity_gather_actions_Lv_Tab[action_number][place][0]++;
                lv = this.entity_gather_actions_Lv_Tab[action_number][place][0];
                break;
            }
            case "crafting":
            {
                this.craftingPoints[0]++;
                lv = this.craftingPoints[0];
                break;
            }
            default:
            {
                System.err.println("ERROR: UKNOWN LVL UP MODE! (Entity.action_Lv_up)");
            }
        }
        System.out.println("** Congratulations! Your level has increased to " + lv + "! **");
    }
    //-------------------------------------------------------------------------------------------------- ROLLING AND ADDING XP FROM THE ACTION x
    public void trade(Entity target_player, String item_id, int amount)
    {
        if(this.removeItem(item_id, amount))
        {
            target_player.addItem(item_id, amount);
        }
        else
            System.err.println("ERROR: NOT ENOUGH ITEM TO TRADE WITH! (Entity.trade)");
    }
    //===========================================
    //------------------------------------ CRAFTING COMMANDS -------------------
    //===========================================
    
    //------------------------------------------------------------------------------------------------ ADDING XP TO CRAFTING POINTS
    public void crafting_add_XP(int XP)
    {
        //generowanie górnego progu XP dla craftingu
        
        int xp_edge = 350;
        //craftingPoints [0] - lv
        //craftingPoints [1] - xp
        
        //przemno¿enie bazy o tyle razy, ile wynosi poziom. jeœli 0, to nie mno¿ymy, jeœli 1, to 500*2, jeœli lv 2, to 500*2 * 2, itd.
        if(this.craftingPoints[0] != 0)
        {
            for(int i = 0; i < this.craftingPoints[0]; i++) //mno¿y tak d³ugo, a¿ nie otrzyma wymaganej liczy xp na lvl up dla danego poziomu
                xp_edge *= 2;
        }

        //----- sprawdzanie, czy gracz ma zdoby³ wystarczaj¹co XP na lvl up
        if(this.craftingPoints[1] + XP >= xp_edge)// jeœli suma ca³ego XP jest wiêksza lub równa dla edge danego poziomu
        {
            action_Lv_up(0,0, "crafting"); // gracz dostaje lvl up
            this.craftingPoints[1] = this.craftingPoints[1] + XP - xp_edge;         //przenosi nadwy¿kê XP na kolejny Lv 
        }
        else //jeœli nie dosz³o do lvl up, to po prostu dodaje XP
        {
            this.craftingPoints[1] += XP; 
            System.out.println("You've gained "+ XP +" crafting XP.");
        }
    }
    //------------------------------------------------------------------------------------------------ PERFORMING CRAFT-AN-ITEM ACTIO
    public void craft_item(String crafted_id)
    {
        //sprawdzenie, czy przedmiot istnieje na liœcie
        boolean if_item_exists = false;
        String temp = "";
        int item_id = 0; //liczy który w kolei jest znaleziony item, ¿eby potem siê do niego odwo³aæ
        
        for(String x : crafting_recipes_tab)
        {
            for(int i = 0; i < x.length(); i++)
            {
                if(x.charAt(i) == '!') //jeœli trafi na !, ma porównaæ id itemu z szukanym. Jeœli znalaz³, if_item_exists = true, wychodzi z pêtli
                {
                    if(temp.equals(crafted_id))
                    {
                        if_item_exists = true;
                        
                    }
                    temp = "";
                    break;
                }
                else
                {
                    temp += x.charAt(i);
                }
            }
            if(if_item_exists)
                break;
            item_id++;
        }
        //jeœli item jest na liœcie, przechodzimy dalej
        if(if_item_exists)
        {
            String craft_String = crafting_recipes_tab.get(item_id);
            //sprawdza, czy gracz ma odpowiedni poziom i rasê do craftowania wybranego itemu   ORAZ czy gracz ma itemy potrzebne do craftu - jeœli ma, to je pobiera
            if(  (crafting_stat_check(craft_String)  ) && (crafting_items_check_take(craft_String) )  )
            {
                int item_amount = -1;
                int item_xp = -1;
                
                //rozdrabnianie stringa, ¿eby wyczytaæ ile gracz dostanie XP za craft i ile wycraftowanego itemu
                for(int i = 0; i <craft_String.length(); i++)
                {
                    if(  (craft_String.charAt(i)=='!' )  || (craft_String.charAt(i) == '?'))
                        temp = "";
                    else if(craft_String.charAt(i)== '$') //wczytuje iloœæ craftowanego itemu
                    {
                        item_amount = String_to_int(temp);
                        temp = "";
                    }
                    else if(craft_String.charAt(i)== '#') //wczytuje iloœæ XP za craftowanie itemu
                    {
                        item_xp = String_to_int(temp);
                        temp = "";
                        break;
                    }
                    else
                        temp += craft_String.charAt(i); //dokleja kolejne znaki
                }
                //wstawianie scraftowanego itemu do ekwipunku gracza, dodawanie xp i ewentualny lvl up
                System.out.println("You've crafted " + item_map.get(crafted_id).item_name + ", amount: " + item_amount + ".");
                this.addItem(crafted_id, item_amount);
                this.crafting_add_XP(item_xp);
            }
        }
        else
            System.err.println("ERROR: ITEM YOU WANT TO CRAFT ISN'T ON THE CRAFTING LIST! (Entity.craft_item)");
    }
    
    //----------------------------------------------------------------------------------------- sprawdzanie poziomu/rasy gracza do craftingu
    public boolean crafting_stat_check(String crafted_id)
    {
        String race = "";
        String temp = "";
        int lv = -1;
        boolean race_check = false;
        boolean lv_check = false;
        
        //przechodzi przez stringa i wyczytuje z niego wymagany lvl i rasy, dla których jest dostêpny crafting
        for(int i = 0; i < crafted_id.length(); i++)
        {
            if(   (crafted_id.charAt(i) == '!') || (crafted_id.charAt(i) == '$') || (crafted_id.charAt(i) == '#')  )
                temp = "";
            else if(crafted_id.charAt(i) == '?')
            {
                lv = String_to_int(temp);
                temp = "";
            }
            else if(crafted_id.charAt(i) == '@')
            {
                race = temp;
                break;
            }
            else
                temp += crafted_id.charAt(i);
        }
        
        //sprawdza, czy wymagana rasa zgadza siê z t¹ od gracza
        switch(race)
        {
            case "A":
            {
                if(this.entity_race.equals("Azgoran"))
                    race_check = true;
                break;
            }
            case "D":
            {
                if(this.entity_race.equals("Duargian"))
                    race_check = true;
                break;
            }
            case "J":
            {
                if(this.entity_race.equals("Jessari"))
                    race_check = true;
                break;
            }
            case "N":
            {
                if(this.entity_race.equals("Nyrion"))
                    race_check = true;
                break;
            }
            case "E":
            {
                race_check = true;
                break;
            }
            default:
            {
                System.err.println("ERROR: UNKNOWN RACE TO CHECK! (Entity.crafting_stat_check.switch_race_default)");
                break;
            }
        }
        
        if(this.craftingPoints[1] >= lv) //jeœli poziom jest wiêkszy lub równy poziomowi wymaganemu, to zwraca prawdê
            lv_check = true;
          
        if(lv_check && race_check)
            return true;
        System.err.println("ERROR: Cannot craft an item - Your race of crafting level doesn't met requirements! (Entity.crafting_stat_check.return_false)");
        return false;
    }
    //----------------------------------------------------------------------------------------- sprawdzanie czy gracz ma wszystkie itemy potrzebne do craftu - jeœli je ma, to funkcja je pobiera
    public boolean crafting_items_check_take(String crafted_id)
    {
        boolean item_mode = false;
        int item_c = 0;
        //przechodzi przez ca³ego stringa i liczy iloœæ itemów
        for(int i = 0; i < crafted_id.length(); i++)
        {
            if(crafted_id.charAt(i)== '@') // w³¹cza tryb liczenia itemów
                item_mode = true;
            if(item_mode)//jeœli mam tryb liczenia itemów
            {
                if(crafted_id.charAt(i) == '!')
                    item_c++;
                else if(crafted_id.charAt(i) == '?')
                {
                    item_c++;
                    break;
                }
            }
        }
        
        //tworzy tablicê na podstawie wyliczonych przedmiotów
        String[][] item_tab = new String[2][item_c]; // A [0] [ nazwa itemu],  B [1][ iloœæ itemu]
        String temp = "";
        item_mode = false;
        
        for(int i = 0, A = 0, B = 0; i < crafted_id.length(); i++)
        {
            if(item_mode)//jeœli mam tryb liczenia itemów
            {
                if(crafted_id.charAt(i) == '!')
                {
                    item_tab[A][B] = temp; //wstawia item id lub iloœæ do tablicy
                    temp = "";
                    B++;
                }
                else if(crafted_id.charAt(i) == '?')
                {
                    item_tab[A][B] = temp; //wstawia item id lub iloœæ do tablicy
                    A++;
                    B = 0;
                    temp = "";
                }
                else
                    temp += crafted_id.charAt(i);
            }
            if(crafted_id.charAt(i)== '@') // w³¹cza tryb liczenia itemów
                item_mode = true;
        }
        //po skoñczeniu wczytywania listy itemów, sprawdza za pomoc¹ funkcji, czy gracz ma ka¿dy item w wystarczaj¹cej iloœci
        boolean items_check = true;
        for(int i = 0; i < item_c; i++)
        {   // sprawdza, czy w ekwipunku jest wystarczaj¹co itemu (u¿ywa string to int, bo w tablicy liczba jest zapisana jako string)
            if( showItemAmount(item_tab[0][i]) < String_to_int(item_tab[1][i]) ) //jeœli gracz ma za ma³o konkretnego itemu do craftu
            {
                System.err.println("ERROR: You don't have enough " + item_map.get(item_tab[0][i]).item_name + "! (Entity.crafting_items_check_take)");
                items_check = false;
            }
        }
        // jeœli gracz ma wszystkie itemy do craftu, zostaj¹ one pobrane i rozpoczyna siê crafting
        if(items_check)
        {
            int new_amount = -1;
            for(int i = 0; i < item_c; i++)
            {
                new_amount = this.showItemAmount(item_tab[0][i])-String_to_int(item_tab[1][i]);
                System.out.println("You have " + new_amount + " of " + item_map.get(item_tab[0][i]).item_name + " left.");
                this.removeItem(item_tab[0][i], String_to_int(item_tab[1][i]));
                
            }
            return true;
        }
        return false;
    }
    //==========================================================================
    //==============================------ BOLB COMMANDS SYSTEM ---??
    //==========================================================================
    
    
    
    //==========================================================================
    //==============================------ GATHER ACTION COMMANDS SYSTEM
    //==========================================================================

    //-------------------------------------------------------------------------------------------------- ROLLING AND ADDING XP FROM THE ACTION x
    public void gather_actions_add_XP(int action_number,int place, int lv, String item_id)
    {
        //wstawiæ zabezpieczenie, co jeœli id == "", to liczy XP dla nothing, jeœli id =="NPC", to liczy XP dla npc wg chance,itd.
        
        int XP;
        int XP_range;
        
        double xp_temp;
        double range_temp;
        
        switch(item_id)//rozpatruje 3 przypadki: XP dla NPC, XP dla nothing, XP dla itemu
        {
            //WZÓR NA LV 0 = (( (100-place chance) + (100 - item chance) ) * item_xp_multiplier
            //WZÓR NA LV n = (( (100-place chance) + (100 - item chance) * item_xp_multiplier) * place lv )*1.5
            //XP RANGE = 35% XP
            case "NPC": //NPC case
            {
                if(lv == 0)
                {
                   xp_temp = (double)(100 - gather_actions_event_chance_tab[action_number][place][lv][2]);
                   range_temp = 0.35*xp_temp;
                }
                else
                {
                   xp_temp = ( (double)(100 - gather_actions_event_chance_tab[action_number][place][lv][2])) *(double)(lv);
                   range_temp = 0.35*xp_temp;
                }
                XP = (int)(Math.round(xp_temp));
                XP_range = (int)(Math.round(range_temp));
                break;
            }
            
            case "": //NOTHING case
            {
                if(lv == 0)
                {
                   xp_temp = ((double)(100 - gather_actions_event_chance_tab[action_number][place][lv][2])/5)*1.5;
                   range_temp = 0.35*xp_temp;
                }
                else
                {
                   xp_temp = ( (double)(100 - gather_actions_event_chance_tab[action_number][place][lv][2])/5)*1.5 *(double)(lv);
                   range_temp = 0.35*xp_temp;
                }
                XP = (int)(Math.round(xp_temp));
                XP_range = (int)(Math.round(range_temp));
                break;
            }
            
            default: //dla dowolnego ID itemu (..i ewentualnych buggów xddxdx)
            {
                int[] XP_tab = new int[2];
                XP_tab = generate_XP(action_number, place, lv, item_id);//komenda do generowana XP jakiegokolwiek poziomu dla danego itemu

                XP = XP_tab[0];
                XP_range = XP_tab[1];
                break;
            }
            
        }
        //rolling XP
        XP = Roll(XP-XP_range, XP+XP_range);
        
        //===================================================================================================================
        //generowanie górnych progów XP dla wybranej akcji, konkretnego poziomu
        // powiedzmy, ¿e limity XP dla ka¿dego miejsca i ka¿dej akcji s¹ takie same, aby u³atwiæ komputerowi obliczanie czy iloœæ XP mieœci siê w limicie czy nie
        
        int xp_edge = 500;

        //przemno¿enie bazy o tyle razy, ile wynosi poziom. jeœli 0, to nie mno¿ymy, jeœli 1, to 500*2, jeœli lv 2, to 500*2 * 2, itd.
        if(lv != 0)
        {
            for(int i = 0; i < lv; i++) //mno¿y tak d³ugo, a¿ nie otrzyma wymaganej liczy xp na lvl up dla danego poziomu
                xp_edge *= 2;
        }

        //----- sprawdzanie, czy gracz ma zdoby³ wystarczaj¹co XP na lvl up
        if(this.entity_gather_actions_Lv_Tab[action_number][place][1] + XP >= xp_edge)// jeœli suma ca³ego XP jest wiêksza lub równa dla edge danego poziomu
        {
         action_Lv_up(action_number, place, "gather_actions"); // gracz dostaje lvl up
         this.entity_gather_actions_Lv_Tab[action_number][place][1] = this.entity_gather_actions_Lv_Tab[0][place][1] + XP - xp_edge; 
                                                                                                                     //przenosi nadwy¿kê XP na kolejny Lv 
        }
        else //jeœli nie dosz³o do lvl up, to po prostu dodaje XP
        {
            this.entity_gather_actions_Lv_Tab[action_number][place][1] += XP; 
            System.out.println("You've gained "+ XP +" XP.");
        }
    }
    //-------------------------------------------------------------------------------------------------- ROLLING AND ADDING ITEM FROM LOOT NPC
    public void gather_actions_npc_loot(int action, int place)
    {
        //(int action, int place, int lv, STRING z tablicy dla tego lvl/akcji/poziomu/NPC ITEMS/tryb lootowania 
                                                                                                    // "" - zwyk³y, NPC_LOOT - dla NPC
        String id = Loot_Item(action,place,this.entity_gather_actions_Lv_Tab[action][place][0],gather_actions_place_item_loot[action][place][1], "NPC_LOOT");  
        int amount = Roll_Item_Amount(id, action, place,this.entity_gather_actions_Lv_Tab[action][place][0]); // LV = this.entity_gather_actions_Lv_Tab[A][place][0] 
                                                                //(ID,akcja, miejsce, lvl
        addItem(id, amount);
    }
    //-------------------------------------------------------------------------------------------------- GATHER ACTION ---> LOOK AROUND
    public void look_around(int place) //action --> 0
    {
        String nyrion_bridge_id = "";
        //szuka mostu nyrionów, który jest potrzebny do spe³nienia warunków
        for(int i = 0; i < building_array.size(); i++)
        {
            if( (building_array.get(i).building_class == 7) && (building_array.get(i).building_race.equals("Nyrion"))  )
                nyrion_bridge_id = building_array.get(i).building_id;
        }
            
            
        int A = 0; //akcja  LOOK AROUND
        //pobiera token, aby akcja siê odby³a, jeœli nie ma tokenów, nie uruchamia akcji!
        if(this.entity_tokens > 0)
        {
            //----------------- MECHANIZM ZABEZPIECZAJ¥CY GRACZY PRZED WCHODZENIEM NA NIEDOSTÊPNE LOKACJE:
            // Nyrioni nie mog¹ wejœæ na lokacjê nr 1,2,3,4, a reszta ras tylko na lokacjê nr 5!!
            if(
                ( (this.entity_race == "Nyrion") && (place != 5) && (building_map.get(nyrion_bridge_id).building_Lv == 0) ) //gdy gracz jest Nyrionem i chce iœæ gdzieœ indziej ni¿ 5 a nie ma mostu
                                                                ||
                ( (this.entity_race != "Nyrion") &&(place == 5) && (building_map.get(nyrion_bridge_id).building_Lv == 0) ) //gdy gracz nie jest Nyrionem i chce iœæ na 5 a nie ma mostu
                )
            {
                System.err.println("WARNING: You can't enter this place yet! (Entity.look_around.place_enter_protection)");
            }
            else
            {
                System.out.println("==============================================================================================================");
                //nawi¹zuje do tablicy NPC,NOTHING,ITEM LOOT
                int wynik = look_around_roll_event(gather_actions_event_chance_tab[A][place][  this.entity_gather_actions_Lv_Tab[A][place][0]  ][0],   //npc chance
                                                   gather_actions_event_chance_tab[A][place][  this.entity_gather_actions_Lv_Tab[A][place][0]  ][1],   //nothing chance
                                                   gather_actions_event_chance_tab[A][place][  this.entity_gather_actions_Lv_Tab[A][place][0]  ][2]);  //item loot chance
                switch(wynik)
                {
                    case 0: //NPC case //DOKONCZYC!
                    {
                        int reaction = 0; // reakcje NPC: [0] funny,  [1] hide,   [2] loot item,   [3] neutral,   [4] tip
                        //LOSOWANIE REAKCJI NPC
                        if(place !=1) // losowanie reakcji jeœli miejsce nie jest moonshorem bo tam nie ma npc - EFFICIENCY
                            reaction = look_around_roll_event(gather_actions_npc_reaction_chance[A][place] [this.entity_gather_actions_Lv_Tab[A][place][0]]  [0], // FUNNY
                                                          gather_actions_npc_reaction_chance[A][place] [this.entity_gather_actions_Lv_Tab[A][place][0]]  [1], // HIDE
                                                          gather_actions_npc_reaction_chance[A][place] [this.entity_gather_actions_Lv_Tab[A][place][0]]  [2], // LOOT ITEM
                                                          gather_actions_npc_reaction_chance[A][place] [this.entity_gather_actions_Lv_Tab[A][place][0]]  [3], // NEUTRAL
                                                          gather_actions_npc_reaction_chance[A][place] [this.entity_gather_actions_Lv_Tab[A][place][0]]  [4]); // TIP
                            
                        switch(place)
                        {
                            case 1://Moonshore
                            {
                                System.err.print("ERROR: YOU CAN'T MEET ANY NPC HERE!! (Entity.look_around.npc_unavaiable)"); 
                                break;
                            }
                            
                            case 2://Lemon Beach
                            {
                                switch(reaction) //TORK REACTION
                                {
                                    case 0: // funny
                                    {
                                        System.out.println("While you were wandering around Lemon Beach, you saw Tork(Duargian) - he was "
                                                + "running around the beach, acting like he's a butterfly.");
                                        break;
                                    }
                                    case 1: // hide
                                    {
                                        System.out.println("You've noticed Tork(Duargian) was throwing rocks all around the place. He seemed really, really angry "
                                                + "and you decided to avoid him until he calms down.");
                                        break;
                                    }
                                    case 2: // loot_item
                                    {
                                        System.out.println("You've met Tork(Duargian) and he decided to give you a gift.");
                                        gather_actions_npc_loot(A, place);

                                        break;
                                    }
                                    case 3: // neutral
                                    {
                                        System.out.println("Tork(Duargian) was so busy with poking stones, that he didn't recognize you at all, ignoring you.");
                                        break;
                                    }
                                    case 4: // tip
                                    {
                                        System.out.println("You've got surprised by Tork(Duargian) from behind. He laughed and warned you: \"Watch out for angry Duargians! "
                                                + "They're very angry and don't like others, even me!\" ");
                                        break;
                                    }
                                }
                                break;
                            }
                            //==========================================================================================================================
                            case 3://Arcane Forest
                            {
                                switch(reaction) //SNORI REACTION
                                {
                                    case 0: // funny
                                    {
                                        System.out.println("While you were wandering around Arcane Forest, you've heard some rustle behind you - it was Snori(Jessari). His robes "
                                                + "were stuck on the branch he couldn't break. \"H-Hey, friend.. Could you help me get off "
                                                + "that tree please...?\" - You've broke the branch and catched falling Snori.");
                                        break;
                                    }
                                    case 1: // hide
                                    {
                                        System.err.println("ERROR: YOU CAN'T REACH SNORI'S REACTION \"HIDE\"! (Entity.look_around.npc_snori_reaction_unavaiable)");
                                        break;
                                    }
                                    case 2: // loot_item
                                    {
                                        System.out.println("You've found Snori(Jessari) next to small pond. He greeted you, giving you a small package. \"Here, that might "
                                                + "help you in your adventure.\"");
                                        gather_actions_npc_loot(A, place);

                                        
                                        break;
                                    }
                                    case 3: // neutral
                                    {
                                        System.out.println("Snori(Jessari) nod at you, seeing you're passing by and returned to taking care of wounded animal.");
                                        break;
                                    }
                                    case 4: // tip
                                    {
                                        System.out.println("When you've entered the Arcane Forest, something bright flew right in front of your face. A momment later, the "
                                                + "golden flash landed on Snori's arm. He chuckled, saying: \"Maybe this forest looks calm, but never let go of your guard.\"");
                                        break;
                                    }
                                }
                                break;
                            }
                            //==========================================================================================================================
                            case 4://Dark Forest
                            {
                                switch(reaction) //DARCUS REACTION
                                {
                                    case 0: // funny
                                    {
                                        System.err.println("ERROR: YOU CAN'T REACH DARCUS' REACTION \"FUNNY!\"! (Entity.look_around.npc_darcus_reaction_unavaiable)");
                                        break;
                                    }
                                    case 1: // hide
                                    {
                                        System.out.println("When you've entered Dark Forest, you've seen Darcus(Azgoran) in the distance. He seemed angry as hell, walking around with "
                                                + "his Soul Blade called Snekate, ready to attack anyone who's going to bother him now. You decided to slowly back away.");
                                        break;
                                    }
                                    case 2: // loot_item
                                    {
                                        System.out.println("While you were wandering around Dark Forest, you've found yourself near Darcus' house. He was already there, but since "
                                                + "his black fur perfectly fit the background, you barerly noticed him. He threw a package near your feet. \"Hey. This is for you. "
                                                + "Thanks. \" - he said, disappearing in the shadows. ");
                                        gather_actions_npc_loot(A, place);

                                        
                                        break;
                                    }
                                    case 3: // neutral
                                    {
                                        System.out.println("You've heard something running towards you. It was a wild animal, chased by Darcus. You quickly moved "
                                                + "away, watching Darcus disappear after his prey in the bushes.");
                                        break;
                                    }
                                    case 4: // tip
                                    {
                                        System.out.println("You found yourself near black liquid, knowns as Corruption. Looked like you were really close to Corrupted "
                                                + "Fields and that wasn't a pleasant view. Suddenly you've realized that Darcus popped up behind you when he started "
                                                + "speaking: \"You better stay away from that thing, unless you want to lose a limb or even life. Look at me, I don't "
                                                + "even look like Azgoran anymore. You don't want to share my fate.");
                                        break;
                                    }
                                }
                                break;
                            }
                            
                            case 5://Sorri's Swamps
                            {
                                switch(reaction) //GYRIO REACTION
                                {
                                    case 0: // funny
                                    {
                                        System.out.println("While on your way around Sorri's Swamps, you've met Gyrio(Nyrion). He smiled and decided to tell you a "
                                                + "joke: \"What's the difference between normal Nyrion and corrupted Nyrion? It's the taste, hahaha!\" - he said, bursting out with "
                                                + "laugh. You forced a laugh too, quickly walking away, confused about the funny part in the joke.");
                                        break;
                                    }
                                    case 1: // hide
                                    {
                                        System.out.println("While you were walking around swamps, you've noticed that Gyrio(Nyrion) is walking with a walking cane today. "
                                                + "Some Nyrion spot the old man too and greeted him, getting hit by the cane. \"Don't disturb me today!\" - Gyrio growled, "
                                                + "leaving confused Nyrion. You decided not to bother him today.");
                                        break;
                                    }
                                    case 2: // loot_item
                                    {
                                        System.out.println("Gyrio(Nyrion) waved at you from the distance, calling your name. He smiled, touching your hand and leaving "
                                                + "something there, before going away.");
                                        gather_actions_npc_loot(A, place);

                                        
                                        break;
                                    }
                                    case 3: // neutral
                                    {
                                        System.out.println("You spot Gyrio(Nyrion) in the distance. He was talking with someone. You waved 'hello' to him. He waved back and turned back"
                                                + "to his listener.");
                                        break;
                                    }
                                    case 4: // tip
                                    {
                                        System.out.println("You've asked Gyrio(Nyrion) for some tips.He smiled, happy that someone wants to listen to him. \"Whatever you do, don't "
                                                + "touch the Corruption. That's the only and most imporant thing to remember.\"");
                                        break;
                                    }
                                }
                                break;
                            }
                        }  
                        
                        gather_actions_add_XP(A,place, entity_gather_actions_Lv_Tab[A][place][0], "NPC");//action/place/lvl/STRING //----------- wstawianie XP dla eventu akcji
                        break;
                    }
                    case 1: //NOTHING
                    {
                        System.out.print("You've visited "); 
                        
                        //WSTAWKA DLA MIEJSCA
                        switch(place)
                        {
                            case 1://Moonshore
                            {
                                System.out.print("Moonshore"); 
                                break;
                            }
                            
                            case 2://Lemon Beach
                            {
                                System.out.print("Lemon Beach"); 
                                break;
                            }
                            
                            case 3://Arcane Forest
                            {
                                System.out.print("Arcane Forest"); 
                                break;
                            }
                            
                            case 4://Dark Forest
                            {
                                System.out.print("Dark Forest"); 
                                break;
                            }
                            
                            case 5://Sorri's Swamps
                            {
                                System.out.print("Sorri's Swamps"); 
                                break;
                            }
                        }  

                        System.out.println(" but found nothing interesting.");
                        gather_actions_add_XP(A,place,entity_gather_actions_Lv_Tab[A][place][0],""); //action/place/lvl/STRING
                        break;
                    }
                    case 2: // ITEM
                    {
                        String id = Loot_Item(A,place,this.entity_gather_actions_Lv_Tab[A][place][0],gather_actions_place_item_loot[A][place][0], ""); 
                                                                    //(int action, int place, int lv, STRING z tablicy dla tego lvl/akcji/poziomu) 
                        int amount = Roll_Item_Amount(id, A, place,this.entity_gather_actions_Lv_Tab[A][place][0]); // LV = this.entity_gather_actions_Lv_Tab[A][place][0] 
                                                        //(ID,akcja, miejsce, lvl
                        addItem(id, amount);
                        gather_actions_add_XP(A,place, this.entity_gather_actions_Lv_Tab[A][place][0], id);//action/place/lvl/STRING //----------- wstawianie XP dla eventu akcji
                        break;
                    }
                    default:
                    {
                        System.err.println("ERROR: Unknown event roll! (Entity.look_around.switch-wynik)");
                        this.addTokens(1);
                        break;
                    }
                        
                }

                this.addTokens(-1);
            }
        }
        else//Jeœli gracz nie ma tokenów
            System.err.println("ERROR: NOT ENOUGH TOKENS!! (Entity.look_around.missing_token)");
    }
}
