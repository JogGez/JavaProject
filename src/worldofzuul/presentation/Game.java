package worldofzuul.presentation;
import worldofzuul.logic.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class used to get the game running.
 *
 * @author JogGez
 * @version 1.0
 * @since 2017-09-22
 */
public class Game
{
    // Parser for handling the user input
    private Parser parser;
    // Stores what room we are currently in
    private Map currentMap;
    // We are storing the class player's name for player.
    private Player player;
    // Creating a battle class object.
    private Battle battle;
    //Creating print to console object
    private PrintToConsole printToConsole;
    //Creating print to console object
    private TextForPrintToConsole textForPrintToConsole;

    /**
     * Class constructor.
     * Used to instantiate the Parser + Print to console + text for print to console
     */
    public Game()
    {
        // Instantiating the Parser
        parser = new Parser();

        //Instantiating TextForPrintToConsole
        textForPrintToConsole = new TextForPrintToConsole();

        //Instantiating PrintToConsole
        printToConsole = new PrintToConsole();
    }

    /**
     * Start menu
     * Used to call method to start game + Show start logo + Print welcome menu
     */
    public void start()
    {
        //Prints welcome logo and welcome text
        printToConsole.print(textForPrintToConsole.getAsciiTitle());
        printToConsole.print(textForPrintToConsole.getWelcomeText());

        // Call menu
        this.menu();
    }

    /**
     * Method that starts the game and runs till the end of the game.
     */
    public void play() 
    {
        // Instantiating currentMap
        currentMap = new Map(3,4,2 );

        //Prints "Enter your name here: "
        printToConsole.print(textForPrintToConsole.getEnterPlayerName());

        // Instantiating player and initiating name
        player = new Player(parser.getUserInput());

        //Prints Start info (passing object player to be able to print name)
        printToConsole.print(textForPrintToConsole.getMessageHello(player));

        boolean acceptedInput = false;
        while (!acceptedInput)
        {
            String input = parser.getUserInput();//returns a String

            if (input.toLowerCase().contains("enter"))
            {
                acceptedInput = true;
            }
            else
            {
                //Prints "Type \"enter\" to start the game."
                printToConsole.print(textForPrintToConsole.getEnterToStartGame());
            }
        }

        // sets the current room as entered
        currentMap.setRoomHasBeenEntered(player.getLocation());
        checkRoom();

        // Boolean witch hold the value for exiting the game.
        boolean finished = false;
        // While loop that runs through the entirety of the game. (until the user types "quit")
        while (! finished)
        {
            // Call the parser object that waits for the user to type a command
            Command command = parser.getCommand();
            // Set the finished boolean to true or false, depending on the command
            // Checks if commandWord quit
            if (processCommand(command)) 
            {
                finished = processCommand(command);    
            } 
            // checks if player has 0 health
            else if (player.getHealth() <= 0 )
            {
                // Stops the game if you reach 0 health, and then prints out the line 
                finished = player.getHealth() <= 0;
                //Prints "You have died"
                printToConsole.print(textForPrintToConsole.getYouHaveDied());

            }
        }
        // Writes the last output before closing the application, also says goodbye to the username
        printToConsole.print(textForPrintToConsole.getThanksForPLaying(player));


    }

    /**
     * Method that prints Highscore
     */
    private void printHighScore()
    {
        //Prints high score
        printToConsole.printHightScore();
        parser.getUserInput();
        this.menu();
    }

    /**
     * Method that prints menu and and acts on userinput
     */
    private void menu()
    {
        //Prints menu
        printToConsole.print(textForPrintToConsole.getMenu());


        switch (parser.getUserInput())
        {
            case "1":
                printToConsole.print(textForPrintToConsole.getEmptyLine());//Prints empty line
                play();
                break;
            case "2":
                printToConsole.print(textForPrintToConsole.getEmptyLine());//Prints empty line
                break;
            case "3":
                printToConsole.print(textForPrintToConsole.getEmptyLine());//Prints empty line
                printHighScore();
                break;
            case "4":
                printToConsole.print(textForPrintToConsole.getEmptyLine());//Prints empty line
                break;
            case "5":
                printToConsole.print(textForPrintToConsole.getEmptyLine());//Prints empty line
                //Print thank you for playing
                printToConsole.print(textForPrintToConsole.getThanksForPLaying(player));//"Thanks for playing "+player name+". Good bye!"
                System.exit(0);
                break;
        }
    }

