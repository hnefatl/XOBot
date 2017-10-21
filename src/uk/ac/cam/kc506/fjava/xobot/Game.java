package uk.ac.cam.kc506.fjava.xobot;

import java.util.Map;
import java.util.TreeMap;

public class Game
{
    public String playerOne;    
    public String playerTwo;    
    public Map<String, Player> players;

    public String nextPlayer;

    public Board board;

    public Game(String playerOne, String playerTwo)
    {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        players = new TreeMap<>();
        players.put(playerOne, Player.X);
        players.put(playerTwo, Player.O);

        nextPlayer = playerOne;

        board = new Board();
    }

    public void swapNextPlayer(String currentPlayer)
    {
        if (currentPlayer.equals(playerOne))
            nextPlayer = playerTwo;
        else
            nextPlayer = playerOne;
    }
}