package rpg_express_0.pkg2;

import static rpg_express_0.pkg2.Utility_Commands.A_ACTION;
import static rpg_express_0.pkg2.Utility_Commands.B_PLACE;
import static rpg_express_0.pkg2.Utility_Commands.C_LEVEL;
import static rpg_express_0.pkg2.Utility_Commands.String_to_int; //importuje komendê String_to_int
import static rpg_express_0.pkg2.Utility_Commands.item_array;
import static rpg_express_0.pkg2.Utility_Commands.item_map;

public class Item 
{
    public Item(String ID, String N, String C, String D, String tab_string)
    {
        setId(ID);
        setName(N);
        setClass(C);
        setDescription(D);
        setItem_gather_actions_Lv_XP_Chance_Tab_BASE(tab_string);
    }
    
    //==================================================================================================================================
    //================================================================================================== ZMIENNE
    //==================================================================================================================================
    public String item_id = "i0_000";
    public String item_name = "Test";
    public String item_class = "class_test";
    public String item_description = "Description_test";
    public double item_xp_multi = 0;

    public int[][][] item_gather_actions_chance_Tab = new int[A_ACTION][B_PLACE][C_LEVEL]; 
                                             // dla ka¿dego miejsca, poziomu i akcji zawiera item loot chance //[akcja][miejsce][lv i równoczeœnie miejsce na dane na temat szansy itemu] 
    //tab[A][B][D]
    public int[][][] item_gather_actions_Lv_XP_amount_Tab =  new int
                    [A_ACTION]//nr akcji 
                    [B_PLACE] //0 jest placeholderem //miejsce akcji 1-moonshore, 2-lemon beach, 3-arcane forest, 4-dark forest, 5-sorri's swamps
                    [4]; // 0 - XP/ 1 - XP RANGE/ 2 - item_amount_min/ 3 - item_amount_max
    /*
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
    inne informacje o itemie:
        * 0 --> XP
        * 1 --> XP_range
        * 2 --> item_amount_min
        * 3 --> item_amount_max
    */
    

    //==================================================================================================================================
    //================================================================================================== FUNKCJE
    //==================================================================================================================================
    
    //===========================================
    //------------------------------------ CONSTRUCTOR -------------------
    //===========================================
    
    private void setId(String ID)
    {
        this.item_id = ID;
        item_map.put(ID, this); //przedmiot sam siê wstawia do mapy
        item_array.add(this);//przedmiot sam siê wstawia do array
    }
    //===========================================
    private void setName(String N)
    {
        this.item_name = N;
    }
    //===========================================
    private void setClass(String C)
    {
        this.item_class = C;
        switch(C)
        {
            case "gem":
            {
                this.item_xp_multi = 0.7;
                break;
            }
            
            case "stone":
            {
                this.item_xp_multi = 0.4;
                break;
            }
            
            case "wood":
            {
                this.item_xp_multi = 0.5;
                break;
            }
            
            case "mana":
            {
                this.item_xp_multi = 0.55;
                break;
            }
            
            case "craft":
            {
                this.item_xp_multi = 0.55;
                break;
            }
            
            case "bolb_food":
            {
                this.item_xp_multi = 0.75;
                break;
            }            
        }
    }
    //===========================================
    private void setDescription(String D)
    {
        this.item_description = D;
    }
    //===========================================
    private void setItem_gather_actions_Lv_XP_Chance_Tab_BASE(String tab_string) //rozbija bazowy string z konstruktora
    {
        /*
            //xp!xp_range!item_amount_min!item_amount_max?finding_chance_Lv_0!..!finding_chance_Lv_n!@
        (@ = next place, # = next action, ~ = omiñ ca³e jedno miejsce, - = omiñ wpisywanie itemu - nie mo¿na go nigdzie znaleŸæ!)
            $32!11!5!10?90!87!84!80!76!73@
            32!11!2!5?20!19!16!15!14!12@
            36!13!2!5?10!9!8!7!6!5@
            32!11!2!6?20!18!16!15!14!13@
            0!0!0!0?0!0!0!0!0!0!
        
        //Kolejnoœæ wprowadzania danych:
        // 0 - akcja look_around
            // 1 - Moonshore
                // 0 - Lv 0
                    // 0 - XP
                    // 1 - XP_range
                    // 2 - item_amount_min
                    // 3 - item_amount_max
                    // INNY TAB - findind_chance
                    .
                    .   
                    .
                // 5 - Lv 5
                    // INNY TAB - finding_chance
                    .
                    .
                    .
            // 5 - Sorri's Swamps
                //(...)
        */
        if(tab_string.charAt(0) != '-')
        {
            int mode = 0; // 0 - wstawianie danych do item_gather_actions_Lv_XP_amount_Tab, 1 - wstawianie danych do
            int D = 0;// xp/xp_range/item_amount_min/item_amount_max/
            int C = 0; //lvl miejsca
            //finding_chance jest w innej tabeli!

            int string_pos = 0;
            String temp = "";

            for(int A = 0; A <A_ACTION; A++) //przechodzi przez wszystkie akcje
            {
                for(int B = 1; B < B_PLACE; B++) //przechodzi przez wszystkie miejsca
                {
                    //system roz³upywania stringa
                    for(; string_pos < tab_string.length(); string_pos++)
                    {
                        if(mode == 0) //tryb wpisywania danych dla item_gather_actions_Lv_XP_amount_Tab
                        {
                            if(tab_string.charAt(string_pos) == '!')
                            {
                                this.item_gather_actions_Lv_XP_amount_Tab[A][B][D]= String_to_int(temp); // wstawia pobran¹ szansê dla okreœlonego pola poziomu etc
                                D++;
                                temp = ""; // zeruje temp
                            }
                            else if(tab_string.charAt(string_pos) == '?')
                            {
                                this.item_gather_actions_Lv_XP_amount_Tab[A][B][D]= String_to_int(temp); // wstawia pobran¹ szansê dla okreœlonego pola poziomu etc
                                D = 0; //zeruje D, aby mo¿na by³o wpisywaæ resztê szans na pozosta³e itemy
                                temp = ""; // zeruje temp
                                C = 0;
                                mode = 1; // ustawia tryb wpisywania danych dla item_finding_chance
                            }
                            else if(tab_string.charAt(string_pos) == '~') // jeœli trafi na tyldê, to skippuje ca³e miejsce
                            {
                                D = 0; //zeruje D, aby mo¿na by³o wpisywaæ resztê 
                                temp = ""; // zeruje temp
                                string_pos++; //trzeba rêcznie przeskoczyæ do nastêpnego znaku
                                break;
                            }
                            else 
                                temp += tab_string.charAt(string_pos); //jesli nie zakoñczy³o liczby !, to dokleja cyfry dalej
                        }
                        //==================================================================================
                        else if (mode == 1) //tryb wpisywania danych dla item_finding_chance
                        {
                            if(tab_string.charAt(string_pos) == '!')
                            {
                                this.item_gather_actions_chance_Tab[A][B][C]= String_to_int(temp); // wstawia pobran¹ szansê dla okreœlonego pola poziomu etc
                                C++;
                                temp = ""; // zeruje temp
                            }
                            else if(tab_string.charAt(string_pos) == '@')
                            {
                                this.item_gather_actions_Lv_XP_amount_Tab[A][B][D]= String_to_int(temp); // wstawia pobran¹ szansê dla okreœlonego pola poziomu etc
                                D = 0; //zeruje D, aby mo¿na by³o wpisywaæ resztê szans na pozosta³e itemy
                                temp = ""; // zeruje temp
                                mode = 0; // ustawia tryb wpisywania danych dla item_finding_chance
                                string_pos++;//trzeba rêcznie przeskoczyæ do nastêpnego znaku
                                break; // przechodzi do nastêpnej lokacji
                            }
                            else 
                                temp += tab_string.charAt(string_pos); //jesli nie zakoñczy³o liczby !, to dokleja cyfry dalej
                        }
                    }
                }
            }
        }
    } 
    //=========================================== ------------------------------ generuje pozosta³e w³aœciwoœci dla itemu, XP, XP range, itd. --- PRZEROBIÆ 
    