    /**
     * This method processes the command recieved from the parser
     * and returns whether or not to quit.
     *
     * @param command command to process.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        // Gets the last commandWord from the Command object
        CommandWord commandWord = command.getCommandWord();

        // Checks if the commandWord is unknown
        if(commandWord == CommandWord.UNKNOWN) 
        {
            //Prints: "I don't know what you mean..."
            printToConsole.print(textForPrintToConsole.getWhatDoYouMean());
            return false;
        }

        // Checks if the command is Help and runs the printHelp() method
        if (commandWord == CommandWord.HELP) 
        {
            //Invoke help method
            printHelp();
        }
        // Checks if the command is Go and runs the goRoom() method
        else if (commandWord == CommandWord.GO) 
        {
            goRoom(command);
        }
        else if (commandWord == CommandWord.SHOW)
        {
            show(command);
        }
        else if (commandWord == CommandWord.USE)
        {
            use(command);
        }
        else if (commandWord == CommandWord.ATTACK)
        {
            attack(command);
        }
         //Checks if the command is Quit and sets the boolean to true
        else if (commandWord == CommandWord.QUIT || commandWord == CommandWord.EXIT )
        {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }
    private void attack(Command command)
    {
        while (!battle.getIsBattleOver())
        {
        }
    }

    /**
     *
     * @param command
     */
    private void use(Command command)
    {
        if(!command.hasSecondWord())
        {
            //Prints "Use what?"
            printToConsole.print(textForPrintToConsole.getUseWhat());
            return;
        }

        switch (command.getSecondWord())
        {
            case "slot":

                if (0 <= (Integer.parseInt(command.getThirdWord()) - 1) &&
                        (Integer.parseInt(command.getThirdWord()) - 1)
                                <  player.getInventory().getSize())
                {
                    for (int i = 0; i < player.getInventory().getSize(); i++)
                    {
                        if (i == Integer.parseInt(command.getThirdWord()) - 1)
                        {
                            useSlot(i);
                            break;
                        }
                    }
                }
                else
                {
                    //Prints"Slot is out of range"
                    printToConsole.print(textForPrintToConsole.getSlotIsOutOfRange());
                }
                break;
        }
    }

