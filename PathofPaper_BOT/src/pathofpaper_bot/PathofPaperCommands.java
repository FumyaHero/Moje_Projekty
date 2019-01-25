/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathofpaper_bot;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Fumi
 */
public class PathofPaperCommands 
{
    private static Scanner scanner = new Scanner(System.in);
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL 1-NUMBER
    public static int Roll(int max) 
    {
        Random roll = new Random();
        int wynik = roll.nextInt(max-1)+1;
        
        return wynik;
    }
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------------- DESCRIBE TYPE_DETAILS 
    //----------------------------------------------------------------------------------------------------------------------------------------------- OPISZ BRON/ARMOUR I JEJ TYP
    public static void DESCRIBE_TYPE_DETAILS(int tier, int[] type_detail)
    {
        //--------------------------------- OPIS ITEMU
        int suma = 0;
        switch(type_detail[0])
        {
            case 1:
                System.out.println("Przedmiot: Top�r");
                System.out.println("+1 do obra�e� za ka�dy punkt WAM");
                System.out.println("Jednor�czny: +1 WAM / Dwur�czny: +3 WAM ");
                break;
                
            case 2:
                System.out.println("Przedmiot: Laska");
                System.out.println("Je�li przeciwnik rzuca d10 i otrzyma 2 lub ni�ej, blokujesz atak. ");
                System.out.println("Dwur�czna: +3 WAM ");
                break;
                
            case 3:
                System.out.println("Przedmiot: Bu�awa");
                System.out.println("Zmniejsza pr�g og�uszenia(Threshold) przeciwnika o 5.");
                System.out.println("Jednor�czna: +1 WAM / Dwur�czna: +3 WAM");
                break;
                
            case 4:
                System.out.println("Przedmiot: Miecz");
                System.out.println("+2 do obra�e� przy rzucie d10 dla 10");
                System.out.println("Jednor�czny: +1 wAM / Dwur�czny: +3 WAM");
                break;
                
            case 5:
                System.out.println("Przedmiot: Sztylet");
                System.out.println("+1 do obra�e� przy rzucie d10 dla 10 i 9");
                System.out.println("Jednor�czny: +1 WAM");
                break;
                
            case 6:
                System.out.println("Przedmiot: �uk");
                System.out.println("Mo�esz wykona� rzut na inicjatyw� dwa razy i wybra� wy�szy wynik.");
                System.out.println("Dwur�czny: +3 WAM");
                break;
                
            case 7: 
                System.out.println("Przedmiot: Pazury");
                suma = 4*tier;
                System.out.println("Kradzie� �ycia: +"+ suma);
                System.out.println("Jednor�czne: +1 WAM");
                break;
                
            case 8: 
                System.out.println("Przedmiot: Ber�o");
                System.out.println("Zwi�ksza d�ugo�� trwania og�uszenia na przeciwnikach o 1 tur�.");
                System.out.println("Jednor�czna: +1 WAM");
                break;
                
            case 9:
                System.out.println("Przedmiot: R�d�ka");
                suma = 2*tier;
                System.out.println("+" +suma+" do many");
                System.out.println("Jednor�czna: +1 WAM");
                break;
                
            case 10:
                System.out.println("Przedmiot: W��cznia");
                System.out.println("Mo�esz wykona� rzut na Manewr i Lekkoatletyk� dwa razy i wybra� wy�szy wynik");
                System.out.println("Jednor�czna: +1 WAM / Dwur�czna: +3 WAM");
                break;
                
            case 11:
                System.out.println("Przedmiot: Napier�nik");
                
                if  (type_detail[1] == 1)
                {
                    System.out.println("Typ: Ci�ki p�ytowy");
                    System.out.println("Pancerz(Armour) +6, Manewr -4, Lekkoatletyka -4");
                }
                else if(type_detail[1] == 2)
                {
                    System.out.println("Typ: Sk�rzany");
                    System.out.println("Odporno�� (Resistance) +4");
                }
                else if(type_detail[1] == 3)
                {
                    System.out.println("Typ: Jedwabny");
                    System.out.println("Energy Shield +20");
                }
                break;
                
            case 12:
                System.out.println("Przedmiot: Tarcza");
                
                if  (type_detail[1] == 1)
                {
                    System.out.println("Typ: Metalowa");
                    suma = 8*tier;
                    System.out.println("Pancerz(Armour) +" + suma + ", Manewr -2, Lekkoatletyka -2");
                }
                else if(type_detail[1] == 2)
                {
                    System.out.println("Typ: Sk�rzana");
                    suma = 6*tier;
                    System.out.println("Odporno�� (Resistance) +" + suma);
                }
                else if(type_detail[1] == 3)
                {
                    System.out.println("Typ: Voodoo");
                    suma = 20*tier; 
                    int suma2 = 2*tier;
                    System.out.println("Energy Shield +" + suma + ", Obra�enia +" + suma2);
                }
                break;
                
            case 13:
                System.out.println("Przedmiot: Buty");
                if  (type_detail[1] == 1)
                {
                    System.out.println("Typ: Metalowe");
                    System.out.println("Pancerz(Armour) +2");
                }
                else if(type_detail[1] == 2)
                {
                    System.out.println("Typ: Sk�rzane");
                    System.out.println("Odporno�� (Resistance) +2");
                }
                else if(type_detail[1] == 3)
                {
                    System.out.println("Typ: Jedwabne");
                    System.out.println("Energy Shield +10");
                }
                break;
                
            case 14:
                System.out.println("Przedmiot: He�m");
                if  (type_detail[1] == 1)
                {
                    System.out.println("Typ: Metalowy");
                    System.out.println("Pancerz(Armour) +2");
                }
                else if(type_detail[1] == 2)
                {
                    System.out.println("Typ: Sk�rzany");
                    System.out.println("Odporno�� (Resistance) +2");
                }
                else if(type_detail[1] == 3)
                {
                    System.out.println("Typ: Jedwabny");
                    System.out.println("Energy Shield +10");
                }
                break;
                
            case 15:
                System.out.println("Przedmiot: Amulet");
                if  (type_detail[1] == 1)
                {
                    System.out.println("Typ: Koralowy");
                    suma = Roll(10)*2 +10*tier;
                    System.out.println("Zdrowie +" + suma);
                }
                else if(type_detail[1] == 2)
                {
                    System.out.println("Typ: Paua");
                    suma = Roll(4) +2*tier;
                    System.out.println("Mana +" + suma);
                }
                else if(type_detail[1] == 3)
                {
                    System.out.println("Typ: Lapisowy");
                    suma = Roll(10) +5*tier;
                    System.out.println("INT +" + suma);
                }
                else if(type_detail[1] == 4)
                {
                    System.out.println("Typ: Onyksowy");
                    suma = Roll(4) +2*tier;
                    System.out.println("Odporno�� (Resistance) +" + suma);
                }
                else if(type_detail[1] == 5)
                {
                    System.out.println("Typ: Bursztynowy");
                    suma = Roll(10) +5*tier;
                    System.out.println("STR +" + suma);
                }
                else if(type_detail[1] == 6)
                {
                    System.out.println("Typ: Jadeitowy");
                    suma = Roll(10) +5*tier;
                    System.out.println("DEX +" + suma);
                }
                break;
                
            case 16:
                System.out.println("Przedmiot: Pier�cie�");
                if  (type_detail[1] == 1)
                {
                    System.out.println("Typ: Koralowy");
                    suma = Roll(10)*2 +10*tier;
                    System.out.println("Zdrowie +" + suma);
                }
                else if(type_detail[1] == 2)
                {
                    System.out.println("Typ: Paua");
                    suma = Roll(4) +2*tier;
                    System.out.println("Mana +" + suma);
                }
                else if(type_detail[1] == 3)
                {
                    System.out.println("Typ: �elazny");
                    suma = 1 +1*tier;
                    System.out.println("WAM +" + suma);
                }
                else if(type_detail[1] == 4)
                {
                    System.out.println("Typ: Onyksowy");
                    suma = Roll(4) +2*tier;
                    System.out.println("Odporno�� (Resistance) +" + suma);
                }
                break;
                
            default:
                System.err.println("B��D: NIEPOPRAWNY TYP PRZEDMIOTU!");
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL_TYPE_DETAIL
    public static int[] ROLL_TYPE_DETAIL(int tier)
    {
        int[] type_detail = new int[2];
        int suma = 0;
        
        type_detail[0] = Roll(16); //rolluje typ itemu
        
        if(     type_detail[0] == 11 || 
                type_detail[0] == 12 || 
                type_detail[0] == 13 ||
                type_detail[0] == 14) //je�li jedno z tych zosta�o wyrollowane, losuj typ armoura
            type_detail[1] = Roll(3);
        
        else if(type_detail[0] == 15) //losuj typ naszyjnika
            type_detail[1] = Roll(6);
        
        else if( type_detail[0] == 16) //losuj typ pier�cionka
            type_detail[1] = Roll(4);
            
        
       DESCRIBE_TYPE_DETAILS(tier, type_detail);
        

        System.out.println("--------------------------------");
        return type_detail;
    }
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL_WEAPON_1_10_MOD
    public static void ROLL_WEAPON_1_10_MOD(int tier)
    {
        int roll = Roll(8);
        int suma = 0;
        /*----------- BRONIE - ROLLE:
        1 - 1d10 DEX +5 per additional tier
        2 - 1d10 STR +5 per additional tier
        3 - 1d10 INT +5 per additional tieR
        4 - Increase Threshold effect duration by 1 round
        5 - 1 WAM per tier
        6 - 1d10 Lower enemy threshold +6 per additional tier
        7 - 2 life leech per +2 per additional tier
        8 - 1 Mana leech per tier +1 per additional tier
        */
        
        switch(roll)
        {
            case 1:
                suma = Roll(10) + 5*tier;
                System.out.println("DEX +" + suma);
                break;
                
            case 2:
                suma = Roll(10) + 5*tier;
                System.out.println("STR +" + suma);
                break;
                
            case 3:
                suma = Roll(10) + 5*tier;
                System.out.println("INT +" + suma);
                break;
                
            case 4:
                System.out.println("Zwi�ksza d�ugo�� trwania og�uszenia na przeciwnikach o 1 tur�");
                break;
                
            case 5:
                System.out.println("WAM +" + tier);
                break;
                
            case 6:
                suma = Roll(10)+6*tier;
                System.out.println("Zmniejsza pr�g og�uszenia(Threshold) przeciwnika o "+ suma);
                break;
                
            case 7:
                suma = 2*tier + 2*tier;
                System.out.println("Kradzie� �ycia +"+ suma);
                break;
                
            case 8:
                suma = 1*tier + 1*tier;
                System.out.println("Kradzie� many +"+ suma);
                break;
            
            default:
                System.out.println("B��D: NIEZNANY ROLL!");
                break;
            
        }
        
    }
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL_WEAPON_11_14_MOD
    public static void ROLL_WEAPON_11_14_MOD(int[] type_detail, int tier)
    {
        /*----------- BUTY - ROLLE:
        1 - 1d10 DEX +5 per additional tier
        2 - 1d10 STR +5 per additional tier
        3 - 1d10 INT +5 per additional tieR
        4 - 1d10  increased threshold +6 per additional tier
        5 - 1d4 Resistance per +2 per additional tier
        6 - 1d10 x2 Life per +10 per additional tier
        7 - DLA TYPU:
            1 - metal - 1d4 armour +2 per additional tier
            2 - leather - 1d4 Elemental Resistance +2 per additional tier
            3 - cloth - 1d10 x 2  increased energy shield +10 per additional tier
        8 - 1d10 Maneuver +5 per additional tier
        9 - +1 speed +1 per additional tier
        */
        
        /*----------- RESZTA ZBROI - ROLLE:
        1 - 1d10 DEX +5 per additional tier
        2 - 1d10 STR +5 per additional tier
        3 - 1d10 INT +5 per additional tieR
        4 - 1d10  increased threshold +6 per additional tier
        5 - 1d4 Resistance per +2 per additional tier
        6 - 1d10 x2 Life per +10 per additional tier
        7 - DLA TYPU:
            1 - metal - 1d4 armour +2 per additional tier
            2 - leather - 1d4 Elemental Resistance +2 per additional tier
            3 - cloth - 1d10 x 2  increased energy shield +10 per additional tier
        
        */
        
        
        int suma = 0;
        int roll = 0;
        
        if(type_detail[0] == 13) // rollowanie dla but�w
        {
            roll = Roll(9);
            switch(roll)
            {
                case 1:
                    suma = Roll(10) + 5*tier;
                    System.out.println("DEX +" + suma);
                    break;

                case 2:
                    suma = Roll(10) + 5*tier;
                    System.out.println("STR +" + suma);
                    break;

                case 3:
                    suma = Roll(10) + 5*tier;
                    System.out.println("INT +" + suma);
                    break;

                case 4:
                    suma = Roll(10) +6*tier;
                    System.out.println("Odporno�� na og�uszenia(Threshold) +" + suma);
                    break;

                case 5:
                    suma = Roll(4) + 2*tier;
                    System.out.println("Odporno�� (Resistance) +" + suma);
                    break;

                case 6:
                    suma = Roll(10)*2 +10*tier;
                    System.out.println("Zdrowie +" + suma);
                    break;

                case 7:
                    if  (type_detail[1] == 1)
                    {
                        suma = Roll(4) +2*tier;
                        System.out.println("Pancerz(Armour) +"+ suma);
                    }
                    else if(type_detail[1] == 2)
                    {
                        suma = Roll(4) +2*tier;
                        System.out.println("Odporno�� (Resistance) +"+ suma);
                    }
                    else if(type_detail[1] == 3)
                    {
                        suma = Roll(10)*2 +10*tier;
                        System.out.println("Energy Shield +"+ suma);
                    }
                    break;

                case 8:
                    suma = Roll(10) + 5*tier;
                    System.out.println("Manewr +"+ suma);
                    break;
                    
                case 9:
                    suma = 1 + 1*tier;
                    System.out.println("Inicjatywa +"+ suma);
                    break;

                default:
                    System.out.println("B��D: NIEZNANY ROLL!");
                    break;

            }
        }
        
        else //rollowanie dla reszty rzeczy
        {
            roll = Roll(7);
            switch(roll)
            {
                case 1:
                    suma = Roll(10) + 5*tier;
                    System.out.println("DEX +" + suma);
                    break;

                case 2:
                    suma = Roll(10) + 5*tier;
                    System.out.println("STR +" + suma);
                    break;

                case 3:
                    suma = Roll(10) + 5*tier;
                    System.out.println("INT +" + suma);
                    break;

                case 4:
                    suma = Roll(10) +6*tier;
                    System.out.println("Odporno�� na og�uszenia(Threshold) +" + suma);
                    break;

                case 5:
                    suma = Roll(4) + 2*tier;
                    System.out.println("Odporno�� (Resistance) +" + suma);
                    break;

                case 6:
                    suma = Roll(10)*2 +10*tier;
                    System.out.println("Zdrowie +" + suma);
                    break;

                case 7:
                    if  (type_detail[1] == 1)
                    {
                        suma = Roll(4) +2*tier;
                        System.out.println("Pancerz(Armour) +"+ suma);
                    }
                    else if(type_detail[1] == 2)
                    {
                        suma = Roll(4) +2*tier;
                        System.out.println("Odporno�� (Resistance) +"+ suma);
                    }
                    else if(type_detail[1] == 3)
                    {
                        suma = Roll(10)*2 +10*tier;
                        System.out.println("Energy Shield +"+ suma);
                    }
                    break;

                default:
                    System.out.println("B��D: NIEZNANY ROLL!");
                    break;

            }
        }
        
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL_WEAPON_15_MOD
    public static void ROLL_WEAPON_15_MOD(int tier)
    {
        /*----------- AMULET - ROLLE:
        1 - 1d10 DEX +5 per additional tier
        2 - 1d10 STR +5 per additional tier
        3 - 1d10 INT +5 per additional tieR
        4 - Reduce stunned enemy speed by 1 per tier
        5 - Increase damage against shocked enemies for you and allies by 1 WAM per tier
        6 - 2 Life leech per tier
        7 - 1 Mana leech per tier
        8 - 1d4 Armour +2 per additional tier
        9 - 1d10 x2 Life +10 per additional tier
        10 - +3 do obra�e� przy rzucie d10 dla 10 i 9 PER TIER
        11 - +1 WAM +1 per additional tier
        */
        int roll = Roll(11);
        int suma = 0;
        
        switch(roll)
        {
            case 1:
                suma = Roll(10) + 5*tier;
                System.out.println("DEX +" + suma);
                break;
                
            case 2:
                suma = Roll(10) + 5*tier;
                System.out.println("STR +" + suma);
                break;
                
            case 3:
                suma = Roll(10) + 5*tier;
                System.out.println("INT +" + suma);
                break;
                
            case 4:
                System.out.println("Zwi�ksza d�ugo�� trwania og�uszenia na przeciwnikach o 1 tur�");
                break;
                
            case 5:
                System.out.println("Zwi�ksz obra�enia swoje i swoich sojusznik�w wobec przeciwnika, kt�ry jest podpalony/och�odzony/pora�ony - WAM +" + tier);
                break;
                
            case 6:
                suma = 2*tier;
                System.out.println("Kradzie� �ycia +"+ suma);
                break;
                
            case 7:
                suma = 1*tier;
                System.out.println("Kradzie� many +"+ suma);
                break;
                
            case 8:
                suma = Roll(4) +2*tier;
                System.out.println("Pancerz(Armour) +"+ suma);
                break;
                
            case 9:
                suma = Roll(10)*2 +10*tier;
                System.out.println("Zdrowie +" + suma);
                break;
                
            case 10:
                suma = 3*tier;
                System.out.println("+"+ suma +" do obra�e� przy rzucie d10 dla 10 i 9");
                break;
                
            case 11:
                suma = 1+1*tier;
                System.out.println("WAM +"+ suma);
                break;
            
            default:
                System.out.println("B��D: NIEZNANY ROLL!");
                break;
            
        }
        
        
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL_WEAPON_16_MOD
    public static void ROLL_WEAPON_16_MOD(int tier)
    {
        /*----------- PIER�CIE� - ROLLE:
        1 - 1d10 DEX +5 per additional tier
        2 - 1d10 STR +5 per additional tier
        3 - 1d10 INT +5 per additional tieR
        4 - 1d4 damage +2 per additional tier
        5 - 2 Life leech +2 per additional tier
        6 - 1d4 Resistance +2 per additional tier
        7 - 1d10 x2 life +10 per additional tier
        8 - 1 WAM +1 per additional tier
        9 - 1d10 x2 Life +10 per additional tier
        10 - 1d4 Armour +2 per additional tier

        */
        int roll = Roll(10);
        int suma = 0;
        
        switch(roll)
        {
            case 1:
                suma = Roll(10) + 5*tier;
                System.out.println("DEX +" + suma);
                break;
                
            case 2:
                suma = Roll(10) + 5*tier;
                System.out.println("STR +" + suma);
                break;
                
            case 3:
                suma = Roll(10) + 5*tier;
                System.out.println("INT +" + suma);
                break;
                
            case 4:
                suma = Roll(4) +2*tier;
                System.out.println("Obra�enia +"+ suma);
                break;
                
            case 5:
                suma = 2 + 2*tier;
                System.out.println("Kradzie� �ycia +"+ suma);
                break;
                
            case 6:
                suma = Roll(4) + 2*tier;
                System.out.println("Odporno�� (Resistance) +" + suma);
                break;
                
            case 7:
                suma = Roll(10)*2 +10*tier;
                System.out.println("Zdrowie +" + suma);
                break;
                
            case 8:
                suma = 1+1*tier;
                System.out.println("WAM +"+ suma);
                break;
                
            case 9:
                suma = Roll(10)*2 +10*tier;
                System.out.println("Zdrowie +" + suma);
                break;
                
            case 10:
                suma = Roll(4) +2*tier;
                System.out.println("Pancerz(Armour) +"+ suma);
                break;
            
            default:
                System.out.println("B��D: NIEZNANY ROLL!");
                break;
            
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL WEAPON MODS_DETAILS
    public static void ROLL_WEAPON_MODS_DETAILS(int[] type_detail, int tier)
    {
        switch(type_detail[0])
        {
            case 1:
                ROLL_WEAPON_1_10_MOD(tier);
                break;
                
            case 2:
                ROLL_WEAPON_1_10_MOD(tier);
                break;
                
            case 3:
                ROLL_WEAPON_1_10_MOD(tier);
                break;
                
            case 4:
                ROLL_WEAPON_1_10_MOD(tier);
                break;
                
            case 5:
                ROLL_WEAPON_1_10_MOD(tier);
                break;
                
            case 6:
                ROLL_WEAPON_1_10_MOD(tier);
                break;
                
            case 7: 
                ROLL_WEAPON_1_10_MOD(tier);
                break;
                
            case 8: 
                ROLL_WEAPON_1_10_MOD(tier);
                break;
                
            case 9:
                ROLL_WEAPON_1_10_MOD(tier);
                break;
                
            case 10:
                ROLL_WEAPON_1_10_MOD(tier);
                break;
                
            case 11:
                ROLL_WEAPON_11_14_MOD(type_detail, tier);
                break;
                
            case 12:
                ROLL_WEAPON_11_14_MOD(type_detail, tier);
                break;
                
            case 13:
                ROLL_WEAPON_11_14_MOD(type_detail, tier);
                break;
                
            case 14:
                ROLL_WEAPON_11_14_MOD(type_detail, tier);
                break;
                
            case 15:
                ROLL_WEAPON_15_MOD(tier);
                break;
                
            case 16:
                ROLL_WEAPON_16_MOD(tier);
                break;

            default:
                System.err.println("B��D: NIEPOPRAWNY TYP PRZEDMIOTU!");
            
            
        }
        
        
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL WEAPON MODS
    public static void ROLL_WEAPON_MODS(int item_rarity, int[] type_detail, int tier)
    {
        switch(item_rarity)
        {
            case 1: //normal
                System.out.println("Rarity: Zwyk�y ");
                ROLL_WEAPON_MODS_DETAILS(type_detail, tier);
                break;
                
            case 2: //magic
                System.out.println("Rarity: Magiczny ");
                for(int i = 0; i < 2; i++) //rolluje 2 mody dla magic
                    ROLL_WEAPON_MODS_DETAILS(type_detail, tier);
                break;
                
            case 3: //rare
                System.out.println("Rarity: Rzadki ");
                int luck = Roll(3) + 2; //zakres od 3 do 5
                for(int i = 0; i < luck; i++)
                    ROLL_WEAPON_MODS_DETAILS(type_detail, tier);
                break;
                
            case 4: //unique
                System.out.println("Rarity: Unikatowy ");
                System.err.println("ZROBI� POTEM LOLOLOL");
                break;
            
            default:
                System.err.println("B��D: NIEPOPRAWNE RARITY!");
                break;
        }
        
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL_WEAPON_UNIQUE
    public static void ROLL_WEAPON_UNIQUE()
    {
        int roll = Roll(19);
        int suma = 0;
        
        switch(roll)
        {
            case 1:
                System.out.println("Nazwa unikatu: Varunastra");
                System.out.println("Przedmiot: Miecz");
                System.out.println("Jednor�czna: +1 WAM");
                System.out.println("-----------------------------------");
                System.out.println("Kradzie� many +1");
                System.out.println("Dzia�a jak top�r, miecz, sztylet, ber�o, pazury, w��cznia i bu�awa R�WNOCZE�NIE.");
                System.out.println("+1 do obra�e� za ka�dy punkt WAM");
                System.out.println("+2 do obra�e� przy rzucie d10 dla 10");
                System.out.println("+1 do obra�e� przy rzucie d10 dla 10 i 9");
                System.out.println("Zwi�ksza d�ugo�� trwania og�uszenia na przeciwnikach o 1 tur�.");
                System.out.println("Kradzie� �ycia: +4");
                System.out.println("Mo�esz wykona� rzut na Manewr i Lekkoatletyk� dwa razy i wybra� wy�szy wynik"); 
                
                System.out.println("Wygl�d: https://pathofexile.gamepedia.com/Varunastra"); 
                break;
            
            case 2:
                System.out.println("Nazwa unikatu: Karui Ward");
                System.out.println("Przedmiot: Amulet");
                System.out.println("Typ: Jadeitowy");
                suma = Roll(10);
                System.out.println("DEX +" + suma);
                System.out.println("-----------------------------------");
                System.out.println("DEX +10");
                System.out.println("STR +20");
                System.out.println("Inicjatywa +1");
                System.out.println("WAM dla pocisk�w +2");
                System.out.println("Nie mo�esz chybi� - trafiasz nawet przy wyrzuceniu 1 dla d10");

                System.out.println("Wygl�d: https://pathofexile.gamepedia.com/Karui_Ward");
                break;
            
            case 3:
                System.out.println("Nazwa unikatu: Mj�lnir");
                System.out.println("Przedmiot:  Bu�awa");
                System.out.println("Jednor�czna: +1 WAM");
                System.out.println("-----------------------------------");
                System.out.println("WAM +1");
                System.out.println("Wszelkie obra�enia jakie zadajesz s� przekonwertowane do obra�e� b�yskawicy (lightning damage)");
                System.out.println("(TYLKO RAZ NA TUR�!) Je�li pomy�lnie zaatakujesz przeciwnika, mo�esz u�y� czaru b�yskawicy (lightning spell) - p�ac�c za niego man�, oczywi�cie. Je�li posta� u�ywa dw�ch borni, ta umiej�tno�� jest niedost�pna. ");
                
                System.out.println("Wygl�d: https://pathofexile.gamepedia.com/Mj%C3%B6lner"); 
                break; 
                
            case 4:
                System.out.println("Nazwa unikatu: Malachai�s Artifice");
                System.out.println("Przedmiot:  Pier�cie�");
                System.out.println("Typ: Onyksowy");
                suma = Roll(4);
                System.out.println("Odporno�� (Resistance) +" + suma);
                System.out.println("-----------------------------------");
                System.out.println("Odporno�� (Resistane) -8");
                System.out.println("Jako g��wn� akcj� (Major Action) wybierz jeden z trzech �ywio��w (Elements) - (ogie�, l�d, b�yskawica). Zwi�ksz swoj� Odporno�� (Resistance) o 16 dla wybranego �ywio�u.");
                                
                System.out.println("Wygl�d: https://pathofexile.gamepedia.com/Malachai%27s_Artifice"); 
                break;   
                
            case 5:
                System.out.println("Nazwa unikatu: Star of Wraeclast");
                System.out.println("Przedmiot:  Amulet");
                System.out.println("Typ: Rubin");
                System.out.println("Odporno�� (Resistance) +2");
                System.out.println("-----------------------------------");
                System.out.println("Odporno�� (Resistane) +10");
                System.out.println("Twoje kl�twy s� wspomagane (supported) Zredukowaniem Many(Reduced Mana).");
                System.out.println("Nie mo�esz by� przekl�ty poprzez Soul Skille(Gemy).");
                System.out.println("Pozwala na u�ycie Illusionary Warp (Teleport iluzji): ");
                System.out.println("Koszt: 3 Many, �ywio�: L�d, Zakl�cie(Spell), Mniejsza Akcja(Minor)"); 
                System.out.println("Teleportuje u�ytkownika do wybranej przez niego lokacji (W zasi�gu 20m max). Miejsce pocz�tkowe i ko�cowe teleportu s� pokryte lodem, spowalniaj�c przeciwnik�w(Inicjatywa -2) na 2 tury. GM NOTES: S.83");
                
                System.out.println("Wygl�d: https://pathofexile.gamepedia.com/Star_of_Wraeclast");
                break;
                
            case 6:
                System.out.println("Nazwa unikatu: Wanderlust");
                System.out.println("Przedmiot:  Buty");
                System.out.println("Typ: Jedwabne");
                System.out.println("Energy Shield +4");
                System.out.println("-----------------------------------");
                System.out.println("Inicjatywa +1");
                System.out.println("Posta� nie mo�e by� zamro�ona");
                System.out.println("Mana +2");
                System.out.println("Mo�esz wykona� rzut na survival dwa razy i wybra� wy�szy wynik.");
                
                System.out.println("Wygl�d: https://pathofexile.gamepedia.com/Wanderlust");
                break;   
                
            case 7:
                System.out.println("Nazwa unikatu: Matua Tupuna");
                System.out.println("Przedmiot:  Tarcza");
                System.out.println("Typ: Voodoo");
                System.out.println("Energy Shield +20");
                System.out.println("-----------------------------------");
                System.out.println("Obra�enia (Damage) +2");
                System.out.println("Maksymalna ilo�� Zombie +1");
                System.out.println("Mana +4");
                System.out.println("+1 Tier do efektywno�ci aur postaci.");
                System.out.println("Energy Shield +10.");
                System.out.println("Bezcielesna g�owa, kt�ra zwymiotuje na ka�dego, kto b�dzie zagra�a� jej Mistrzowi. Musi by� karmiona dusz� raz na tydzie�.");
                
                System.out.println("Wygl�d: https://pathofexile.gamepedia.com/Matua_Tupuna");
                break;
                
            case 8:
                System.out.println("Nazwa unikatu: Dreamfeather");
                System.out.println("Przedmiot:  Miecz");
                System.out.println("Jednor�czny: +1 WAM");
                System.out.println("-----------------------------------");
                System.out.println("DEX +20");
                System.out.println("+1 WAM za ka�de 20 DEX");
                System.out.println("+1 Inicjatywa za ka�de 40 DEX");
                System.out.println("Lekki jak pi�rko. Podczas pe�ni ksi�yca, miecz jasno �wieci i dodaje w�a�cicielowi +4 Inicjatywy i +2 WAM");
                
                System.out.println("Wygl�d: https://pathofexile.gamepedia.com/Dreamfeather");
                break;
                
            case 9:
                System.out.println("Nazwa unikatu: Facebreaker");
                System.out.println("Przedmiot:  R�kawice (Bro�)");
                System.out.println("Typ: Sk�rzane");
                System.out.println("Energy Shield +2");
                System.out.println("-----------------------------------");
                System.out.println("STR +10");
                System.out.println("+1 WAM za ka�de 5 STR, je�li nie u�ywasz broni");
                System.out.println("Posta� nie mo�e u�ywa� broni, ale mo�e nadal u�ywa� tarczy");
                System.out.println("Je��i posta� zabije przeciwnika, eksploduje on w najbardziej makabryczny spos�b. Zastraszenie +4 do ko�ca walki");
                
                System.out.println("Wygl�d: https://pathofexile.gamepedia.com/Facebreaker");
                break;
                
            case 10:
                System.out.println("Nazwa unikatu: Bonesaw");
                System.out.println("Przedmiot:  Top�r");
                System.out.println("Dwur�czny: +2 WAM");
                System.out.println("-----------------------------------");
                System.out.println("Obra�enia (Damage) +25");
                System.out.println("WAM +2");
                System.out.println("Threshold przeciwnika -12");
                System.out.println("Regeneracja �ycia na tur�: +8");
                System.out.println("Posta� nie mo�e krwawi�.");
                
                System.out.println("Wygl�d: Wielki, z�bkowany top�r zrobiony z ko�ci.");
                break;
                
            case 11:
                System.out.println("Nazwa unikatu: Wyrmskull");
                System.out.println("Przedmiot:  He�m");
                System.out.println("Typ: Jedwabny");
                System.out.println("Energy Shield +10");
                System.out.println("-----------------------------------");
                System.out.println("Pancerz(Armour) +4");
                System.out.println("Odporno�� (Resistance) +4");
                System.out.println("Obra�enia wszystkich minion�w +2");
                System.out.println("Maksymalna ilo�� Zombie +1");
                
                System.out.println("Wygl�d: He�m wykonany z ko�ci. Sprawia, �e wszystkie twoje miniony s� szkieletami. Kiedy Posta� ubiera przedmiot, unikat ��czy si� z g�ow� u�ytkownika, sprawiaj�c, �e jego g�os jest niski i wibruj�cy, a oczy �wiec� na czerwono.");
                break;
                
            case 12:
                System.out.println("Nazwa unikatu: Hailrain");
                System.out.println("Przedmiot:  �uk");
                System.out.println("Dwur�czny: +3 WAM");
                System.out.println("-----------------------------------");
                System.out.println("Obra�enia dla �ywio�u L�d +8");
                System.out.println("Threshold przeciwnika -6");
                System.out.println("Posta� nie mo�e by� zamro�ona");
                System.out.println("Konwertuje WSZYSTKIE obra�enia fizyczne w obra�enia dla �ywio�u L�d");
                System.out.println("Mo�esz wykona� rzut na Inicjatyw� dwa razy i wybra� wy�szy wynik.");
                
                System.out.println("Wygl�d: Hailrain to pi�kny, d�ugi �uk wykonany z szafir�w. Jego ci�ciwa to nitka energii, kt�ra tworzy lodowe strza�y. Temperatura wok� u�ytkownika wynosi oko�o 20 stopni Celsjusza.");
                break;
                
            case 13:
                System.out.println("Nazwa unikatu: Kingmaker (TIER 2)");
                System.out.println("Przedmiot:  Miecz");
                System.out.println("Dwur�czny: +6 WAM");
                System.out.println("-----------------------------------");
                System.out.println("WAM +4");
                System.out.println("Negocjacja +5");
                
                System.out.println("Wygl�d: https://pathofexile.gamepedia.com/Kingmaker");
                break;
                
            case 14:
                System.out.println("Nazwa unikatu: Ezomyte Peak");
                System.out.println("Przedmiot:  He�m");
                System.out.println("Typ: Metalowy");
                System.out.println("Pancerz (Armour) +2");
                System.out.println("-----------------------------------");
                System.out.println("Pancerz (Armour) +8");
                System.out.println("�ycie +20");
                System.out.println("Obra�enia (Damage) +4");
                System.out.println("Wytrzyma�o�� (Soul Endurance) +4");
                
                System.out.println("Wygl�d: https://pathofexile.gamepedia.com/Ezomyte_Peak");
                break;
                
            case 15:
                System.out.println("Nazwa unikatu: Wake of Destruction");
                System.out.println("Przedmiot:  Buty");
                System.out.println("Typ: Sk�rzane");
                System.out.println("Odporno�� (Resistance) +2");
                System.out.println("-----------------------------------");
                System.out.println("Konwertuje WSZYSTKIE obra�enia w obra�enia dla �ywio�u B�yskawica");
                System.out.println("WAM +2");
                System.out.println("Inicjatywa +1");
                
                System.out.println("Wygl�d: https://pathofexile.gamepedia.com/Wake_of_Destruction");
                break;
                
            case 16:
                System.out.println("Nazwa unikatu: Cloak of Flame (TIER 2)");
                System.out.println("Przedmiot:  Napier�nik");
                System.out.println("Typ: Jedwabny");
                System.out.println("Energy Shield +40");
                System.out.println("-----------------------------------");
                System.out.println("Zwi�ksza d�ugo�� trwania podpalenia na przeciwnikach o 1 tur�.");
                System.out.println("Obra�enia dla �ywio�u Ogie� +5");
                System.out.println("Odporno�� na ogie� +10");
                System.out.println("Wszystkie obra�enia fizyczne jakie otrzyma posta� s� konwertowane na obra�enia dla �ywio�u Ogie�");
                
                System.out.println("Wygl�d: https://pathofexile.gamepedia.com/Cloak_of_Flame"); 
                break;
                
            case 17:
                System.out.println("Nazwa unikatu: Sange");
                System.out.println("Przedmiot:  Miecz");
                System.out.println("Jednor�czny: +1 WAM");
                System.out.println("-----------------------------------");
                System.out.println("STR +10 ");
                System.out.println("Manewrowo�� +4 ");
                System.out.println("Lekkoatletyka +3 ");
                System.out.println("Og�usza i wywo�uje krwawienie u przeciwnika przy prze�amaniu poziomu Thresholda");
                
                System.out.println("Wygl�d: Tzw. Bli�niacze ostrze. Sange to katana roz�upana na dwie cz�ci - jest przepi�knym po��czeniem fioletowej i czarnej stali. W po��czeniu z drugim bli�niaczym ostrzem, Yasha, otrzymuje podwojenie statystyk.");
                break;
                
            case 18:
                System.out.println("Nazwa unikatu: Yasha");
                System.out.println("Przedmiot:  Miecz");
                System.out.println("Jednor�czny: +1 WAM");
                System.out.println("-----------------------------------");
                System.out.println("DEX +10 ");
                System.out.println("Obra�enia (Damage) +4 ");
                System.out.println("Inicjatywa +1 ");
                System.out.println("Pancerz (Armour) +2");
                
                System.out.println("Wygl�d: Tzw. Bli�niacze ostrze. Yashe to skromna, jadeitowa katana. W po��czeniu z drugim bli�niaczym ostrzem, Sange, otrzymuje podwojenie statystyk."); 
                break;
                
            case 19:
                System.out.println("Nazwa unikatu: Terminus Est (TIER 3)");
                System.out.println("Przedmiot:  Miecz");
                System.out.println("Dwur�czny: +9 WAM");
                System.out.println("-----------------------------------");
                System.out.println("WAM +15 ");
                System.out.println("Mana za zab�jstwo +12");
                System.out.println("Inicjatywa +1 ");
                System.out.println("Threshold przeciwnika -35");
                
                System.out.println("Wygl�d: https://pathofexile.gamepedia.com/Terminus_Est"); 
                break;
               
        }
            
        
        
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL_WEAPON
    public static void ROLL_WEAPON()
    {

        int[] type_detail = new int[2];// [0]ITEM_TYPE/ [1]ITEM_TYPE_DETAIL
          
        System.out.println("Podaj Tier(1-3): ");
        int tier = scanner.nextInt();
         
        System.out.println("===================================================");
        System.out.println("===================================================");
        
        int rarity = Roll(20); //rolluje D20 dla item rarity
        if(rarity != 20)//je�li nie jest to unikat, rolluj dalej
            type_detail = ROLL_TYPE_DETAIL(tier);
        
         //item_type_detail oznacza z czego jest wykonana np. zbroja, albo jaki jest naszyjnik
        
        /* ITEM_TYPE LIST:
            0 - BASIC NOTHING
            1 - AXE
            2 - STAFF
            3 - MACE
            4 - SWORD
            5 - DAGGER
            6 - BOW
            7 - CLAWS
            8 - SCEPTRE
            9 - WAND
            10 - SPEAR
            11 - ARMOUR - ITEM_TYPE_DETAILS:
                1 - HEAVY PLATE
                2 - LEATHER
                3 - CLOTH
            12 - SHIELD - ITEM_TYPE_DETAILS:
                1 - METAL
                2 - LEATHER
                3 - VOODOO
            13 - BOOTS - ITEM_TYPE_DETAILS:
                1 - METAL
                2 - LEATHER
                3 - CLOTH   
            14 - HELMET - ITEM_TYPE_DETAILS:
                1 - METAL
                2 - LEATHER
                3 - CLOTH
            15 - AMULET - ITEM_TYPE_DETAILS:
                1 - CORAL
                2 - PAUA
                3 - LAPIS
                4 - ONYX
                5 - AMBER
                6 - JADE
            16 - RING - ITEM_TYPE_DETAILS:
                1 - CORAL
                2 - PAUA
                3 - IRON
                4 - ONYX
        
        */
        
        if(rarity <= 6) //normal item
        {
            ROLL_WEAPON_MODS(1, type_detail, tier); 
        }
        else if(rarity <=13) //magic item
        {
            ROLL_WEAPON_MODS(2, type_detail, tier);  
        }
        else if(rarity <=19) //rare item
        {
            ROLL_WEAPON_MODS(3, type_detail, tier);
        }
        else //unikat
        {
            ROLL_WEAPON_UNIQUE(); 
        }
        
        System.out.println("===================================================");
        System.out.println("==============================================================================");
        System.out.println("===============================================================================================================");
        
    }   
    
     //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL_CHOOSEN
    public static void ROLL_CHOOSEN()
    {

        int[] type_detail = new int[2];// [0]ITEM_TYPE/ [1]ITEM_TYPE_DETAIL
          
        System.out.println("Podaj Tier(1-3): ");
        int tier = scanner.nextInt();
        System.out.println("--------------");
        
        System.out.println("Podaj rarity przedmiotu: 1 - zwyk�y, 2 - magiczny, 3 - rzadki, 4 - unikatowy");
        int rarity = scanner.nextInt();
        System.out.println("--------------");
        
        if(rarity != 4)
        {
            System.out.println("Podaj typ przedmiotu z listy:");
            System.out.println("1 - AXE ");
            System.out.println("2 - STAFF ");
            System.out.println("3 - MACE ");
            System.out.println("4 - SWORD ");
            System.out.println("5 - DAGGER ");
            System.out.println("6 - BOW ");
            System.out.println("7 - CLAWS ");
            System.out.println("8 - SCEPTRE ");
            System.out.println("9 - WAND ");
            System.out.println("10 - SPEAR ");
            System.out.println("11 - ARMOUR - ITEM_TYPE_DETAILS: ");
            System.out.println("-- 1 - HEAVY PLATE ");
            System.out.println("-- 2 - LEATHER ");
            System.out.println("-- 3 - CLOTH ");
            System.out.println("12 - SHIELD - ITEM_TYPE_DETAILS: ");
            System.out.println("-- 1 - METAL ");
            System.out.println("-- 2 - LEATHER ");
            System.out.println("-- 3 - VOODOO ");
            System.out.println("13 - BOOTS - ITEM_TYPE_DETAILS: ");
            System.out.println("-- 1 - METAL ");
            System.out.println("-- 2 - LEATHER ");
            System.out.println("-- 3 - CLOTH ");
            System.out.println("14 - HELMET - ITEM_TYPE_DETAILS: ");
            System.out.println("-- 1 - METAL ");
            System.out.println("-- 2 - LEATHER ");
            System.out.println("-- 3 - CLOTH ");
            System.out.println("15 - AMULET - ITEM_TYPE_DETAILS: ");
            System.out.println("-- 1 - CORAL ");
            System.out.println("-- 2 - PAUA ");
            System.out.println("-- 3 - LAPIS ");
            System.out.println("-- 4 - ONYX ");
            System.out.println("-- 5 - AMBER ");
            System.out.println("-- 6 - JADE ");
            System.out.println("16 - RING - ITEM_TYPE_DETAILS: ");
            System.out.println("-- 1 - CORAL ");
            System.out.println("-- 2 - PAUA ");
            System.out.println("-- 3 - IRON ");
            System.out.println("-- 4 - ONYX ");

            type_detail[0] = scanner.nextInt();
            System.out.println("--------------");

            System.out.println("Podaj z czego jest wykonany przedmiot(Jesli nie ma wyboru, wybierz 0):");
            type_detail[1] = scanner.nextInt();
        }
        
        System.out.println("===================================================");
        System.out.println("===================================================");
        
        DESCRIBE_TYPE_DETAILS(tier, type_detail);
        System.out.println("--------------");
       
        
         //item_type_detail oznacza z czego jest wykonana np. zbroja, albo jaki jest naszyjnik
        
        /* ITEM_TYPE LIST:
            0 - BASIC NOTHING
            1 - AXE
            2 - STAFF
            3 - MACE
            4 - SWORD
            5 - DAGGER
            6 - BOW
            7 - CLAWS
            8 - SCEPTRE
            9 - WAND
            10 - SPEAR
            11 - ARMOUR - ITEM_TYPE_DETAILS:
                1 - HEAVY PLATE
                2 - LEATHER
                3 - CLOTH
            12 - SHIELD - ITEM_TYPE_DETAILS:
                1 - METAL
                2 - LEATHER
                3 - VOODOO
            13 - BOOTS - ITEM_TYPE_DETAILS:
                1 - METAL
                2 - LEATHER
                3 - CLOTH   
            14 - HELMET - ITEM_TYPE_DETAILS:
                1 - METAL
                2 - LEATHER
                3 - CLOTH
            15 - AMULET - ITEM_TYPE_DETAILS:
                1 - CORAL
                2 - PAUA
                3 - LAPIS
                4 - ONYX
                5 - AMBER
                6 - JADE
            16 - RING - ITEM_TYPE_DETAILS:
                1 - CORAL
                2 - PAUA
                3 - IRON
                4 - ONYX
        
        */
        
        if(rarity == 1) //normal item
        {
            ROLL_WEAPON_MODS(1, type_detail, tier); 
        }
        else if(rarity == 2) //magic item
        {
            ROLL_WEAPON_MODS(2, type_detail, tier);  
        }
        else if(rarity == 3) //rare item
        {
            ROLL_WEAPON_MODS(3, type_detail, tier);
        }
        else if(rarity == 4)//unikat
        {
            ROLL_WEAPON_UNIQUE();
        }
        
        System.out.println("===================================================");
        System.out.println("==============================================================================");
        System.out.println("===============================================================================================================");
        
    }  
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL_MONSTER
    public static void ROLL_MONSTER()
    {
        int HP_base;
        int WAM_base;
        int DMG_base;
        int Threshold_base;
        int Reduction_base;
        int Target_Lv;
        String Monster_Name = "EMPTY";
          
        System.out.println("Podaj nazw� potwora: ");
        Monster_Name = scanner.nextLine();
        
        System.out.println("Podaj HP potwora na poziom 1: ");
        HP_base = scanner.nextInt();
        Threshold_base = (int)(HP_base/2);
        
        System.out.println("Podaj WAM potwora na poziom 1: ");
        WAM_base = scanner.nextInt();
        
        System.out.println("Podaj DMG potwora na poziom 1: ");
        DMG_base = scanner.nextInt();

        
        System.out.println("Podaj Reduction (Redukcja DMGu) potwora na poziom 1: ");
        Reduction_base = scanner.nextInt();
        
        System.out.println("Podaj Oczekiwany poziom potwora: ");
        Target_Lv = scanner.nextInt();
        
        System.out.println("===================================================");
        System.out.println("===================================================");
        
        for(int i = 1; i < Target_Lv; i++)
        {
            HP_base = (int)(HP_base * 1.5) + Roll((int)(HP_base * 0.5));
            WAM_base = (int)(WAM_base * 1.75);
            DMG_base = (int)(DMG_base * 1.5) + Roll((int)(DMG_base * 0.5));
            Threshold_base = (int)(Threshold_base * 1.5) + Roll((int)(Threshold_base * 0.5));
            Reduction_base = (int)(Reduction_base * 1.5) + Roll((int)(Reduction_base * 0.5));
            
        }
        
        System.out.println("Nazwa potwora: " + Monster_Name);
        System.out.println("Poziom potwora: " + Target_Lv);
        System.out.println("------------------------------------");
        System.out.println("HP potwora: " + HP_base);
        System.out.println("WAM potwora: " + WAM_base);
        System.out.println("DMG potwora: " + DMG_base);
        System.out.println("Threshold potwora: " + Threshold_base);
        System.out.println("Reduction potwora: " + Reduction_base);
        
        
        
        System.out.println("===============================================================================================================");
        System.out.println("===============================================================================================================");
        
    } 

    //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL_MONSTER
    public static void GENERATE()
    {
        int[] type_detail = new int[2];// [0]ITEM_TYPE/ [1]ITEM_TYPE_DETAIL
        //startowe
        type_detail[0] = 1;
        type_detail[1] = 0;
        
        System.out.println("Podaj Tier(1-3): ");
        int tier = scanner.nextInt();
         
        System.out.println("=============================================");
        System.out.println("=============================================");
        
        for(int i = 0; i < 32; i++) //generuje z ka�dego itemu po ka�dym typie chocia� raz (losowe rarity!
        {
            DESCRIBE_TYPE_DETAILS(tier,type_detail);
            
            int rarity = Roll(19); //rolluje D20 dla item rarity (NIE WLICZONE 20, DO TEGO B�DZIE INNY ROLL
            if(rarity <= 6) //normal item
            {
                ROLL_WEAPON_MODS(1, type_detail, tier); 
            }
            else if(rarity <=13) //magic item
            {
                ROLL_WEAPON_MODS(2, type_detail, tier);  
            }
            else if(rarity <=19) //rare item
            {
                ROLL_WEAPON_MODS(3, type_detail, tier);
            }
            //----------- sprawdza, czy ma i�� do kolejnego itemu, czy do kolejnego typu
            if(type_detail[0] <=9)//next item
                type_detail[0]++;
            
            else if(type_detail[0] == 10) //next item + next type
            {
                type_detail[0]++;
                type_detail[1]++;
            }
            
            else if(   (type_detail[0] >=11) && (type_detail[0] <=14)) //next type
            {
                if(type_detail[1] == 3) //next item, type = 1 - zeruje rodzaj itemu, �eby lecia� od pocz�tku
                {
                    type_detail[0]++;
                    type_detail[1] = 1; 
                }
                else
                    type_detail[1]++; //next type
            }
            
            else if(type_detail[0] >= 15) // dla 15 i 16 - 16 ma o dwie pozycje mniej, ale p�tla nie przewiduje, �eby wchodzi�o do nieistniej�cych pozycji
            {
                if(type_detail[1] == 6) //next item, type = 1;
                {
                    type_detail[0]++;
                    type_detail[1] = 1;
                }
                else
                    type_detail[1]++;
            }
            System.out.println("=============================================");
        }
    }
}
