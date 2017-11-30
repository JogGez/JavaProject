/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonCrawler.data;

import dungeonCrawler.aqu.IMap;
import dungeonCrawler.aqu.IPlayer;
import java.io.Serializable;

/**
 *
 * @author Computer
 */
public class GameStateDTO implements Serializable
{
    private IPlayer player;
    private IMap map;

    public GameStateDTO(IPlayer player, IMap map)
    {
        this.player = player;
        this.map = map;
    }

    public IPlayer getPlayer()
    {
        return player;
    }

    public IMap getMap()
    {
        return map;
    }
    
    
    
}