    private void show(Command command)
    {
        if(!command.hasSecondWord())
        {
            //Prints "Show what?"
            printToConsole.print(textForPrintToConsole.getShowWhat());
            return;
        }

        switch (command.getSecondWord()){
            case "map":

                ArrayList<String> mapList = new ArrayList<>();

                for (int i = 0; i < currentMap.getHeight(); i++)
                {
                    String mapString = " " + i + " \u2551 ";
                    for (Room room : currentMap.getRoomList())
                    {
                        if (room.getLocation().y == i)
                        {
                            if (room.getLocation().x == player.getLocation().x &&
                                    room.getLocation().y == player.getLocation().y)
                            {
                                mapString = mapString + "  P   ";
                            }
                            else if (room.getHasBeenEntered())
                            {
                                mapString = mapString + "  O   ";
                            }
                            else if (!room.getHasBeenEntered())
                            {
                                mapString = mapString + "  X   ";
                            }
                        }
                    }
                    mapString += " \u2551";
                    mapList.add(0,mapString);
                }

                mapList.set(0 , mapList.get(0) + "   X = Unseen Rooms  ");
                mapList.set(1 , mapList.get(1) + "   O = Seen Rooms ");
                mapList.set(2 , mapList.get(2) + "   P = Player ");
                mapList.set(3 , mapList.get(3) + "   G = Guides ");

                //Prints Map layout part 1
                printToConsole.print(textForPrintToConsole.getMapLayoutPart1());

                for (String s : mapList)

                //Prints players whereabouts -> Passing  String s as parameter
                printToConsole.print(s);

                //Prints Map layout part 2
                printToConsole.print(textForPrintToConsole.getMapLayoutPart2());

                //Prints Map layout part 3
                printToConsole.print(textForPrintToConsole.getMapLayoutPart3());
                break;
            case "exits":
                int counter = 1;
                for(String s : checkExits())
                {
                //Prints exits from current room
                printToConsole.print(textForPrintToConsole.getExits(counter,s));
                counter++;
                }
                printToConsole.print(textForPrintToConsole.getEmptyLine());//Prints empty line
                break;
            case "health":
                //Prints players current hp
                printToConsole.print(textForPrintToConsole.getYouCurrentlyHaveHp(player));
                break;
            case "score":
                //Prints players current score
                printToConsole.print(textForPrintToConsole.getYouCurrentlyHavePoints(player));
                break;
            case "weapon":

                //Prints players current weapon
                printToConsole.print(textForPrintToConsole.getCurrentWeapon(player));
                break;

            case "inventory":
                showInventory();
                break;
            case "slot":

                if (0 <= (Integer.parseInt(command.getThirdWord()) - 1) &&
                        (Integer.parseInt(command.getThirdWord()) - 1) <
                        player.getInventory().getSize())
                {
                    for (int i = 0; i < player.getInventory().getSize(); i++)
                    {
                        if (i == Integer.parseInt(command.getThirdWord()) - 1)
                        {
                            showSlot(i);
                            break;
                        }
                    }
                }
                else
                {
                    //Prints "Slot is out of range"
                    printToConsole.print(textForPrintToConsole.getSlotIsOutOfRange());
                }
                break;
            default:
                //Prints "Huh?"
                printToConsole.print(textForPrintToConsole.getHuh());
                break;
        }
    }

    public void showInventory()
    {
        String  top = " \u256D";
        String mTop = " \u2551";
        String  middle = " \u2551";
        String  mBottom = " \u2551";
        String  bottom = " \u2570";
        String  slot = "  ";
        for (int i = 0; i < player.getInventory().getSize(); i++)
        {
            top += "-" + player.getInventory().getItem(i).name.replaceAll(".","-") + "-";

            mTop +=" " + player.getInventory().getItem(i).name.replaceAll("."," ") + " ";
            middle +=" " + player.getInventory().getItem(i).name + " ";
            mBottom +=" " + player.getInventory().getItem(i).name.replaceAll("."," ") + " ";
            bottom += "-" + player.getInventory().getItem(i).name.replaceAll(".","-") + "-";
            slot += " " + String.valueOf(i+1) +
                    player.getInventory().getItem(i).name.replaceAll(".", " ") + "";
        }
        top = top+ "\u256E";
        mTop = mTop + "\u2551";
        middle =middle+ "\u2551" + "  To get information on an item type \"show slot 1\",slot 2 ...";
        mBottom = mBottom + "\u2551";
        bottom = bottom + "\u256F" + "  To use an item type \"use slot 1\",slot 2 ...";

        String inventory = top +"\n"+ mTop+"\n" + middle+"\n" + mBottom+"\n" + bottom+"\n" + slot;

        //Prints players inventory
        printToConsole.print(textForPrintToConsole.getShowInventory(inventory));
    }

    public void showSlot(int index)
    {
        Item item = player.getInventory().getItem(index);

        if (item instanceof Weapon)
        {
            //Prints players slot if it's a weapon
            printToConsole.print(textForPrintToConsole.getWeapon(item));
        }
        else if (item instanceof Potion)
        {
            //Prints players slot if it's a potion
            printToConsole.print(textForPrintToConsole.getPotion(item));
        }
        else
        {
            //Prints "Slot is empty."
            printToConsole.print(textForPrintToConsole.getSlotIsEmpty());
        }
    }