    /*
    przerob to na mniejsze funkcje, ktore licz¹ albo konkretn¹ rzecz, np. amount min i max n-tego poziomu, albo xp, albo wypisuj¹ ca³a listê xp/itemamount/../ dla miejsca jednego lub wielu - takie sprawdzanie jakby coœ siê Ÿle wstuka³o
    
    private void setItem_gather_actions_Lv_XP_Chance_Tab_REST()
    {
        for(int A = 0; A<item_gather_actions_A; A++)//przechodzi przez wszystkie akcje typu gather_actions
        {
            for(int B  = 1; B < item_gather_actions_B; B++)//przechodzi przez wszystkie miejsca akcji (Moonshore, Lemon Beach, Arcane Forest, Dark Forest, Sorri's Swamps)
            {
               for(int C = 1; C < item_gather_actions_C; C++) //przechodzi przez wszystkie poziomy miejsca akcji
               {
                   if(this.item_gather_actions_Lv_XP_amount_Tab[A][B][C-1][0] != 0) //jeœli rubryki nie s¹ puste, to liczy resztê
                   {
                       //========================================= D ---> 0 - XP
//                    double xp_temp = (double)(this.item_gather_actions_Lv_XP_Chance_Tab[A][B][C-1][0])*this.item_XP_multiplier; //obliczanie nowej iloœci XP
//                    this.item_gather_actions_Lv_XP_Chance_Tab[A][B][C][0] = (int)(xp_temp);

                     double xp_temp = (double)(this.item_gather_actions_Lv_XP_amount_Tab[A][B][C][0])//(double)(gather_actions_event_chance_tab[A][B][C][2]//dokoñczyæ

                    //========================================= D ---> 1 - XP_RANGE
                    double xp_range_temp = (double)(this.item_gather_actions_Lv_XP_amount_Tab[A][B][C][0])*this.item_XP_range_multiplier; //obliczanie nowej iloœci XP_RANGE
                    this.item_gather_actions_Lv_XP_amount_Tab[A][B][C][1] = Math.round((int)(xp_range_temp));
                    
                    //========================================= D ---> 2 - ITEM_AMOUNT_MIN
                    this.item_gather_actions_Lv_XP_amount_Tab[A][B][C][2] = this.item_gather_actions_Lv_XP_amount_Tab[A][B][C-1][3];      //przypisanie wartoœci MAX poprzedniego poziomu 
                                                                                                                                        // jako nowy MIN aktualnego poziomu
                                                                                                                                        
                    //========================================= D ---> 3 - ITEM_AMOUNT_MAX
                    double amount_temp = (double)(this.item_gather_actions_Lv_XP_amount_Tab[A][B][C][2])*item_amount_multiplier;      //przypisanie wartoœci MAX jako 1,75 * MIN 
                    this.item_gather_actions_Lv_XP_amount_Tab[A][B][C][3] = Math.round((int)(amount_temp));
                   }
               }
            }  
        }       
    } */
    
    //=============================================================== UTILITY COMMANDS ===============================================
    public void showItem()
    {
        System.out.println("========================================================================");
        System.out.println("Item ID: "+ this.item_id);
        System.out.println("Item Name: "+ this.item_name);
        System.out.println("Item Class: "+ this.item_class);
        System.out.println("Item Description: "+ this.item_description);
    }
}
