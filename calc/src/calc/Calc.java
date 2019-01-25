
package calc;

import java.util.Scanner;


public class Calc 
{


    public static void main(String[] args) 
    {    
        //0 - OBLICZANIE AMOUNT MIN I MAX NA PODSTAWIE POPRZEDNIEGO MIN ---> OBLICZANIE HURTOWE ---> MO¯NA OBLICZYÆ WIÊCEJ NI¯ 1 POZIOM
        //1 - MULTIPLIER
        Scanner scanner = new Scanner(System.in);        
        int continue_check = 0;
        
        
        double temp = 0;
        int place_chance = 0;
        int chance_item = 0;
        int place_lv = 0;
        
        int wynik = 0;
        int range = 0;    
        
        //Math.round(i) - S£U¯Y DO ZAOKR¥GLANIA

        double MULTI = 0.5;
        System.out.println("Podaj tryb:  \n0 - OBLICZANIE AMOUNT MIN MAX,  \n1 - OBLICZANIE MULTIPLIER dla poziomów od 1 - n  \n2 - OBLICZANIE MULTIPLIER dla poziomu 0");
        int mode = scanner.nextInt();


        System.out.println("-----------------------------------------");
        do
        {
            switch(mode)
            {
                case 0: //OBLICZANIE AMOUNT MIN I MAX NA PODSTAWIE POPRZEDNIEGO MIN ---> OBLICZANIE HURTOWE ---> MO¯NA OBLICZYÆ WIÊCEJ NI¯ 1 POZIOM
                {
                    System.out.println("Podaj w nastêpuj¹cej kolejnoœci: \nBASE_MAX_AMOUNT  \nSTARTING_LEVEL_NUMBER  "
                                                                                            + "\nLEVELS_TO_PROCEED__NUMBER(Including the STARTING_LEVEL)  \n1/0 (1 - wiêcej itemów, 0 - koniec programu)");
                    
                    int base_max = scanner.nextInt();
                    int starting_level = scanner.nextInt();
                    int how_many_levels = scanner.nextInt();
                    int curr_lv = 0;
                    int[][] lvl_tab = new int[2][how_many_levels];//[0 - min, 1 - max][number]
                    
                    System.out.println("---------------------------------------");
                    
                    lvl_tab[0][0] = base_max;
                    temp = lvl_tab[0][0]*1.75;
                    lvl_tab[1][0] = (int)(Math.round(temp));
                    System.out.println("Amount calculated for level: "+starting_level+"\nAmount min: "+lvl_tab[0][0]+ "\nAmount max: "+lvl_tab[1][0]);
                    
                    for(int i=1; i<how_many_levels; i++)
                    {
                        lvl_tab[0][i] = lvl_tab[1][i-1];
                        temp = lvl_tab[0][i]*1.75;
                        lvl_tab[1][i] = (int)(Math.round(temp));
                        
                        curr_lv = starting_level+i;
                        System.out.println("Amount calculated for level: "+curr_lv+"\nAmount min: "+lvl_tab[0][i]+ "\nAmount max: "+lvl_tab[1][i]);
                    }
                    
                    System.out.println("---------------------------------------");
                    
                    
                    continue_check = scanner.nextInt();
                    break;
                }
                
                case 1: //MULTIPLIER
                {
                    System.out.println("[PRZECINEK!]Podaj multiplier: ");
                    MULTI = scanner.nextDouble();
                    System.out.println("Wybrany multiplier: "+MULTI);
                    
                    System.out.println("Podaj w nastêpuj¹cej kolejnoœci: \nPLACE_LV  \nPLACE_CHANCE  \nCHANCE_OF_FINDING_ITEM  \n1/0 (1 - wiêcej itemów, 0 - koniec programu)");
                    
                    place_lv = scanner.nextInt();
                    place_chance = scanner.nextInt();
                    chance_item = scanner.nextInt();
                    
                    System.out.println("---------------------------------------");
                    temp = ( (100-place_chance) + (100-chance_item))*MULTI*1.5*place_lv;
                    wynik = (int)(Math.round(temp));

                    temp = wynik*0.35;
                    range = (int)(Math.round(temp));
                    
                    System.out.println("Place Lv: "+place_lv+"\nXP_Multiplier: "+MULTI+" \nXP dla itemu: "+wynik+ "\nXP range: "+range);
                    System.out.println("---------------------------------------");
                    
                    
                    continue_check = scanner.nextInt();
                    break;
                }
                
                case 2: //MULTIPLIER LV 0
                {
                    System.out.println("[PRZECINEK!]Podaj multiplier: ");
                    MULTI = scanner.nextDouble();
                    System.out.println("Wybrany multiplier: "+MULTI);
                    
                    System.out.println("Podaj w nastêpuj¹cej kolejnoœci: \nPLACE_CHANCE  \nCHANCE_OF_FINDING_ITEM  \n1/0 (1 - wiêcej itemów, 0 - koniec programu)");
                    
                    place_chance = scanner.nextInt();
                    chance_item = scanner.nextInt();
                    
                    System.out.println("---------------------------------------");
                    temp = ( (100-place_chance) + (100-chance_item))*MULTI;
                    wynik = (int)(Math.round(temp));

                    temp = wynik*0.35;
                    range = (int)(Math.round(temp));
                    
                    System.out.println("Place Lv: "+place_lv+"\nXP_Multiplier: 0.4 \nXP dla itemu: "+wynik+ "\nXP range: "+range);
                    System.out.println("---------------------------------------");
                    
                    
                    continue_check = scanner.nextInt();
                    break;
                }
                
                
                
                

            }
        }while(continue_check == 1);
        scanner.next();
    }

}