
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
        //zrobiæ show building, zrobiæ show_building_array, 

    }
    
    private static void init()
    {
        fill_gather_actions_event_chance_tab(); //inicjalizuje listê szans na zdarzenie dla gather_actions
        fill_gather_actions_place_item_loot(); // inicjalizuje listê Stringów z opisem lootowania itemów dla konkretnych miejsc dla gather_actions, oraz listy itemów dropi¹cych przez NPC
        fill_gather_actions_npc_items_chance(); //inicjalizuje listê szans na itemy dropione przez NPC
        fill_gather_actions_npc_reaction_chance(); //inicjalizuje listê szans na reakcje NPC
        fill_bolb_food_evolution_list(); //inicjalizujê listê bolb_food_evolution - czyli co siê stanie jak bolb zje konkretny item
        fill_crafting_recipes_tab(); //inicjalizujê listê crafting_recipes_tab - umo¿liwia craftowanie itemów na podstawie przepisu
        fill_warehouse_items(); //inicjalizuje listê itemów, które mog¹ zostaæ wsadzone do  warehouse
    }

}