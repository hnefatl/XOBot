package uk.ac.cam.kc506.fjava.xobot;

import java.util.Map;
import java.util.HashMap;

public class GameManager
{
    private Map<String, Game> games;
    
    public GameManager()
    {
        games = new HashMap<>();
    }

    public boolean hasGame(String ip)
    {
        return games.containsKey(ip);
    }
    public Game getGame(String ip)
    {
        return games.getOrDefault(ip, null);
    }
    // Returns null if the game was added successfully, or the IP of the sender who's already playing a game if it failed
    public String addGame(String ip1, String ip2)
    {
        if (games.containsKey(ip1))
            return ip1;
        else if (games.containsKey(ip2))
            return ip2;
        else
        {
            // Make a new game, register both players
            Game g = new Game(ip1, ip2);
            games.put(ip1, g);
            games.put(ip2, g);
            return null;
        }
    }

    public void removeGame(String ip)
    {
        Game g = getGame(ip);
        if (g == null)
            return;

        games.remove(g.playerOne);
        games.remove(g.playerTwo);
    }
}