package dungeonCrawler.logic;

import dungeonCrawler.aqu.IGuide;
import dungeonCrawler.aqu.IRoom;

import java.awt.*;
import java.util.ArrayList;

/**
 * The Map class.
 * @author 
 */
class Map implements dungeonCrawler.aqu.IMap
{
    // Data fields
    private int height;   
    private int width;
    private ArrayList<IRoom> roomList; //Declare the ArrayList, Roomlist(name), containing Room(Object).
    private ArrayList<IGuide> guideList;
    private int numberOfGuides;
    private int numberOfContent;
    
    /**
     * Getter method for Height
     * Used in the array (coordinates)
     * Used to print the 3 lines in the map. 
     * @return int
     */
    @Override
    public int getHeight()
    {
        return height;
    }
    
    /**
     * Getter method for Width
     * Used to generate the Array
     * @return int
     */
    @Override
    public int getWidth()
    {
        return width;
    }

    @Override
    public int getNumberOfContent()
    {
        return numberOfContent;
    }

    /**
     * Contructor Map
     * 
     */
    public Map()
    {
        // Initializing (gives value to) private fields (constructor parameter)
        this.width = GameConstants.getMapSize().x;
        this.height = GameConstants.getMapSize().y;
        this.numberOfGuides = GameConstants.getMovingGuides();
        this.numberOfContent = GameConstants.getRoomContents();
        
         //Instantiate a ArrayList, allocates the ArrayList.
        roomList = new ArrayList<>();
        guideList = new ArrayList<>();
        for (int x = 0; x < numberOfGuides; x++)
        {
            guideList.add(new Guide());
        }

        //Creates the coordinate system of the rooms. 
        for (int x = 0; x < width; x++) // Runs through the width.
        {
            for (int y = 0; y < height; y++)// runs through the height.
            {
                //RoomStrings, is a String array, that collects the name and description from the RoomEnum. 
                String[] roomStrings = RoomEnum.getRandomString();
                roomList.add(new Room(new Point(x, y), numberOfContent, roomStrings[0], roomStrings[1]));//Point class in Java.
            }
        }

    }

    /**
     * Method that tells if the room exists. 
     * @param exitPoint
     * @return boolean
     */
    @Override
    public boolean roomExists(Point exitPoint)
    {
        //For each loop. 
        //Which type, name, and list it runs through
        for (IRoom room : roomList)
        {
            //Runs through all the rooms, and tells if it have a x, y- value.  
            if (room.getLocation().x == exitPoint.x && room.getLocation().y == exitPoint.y)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Setter method for Room Has Been Entered
     * Controls the Arraylist roomlist, that was created from the Map.
     * @param playerLocation
     */
    @Override
    public void setRoomHasBeenEntered(Point playerLocation)
    {
        for (IRoom room : roomList)
        {
            if (room.getLocation().x == playerLocation.x && room.getLocation().y == playerLocation.y)
            {
                room.setHasBeenEntered(true);
            }
        }
    }
    
    /**
     * Getter Method 
     * Returens the setter methods value. 
     * @param p
     * @return boolean
     */
    @Override
    public boolean getRoomHasBeenEntered(Point p)
    {
        //For each loop: Which type, name, and list it runs through
        for (IRoom room : roomList)
        {
            // if x == x and y==y, for point p, reurns entered. 
            if (room.getLocation().x == p.x && room.getLocation().y == p.y)
            {
                return getRoomHasBeenEntered(p);
            }
        }
        return false;
    }
    /**
    * Getter Method for Roomlist
    * @return ArrayList
    */
    @Override
    public ArrayList<IRoom> getRoomList()
    {
        return roomList;
    }

    @Override
    public int numberOfEnteredRooms()
    {
        int numberOfRoomsEntered = 0;
        for (IRoom room: roomList)
        {
            if (room.getHasBeenEntered() == true)
            {
                numberOfRoomsEntered ++; 
            }
        }
        return numberOfRoomsEntered;
    }
    
    
}
