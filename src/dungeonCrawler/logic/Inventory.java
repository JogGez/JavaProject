package dungeonCrawler.logic;

import dungeonCrawler.aqu.IItem;
import dungeonCrawler.aqu.IPotion;
import dungeonCrawler.aqu.IKey;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author 
 */
class Inventory implements dungeonCrawler.aqu.IInventory
{
    Item[] items;
 /**
 * 
 * @param size 
 */
    public Inventory(int size)
    {
        this.items = new Item[size];
    }
/**
 * 
 * @param item
 * @param index 
 */

    public void addItem(Item item, int index)
    {
        items[index] = item;
    }
/**
 * 
 * @param index
 * @return 
 */

    public Item getItem(int index)
    {
        return items[index];
    }
/**
 * 
 * @param item
 * @return 
 */

    public int getItemIndex(Item item)
    {
        return Arrays.asList(items).indexOf(item);
    }
/**
 * 
 * @param index 
 */
    @Override
    public void removeItem(int index)
    {
        items[index] = null;
        
    }
/**
 * Method that creats a empty array, and adds our potions. 
 * @return ArrayList with potions
 */
    public ArrayList<Potion> potionArrayList()
    {
        ArrayList<Potion> availablePotions = new ArrayList<>();

        for (int i = 0; i < items.length; i++)
        {
            // Controls if its a weapon or potions in our Items
            if (items[i] instanceof Potion)
            {
                availablePotions.add((Potion)items[i]);
            }
        }

        return availablePotions;
    }
/**
 * Tells how big the ArrayList is.
 * Used to control our ArrayList size. 
 * @return int
 */
    @Override
    public int getSize()
    {
        return items.length;
    }
    
     public ArrayList<Key> keyArrayList()
    {
        ArrayList<Key> availableKeys = new ArrayList<>();

        for (int i = 0; i < items.length; i++)
        {
            // Controls if its a weapon or potions in our Items
            if (items[i] instanceof Key)
            {
                availableKeys.add((Key)items[i]);
            }
        }

        return availableKeys;
    }
}
