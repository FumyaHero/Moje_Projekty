
package rpg_express_0.pkg2;

import static rpg_express_0.pkg2.Utility_Commands.BOLB_APPEARANCE_ELEMENTS;
import static rpg_express_0.pkg2.Utility_Commands.BOLB_COLORS_AMOUNT;
import static rpg_express_0.pkg2.Utility_Commands.Roll;
import static rpg_express_0.pkg2.Utility_Commands.String_to_int;
import static rpg_express_0.pkg2.Utility_Commands.bolb_array;
import static rpg_express_0.pkg2.Utility_Commands.bolb_food_evolution_list;
import static rpg_express_0.pkg2.Utility_Commands.bolb_map;
import static rpg_express_0.pkg2.Utility_Commands.createNewBolb;
import static rpg_express_0.pkg2.Utility_Commands.entity_item_id_length;
import static rpg_express_0.pkg2.Utility_Commands.find_bolb_owner;
import static rpg_express_0.pkg2.Utility_Commands.item_map;
import static rpg_express_0.pkg2.Utility_Commands.look_around_roll_event;
import static rpg_express_0.pkg2.Utility_Commands.player_ADD_bolb;
import static rpg_express_0.pkg2.Utility_Commands.player_REMOVE_bolb;
import static rpg_express_0.pkg2.Utility_Commands.player_array;


public class Bolb 
{
    //BOLB OUTFIT COMBO:
    // WINGS/HORNS/SKIN/TAIL/LEGS/PRIMARY COLOR/SECONDARY COLOR
    public Bolb(String owner_id, String name, boolean gender, int wings, int horns, int skin, int tail, int legs, int primary_col, int secondary_col ) //tworzenie breedowanych bolbów
    {
        setOwner(owner_id);
        setName(name);
        setGender(gender);
        setAppearance(wings,horns,skin,tail,legs,primary_col,secondary_col);
        this.bolb_age = -3; //dni potrzebne do wyklucia siê bolba z jajka
    }
    public Bolb(String owner_id, String name, boolean gender) //wykluwanie "pustych" genowo bolbów ze znalezionych jajek
    {
        setOwner(owner_id);
        setName(name);
        setGender(gender);
        this.bolb_appearance[5] = -1;   //zeruje primary color
        this.bolb_appearance[6] = -1;   //zeruje secondary color
    }
    
    public String bolb_owner_id = "e0_000";
    public String bolb_id = "bb_0";
    public String bolb_name = "test_bolb_name";
    public boolean bolb_gender = true; //TRUE - GIRL, FALSE = BOY
    public int bolb_age = 0; //0 - egg, 1 - baby, 2 - adult// UWAGA! -3, ... , -1, 0  to czas potzebny do wyklucia siê Bolba!
    public int bolb_breeding_cd = 7; // 0 = READY TO BREED! 7 days - startowe
    public int bolb_hunger = 0; // 0 - fed, 1 - hungry, 2 - very hungry, 3 - starving, 4 - run away
    public boolean bolb_brushing = false; //false = nie by³ jeszcze czesany, true - by³ ju¿ czesany
    public int[] bolb_appearance = new int[BOLB_APPEARANCE_ELEMENTS];
    //aktualnie jest ich: 0 - wings, 1 - horns, 2 - skin, 3 - tail, 4 - legs, 5- primary color, 6 - secondary color
    
    //UWAGA!! COLOR -1 TO COLOR NIE ZMIENIONY-BIA£Y, A COLOR 0 TO BIA£Y-PERMAMENTNIE!!
    //=========================================================================================================================
    //========================================================= CONSTRUCTOR COMMANDS ==========================================
    //=========================================================================================================================
    public void setOwner(String owner_id) //potem mo¿e daæ na public jak siê da oswoiæ dzikie bolby??
    {
        this.bolb_owner_id = owner_id;
        setId();
        bolb_array.add(this); //dodaje aktualnie tworzonego Bolba do arraya bolbów
        bolb_map.put(this.bolb_id, this);
        player_ADD_bolb(owner_id,this.bolb_id);
        
    }
    //------------------------
    public void setId()//ustawia siê id bolba na podstawie nr w bolba w arrayu bolbów
    {
        this.bolb_id = "bb_" + String.valueOf(bolb_array.size()); //id bolba jest tworzone na podstawie liczby ju¿ utworzonych bolbów. Pierwszy bolb ma id 0, drugi bolb ma id 1, itd.
    }
    //------------------------
    private void setName(String name) //potem mo¿e daæ na public jak siê da oswoiæ dzikie bolby??
    {
        this.bolb_name = name;
    }
    //------------------------
    private void setGender(boolean gender)
    {
        this.bolb_gender = gender;
    }
    //------------------------
    private void setAppearance(int wings, int horns, int skin, int tail, int legs, int primary_col, int secondary_col )
    {
        this.bolb_appearance[0] = wings;
        this.bolb_appearance[1] = horns;
        this.bolb_appearance[2] = skin;
        this.bolb_appearance[3] = tail;
        this.bolb_appearance[4] = legs;
        this.bolb_appearance[5] = primary_col;
        this.bolb_appearance[6] = secondary_col;
    }
    