    public void useSlot(int index)
    {
        Item item = player.getInventory().getItem(index);

        if (item instanceof Weapon)
        {
            player.setCurrentWeapon((Weapon) item);
            //Prints "Your current weapon is now: " + player.getCurrentWeapon().name
            printToConsole.print(textForPrintToConsole.getSetCurrentWeapon(player));
        }
        else if (item instanceof Potion)
        {
            player.setHealth(player.getHealth() + ((Potion) item).getHealthRecovery());
            player.getInventory().removeItem(player.getInventory().getItemIndex(item));
            //Prints "Yom yom ... Your health is now: " + player.getHealth() + "hp"
            printToConsole.print(textForPrintToConsole.getYomYom(player));
        }
        else
        {
            //Prints "Slot is empty."
            printToConsole.print(textForPrintToConsole.getSlotIsEmpty());
        }
    }
    /**
     * Method that prints a help message to the screen.
     */
    private void printHelp() 
    {
        //Prints "Help menu"
        printToConsole.print(textForPrintToConsole.getHelpMenu());
        String input = parser.getUserInput();

        if (parser.getUserInput().contains("1"))
        {
            //Prints "Your command words are:"
            printToConsole.print(textForPrintToConsole.getHelpCommandWords());

            // prints all the available commands to screen
            parser.showCommands();
        }
        else if (parser.getUserInput().contains("2"))
        {
            //Prints "The goals of the game is to defeat the devil"
            printToConsole.print(textForPrintToConsole.getHelpGoals());
     }
        else if (parser.getUserInput().contains("3"))
        {
            //Prints "No tips or tricks available :( "
            printToConsole.print(textForPrintToConsole.getNoTipsAvaiable());
        }
        else
        {
            //Prints "Invalid menu choice"
            printToConsole.print(textForPrintToConsole.getInvalidChoice());
        }
    }

    /**
     * Method for moving around the rooms.
     *
     * @param command go command.
     */
    private void goRoom(Command command) 
    {
        // Checks if the command has a second word and if not prints a message to the screen
        if(!command.hasSecondWord()) 
        {
            //Prints "Go where?"
            printToConsole.print(textForPrintToConsole.getGoWhere());
            return;
        }

        // Sets the String direction to the location of the room you want to go to (east, west, north, south)
        String direction = command.getSecondWord();

        switch (direction){
            case "up":
                if (currentMap.roomExists(new Point(player.getLocation().x, player.getLocation().y + 1)))
                {
                    player.setLocation(new Point(player.getLocation().x, player.getLocation().y + 1));

                    //Prints "You entered new room."
                    printToConsole.print(textForPrintToConsole.getYouEnteredANewRoom());

                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();

                }
                else
                    //Prints "You ran into wall :("
                        printToConsole.print(textForPrintToConsole.getYouRanIntoAWall());
                break;
            case "down":
                if (currentMap.roomExists(new Point(player.getLocation().x, player.getLocation().y - 1)))
                {
                    player.setLocation(new Point(player.getLocation().x, player.getLocation().y - 1));

                    //Prints "You entered new room."
                    printToConsole.print(textForPrintToConsole.getYouEnteredANewRoom());

                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();
                }
                else
                    //Prints "You ran into wall :("
                    printToConsole.print(textForPrintToConsole.getYouRanIntoAWall());
                break;
            case "left":
                if (currentMap.roomExists(new Point(player.getLocation().x - 1, player.getLocation().y)))
                {
                    player.setLocation(new Point(player.getLocation().x - 1, player.getLocation().y));

                    //Prints "You entered new room."
                    printToConsole.print(textForPrintToConsole.getYouEnteredANewRoom());

                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();
                }
                else
                    //Prints "You ran into wall :("
                    printToConsole.print(textForPrintToConsole.getYouRanIntoAWall());
                break;
            case "right":
                if (currentMap.roomExists(new Point(player.getLocation().x + 1, player.getLocation().y )))
                {
                    player.setLocation(new Point(player.getLocation().x + 1, player.getLocation().y));

                    //Prints "You entered new room."
                    printToConsole.print(textForPrintToConsole.getYouEnteredANewRoom());

                    currentMap.setRoomHasBeenEntered(player.getLocation());
                    checkRoom();
                }
                else
                    //Prints "You ran into wall :("
                    printToConsole.print(textForPrintToConsole.getYouRanIntoAWall());
                break;
            case "back":
                player.setLocation(new Point(player.getLastLocation().x, player.getLastLocation().y));

                //Prints "You went back to the previous room."
                printToConsole.print(textForPrintToConsole.getYouWentBackToPreviousRoom());
                checkRoom();
                break;
            default:
                //Prints "Go where? No such direction found..."
                printToConsole.print(textForPrintToConsole.getNoSuchDirection());
                break;
        }
    }

