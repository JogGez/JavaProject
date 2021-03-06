/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.logic;

import java.awt.*;
import java.io.Serializable;

/**
 * Gamesettings, static variables for the game.
 * @author Peter Jonathan, Joakim, Linea, Frederik, Simon og Brian.
 */
class GameSettings implements Serializable
{
    // Static because of static methods.
    private static Point mapSize;

     // number of contents in a room.
    private static int roomContents;

    // Player settings.
    private static int playerHealth;
    private static int playerTime;
    private static int playerPower;
    private static int inventorySize;

    // Monster settings.
    private static int monsterHealth;
    private static int monsterPower;

    // Lucifer settings.
    private static int luciferHealth;
    private static int luciferPower;

    // Map settings.
    private static int numberOfMerchants;
    private static int merchantMoveRounds;

    private static int numberOfThieves;
    private static int thiefPickingRounds;

    private static int lockedRooms;

    // Room Settiings. (combined chance must be 100% or under)
    private static int chanceOfMonster;
    private static int chanceOfChest;
    private static int chanceOfGuide;

    // Chest Settiings. (combined chance must be 100% or under)
    private static int chanceOfWeapon;
    private static int chanceOfHealthPotion;
    private static int chanceOfTimePotion;
    private static int chanceOfKey;
    private static int chanceOfSpecial;
    private static int scoreMulitplier;

    /**
     * Private constructor to maintain static and to prevent instantiating
     */
    private GameSettings()
    {
    }

    /**
     * Sets GameSettings fields to a variable
     */
    public static void setEasyDifficulty()
    {
        mapSize = new Point (3,4);
        roomContents = 2;
        playerHealth = 1000;
        inventorySize = 5;
        playerTime = 320;
        playerPower = 25;

        monsterHealth = 0;
        monsterPower = 0;

        luciferHealth = 0;
        luciferPower = 0;

        numberOfMerchants = 1;
        merchantMoveRounds = 1;
        numberOfThieves = 2;
        thiefPickingRounds = 3;
        lockedRooms = 2;

        chanceOfMonster = 45;
        chanceOfChest = 45;
        chanceOfGuide = 10;

        chanceOfWeapon = 25;
        chanceOfHealthPotion = 20;
        chanceOfTimePotion = 20;
        chanceOfKey = 10;
        chanceOfSpecial = 25;

        scoreMulitplier = 1;
    }

    /**
     * Sets GameSettings fields to a variable
     */
    public static void setNormalDifficulty()
    {
        mapSize = new Point (4,5);
        roomContents = 2;
        playerHealth = 900;
        inventorySize = 4;
        playerTime = 260;
        playerPower = 25;

        monsterHealth = 5;
        monsterPower = 3;

        luciferHealth = 100;
        luciferPower = 5;

        numberOfMerchants = 2;

        merchantMoveRounds = 1;
        numberOfThieves = 2;
        thiefPickingRounds = 3;
        lockedRooms = 3;

        chanceOfMonster = 40;
        chanceOfChest = 40;
        chanceOfGuide = 20;

        chanceOfWeapon = 25;
        chanceOfHealthPotion = 35;
        chanceOfTimePotion = 30;
        chanceOfKey = 5;
        chanceOfSpecial = 5;

        scoreMulitplier = 2;
    }


    /**
     * Sets GameSettings fields to a variable
     */
    public static void setHardDifficulty()
    {
        mapSize = new Point (5,6);
        roomContents = 3;
        playerHealth = 750;
        inventorySize = 3;
        playerTime = 220;
        playerPower = 20;

        monsterHealth = 10;
        monsterPower = 5;

        luciferHealth = 200;
        luciferPower = 10;

        numberOfMerchants = 3;
        merchantMoveRounds = 0;
        numberOfThieves = 3;
        thiefPickingRounds = 2;
        lockedRooms = 4;

        chanceOfMonster = 50;
        chanceOfChest = 40;
        chanceOfGuide = 10;

        chanceOfWeapon = 30;
        chanceOfHealthPotion = 25;
        chanceOfTimePotion = 30;
        chanceOfKey = 5;
        chanceOfSpecial = 10;

        scoreMulitplier = 3;
    }

    /**
     * Getter method for chance of a key
     * @return int
     */
    static int getChanceOfKey()
    {
        return chanceOfKey;
    }

    /**
     * Getter method for difficulty level
     * @return int
     */
    static int getDifficultyLevel(){return scoreMulitplier;}



    /**
     * Getter method for chance of a special item
     * @return int
     */
    static int getChanceOfSpecial()
    {
        return chanceOfSpecial;
    }

    /**
     * Getter method for chance of a monster
     * @return int
     */
    static int getChanceOfMonster()
    {
        return chanceOfMonster;
    }

    /**
     * Getter method for chance of a chest
     * @return int
     */
    static int getChanceOfChest()
    {
        return chanceOfChest;
    }

    /**
     * Getter method for chance of a guide
     * @return int
     */
    static int getChanceOfGuide()
    {
        return chanceOfGuide;
    }

    /**
     * Getter method for chance of a weapon
     * @return int
     */
    static int getChanceOfWeapon()
    {
        return chanceOfWeapon;
    }

    /**
     * Getter method for chance of a health potion
     * @return int
     */
    static int getChanceOfHealthPotion()
    {
        return chanceOfHealthPotion;
    }

    /**
     * Getter method for chance of a time potion
     * @return int
     */
    static int getChanceOfTimePotion()
    {
        return chanceOfTimePotion;
    }

    /**
     * Getter method for number of locked rooms
     * @return int
     */
    static int getLockedRooms()
    {
        return lockedRooms;
    }

    /**
     * Getter method for map size. X * Y
     * @return Point
     */
    static Point getMapSize()
    {
        return mapSize;
    }

    /**
     * Getter method for amount of content a room can contain
     * @return int
     */
    static int getRoomContents()
    {
        return roomContents;
    }

    /**
     * Getter method for players start health
     * @return int
     */
    static int getPlayerHealth()
    {
        return playerHealth;
    }

    /**
     * Getter method for how many slots the inventory have
     * @return int
     */
    static int getInventorySize()
    {
        return inventorySize;
    }

    /**
     * Getter method for the start time of the game
     * @return int
     */
    static int getPlayerTime()
    {
        return playerTime;
    }

    /**
     * Getter method for the players default attack value
     * @return int
     */
    static int getPlayerPower()
    {
        return playerPower;
    }

    /**
     * Getter method for the monsters default health value
     * @return int
     */
    static int getMonsterHealth()
    {
        return monsterHealth;
    }

    /**
     * Getter method for the monsters default attack value
     * @return int
     */
    static int getMonsterPower()
    {
        return monsterPower;
    }

    /**
     * Getter method for Lucifer default health value
     * @return int
     */
    static int getLuciferHealth()
    {
        return luciferHealth;
    }
    /**
     * Getter method for Lucifers default attack value
     * @return int
     */
    static int getLuciferPower()
    {
        return luciferPower;
    }

    /**
     * Getter method for how many merchants there is in the game
     * @return int
     */
    static int getNumberOfMerchants()
    {
        return numberOfMerchants;
    }

    /**
     * Getter method for how many rounds it takes a merchant to move
     * @return int
     */
    static int getMerchantMoveRounds()
    {
        return merchantMoveRounds;
    }
    /**
     * Getter method for how many thieves there is in the game
     * @return int
     */
    static int getNumberOfThieves()
    {
        return numberOfThieves;
    }

    /**
     * Getter method for how many rounds it takes the thief to steal a chest
     * @return int
     */
    static int getThiefPickingRounds()
    {
        return thiefPickingRounds;
    }
}
