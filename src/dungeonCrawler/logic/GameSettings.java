/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.logic;

import java.awt.*;
import java.io.Serializable;

/**
 *
 * @author Computer
 */
class GameSettings implements Serializable
{
    // Static because of static methods.
    private static Point mapSize;
     // number of contents in a room.
    private static int roomContents;
    //
    private static int playerHealth;
    private static int inventorySize;
    private static int playerTime;
    private static int playerPower;
    private static int numberOfMerchants;
    private static int numberOfThieves;
    private static int thiefPickingRounds;
    private static int lockedRooms;

    // Combined chance must be under 100%
    private static int chanceOfMonster;
    private static int chanceOfChest;
    private static int chanceOfGuide;

    // Combined chance must be under 100%
    private static int chanceOfWeapon;
    private static int chanceOfHealthPotion;
    private static int chanceOfTimePotion;
    private static int chanceOfKey;
    private static int chanceOfSpecial;
    private static int scoreMulitplier;

    // Private constructor to maintain static and to prevent instantiating

    private GameSettings()
    {
    }
    public static void setEasyDifficulty()
    {
       mapSize = new Point (2,2);
       roomContents = 2;
       playerHealth = 1000;
       inventorySize = 5;
       playerTime = 300;
       playerPower = 25;
       numberOfMerchants = 0;
       numberOfThieves = 0;
       thiefPickingRounds = 3;
       lockedRooms = 0;

       //Chance has to be a total of 100
       chanceOfMonster = 40;
       chanceOfChest = 40;
       chanceOfGuide = 20;

       chanceOfWeapon = 15;
       chanceOfHealthPotion = 15;
       chanceOfTimePotion = 15;
       chanceOfKey = 15;
       chanceOfSpecial = 40;

       scoreMulitplier = 1;
    }

    public static void setNormalDifficulty()
    {
        mapSize = new Point (4,5);
        roomContents = 2;
        playerHealth = 500;
        inventorySize = 4;
        playerTime = 240;
        playerPower = 20;
        numberOfMerchants = 2;
        numberOfThieves = 2;
        thiefPickingRounds = 3;
        lockedRooms = 4;

        //Chance has to be a total of 100
        chanceOfMonster = 25;
        chanceOfChest = 50;
        chanceOfGuide = 15;

        chanceOfWeapon = 30;
        chanceOfHealthPotion = 30;
        chanceOfTimePotion = 30;
        chanceOfKey = 0;
        chanceOfSpecial = 10;

        scoreMulitplier = 2;
    }


    public static void setHardDifficulty()
    {
        mapSize = new Point (5,6);
        roomContents = 3;
        playerHealth = 300;
        inventorySize = 3;
        playerTime = 180;
        playerPower = 10;
        numberOfMerchants = 3;
        numberOfThieves = 3;
        thiefPickingRounds = 2;
        lockedRooms = 6;

        //Chance has to be a total of 100
        chanceOfMonster = 25;
        chanceOfChest = 50;
        chanceOfGuide = 15;

        chanceOfWeapon = 30;
        chanceOfHealthPotion = 30;
        chanceOfTimePotion = 30;
        chanceOfKey = 0;
        chanceOfSpecial = 10;

        scoreMulitplier = 3;
    }
    static int getChanceOfKey()
    {
        return chanceOfKey;
    }

    static int getDifficultyLevel(){return scoreMulitplier;}

    static int getThiefPickingRounds()
    {
        return thiefPickingRounds;
    }

    static int getChanceOfSpecial()
    {
        return chanceOfSpecial;
    }

    static int getChanceOfMonster()
    {
        return chanceOfMonster;
    }

    static int getChanceOfChest()
    {
        return chanceOfChest;
    }

    static int getChanceOfGuide()
    {
        return chanceOfGuide;
    }

    static int getChanceOfWeapon()
    {
        return chanceOfWeapon;
    }

    static int getChanceOfHealthPotion()
    {
        return chanceOfHealthPotion;
    }

    static int getChanceOfTimePotion()
    {
        return chanceOfTimePotion;
    }

    static int getLockedRooms()
    {
        return lockedRooms;
    }

    static Point getMapSize()
    {
        return mapSize;
    }

    static int getRoomContents()
    {
        return roomContents;
    }

    static int getPlayerHealth()
    {
        return playerHealth;
    }

    static int getInventorySize()
    {
        return inventorySize;
    }

    static int getPlayerTime()
    {
        return playerTime;
    }

    static int getPlayerPower()
    {
        return playerPower;
    }

    static int getNumberOfMerchants()
    {
        return numberOfMerchants;
    }

    static int getNumberOfThieves()
    {
        return numberOfThieves;
    }

    static int getThiftPickingRounds()
    {
        return thiefPickingRounds;
    }
}
