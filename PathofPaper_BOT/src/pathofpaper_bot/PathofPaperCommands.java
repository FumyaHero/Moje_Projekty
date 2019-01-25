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
                System.out.println("Przedmiot: Topór");
                System.out.println("+1 do obra¿eñ za ka¿dy punkt WAM");
                System.out.println("Jednorêczny: +1 WAM / Dwurêczny: +3 WAM ");
                break;
                
            case 2:
                System.out.println("Przedmiot: Laska");
                System.out.println("Jeœli przeciwnik rzuca d10 i otrzyma 2 lub ni¿ej, blokujesz atak. ");
                System.out.println("Dwurêczna: +3 WAM ");
                break;
                
            case 3:
                System.out.println("Przedmiot: Bu³awa");
                System.out.println("Zmniejsza próg og³uszenia(Threshold) przeciwnika o 5.");
                System.out.println("Jednorêczna: +1 WAM / Dwurêczna: +3 WAM");
                break;
                
            case 4:
                System.out.println("Przedmiot: Miecz");
                System.out.println("+2 do obra¿eñ przy rzucie d10 dla 10");
                System.out.println("Jednorêczny: +1 wAM / Dwurêczny: +3 WAM");
                break;
                
            case 5:
                System.out.println("Przedmiot: Sztylet");
                System.out.println("+1 do obra¿eñ przy rzucie d10 dla 10 i 9");
                System.out.println("Jednorêczny: +1 WAM");
                break;
                
            case 6:
                System.out.println("Przedmiot: £uk");
                System.out.println("Mo¿esz wykonaæ rzut na inicjatywê dwa razy i wybraæ wy¿szy wynik.");
                System.out.println("Dwurêczny: +3 WAM");
                break;
                
            case 7: 
                System.out.println("Przedmiot: Pazury");
                suma = 4*tier;
                System.out.println("Kradzie¿ ¿ycia: +"+ suma);
                System.out.println("Jednorêczne: +1 WAM");
                break;
                
            case 8: 
                System.out.println("Przedmiot: Ber³o");
                System.out.println("Zwiêksza d³ugoœæ trwania og³uszenia na przeciwnikach o 1 turê.");
                System.out.println("Jednorêczna: +1 WAM");
                break;
                
            case 9:
                System.out.println("Przedmiot: Ró¿d¿ka");
                suma = 2*tier;
                System.out.println("+" +suma+" do many");
                System.out.println("Jednorêczna: +1 WAM");
                break;
                
            case 10:
                System.out.println("Przedmiot: W³ócznia");
                System.out.println("Mo¿esz wykonaæ rzut na Manewr i Lekkoatletykê dwa razy i wybraæ wy¿szy wynik");
                System.out.println("Jednorêczna: +1 WAM / Dwurêczna: +3 WAM");
                break;
                
            case 11:
                System.out.println("Przedmiot: Napierœnik");
                
                if  (type_detail[1] == 1)
                {
                    System.out.println("Typ: Ciê¿ki p³ytowy");
                    System.out.println("Pancerz(Armour) +6, Manewr -4, Lekkoatletyka -4");
                }
                else if(type_detail[1] == 2)
                {
                    System.out.println("Typ: Skórzany");
                    System.out.println("Odpornoœæ (Resistance) +4");
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
                    System.out.println("Typ: Skórzana");
                    suma = 6*tier;
                    System.out.println("Odpornoœæ (Resistance) +" + suma);
                }
                else if(type_detail[1] == 3)
                {
                    System.out.println("Typ: Voodoo");
                    suma = 20*tier; 
                    int suma2 = 2*tier;
                    System.out.println("Energy Shield +" + suma + ", Obra¿enia +" + suma2);
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
                    System.out.println("Typ: Skórzane");
                    System.out.println("Odpornoœæ (Resistance) +2");
                }
                else if(type_detail[1] == 3)
                {
                    System.out.println("Typ: Jedwabne");
                    System.out.println("Energy Shield +10");
                }
                break;
                
            case 14:
                System.out.println("Przedmiot: He³m");
                if  (type_detail[1] == 1)
                {
                    System.out.println("Typ: Metalowy");
                    System.out.println("Pancerz(Armour) +2");
                }
                else if(type_detail[1] == 2)
                {
                    System.out.println("Typ: Skórzany");
                    System.out.println("Odpornoœæ (Resistance) +2");
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
                    System.out.println("Odpornoœæ (Resistance) +" + suma);
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
                System.out.println("Przedmiot: Pierœcieñ");
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
                    System.out.println("Typ: ¯elazny");
                    suma = 1 +1*tier;
                    System.out.println("WAM +" + suma);
                }
                else if(type_detail[1] == 4)
                {
                    System.out.println("Typ: Onyksowy");
                    suma = Roll(4) +2*tier;
                    System.out.println("Odpornoœæ (Resistance) +" + suma);
                }
                break;
                
            default:
                System.err.println("B£¥D: NIEPOPRAWNY TYP PRZEDMIOTU!");
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
                type_detail[0] == 14) //jeœli jedno z tych zosta³o wyrollowane, losuj typ armoura
            type_detail[1] = Roll(3);
        
        else if(type_detail[0] == 15) //losuj typ naszyjnika
            type_detail[1] = Roll(6);
        
        else if( type_detail[0] == 16) //losuj typ pierœcionka
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
                System.out.println("Zwiêksza d³ugoœæ trwania og³uszenia na przeciwnikach o 1 turê");
                break;
                
            case 5:
                System.out.println("WAM +" + tier);
                break;
                
            case 6:
                suma = Roll(10)+6*tier;
                System.out.println("Zmniejsza próg og³uszenia(Threshold) przeciwnika o "+ suma);
                break;
                
            case 7:
                suma = 2*tier + 2*tier;
                System.out.println("Kradzie¿ ¿ycia +"+ suma);
                break;
                
            case 8:
                suma = 1*tier + 1*tier;
                System.out.println("Kradzie¿ many +"+ suma);
                break;
            
            default:
                System.out.println("B£¥D: NIEZNANY ROLL!");
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
        
        if(type_detail[0] == 13) // rollowanie dla butów
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
                    System.out.println("Odpornoœæ na og³uszenia(Threshold) +" + suma);
                    break;

                case 5:
                    suma = Roll(4) + 2*tier;
                    System.out.println("Odpornoœæ (Resistance) +" + suma);
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
                        System.out.println("Odpornoœæ (Resistance) +"+ suma);
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
                    System.out.println("B£¥D: NIEZNANY ROLL!");
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
                    System.out.println("Odpornoœæ na og³uszenia(Threshold) +" + suma);
                    break;

                case 5:
                    suma = Roll(4) + 2*tier;
                    System.out.println("Odpornoœæ (Resistance) +" + suma);
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
                        System.out.println("Odpornoœæ (Resistance) +"+ suma);
                    }
                    else if(type_detail[1] == 3)
                    {
                        suma = Roll(10)*2 +10*tier;
                        System.out.println("Energy Shield +"+ suma);
                    }
                    break;

                default:
                    System.out.println("B£¥D: NIEZNANY ROLL!");
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
        10 - +3 do obra¿eñ przy rzucie d10 dla 10 i 9 PER TIER
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
                System.out.println("Zwiêksza d³ugoœæ trwania og³uszenia na przeciwnikach o 1 turê");
                break;
                
            case 5:
                System.out.println("Zwiêksz obra¿enia swoje i swoich sojuszników wobec przeciwnika, który jest podpalony/och³odzony/pora¿ony - WAM +" + tier);
                break;
                
            case 6:
                suma = 2*tier;
                System.out.println("Kradzie¿ ¿ycia +"+ suma);
                break;
                
            case 7:
                suma = 1*tier;
                System.out.println("Kradzie¿ many +"+ suma);
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
                System.out.println("+"+ suma +" do obra¿eñ przy rzucie d10 dla 10 i 9");
                break;
                
            case 11:
                suma = 1+1*tier;
                System.out.println("WAM +"+ suma);
                break;
            
            default:
                System.out.println("B£¥D: NIEZNANY ROLL!");
                break;
            
        }
        
        
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL_WEAPON_16_MOD
    public static void ROLL_WEAPON_16_MOD(int tier)
    {
        /*----------- PIERŒCIEÑ - ROLLE:
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
                System.out.println("Obra¿enia +"+ suma);
                break;
                
            case 5:
                suma = 2 + 2*tier;
                System.out.println("Kradzie¿ ¿ycia +"+ suma);
                break;
                
            case 6:
                suma = Roll(4) + 2*tier;
                System.out.println("Odpornoœæ (Resistance) +" + suma);
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
                System.out.println("B£¥D: NIEZNANY ROLL!");
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
                System.err.println("B£¥D: NIEPOPRAWNY TYP PRZEDMIOTU!");
            
            
        }
        
        
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------- ROLL WEAPON MODS
    public static void ROLL_WEAPON_MODS(int item_rarity, int[] type_detail, int tier)
    {
        switch(item_rarity)
        {
            case 1: //normal
                System.out.println("Rarity: Zwyk³y ");
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
                System.err.println("ZROBIÊ POTEM LOLOLOL");
                break;
            
            default:
                System.err.println("B£¥D: NIEPOPRAWNE RARITY!");
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
                System.out.println("Jednorêczna: +1 WAM");
                System.out.println("-----------------------------------");
                System.out.println("Kradzie¿ many +1");
                System.out.println("Dzia³a jak topór, miecz, sztylet, ber³o, pazury, w³ócznia i bu³awa RÓWNOCZEŒNIE.");
                System.out.println("+1 do obra¿eñ za ka¿dy punkt WAM");
                System.out.println("+2 do obra¿eñ przy rzucie d10 dla 10");
                System.out.println("+1 do obra¿eñ przy rzucie d10 dla 10 i 9");
                System.out.println("Zwiêksza d³ugoœæ trwania og³uszenia na przeciwnikach o 1 turê.");
                System.out.println("Kradzie¿ ¿ycia: +4");
                System.out.println("Mo¿esz wykonaæ rzut na Manewr i Lekkoatletykê dwa razy i wybraæ wy¿szy wynik"); 
                
                System.out.println("Wygl¹d: https://pathofexile.gamepedia.com/Varunastra"); 
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
                System.out.println("WAM dla pocisków +2");
                System.out.println("Nie mo¿esz chybiæ - trafiasz nawet przy wyrzuceniu 1 dla d10");

                System.out.println("Wygl¹d: https://pathofexile.gamepedia.com/Karui_Ward");
                break;
            
            case 3:
                System.out.println("Nazwa unikatu: Mjölnir");
                System.out.println("Przedmiot:  Bu³awa");
                System.out.println("Jednorêczna: +1 WAM");
                System.out.println("-----------------------------------");
                System.out.println("WAM +1");
                System.out.println("Wszelkie obra¿enia jakie zadajesz s¹ przekonwertowane do obra¿eñ b³yskawicy (lightning damage)");
                System.out.println("(TYLKO RAZ NA TURÊ!) Jeœli pomyœlnie zaatakujesz przeciwnika, mo¿esz u¿yæ czaru b³yskawicy (lightning spell) - p³ac¹c za niego man¹, oczywiœcie. Jeœli postaæ u¿ywa dwóch borni, ta umiejêtnoœæ jest niedostêpna. ");
                
                System.out.println("Wygl¹d: https://pathofexile.gamepedia.com/Mj%C3%B6lner"); 
                break; 
                
            case 4:
                System.out.println("Nazwa unikatu: Malachai’s Artifice");
                System.out.println("Przedmiot:  Pierœcieñ");
                System.out.println("Typ: Onyksowy");
                suma = Roll(4);
                System.out.println("Odpornoœæ (Resistance) +" + suma);
                System.out.println("-----------------------------------");
                System.out.println("Odpornoœæ (Resistane) -8");
                System.out.println("Jako g³ówn¹ akcjê (Major Action) wybierz jeden z trzech ¿ywio³ów (Elements) - (ogieñ, lód, b³yskawica). Zwiêksz swoj¹ Odpornoœæ (Resistance) o 16 dla wybranego ¿ywio³u.");
                                
                System.out.println("Wygl¹d: https://pathofexile.gamepedia.com/Malachai%27s_Artifice"); 
                break;   
                
            case 5:
                System.out.println("Nazwa unikatu: Star of Wraeclast");
                System.out.println("Przedmiot:  Amulet");
                System.out.println("Typ: Rubin");
                System.out.println("Odpornoœæ (Resistance) +2");
                System.out.println("-----------------------------------");
                System.out.println("Odpornoœæ (Resistane) +10");
                System.out.println("Twoje kl¹twy s¹ wspomagane (supported) Zredukowaniem Many(Reduced Mana).");
                System.out.println("Nie mo¿esz byæ przeklêty poprzez Soul Skille(Gemy).");
                System.out.println("Pozwala na u¿ycie Illusionary Warp (Teleport iluzji): ");
                System.out.println("Koszt: 3 Many, ¯ywio³: Lód, Zaklêcie(Spell), Mniejsza Akcja(Minor)"); 
                System.out.println("Teleportuje u¿ytkownika do wybranej przez niego lokacji (W zasiêgu 20m max). Miejsce pocz¹tkowe i koñcowe teleportu s¹ pokryte lodem, spowalniaj¹c przeciwników(Inicjatywa -2) na 2 tury. GM NOTES: S.83");
                
                System.out.println("Wygl¹d: https://pathofexile.gamepedia.com/Star_of_Wraeclast");
                break;
                
            case 6:
                System.out.println("Nazwa unikatu: Wanderlust");
                System.out.println("Przedmiot:  Buty");
                System.out.println("Typ: Jedwabne");
                System.out.println("Energy Shield +4");
                System.out.println("-----------------------------------");
                System.out.println("Inicjatywa +1");
                System.out.println("Postaæ nie mo¿e byæ zamro¿ona");
                System.out.println("Mana +2");
                System.out.println("Mo¿esz wykonaæ rzut na survival dwa razy i wybraæ wy¿szy wynik.");
                
                System.out.println("Wygl¹d: https://pathofexile.gamepedia.com/Wanderlust");
                break;   
                
            case 7:
                System.out.println("Nazwa unikatu: Matua Tupuna");
                System.out.println("Przedmiot:  Tarcza");
                System.out.println("Typ: Voodoo");
                System.out.println("Energy Shield +20");
                System.out.println("-----------------------------------");
                System.out.println("Obra¿enia (Damage) +2");
                System.out.println("Maksymalna iloœæ Zombie +1");
                System.out.println("Mana +4");
                System.out.println("+1 Tier do efektywnoœci aur postaci.");
                System.out.println("Energy Shield +10.");
                System.out.println("Bezcielesna g³owa, która zwymiotuje na ka¿dego, kto bêdzie zagra¿a³ jej Mistrzowi. Musi byæ karmiona dusz¹ raz na tydzieñ.");
                
                System.out.println("Wygl¹d: https://pathofexile.gamepedia.com/Matua_Tupuna");
                break;
                
            case 8:
                System.out.println("Nazwa unikatu: Dreamfeather");
                System.out.println("Przedmiot:  Miecz");
                System.out.println("Jednorêczny: +1 WAM");
                System.out.println("-----------------------------------");
                System.out.println("DEX +20");
                System.out.println("+1 WAM za ka¿de 20 DEX");
                System.out.println("+1 Inicjatywa za ka¿de 40 DEX");
                System.out.println("Lekki jak piórko. Podczas pe³ni ksiê¿yca, miecz jasno œwieci i dodaje w³aœcicielowi +4 Inicjatywy i +2 WAM");
                
                System.out.println("Wygl¹d: https://pathofexile.gamepedia.com/Dreamfeather");
                break;
                
            case 9:
                System.out.println("Nazwa unikatu: Facebreaker");
                System.out.println("Przedmiot:  Rêkawice (Broñ)");
                System.out.println("Typ: Skórzane");
                System.out.println("Energy Shield +2");
                System.out.println("-----------------------------------");
                System.out.println("STR +10");
                System.out.println("+1 WAM za ka¿de 5 STR, jeœli nie u¿ywasz broni");
                System.out.println("Postaæ nie mo¿e u¿ywaæ broni, ale mo¿e nadal u¿ywaæ tarczy");
                System.out.println("Jeœ³i postaæ zabije przeciwnika, eksploduje on w najbardziej makabryczny sposób. Zastraszenie +4 do koñca walki");
                
                System.out.println("Wygl¹d: https://pathofexile.gamepedia.com/Facebreaker");
                break;
                
            case 10:
                System.out.println("Nazwa unikatu: Bonesaw");
                System.out.println("Przedmiot:  Topór");
                System.out.println("Dwurêczny: +2 WAM");
                System.out.println("-----------------------------------");
                System.out.println("Obra¿enia (Damage) +25");
                System.out.println("WAM +2");
                System.out.println("Threshold przeciwnika -12");
                System.out.println("Regeneracja ¿ycia na turê: +8");
                System.out.println("Postaæ nie mo¿e krwawiæ.");
                
                System.out.println("Wygl¹d: Wielki, z¹bkowany topór zrobiony z koœci.");
                break;
                
            case 11:
                System.out.println("Nazwa unikatu: Wyrmskull");
                System.out.println("Przedmiot:  He³m");
                System.out.println("Typ: Jedwabny");
                System.out.println("Energy Shield +10");
                System.out.println("-----------------------------------");
                System.out.println("Pancerz(Armour) +4");
                System.out.println("Odpornoœæ (Resistance) +4");
                System.out.println("Obra¿enia wszystkich minionów +2");
                System.out.println("Maksymalna iloœæ Zombie +1");
                
                System.out.println("Wygl¹d: He³m wykonany z koœci. Sprawia, ¿e wszystkie twoje miniony s¹ szkieletami. Kiedy Postaæ ubiera przedmiot, unikat ³¹czy siê z g³ow¹ u¿ytkownika, sprawiaj¹c, ¿e jego g³os jest niski i wibruj¹cy, a oczy œwiec¹ na czerwono.");
                break;
                
            case 12:
                System.out.println("Nazwa unikatu: Hailrain");
                System.out.println("Przedmiot:  £uk");
                System.out.println("Dwurêczny: +3 WAM");
                System.out.println("-----------------------------------");
                System.out.println("Obra¿enia dla ¿ywio³u Lód +8");
                System.out.println("Threshold przeciwnika -6");
                System.out.println("Postaæ nie mo¿e byæ zamro¿ona");
                System.out.println("Konwertuje WSZYSTKIE obra¿enia fizyczne w obra¿enia dla ¿ywio³u Lód");
                System.out.println("Mo¿esz wykonaæ rzut na Inicjatywê dwa razy i wybraæ wy¿szy wynik.");
                
                System.out.println("Wygl¹d: Hailrain to piêkny, d³ugi ³uk wykonany z szafirów. Jego ciêciwa to nitka energii, która tworzy lodowe strza³y. Temperatura wokó³ u¿ytkownika wynosi oko³o 20 stopni Celsjusza.");
                break;
                
            case 13:
                System.out.println("Nazwa unikatu: Kingmaker (TIER 2)");
                System.out.println("Przedmiot:  Miecz");
                System.out.println("Dwurêczny: +6 WAM");
                System.out.println("-----------------------------------");
                System.out.println("WAM +4");
                System.out.println("Negocjacja +5");
                
                System.out.println("Wygl¹d: https://pathofexile.gamepedia.com/Kingmaker");
                break;
                
            case 14:
                System.out.println("Nazwa unikatu: Ezomyte Peak");
                System.out.println("Przedmiot:  He³m");
                System.out.println("Typ: Metalowy");
                System.out.println("Pancerz (Armour) +2");
                System.out.println("-----------------------------------");
                System.out.println("Pancerz (Armour) +8");
                System.out.println("¯ycie +20");
                System.out.println("Obra¿enia (Damage) +4");
                System.out.println("Wytrzyma³oœæ (Soul Endurance) +4");
                
                System.out.println("Wygl¹d: https://pathofexile.gamepedia.com/Ezomyte_Peak");
                break;
                
            case 15:
                System.out.println("Nazwa unikatu: Wake of Destruction");
                System.out.println("Przedmiot:  Buty");
                System.out.println("Typ: Skórzane");
                System.out.println("Odpornoœæ (Resistance) +2");
                System.out.println("-----------------------------------");
                System.out.println("Konwertuje WSZYSTKIE obra¿enia w obra¿enia dla ¿ywio³u B³yskawica");
                System.out.println("WAM +2");
                System.out.println("Inicjatywa +1");
                
                System.out.println("Wygl¹d: https://pathofexile.gamepedia.com/Wake_of_Destruction");
                break;
                
            case 16:
                System.out.println("Nazwa unikatu: Cloak of Flame (TIER 2)");
                System.out.println("Przedmiot:  Napierœnik");
                System.out.println("Typ: Jedwabny");
                System.out.println("Energy Shield +40");
                System.out.println("-----------------------------------");
                System.out.println("Zwiêksza d³ugoœæ trwania podpalenia na przeciwnikach o 1 turê.");
                System.out.println("Obra¿enia dla ¿ywio³u Ogieñ +5");
                System.out.println("Odpornoœæ na ogieñ +10");
                System.out.println("Wszystkie obra¿enia fizyczne jakie otrzyma postaæ s¹ konwertowane na obra¿enia dla ¿ywio³u Ogieñ");
                
                System.out.println("Wygl¹d: https://pathofexile.gamepedia.com/Cloak_of_Flame"); 
                break;
                
            case 17:
                System.out.println("Nazwa unikatu: Sange");
                System.out.println("Przedmiot:  Miecz");
                System.out.println("Jednorêczny: +1 WAM");
                System.out.println("-----------------------------------");
                System.out.println("STR +10 ");
                System.out.println("Manewrowoœæ +4 ");
                System.out.println("Lekkoatletyka +3 ");
                System.out.println("Og³usza i wywo³uje krwawienie u przeciwnika przy prze³amaniu poziomu Thresholda");
                
                System.out.println("Wygl¹d: Tzw. BliŸniacze ostrze. Sange to katana roz³upana na dwie czêœci - jest przepiêknym po³¹czeniem fioletowej i czarnej stali. W po³¹czeniu z drugim bliŸniaczym ostrzem, Yasha, otrzymuje podwojenie statystyk.");
                break;
                
            case 18:
                System.out.println("Nazwa unikatu: Yasha");
                System.out.println("Przedmiot:  Miecz");
                System.out.println("Jednorêczny: +1 WAM");
                System.out.println("-----------------------------------");
                System.out.println("DEX +10 ");
                System.out.println("Obra¿enia (Damage) +4 ");
                System.out.println("Inicjatywa +1 ");
                System.out.println("Pancerz (Armour) +2");
                
                System.out.println("Wygl¹d: Tzw. BliŸniacze ostrze. Yashe to skromna, jadeitowa katana. W po³¹czeniu z drugim bliŸniaczym ostrzem, Sange, otrzymuje podwojenie statystyk."); 
                break;
                
            case 19:
                System.out.println("Nazwa unikatu: Terminus Est (TIER 3)");
                System.out.println("Przedmiot:  Miecz");
                System.out.println("Dwurêczny: +9 WAM");
                System.out.println("-----------------------------------");
                System.out.println("WAM +15 ");
                System.out.println("Mana za zabójstwo +12");
                System.out.println("Inicjatywa +1 ");
                System.out.println("Threshold przeciwnika -35");
                
                System.out.println("Wygl¹d: https://pathofexile.gamepedia.com/Terminus_Est"); 
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
        if(rarity != 20)//jeœli nie jest to unikat, rolluj dalej
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
        
        System.out.println("Podaj rarity przedmiotu: 1 - zwyk³y, 2 - magiczny, 3 - rzadki, 4 - unikatowy");
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
          
        System.out.println("Podaj nazwê potwora: ");
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
        
        for(int i = 0; i < 32; i++) //generuje z ka¿dego itemu po ka¿dym typie chocia¿ raz (losowe rarity!
        {
            DESCRIBE_TYPE_DETAILS(tier,type_detail);
            
            int rarity = Roll(19); //rolluje D20 dla item rarity (NIE WLICZONE 20, DO TEGO BÊDZIE INNY ROLL
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
            //----------- sprawdza, czy ma iœæ do kolejnego itemu, czy do kolejnego typu
            if(type_detail[0] <=9)//next item
                type_detail[0]++;
            
            else if(type_detail[0] == 10) //next item + next type
            {
                type_detail[0]++;
                type_detail[1]++;
            }
            
            else if(   (type_detail[0] >=11) && (type_detail[0] <=14)) //next type
            {
                if(type_detail[1] == 3) //next item, type = 1 - zeruje rodzaj itemu, ¿eby lecia³ od pocz¹tku
                {
                    type_detail[0]++;
                    type_detail[1] = 1; 
                }
                else
                    type_detail[1]++; //next type
            }
            
            else if(type_detail[0] >= 15) // dla 15 i 16 - 16 ma o dwie pozycje mniej, ale pêtla nie przewiduje, ¿eby wchodzi³o do nieistniej¹cych pozycji
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