    public void checkRoom()
    {
        for (int i = 0; i < 2; i++)
        {
            for (Room room : currentMap.getRoomList())
            {
                if (player.getLocation().x == room.getLocation().x && player.getLocation().y == room.getLocation().y)
                {
                    if (room.getContent(i) instanceof Monster)
                    {
                        randomMonster();
                        //Prints "There is a monster, you can either do battle or flee!"
                        printToConsole.print(textForPrintToConsole.getThereIsAMonster());

                        //Prints "Your health is currently " + player.getHealth() + "hp"
                        printToConsole.print(textForPrintToConsole.getYouCurrentlyHaveHp(player));

                        //Prints "Monsters health is currently " +((Monster) room.getContent(i)).getHealth() + "hp"
                        printToConsole.print(textForPrintToConsole.getMonstersHealth(room,i));

                        //Prints "Type \"battle\" or \"flee\"." // We need to type more information!
                        printToConsole.print(textForPrintToConsole.getBattleOrFlee());

                        boolean acceptedInput = false;
                        while (!acceptedInput)
                        {                            
                            String input = parser.getUserInput();//returns a String

                            if (input.toLowerCase().contains("battle"))
                            {
                                acceptedInput = true;
                                battle = new Battle(player, (Monster)room.getContent(i)); // creates a new battle

                                while (!battle.getIsBattleOver())
                                {
                                    //Prints "attack & drink potion"
                                    printToConsole.print(textForPrintToConsole.getAttackOrDrinkPotion());

                                    input = parser.getUserInput();
                                    if (input.toLowerCase().contains("attack") || input.toLowerCase().contains("a"))
                                    {
                                        //Prints status of battle
                                        printToConsole.print(textForPrintToConsole.getBattle(battle));
                                    }
                                    else if (input.toLowerCase().contains("drink"))
                                    {
                                        if (!player.getInventory().potionArrayList().isEmpty())
                                        {

                                            //Prints "Type number to use."
                                            printToConsole.print(textForPrintToConsole.getTypeSlotNumberToUse());

                                            for (int j = 0; j < player.getInventory().potionArrayList().size(); j++)
                                            {
                                                //Prints
                                                printToConsole.print(textForPrintToConsole.getPotionRecovery(j,player));
                                            }
                                            input = parser.getUserInput();
                                            int index = Integer.parseInt(input) -1;
                                            player.setHealth(player.getHealth() +
                                            player.getInventory().potionArrayList().get(index).getHealthRecovery());
                                            player.getInventory().removeItem(player.getInventory()
                                            .getItemIndex(player.getInventory().potionArrayList().get(index)));

                                            //Prints "Yom yom ... Your health is now: " + player.getHealth() + "hp"
                                            printToConsole.print(textForPrintToConsole.getYomYom(player));
                                        }
                                        else
                                        {
                                            //Prints "You have no potions :("
                                            printToConsole.print(textForPrintToConsole.getYouHaveNoPotions());
                                        }
                                    }
                                    else
                                    {
                                        //Prints "Type \"attack\" or \"drink\""
                                        printToConsole.print(textForPrintToConsole.getAttackOrDrinkPotion());
                                    }
                                }
                                room.removeContent(i);
                            }
                            else if (input.toLowerCase().contains("flee"))    
                            {
                                acceptedInput = true;
                                player.setLocation(player.getLastLocation());
                                return;
                            }
                            else
                                {
                                    //Prints "Type \"battle\" or \"flee\""
                                    printToConsole.print(textForPrintToConsole.getBattleOrFlee());
                                }
                        }

                    }
                    else if (room.getContent(i) instanceof Helper)
                    {
                        randomHelper();
                        //Prints "There is a helper, you can either \"talk\" , \"flee\" or \"kill\"!"
                        printToConsole.print(textForPrintToConsole.getThereIsAHelper());
                        boolean acceptedInput = false;
                        while (!acceptedInput)
                        {
                            String input = parser.getUserInput();
                            if(input.equals("talk"))
                            {
                                acceptedInput = true;
                                //Prints "Hello my name is \"insert name here\" here is a tip ;) ... DON'T DIE!!!"
                                printToConsole.print(textForPrintToConsole.getHelperTalk());
                            }
                            else if(input.equals("flee"))
                            {
                                acceptedInput = true;
                                player.setLocation(player.getLastLocation());
                                return;
                            }
                            else if(input.equals("kill"))
                            {
                                acceptedInput = true;
                                //Prints "You killed the helper, oh might swordsman!"
                                printToConsole.print(textForPrintToConsole.getKilledHelper());
                            }
                            room.removeContent(i);
                        }
                    }
                    else if (room.getContent(i) instanceof Chest)
                    {
                        randomChest();

                        //Prints "There is a chest, type \"open\" to open!"
                        printToConsole.print(textForPrintToConsole.getThereIsAChest());

                        boolean acceptedInput = false;
                        while (!acceptedInput)
                        {
                            String input = parser.getUserInput();
                            if(input.equals("open"))
                            {
                                acceptedInput = true;
                                Item item = ((Chest)room.getContent(i)).getItem();

                                if (item instanceof Weapon)
                                {
                                    //Prints content of chest if it's a weapon
                                    printToConsole.print(textForPrintToConsole.getWeapon(item));
                                }
                                else if (item instanceof Potion)
                                {
                                    //Prints content of chest if it's a potion
                                    printToConsole.print(textForPrintToConsole.getPotion(item));
                                }
                                showInventory();
//                                System.out.println("Do you want to insert this into a slot?");
//                                System.out.println("Type slot number or \"drop\" to drop.");

                                //Prints "Do you want to insert this into a slot?"
                                //"Type slot number or \"drop\" to drop."
                                printToConsole.print(textForPrintToConsole.getWhatSlot());

                                input = parser.getUserInput();

                                for (int j = 0; j < player.getInventory().getSize() ; j++)
                                {
                                    if(input.equals(String.valueOf(j+1)))
                                    {
                                        player.getInventory().addItem(item,j);

                                        //Prints "You saved this item in slot: " + (j+1)
                                        printToConsole.print(textForPrintToConsole.getYouSavedItemInThisSlot(j));
                                    }
                                }
                                if (input.equals("drop"))
                                {
                                    //Prints "You dropped the item"
                                    printToConsole.print(textForPrintToConsole.getYouDroppedTheItem());
                                }
                            }
                            else
                            {
                                //Prints "Hmm... Wrong command"
                                printToConsole.print(textForPrintToConsole.getHmmWrongCommand());
                            }
                            room.removeContent(i);
                        }
                    }
                    else if (room.getContent(i) instanceof RoomContent)
                    {
                        //Prints "Empty space :("
                        printToConsole.print(textForPrintToConsole.getItsAEmptySpace());
                    }
                }
            }
        }
    }