    //=========================================================================================================================
    //========================================================= SET/ADD COMMANDS ==========================================
    //=========================================================================================================================
    //-------------------------------------------------------- SET ----------------------------------
    public void setAge(int age)
    {
        this.bolb_age = age;
    }
    //--------------------------------------------------------
    public void setBreedingcd(int cd)
    {
        this.bolb_breeding_cd = cd;
    }
    //--------------------------------------------------------
    public void setHunger(int hunger)
    {
        this.bolb_hunger = hunger;
    }    
    //-------------------------------------------------------- zmienia wygl¹d JEDNEGO elementu wygl¹du + komentuje to
    public void setPieceofAppearance(int appearance_num, int element_num)
    {
        this.bolb_appearance[appearance_num] = element_num;
    }
    //--------------------------------------------------------
    public void setBrushing(boolean brushing)
    {
        this.bolb_brushing = brushing;
    }   
    
    //-------------------------------------------------------- ADD ----------------------------------
    public void addAge(int add_age)
    {
        if(this.bolb_age+add_age <=2)
        {
            this.bolb_age += add_age;
            
            switch(this.bolb_age)
            {
                default:
                {
                    System.out.println("The egg is still incubating..");
                    break;
                }
                
                case 1:
                {
                    System.out.println(this.bolb_name+ " just have hatched!!");
                    Say_appearance();
                    break;
                }

                case 2:
                {
                    System.out.println(this.bolb_name+ " turned into adult Bolb!");
                    break;
                }
            }
        }
        else
            System.err.println("ERROR: SOMETHING WENT WRONG WITH ADDING BOLB'S AGE! (Bolb.addAge)");
    }
    //--------------------------------------------------------
    public void addBreedingcd(int add_cd)
    {
        if(this.bolb_age == 2)
        {
            if(this.bolb_breeding_cd + add_cd >= 0)
            {
                this.bolb_breeding_cd += add_cd;
                System.out.println("Your Bolb "+ this.bolb_name+ "'s breeding cooldown is now " +this.bolb_breeding_cd);
            }
        }
    }
    //--------------------------------------------------------
    public void addHunger(int add_hunger)
    {
        this.bolb_hunger += add_hunger;
        
        switch(this.bolb_hunger)
        {
            case 0:
            {
                System.out.println("Your Bolb "+this.bolb_name+" is happy and well fed.");
                break;
            }
            case 1:
            {
                System.out.println("Your Bolb "+this.bolb_name+" is getting hungry..");
                break;
            }
            case 2:
            {
                System.out.println("Your Bolb "+this.bolb_name+" is really hungry.");
                break;
            }
            case 3:
            {
                System.out.println("Your Bolb "+this.bolb_name+" is starving!!");
                break;
            }
            case 4:
            {
                System.out.println("Your Bolb "+this.bolb_name+" ran away looking for food somewhere else.");
                player_REMOVE_bolb(this.bolb_owner_id, this.bolb_id);
                break;
            }
            default:
                System.err.println("ERROR: Unknown HUNGRER number! (Bolb.addHunger)");
        }
    }

