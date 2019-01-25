
package pathofpaper_bot;

import java.util.Scanner;
import static pathofpaper_bot.PathofPaperCommands.GENERATE;
import static pathofpaper_bot.PathofPaperCommands.ROLL_CHOOSEN;
import static pathofpaper_bot.PathofPaperCommands.ROLL_MONSTER;
import static pathofpaper_bot.PathofPaperCommands.ROLL_WEAPON;
import static pathofpaper_bot.PathofPaperCommands.Roll;


public class PathofPaper_BOT 
{


    public static void main(String[] args) 
    {

        Scanner scanner = new Scanner(System.in);
        String command = "MENU";
        
        do
            switch(command)
            {
                case "MENU":
                    System.out.println("Witaj w Path of Paper BOT!");
                    System.out.println("Dost�pne komendy: ");
                    System.out.println("RW ---> generuje now� bro�/armor");
                    System.out.println("RC ---> generuje wybran� bro�/armor ");
                    System.out.println("RM ---> generuje nowego potwora na bazie statystyk Lv1 ");
                    System.out.println("GEN ---> chocia� jedn� bro� z ka�dego typu + losow� ilo�� unikat�w z wybranego tieru ");
                    System.out.println("D20");
                    System.out.println("D10");
                    System.out.println("WIP ---> narazie nic wi�cej :P");
                    System.out.println("EXIT ---> Zako�cz dzia�anie programu");
                    command = scanner.nextLine();
                    break;
                
                case "RW": //----------------------------------- ROLL WEAPON
                    ROLL_WEAPON();
                    command = "MENU";
                    scanner.nextLine(); //placeholder
                    break;
                case "RC": //----------------------------------- ROLL CHOOSEN
                    ROLL_CHOOSEN();
                    command = "MENU";
                    scanner.nextLine(); //placeholder
                    break;
                    
                case "RM": //----------------------------------- ROLL MONSTER
                    ROLL_MONSTER();
                    command = "MENU";
                    scanner.nextLine(); //placeholder
                    break; 
                case "D20": //----------------------------------- ROLL MONSTER
                    System.out.println("=======================================");
                    System.out.println(Roll(20));
                    command = "MENU";
                    scanner.nextLine(); //placeholder
                    break; 
                case "D10": //----------------------------------- ROLL MONSTER
                    System.out.println("=======================================");
                    System.out.println(Roll(10));
                    command = "MENU";
                    scanner.nextLine(); //placeholder
                    break; 
                    
                case "GEN": //----------------------------------- ITEM MASS GENERATOR
                    GENERATE();
                    command = "MENU";
                    scanner.nextLine(); //placeholder
                    break;

                case "EXIT":
                    System.out.println("Ko�czenie programu..");
                    command = "EXIT";
                    break;
                
                default:
                    System.out.println("Nieznana komenda, spr�buj jeszcze raz.");
                    command = "MENU";
                    break;
            } while(command!="EXIT");
        
    }


}