    public void randomMonster()
    {
        System.out.println("-----------------------------------------------");
        int random = (int)(Math.random()*40 + 1);
        switch (random)
        {
            case 1: System.out.println(ASCII.getSpider()); break;
            case 2: System.out.println(ASCII.getGryphon()); break;
            case 3: System.out.println(ASCII.getMermaid()); break;
            case 4: System.out.println(ASCII.getUnicorn()); break;
            case 5: System.out.println(ASCII.getFairy()); break;
            case 6: System.out.println(ASCII.getHamster()); break;
            case 7: System.out.println(ASCII.getCyclops()); break;
            case 8: System.out.println(ASCII.getSonic()); break;
            case 9: System.out.println(ASCII.getDevil()); break;
            case 10: System.out.println(ASCII.getBabar()); break;
            case 11: System.out.println(ASCII.getBat()); break;
            case 12: System.out.println(ASCII.getBuddha()); break;
            case 13: System.out.println(ASCII.getDevil2()); break;
            case 14: System.out.println(ASCII.getEasterBunny()); break;
            case 15: System.out.println(ASCII.getFrenshMan()); break;
            case 16: System.out.println(ASCII.getGanesha()); break;
            case 17: System.out.println(ASCII.getGhost()); break;
            case 18: System.out.println(ASCII.getGrim()); break;
            case 19: System.out.println(ASCII.getHamster()); break;
            case 20: System.out.println(ASCII.getHarryPotter()); break;
            case 21: System.out.println(ASCII.getJackInABox()); break;
            case 22: System.out.println(ASCII.getJesus()); break;
            case 23: System.out.println(ASCII.getKnight1()); break;
            case 24: System.out.println(ASCII.getKnight2()); break;
            case 25: System.out.println(ASCII.getMickeyMouse()); break;
            case 26: System.out.println(ASCII.getNakedWoman()); break;
            case 27: System.out.println(ASCII.getYourMom()); break;
            case 28: System.out.println(ASCII.getPope()); break;
            case 29: System.out.println(ASCII.getPentacle()); break;
            case 30: System.out.println(ASCII.getPikachu()); break;
            case 31: System.out.println(ASCII.getRabbit()); break;
            case 32: System.out.println(ASCII.getHamster()); break;
            case 33: System.out.println(ASCII.getSanta()); break;
            case 34: System.out.println(ASCII.getSeaHorse()); break;
            case 35: System.out.println(ASCII.getShark()); break;
            case 36: System.out.println(ASCII.getSheep()); break;
            case 37: System.out.println(ASCII.getTeddyBear()); break;
            case 38: System.out.println(ASCII.getWhale()); break;
            case 39: System.out.println(ASCII.getWitch()); break;
            case 40: System.out.println(ASCII.getYourMom()); break;

        }
        System.out.println("-----------------------------------------------");
        
    }

