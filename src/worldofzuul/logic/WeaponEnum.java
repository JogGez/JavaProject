package worldofzuul.logic;

import worldofzuul.presentation.ASCII;
import java.util.Random;

/**
 *
 * @author Linea Hoffmann
 */
public enum WeaponEnum
{
    EXCALIBUR (new Weapon("Excalibur","The Sword of King Arthur", ASCII.getSword(),1000,1000)), 
    DURANDAL ( new Weapon ("Durandal","Sword of Roland, legendary paladin of Charlemagne",ASCII.getSword(),100,15 )), 
    LEGBITER ( new Weapon ("Legbiter","The Sword of Viking King Magnus III", ASCII.getSword(),150,5)), 
    THE_HOLY_LANCE (new Weapon ("The Holy Lance","Also known as the Spear of Destiny and the Lance of Longinus, was the spear that allegedly pierced the side of Jesus during the crucifixion. ",ASCII.getSword(),500,1500)), 
    GOUJIAN(new Weapon ("Goujian","Emperor Goujian, King of Yue States Sword", ASCII.getSword(),75,15)), 
    MJÖLNIR (new Weapon ("Mjölnir","The Mighty Hammer of Thor", ASCII.getSword(),250,100)), 
    FLOPPY_FISH (new Weapon("Floppy Fish", "Floppy Fish from the sea :(", ASCII.getSword(),1,0))
    ;
    
    private final Weapon weapon;
    
    /**
     * 
     * @param weapon 
     */
    WeaponEnum (Weapon weapon)
    {
        this.weapon = weapon;
    }
    
    /**
     * 
     * @return object
     */
    public Weapon getWeapon()
    {
        return weapon;
    }
    
    /**
     * 
     * @return random weapon
     */
    public static Weapon getRandomWeapon()
    {
        
        return values()[new Random().nextInt(values().length)].weapon;
    }
}
