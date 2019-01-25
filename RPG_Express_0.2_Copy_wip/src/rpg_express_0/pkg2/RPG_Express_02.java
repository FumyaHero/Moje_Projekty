
package rpg_express_0.pkg2;

import static rpg_express_0.pkg2.Recipes_Chances_.fill_bolb_food_evolution_list;
import static rpg_express_0.pkg2.Recipes_Chances_.fill_crafting_recipes_tab;
import static rpg_express_0.pkg2.Recipes_Chances_.fill_gather_actions_event_chance_tab;
import static rpg_express_0.pkg2.Recipes_Chances_.fill_gather_actions_npc_items_chance;
import static rpg_express_0.pkg2.Recipes_Chances_.fill_gather_actions_npc_reaction_chance;
import static rpg_express_0.pkg2.Recipes_Chances_.fill_gather_actions_place_item_loot;
import static rpg_express_0.pkg2.Recipes_Chances_.fill_warehouse_items;


public class RPG_Express_02 
{
    public static void main(String[] args) 
    {
//        
        
        Database_Commands Database = new Database_Commands();
        init();                                
        
//        Database.Create_Bases();
        Database.Load_Bases();
        
        
//        player_map.get("e1_01").addItem("i0_001", 20);
//        player_map.get("e1_01").addItem("i0_010", 30);
//        
//        
//        
//        int tab[] = new int[7];
//        createNewBolb("e2_01", "BOLB_DUMMY", true, tab);
//
//        Database.Save_Players();

//        ((Warehouse)building_map.get("bA_03")).add_item("i0_002", 10);
//        ((Warehouse)building_map.get("bA_03")).add_item("i0_021", 80);
//        ((Warehouse)building_map.get("bN_03")).addHP(-30);
//        
//        
//        Database.Save_Buildings();




        Database.Load_Buildings();
        //Database.Load_Players();

        System.out.println();
        

        
        //to do:
        //zrobi� show building, zrobi� show_building_array, 

    }
    
    private static void init()
    {
        fill_gather_actions_event_chance_tab(); //inicjalizuje list� szans na zdarzenie dla gather_actions
        fill_gather_actions_place_item_loot(); // inicjalizuje list� String�w z opisem lootowania item�w dla konkretnych miejsc dla gather_actions, oraz listy item�w dropi�cych przez NPC
        fill_gather_actions_npc_items_chance(); //inicjalizuje list� szans na itemy dropione przez NPC
        fill_gather_actions_npc_reaction_chance(); //inicjalizuje list� szans na reakcje NPC
        fill_bolb_food_evolution_list(); //inicjalizuj� list� bolb_food_evolution - czyli co si� stanie jak bolb zje konkretny item
        fill_crafting_recipes_tab(); //inicjalizuj� list� crafting_recipes_tab - umo�liwia craftowanie item�w na podstawie przepisu
        fill_warehouse_items(); //inicjalizuje list� item�w, kt�re mog� zosta� wsadzone do  warehouse
    }

}