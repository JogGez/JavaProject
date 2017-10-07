/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 * The type Player.
 *
 * @author Jonathan
 */
public class Player {
    
    // We aren't making the system print out a line, because later on it would
    // result in a error. Therefore we are going to create a seperate class under game => play.
    // this method under game, calls for our parser (which is our primary scanner class) that gets the users next input, and makes it a string that is used for the name. 
    private String name;
    private int health;
    private int score;
    private boolean isDead = false;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
// Used in game class under play method to set the name, which is made in parser (our scanner)
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Sets health.
     *
     * @param health the health
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * Health int.
     *
     * @return the int
     */
    public int health()
    {
        int hp = 100;
        return hp;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score)
    {
        this.score = score;
    }

    /**
     * Power double.
     *
     * @param npc the npc
     * @return the double
     */
// Most of the things underneath can be moved into a different class
    // all the way to return isDead part.
    // gives the player the power to "attack" monster
    public double power(Monster npc)
    {
        // Calculate damage dealt.
        //Should most likly be put under default weapons (Hands/Swords/Mace etc.)
        // Manipulation of the damage
        // Makes the damage be a bit more random
        double damage = Math.round((Math.random() * 4 + 3));
        npc.takeDamage(damage);
        return damage;
    }

    /**
     * Take damage.
     *
     * @param damage the damage
     */
// I thought about making it a none void, but the example i saw, did it with void.
    // Might change.
    public void takeDamage(double damage)
    {
        //Checks damage taken, if 0 or less, return isDead
        if (health - damage <= 0)
        {
        health = 0;
        isDead = true;
        }
        else
        {
            // Otherwise minus health with damage taken
            health -= damage;
        }
    }

    /**
     * Is dead boolean.
     *
     * @return the boolean
     */
// State of death
    public boolean isDead()
    {
        return isDead;
    }
    
}
