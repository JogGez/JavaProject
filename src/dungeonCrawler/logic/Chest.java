package dungeonCrawler.logic;

import dungeonCrawler.aqu.IChest;
import dungeonCrawler.aqu.IItem;

import java.io.Serializable;

/**
 * Chest class
 * Used for items to be stored in, and as a intractable object in rooms
 * implements RoomContent, IChest, Serializable
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian
 */
class Chest implements RoomContent, IChest, Serializable
{
    Item item;

    private String name;
    private String description;
    private String ascii;

    /**
     * Constructor for chest
     * name, description, ascii.
     */
    public Chest()
    {
        item = Item.getRandomItem();

        this.name = "Chest";
        this.description = "Old boring chest";
        this.ascii = GameTextASCII.getChest();
    }

    /**
     * Getter method for Item
     * @return IItem
     */
    @Override
    public IItem getItem()
    {
        return item;
    }

    /**
     * Getter method for ascii
     * @return String
     */
    @Override
    public String getAscii()
    {
        return this.ascii;
    }

    /**
     * Getter method for name
     * @return String
     */
    @Override
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for Description
     * @return String
     */
    @Override
    public String getDescription()
    {
        return description;
    }

}