    public void randomChest()
    {
        System.out.println("-----------------------------------------------");
        int random = (int)(Math.random()*2);
        switch (random)
        {
            case 0: System.out.println( ASCII.getChest()); break;
            case 1: System.out.println( ASCII.getChest3()); break;
        }
        System.out.println("-----------------------------------------------");
    }

    public void randomHelper()
    {
        System.out.println("-----------------------------------------------");
        int random = (int)(Math.random()*3);
        switch (random)
        {
            case 0: System.out.println(ASCII.getHamster()); break;
            case 1: System.out.println(ASCII.getBuddha()); break;
            case 2: System.out.println(ASCII.getGanesha()); break;
        }
        System.out.println("-----------------------------------------------");

    }
    /**
     * This is the quit method that return a true or false value.
     * This will return a boolean value of true if there is no other words then quit.
     *
     * @param command quit command.
     * @return boolean
     */
    private boolean quit(Command command) 
    {
        // Checks if command says more that "quit", and cancels the request if so
        if(command.hasSecondWord()) 
        {
            System.out.println("Quit what?");
            return false;
        }
        else 
        {
            return true;
        }
    }

    public ArrayList<String> checkExits()
    {
        ArrayList<String > exitList = new ArrayList<>();

        if (currentMap.roomExists(new Point(player.getLocation().x - 1, player.getLocation().y)))
            exitList.add("left");
        if (currentMap.roomExists(new Point(player.getLocation().x + 1, player.getLocation().y)))
            exitList.add("right");
        if (currentMap.roomExists(new Point(player.getLocation().x, player.getLocation().y + 1)))
            exitList.add("up");
        if (currentMap.roomExists(new Point(player.getLocation().x, player.getLocation().y - 1)))
            exitList.add("down");
        return exitList;
    }

    public void slowPrint(String message, long millisPerChar)
    {
        for (int i = 0; i < message.length(); i++)
        {
            System.out.print(message.charAt(i));

            try
            {
                Thread.sleep(millisPerChar);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