    //=========================================================================================================================
    //========================================================= UTILITY COMMANDS ==========================================
    //=========================================================================================================================
    //----------------------------------------------------------------------------------- KARMIENIE BOLBA itemkami, sprawdzanie czy s¹ na liœcie, wywo³ywanie efektów przez karmienie
    public void feedBolb(String id)
    {
        //przechodzi przez listê itemów, aby upewniæ siê, czy wybrany item jest na liœcie bolb_food
        boolean item_list_check = false;
        String temp = "";
        int ITEM_NUM = 0;
        
        for(; ITEM_NUM < bolb_food_evolution_list.size(); ITEM_NUM++) //sprawdza ka¿dy element listy
        {
            for(int j = 0; j < bolb_food_evolution_list.get(ITEM_NUM).length(); j++) //sprawdza, czy id itemu jest równe z jednym z listy
            {
                if(bolb_food_evolution_list.get(ITEM_NUM).charAt(j) == '?') //jeœli trafi na koniec id itemu, to ma go porównaæ z szukanym.  
                {
                    if(temp.equals(id)) //Jeœli znajdzie, check = true, wychodzi z pêtli i zaczyna feeding
                    {
                        item_list_check = true;
                        temp ="";
                        break;
                    }
                    temp = "";
                    break; //jeœli nie trafi, to i tak ma wyjœæ
                }
                else
                    temp += bolb_food_evolution_list.get(ITEM_NUM).charAt(j); // dokleja kolejny element id itemu
            }
            if(item_list_check)  // wychodzi z pêtli poniewa¿ znalaz³ item na liœcie
                    break;
        }
        //po przeszukaniu listy sprawdza, czy warunek jest spe³niony
        if(item_list_check) //jeœli jest true, leci dalej
        {
            /*WZORY:
            - zmiania jednej kategorii: i0_006?#2?0!25!75!
            - zmiania wielu/wszystkich kategorii: i3_008?*"             // * - MANA FRUIT
                                                    + "7!7!7?"            // 0 WINGS - 7/7/7
                                                    + "7!7!7?"            // 1 HORNS - 7/7/7
                                                    + "5!5!6?"            // 2 SKIN - 5/5/6
                                                    + "7!7!7?"            // 3 TAIL - 7/7/7
                                                    + "7!7!7?"            // 4 LEGS - 7/7/7
            - zwyk³y bolb food: i0_007?^
            - specjalny bolb_food: i5_007?%1?
            ~ OZNACZA PRZEJŒCIE DO NASTÊPNEJ KATEGORII!*/
            
            char char_type;
            int rolled = Roll(0,99);
            int item_type = -1;
            int suma = 0;
            temp = "";
            
            //sprawdzenie, jaki jest tryb wprowadzania entity_item_id_length
            switch(bolb_food_evolution_list.get(ITEM_NUM).charAt( entity_item_id_length+1 ))
            {
                case '#':
                {
                    int item_count = 0;
                    boolean count_type = false;
                    
                    for(int i = 0; i < bolb_food_evolution_list.get(ITEM_NUM).length(); i++)
                    {
                        if(bolb_food_evolution_list.get(ITEM_NUM).charAt(i) == '!')//liczy iloœæ itemów, ¿eby utworzyæ dla nich 
                            item_count++;
                        
                        if(bolb_food_evolution_list.get(ITEM_NUM).charAt(i) == '#')//w³¹cza tryb szukania nr typu czêœci
                            count_type = true;
                        
                        if(count_type)//jeœli tryb wczytywania typu zosta³ uruchomiony, to sprawdza, jakiej czêœci dotyczy food
                        {
                            if(bolb_food_evolution_list.get(ITEM_NUM).charAt(i) == '?')//znalaz³ koniec liczby dla typu czêœci
                            {
                                item_type = String_to_int(temp); //zapisuje typ czêœci
                                temp = "";
                                count_type = false; //liczba zosta³a znaleziona, nie trzeba doklejaæ reszty
                            }
                            else if(bolb_food_evolution_list.get(ITEM_NUM).charAt(i) != '#')
                                temp+= bolb_food_evolution_list.get(ITEM_NUM).charAt(i);
                        }      
                    }
                    
                    //sprawdza, czy rzecz, któr¹ chcemy zmieniaæ jest ju¿ zajêta
                    if( ((this.bolb_appearance[item_type] == 0) && (item_type != 5))//sprawdza czy jest to item type ró¿ny od 5 i czy jest równy 0
                            ||
                            ((this.bolb_appearance[item_type] == -1) && (item_type == 5)) // jeœli jest to item type 5, to czy jest równy od -1,
                            ||
                            ( ((this.bolb_appearance[item_type] != -1) && (item_type == 5)) && (this.bolb_appearance[6] == -1))   ) 
                                                                                                            //jeœli item type 5 jest "nakarmiony", ale item type 6 nie jest
                            {
                                int[] gene_chance_tab = new int[item_count];
                                //przechodzi przez stringa jeszcze raz i wstawia do tablicy szanse na dany gen
                                 for(int i = 0, x = 0, tab = 0; i < bolb_food_evolution_list.get(ITEM_NUM).length(); i++)
                                {
                                    if(x == 2) //jeœli przeszed³ przez id i liczbê typu
                                    {
                                         if(bolb_food_evolution_list.get(ITEM_NUM).charAt(i) == '!')//znalaz³ koniec gene chance i go zapisuje w tablicy
                                        {
                                            gene_chance_tab[tab] = String_to_int(temp); //zapisuje typ czêœci
                                            tab++;
                                            temp = "";
                                        }
                                        else
                                            temp+= bolb_food_evolution_list.get(ITEM_NUM).charAt(i);
                                    }
                                    
                                    if(bolb_food_evolution_list.get(ITEM_NUM).charAt(i) == '?')//liczy znak ?, przy drugim przechodzi do wbijania szans na itemy
                                        x++;
                                }
                                 
                                //po wygenerowaniu odpowiedniej tablicy, sprawdzamy w jakim zakresie znajduje siê szansa na gen
                                
                                //WSZYSTKIE TYPY POZA 5/6
                                if(item_type != 5)
                                {
                                    for(int i=0; i< gene_chance_tab.length; i++)
                                    {
                                        if(gene_chance_tab[i] == 0) //jeœli roll chance aktualnego genu jest jest równy 0, to pomijamy go i jedziemy dalej
                                            i++;
                                        else
                                        { // w przeciwnym wypadku sprawdzamy, czy liczba jest w zakresie, etc.
                                            if((rolled>= suma) && (rolled<suma+gene_chance_tab[i])) //sprawdza, czy roll mieœci siê w zakresie losowanego eventu
                                            {
                                                this.bolb_appearance[item_type] = i; //ustawia nr genu dla wybranego typu
                                                System.out.print("Your Bolb "+this.bolb_name+" ate "+item_map.get(id).item_name+". Congratulations, your Bolb grew ");
                                                Say_bodypart(item_type);//wypisuje konkretn¹ czêœæ cia³a bolba//wypisuje konkretn¹ czêœæ cia³a bolba
                                                System.out.print(" type "+ i + "!");
                                                System.out.println();
                                            }
                                            else
                                                suma += gene_chance_tab[i];
                                        }
                                    }
                                }
                                else if(item_type == 5) //¯ADEN TYP POZA 5/6
                                {
                                    //sprawdzanie, czy primary color zosta³ nakarmiony
                                    if(this.bolb_appearance[item_type] == -1)//jeœli nie, to zmienia primary
                                    {
                                        this.bolb_appearance[item_type] = gene_chance_tab[0]; //jest tylko jedna rzecz w takiej tabeli, jest ni¹ nr koloru
                                        System.out.print("Your Bolb "+this.bolb_name+" ate "+item_map.get(id).item_name+". Congratulations, your Bolb changed its primary color to ");
                                    }
                                    else if(this.bolb_appearance[item_type] != -1) //jeœli primary color ju¿ jest
                                    {
                                        this.bolb_appearance[item_type+1] = gene_chance_tab[0]; //koloruje secondary color, który jest tu¿ po nr 5, czyli 6, st¹d 5+1
                                        System.out.print("Your Bolb "+this.bolb_name+" ate "+item_map.get(id).item_name+". Congratulations, your Bolb changed its secondary color to ");
                                    }
                                    
                                    Say_color(gene_chance_tab[0]); //wypisuje okreœlony kolor Bolba
                                    System.out.print("!");
                                    System.out.println();
                                }
                            }
                    else //jeœli miejsce jest zajête
                        System.out.println("Your Bolb "+this.bolb_name+" ate "+item_map.get(id).item_name+", but nothing has changed. Looks like it can't transform anymore in that field.");
                    
                    bolb_feed_ending(id); //usuwa przedmiot z ekwipunku oraz komentuje karmienie Bolba
                    break;
                }
                
                case '*':
                {
                    ///
                    int item_count = 1; //bo ostatni gen jest zakonczony ?, a nie !, wiêc trzeba go sztucznie policzyæ
                    int type_count = -1; //-1, poniewa¿ jest jeden dodatkowy ? dla id itemu
                    
                    boolean item_type_check = true;
                    
                    for(int i = 0; i < bolb_food_evolution_list.get(ITEM_NUM).length(); i++)
                    {
                        if(item_type_check) //jeœli jest w trybie liczenia iloœci genów dla jednego typu
                        {
                            if(bolb_food_evolution_list.get(ITEM_NUM).charAt(i) == '!')//liczy iloœæ genów dla jednego typu, ¿eby utworzyæ dla nich 
                            item_count++;
                        }
                        if(bolb_food_evolution_list.get(ITEM_NUM).charAt(i) == '?')//liczy iloœæ typów, ¿eby utworzyæ dla nich 
                        {
                            type_count++;
                            if(type_count > 0) //¿eby nie psu³o siê po pierwszym liczeniu ? z id itemu
                                item_type_check = false;
                        }    
                    }
                    int[][] gene_chance_tab = new int[type_count][item_count];
                                
                    //przechodzi przez stringa i liczy, od którego miejsca ma zacz¹æ wczytywanie danych
                    int START = 0;
                    for(; START < bolb_food_evolution_list.get(ITEM_NUM).length(); START++)
                    {
                        if(bolb_food_evolution_list.get(ITEM_NUM).charAt(START) == '*')
                        {
                            START++; //dodaje jedno miejsce dalej, ¿eby móg³ zacz¹æ liczyæ od razu szanse
                            break;//wychodzi z pêtli #EFFICIENCY
                        }
                    }
                    
                    //przechodzi przez stringa jeszcze raz i wstawia do tablicy szanse na dany gen wg kategorii
                    for(int i = 0; i < type_count; i++ )//pêtla przechodzi przez ka¿dy typ wygl¹du
                    {
                        for(int j = 0; j < item_count; j++) // pêtla przechodzi przez ka¿dy wariant typu wygl¹du dla konkretnej kategorii
                        {
                          for(; START < bolb_food_evolution_list.get(ITEM_NUM).length(); START++)
                            {
                                    if( (bolb_food_evolution_list.get(ITEM_NUM).charAt(START) == '!') || (bolb_food_evolution_list.get(ITEM_NUM).charAt(START) == '?') )
                                                                                        //znalaz³ koniec gene chance i go zapisuje w tablicy
                                    {
                                        gene_chance_tab[i][j] = String_to_int(temp); //zapisuje typ czêœci
                                        temp = "";
                                        START++; //trzeba sztucznie dodaæ przejœcie dalej
                                        break; //wychodzi z pêtli, ¿eby móg³ przejœæ do nastêpnego przedmiotu
                                    }
                                    else if(bolb_food_evolution_list.get(ITEM_NUM).charAt(START) == '~')//jeœli trafi na ~, to ma po prostu przejœæ przez ca³y kawa³ek kategorii jako zera
                                    {
                                        temp = "";
                                        START++; //trzeba sztucznie dodaæ przejœcie dalej
                                        j = item_count; //sztucznie ustawwiamy j poza granicê, ¿eby móg³ przejsæ od razu do nastêpnej kategorii
                                        break; //wychodzi z pêtli, ¿eby móg³ przejœæ do nastêpnego przedmiotu
                                    }
                                    else
                                        temp+= bolb_food_evolution_list.get(ITEM_NUM).charAt(START);
                            }  
                        }
                    }   
                        
                    //po wygenerowaniu odpowiedniej tablicy, sprawdzamy w jakim zakresie znajduje siê szansa na gen
                    for(int i=0; i< type_count; i++)
                    {
                        for(int j = 0; j < item_count; j++)
                        {
                            if(gene_chance_tab[i][j] == 0) //jeœli roll chance aktualnego genu jest jest równy 0, to pomijamy go i jedziemy dalej
                                i++;
                            else
                            { // w przeciwnym wypadku sprawdzamy, czy liczba jest w zakresie, etc.
                                if((rolled>= suma) && (rolled<suma+gene_chance_tab[i][j])) //sprawdza, czy roll mieœci siê w zakresie losowanego eventu
                                {
                                    //sprawdza, czy element jest ju¿ zajêty
                                    if(this.bolb_appearance[i] == 0)
                                    {
                                        this.bolb_appearance[i] = ++j; //ustawia nr genu dla wybranego typu trzeba dodaæ +1, ¿eby nie by³o, ¿e jest typ 0
                                        System.out.print("Your Bolb "+this.bolb_name+" ate "+item_map.get(id).item_name+". Congratulations, your Bolb grew ");
                                        Say_bodypart(i); //wypisuje konkretn¹ czêœæ cia³a bolba
                                        System.out.print(" type "+ j++ + "!");
                                        System.out.println(); 
                                        
                                    }
                                    else //if(this.bolb_appearance[i] != 0) //jeœli miejscce jest ju¿ nakarmione
                                        System.out.println("Your Bolb "+this.bolb_name+" ate "+item_map.get(id).item_name+", but nothing has changed. "
                                                                                                                + "Looks like it can't transform anymore in that field.");
                                    
                                    i = type_count; //sztucznie wychodzi z obu pêtli poprzez niespe³nienie warunku i < type_count
                                    j = item_count; //sztucznie wychodzi z obu pêtli poprzez niespe³nienie warunku j < item_count
                                }
                                else
                                    suma += gene_chance_tab[i][j];
                            }
                        }
                    }
                    
                    bolb_feed_ending(id); //usuwa przedmiot z ekwipunku oraz komentuje karmienie Bolba
                    break;
                }
                
                case '%':
                {
                    boolean count_type = false; // na pocz¹tku nie wczytuje liczby, której jeszcze nie ma
                    
                    //przechodzi przez Stringa, ¿eby sprawdziæ nr specjalnego itemu
                    for(int i = 0; i < bolb_food_evolution_list.get(ITEM_NUM).length(); i++)
                    {                        
                        if(bolb_food_evolution_list.get(ITEM_NUM).charAt(i) == '%')//w³¹cza tryb szukania wczytywania nr specjalnego itemu, tj. w³aœciwoœci
                            count_type = true;
                        
                        if(count_type)//jeœli tryb wczytywania typu zosta³ uruchomiony, to wczytuje nr specjalnego itemu
                        {
                            if(bolb_food_evolution_list.get(ITEM_NUM).charAt(i) == '?')//znalaz³ koniec liczby dla nr itemu
                            {
                                item_type = String_to_int(temp); //zapisuje nr specjalnego itemu
                                temp = "";
                                count_type = false; //liczba zosta³a znaleziona, nie trzeba doklejaæ reszty
                            }
                            else if(bolb_food_evolution_list.get(ITEM_NUM).charAt(i) != '%')//zabezpieczenie
                                temp+= bolb_food_evolution_list.get(ITEM_NUM).charAt(i);
                    }
                    }
                        
                    System.out.print("Your Bolb "+this.bolb_name+" ate "+item_map.get(id).item_name+". ");
                    switch(item_type)
                    {
                        case 0: //Zmniejsza breeding CD o 3 dni
                        {
                            System.out.print("Your Bolb's breeding cooldown has been decreased by 3 days! ");
                            this.addBreedingcd(-3);
                            break;
                        }
                        case 1: //Zmniejsza breeding CD o 5 dni
                        {
                            System.out.print("Your Bolb's breeding cooldown has been decreased by 5 days! ");
                            this.addBreedingcd(-5);
                            break;
                        }
                    }
                    bolb_feed_ending(id); //usuwa przedmiot z ekwipunku oraz komentuje karmienie Bolba
                    break;
                }
                case '^':
                {
                    System.out.println("Your Bolb "+this.bolb_name+" ate "+item_map.get(id).item_name+", but nothing has changed. "
                                                                                                                + "Looks like this item does nothing, but feeds your Bolb well.");

                    bolb_feed_ending(id); //usuwa przedmiot z ekwipunku oraz komentuje karmienie Bolba
                    break;
                }

                default:
                {
                    System.err.println("ERROR: UNKNOWN ITEM CHAR! (Bolb.feedBolb)");
                }
            }
            //WCINANIE PRZEZ BOLBA ITEMU + UPDATE HUNGER
        }
        else
            System.err.println("ERROR: YOU CAN'T FEED YOUR BOLB WITH THIS ITEM!(Bolb.feedBolb)");
        
        
    }
    //==========================================================================================================
    //==========================================================================================================
    public void bolb_feed_ending(String id)
    {
        bolb_item_consumption(id); //bolb wcina item z ekwipunku gracza
        this.setHunger(0);
        this.addHunger(0);
    }
    //==========================================================================================================
    //==========================================================================================================
    //-------------------- PRESENTS BOLB'S ENTIRELY
    public void Say_appearance()
    {
        System.out.println("Your Bolb's current appearance: ");
        for(int i = 0; i <BOLB_APPEARANCE_ELEMENTS; i++)
        {
            System.out.print("* ");
            
            //sprawdza, co jest aktualnie prezentowane
            if(  (i == 5) || (i == 6))//prezentacja kolorów
            {
                if(i == 5)
                    System.out.print("primary ");
                else
                    System.out.print("secondary ");
                System.out.print("color: ");
                Say_color(this.bolb_appearance[i]);
                
                System.out.println();
            }
            else//prezentacja czêœci cia³a
            {
                Say_bodypart(i);
                System.out.print(": ");
                if(this.bolb_appearance[i] == 0 )
                    System.out.println("none");
                else
                    System.out.println("type "+this.bolb_appearance[i]);
            }
        }
    }
    //==========================================================================================================
    //==========================================================================================================
    //-------------------- SAYS CERTAIN BOLB'S BODYPART BY THE NUMBER
    public void Say_bodypart(int bodypart)
    {
        switch(bodypart)
        {
            case 0:
            {
                System.out.print("wings");
                break;
            }
            case 1:
            {
                System.out.print("horns");
                break;
            }
            case 2:
            {
                System.out.print("skin");
                break;
            }
            case 3:
            {
                System.out.print("a tail");
                break;
            }
            case 4:
            {
                System.out.print("legs");
                break;
            }
        }
    }
    //==========================================================================================================
    //==========================================================================================================
    //-------------------- SAYS CERTAIN BOLB COLOR BY THE NUMBER
    public void Say_skin_type(int skin_type)
    {
        switch(skin_type)
        {
            case 0:
            {
                System.out.print("skin");
                break;
            }
            case 1:
            {
                System.out.print("fur");
                break;
            }
            case 2:
            {
                System.out.print("scales");
                break;
            }
            case 3:
            {
                System.out.print("barnacles");
                break;
            }
        }
    }
    //==========================================================================================================
    //==========================================================================================================
    //-------------------- SAYS CERTAIN BOLB COLOR BY THE NUMBER
    public void Say_color(int color)
            {
        switch(color)
        {
            case -1:
            {
                System.out.print("NONE! (translucent)");
                break;
            }
            case 0:
            {
                System.out.print("white");
                break;
            }
            case 1:
            {
                System.out.print("yellow");
                break;
            }
            case 2:
            {
                System.out.print("green");
                break;
            }
            case 3:
            {
                System.out.print("pink");
                break;
            }
            case 4:
            {
                System.out.print("orange");
                break;
            }
            case 5:
            {
                System.out.print("cyan");
                break;
            }
            case 6:
            {
                System.out.print("purple");
                break;
            }
            case 7:
            {
                System.out.print("red");
                break;
            }
            case 8:
            {
                System.out.print("blue");
                break;
            }
            case 9:
            {
                System.out.print("black");
                break;
            }
            case 10:
            {
                System.out.print("rainbow");
                break;
            }
            
        }
    }
    //==========================================================================================================
    //==========================================================================================================
    //-------------------- REMOVES ITEM EATEN BY BOLB FROM PLAYER'S INVENTORY (1 X ITEM)
     public void bolb_item_consumption(String id)
     {
         //przeszukanie listy graczy, aby znaleŸæ w³aœciciela bolba
         for(int i = 0; i < player_array.size(); i++)
         {
             if(player_array.get(i).entity_id.equals(this.bolb_owner_id))//jeœli znalaz³o w³aœciciela
             {
                player_array.get(i).removeItem(id, 1);
             }
         }
     }
     //==========================================================================================================
    //==========================================================================================================
    //-------------------- BOLB BREEDING SYSTEM (SIMPLE ONE) -- BREEDUJE WYBRANEGO BOLBA Z KOLEJNYM PODANYM W NAWIASIE
     public void breed(Bolb bolb) 
     {
         // sprawdzanie, czy s¹ przeciwne p³cie
         if(    ((this.bolb_gender == true) && (bolb.bolb_gender == false)) //samiczka i samiec
                ||
                ((this.bolb_gender == false) && (bolb.bolb_gender == true)) // samiec i samiczka
            )
         {
             int eggs = Roll(1,3); //rolluje, ile potomstwa bêdzie mieæ para
             System.out.println("Your Bolbs are parents of "+eggs+" egg(s).");
             
             //produkcja bolbów za pomoc¹ simple factory
             //(String owner_id, String name, boolean gender, int wings, int horns, int skin, int tail, int legs, int primary_col, int secondary_col)
             for(int i = 0; i < eggs; i++)
             {
                boolean new_gender;
                int outcome_num;
                int[] new_appearance = new int[BOLB_APPEARANCE_ELEMENTS];
                int bool_temp = Roll(0,1);
                
                //------------------------- GENEROWANIE P£CI
                if(bool_temp == 0)
                    new_gender = false;
                else
                    new_gender = true;
                
                //------------------------- GENEROWANIE ELEMENTÓW WYGL¥DU WG REGU£:
                /*
                * JEŒLI KA¯DY Z RODZICÓW MA GEN RÓ¯NY OD 0, (NIE LICZ¥C KOLORÓW, GDZIE JEST TO -1), TO SZANSA NA GEN A TO 45%, SZANSA NA GEN B TO 45%, A SZANSA NA GEN PUSTY(0) TO 10%
                * JEŒLI JEDEN Z RODZICÓW NA GEN PUSTY, TO DRUGI RODZIC MA 90% SZANSY NA DANIE SWOJEMU POTOMSTWU SWOJEGO GENU, I 10% NA GEN PUSTY(0)
                * JEŒLI OBOJE RODZICE MAJ¥ GEN PUSTY(0), TO POTOMSTWO AUTOMATYCZNIE TE¯ OTRZYMUJE GEN PUSTY(0)
                
                =============================== GENEROWANIE KOLORÓW
                * 40% SZANSY NA KOLOR RODZICA A
                * 40% SZANSY NA KOLOR RODZICA B
                * 20% NA INNY KOLOR NI¯ TEN OD RODZICÓW - MA ROLLOWAÆ KOLOR, DOPÓKI NIE BÊDZIE RÓ¯NY OD TYCH CO MAJ¥ RODZICE
                
                0 - wings
                1 - horns
                2 - skin
                3 - tail
                4 - legs
                5 - primary_col
                6 - secondary_col
                */
                
                for(int z = 0; z < new_appearance.length; z++) //przechodzi przez wszystkie elementy wygl¹du
                {
                    //==========================================================================================
                    //sprawdzanie, czy aktualnie rollowany element to primary/secondary color
                    //==========================================================================================
                    if(  (z == 5) || (z == 6)  )
                    {
                        //losuje z podanych szans event w kolejnoœci, jakiej zosta³y podane - 40 - commanded bolb, 40 - 2nd bolb, 20 - random
                        outcome_num = look_around_roll_event(40,40,20);
                        switch(outcome_num)
                        {
                            case 0: //commanded bolb
                            {
                                new_appearance[z] = this.bolb_appearance[z];
                                break;
                            }
                            
                            case 1: //2nd bolb
                            {
                                new_appearance[z] = bolb.bolb_appearance[z];
                                break;
                            }
                            
                            case 2: //random color - losuje kolor ró¿ny od koloru rodziców
                            {
                                int color_roll;
                                do
                                {
                                    color_roll = Roll(0,BOLB_COLORS_AMOUNT);
                                    
                                 }while(  (color_roll == this.bolb_appearance[z]) || (color_roll == bolb.bolb_appearance[z]));
                                
                                new_appearance[z] = color_roll;
                                break;
                            }
                        }
                    }
                    //==========================================================================================
                    else //jeœli nie jest aktualnie rollowany kolor, to rolluje szanse na inne elementy wygl¹du
                    //==========================================================================================
                    {
                        //sprawdzenie, czy oboje rodzice maj¹ gen ró¿ny od pustego
                        if( ( this.bolb_appearance[z] != 0) && (bolb.bolb_appearance[z] != 0) )
                        {
                            outcome_num = look_around_roll_event(45,45,10);
                            switch(outcome_num)
                            {
                                case 0: //commanded bolb - gen rodzica A
                                {
                                    new_appearance[z] = this.bolb_appearance[z];
                                    break;
                                }

                                case 1: //2nd bolb - gen rodzica B
                                {
                                    new_appearance[z] = bolb.bolb_appearance[z];
                                    break;
                                }

                                case 2: //GEN PUSTY
                                {
                                    new_appearance[z] = 0;
                                    break;
                                }
                            }
                        }
                        //======================================================= jeœli command bolb ma gen, a 2nd bolb ma gen pusty
                        else if( (this.bolb_appearance[z] !=0) && (bolb.bolb_appearance[z] == 0))
                        {
                            outcome_num = look_around_roll_event(90,10);
                            switch(outcome_num)
                            {
                                case 0: //commanded bolb - gen rodzica A
                                {
                                    new_appearance[z] = this.bolb_appearance[z];
                                    break;
                                }

                                case 1: //GEN PUSTY
                                {
                                    new_appearance[z] = 0;
                                    break;
                                }
                                
                            }
                        }
                        //======================================================= jeœli command bolb ma gen pusty, a 2nd bolb ma gen 
                        else if( (this.bolb_appearance[z] ==0) && (bolb.bolb_appearance[z] != 0))
                        {
                            outcome_num = look_around_roll_event(90,10);
                            switch(outcome_num)
                            {
                                case 0: //2nd bolb - gen rodzica B
                                {
                                    new_appearance[z] = bolb.bolb_appearance[z];
                                    break;
                                }

                                case 1: //GEN PUSTY
                                {
                                    new_appearance[z] = 0;
                                    break;
                                }
                            }
                        }
                        else//jeœli oboje maj¹ gen pusty
                        {
                            new_appearance[z] = 0;
                        }
                    }
                }
                //------------------- po skoñczeniu generowania ca³ej tablicy, tworzony jest bolb i automatycznie dodany do listy Bolbów gracza
                createNewBolb(this.bolb_owner_id, "--UNNAMED--", new_gender, new_appearance);
                 
             } 
         }
         else //w przeciwnym razie
             System.err.print("ERROR: YOU CAN'T BREED TWO BOLBS WITH THE SAME GENDER! (Bolb.breed)");
     }
    //==========================================================================================================
    //==========================================================================================================
    //-------------------- "Czesanie" Bolba - gathering craft materials once a day
    public void brush_bolb()
    {
         int amount = Roll(1,6);
         int owner = find_bolb_owner(this.bolb_owner_id);
        
        System.out.println();
        System.out.print("You've brushed your Bolb's ");
        Say_skin_type(this.bolb_appearance[2]);
        System.out.print(". You've gained "+amount);
        
       
        this.bolb_brushing = true; // oznacza, ¿e Bolb zosta³ poczesany na dziœ
        
        switch(this.bolb_appearance[2])
        {
            case 0:
            {
                System.out.print(" Bolb Silk.\n");
                player_array.get(owner).addItem("i0_016", amount);
                break;
            }
            
            case 1:
            {
                System.out.print(" Bolb Fur.\n");
                player_array.get(owner).addItem("i0_017", amount);
                break;
            }
            
            case 2:
            {
                System.out.print(" Bolb Scales.\n");
                player_array.get(owner).addItem("i0_018", amount);
                break;
            }
            
            case 3:
            {
                System.out.print(" Bolb Powder.\n");
                player_array.get(owner).addItem("i0_019", amount);
                break;
            }
        }
    }
     
}
