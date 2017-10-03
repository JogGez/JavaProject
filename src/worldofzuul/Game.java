package worldofzuul;

// Class used to run the game
public class Game
{
    // Parser for handling the user input
    private Parser parser;
    // Stores what room we are currently in
    private Room currentRoom;
    // We are storing the class player's name for player.
    private Player player;
        
    // Constructor that launches the class when instantiated through our Main Method
    public Game() 
    {
        // Runs the createRooms() method
        createRooms();
        // Initializes the Parser
        parser = new Parser();
    }

    // Method that creates all the room in the game
    private void createRooms()
    {
        // Create 5 instances of the class Room
        Room outside, theatre, pub, lab, office;
      
        // Initializes the 5 rooms and sets a description for each
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // Adds 3 exits to the room "outside"
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        // Adds 1 exits to the room "theatre"
        theatre.setExit("west", outside);

        // Adds 1 exits to the room "pub"
        pub.setExit("east", outside);

        // Adds 2 exits to the room "lab"
        lab.setExit("north", outside);
        lab.setExit("east", office);

        // Adds 1 exits to the room "office"
        office.setExit("west", lab);

        // Sets the current room to "outside"
        currentRoom = outside;
    }

    // Method that starts the game and runs till the end of the game
    public void play() 
    {            
        // Call printWelcome() method used to write a welcome message
        printWelcome();
        // Calls on the constructor player, and creates the object player
        player = new Player();
        //Prints out the string  beneath
        System.out.print("Enter your name here: ");
        // Gets the name from the parser class, which reads the next input line from the user. Which is going to be the current name for the player.
        player.setName(parser.playerName());
        // I want to create a command that the user can write to print out his health
        player.setHealth(100);
        // Boolean with 
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
            else if (player.isDead())
            {
                // Stops the game if you reach 0 health, and then pritns out the line
                finished = player.isDead();
                System.out.println("You have died :(");
            }
            
            
           
        }
        // Writes the last output before closing the application, also says goodbye to the username
        System.out.println("Thank you for playing. " + player.getName() +  " Good bye.");
    }

     // Method that prints a welcome message to the screen
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    // This method processes the command recieved from the parser and returns whether or not to quit
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        // Gets the last commandWord from the Command object
        CommandWord commandWord = command.getCommandWord();

        // Checks if the commandWord is unknown
        if(commandWord == CommandWord.UNKNOWN) 
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        // Checks if the command is Help and runs the printHelp() method
        if (commandWord == CommandWord.HELP) 
        {
            printHelp();
        }
        // Checks if the command is Go and runs the goRoom() method
        else if (commandWord == CommandWord.GO) 
        {
            goRoom(command);
        }
        // Shows the players current health
        else if (commandWord == CommandWord.HEALTH)
        {
                System.out.println("your current health is " + player.getHealth());
        } 
        else if (commandWord == CommandWord.ATTACK)
        {
                System.out.println("You hit yourself in the face.");
                System.out.println("You take " + player.attack(player) + " points of damage, your health is now " + player.getHealth());
        }
        // Checks if the command is Quit and sets the boolean to true
        else if (commandWord == CommandWord.QUIT) 
        {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

     // Method that prints a help message to the screen
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        // prints all the available commands to screen 
        parser.showCommands();
    }

    // Method for moving around the rooms
    private void goRoom(Command command) 
    {
        // Checkes if the command has a second word and if not prints a message to the screen
        if(!command.hasSecondWord()) 
        {
            System.out.println("Go where?");
            return;
        }

        // Sets the String direction to the location of the room you want to go to (east, west, north, south)
        String direction = command.getSecondWord();

        // Create a placeholder for the room you want to go to
        Room nextRoom = currentRoom.getExit(direction);

        // Checks if the nextRoom exist
        if (nextRoom == null) 
        {
            System.out.println("There is no door!");
        }
        // 
        else
        {
            // Sets the currentRoom to the placeholder and prints out a message
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    // This is the quit method that return a true or false value. 
    // This will return a boolean value of true if there is no other words then quit
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
}
