package uk.ac.cam.kc506.fjava.xobot;

import java.util.List;
import java.util.ArrayList;

public class PlayMessage extends Message
{
    private Position point;

    public PlayMessage(String sender, Position p)
    {
        super(sender);

        point = p;
    }

    @Override
    public List<String> getResponse(GameManager games)
    {
        List<String> r = new ArrayList<>();

        try
        {
            if (!games.hasGame(getSender()))
                r.add(getSender() + " isn't in a game.");
            else
            {
                Game g = games.getGame(getSender());
                if (!g.nextPlayer.equals(getSender()))
                    r.add("It's not " + getSender() + "'s turn yet.");
                else
                {
                    if (g.board.getCell(point) != null) // Cell not empty
                        r.add("Position " + point.toString() + " isn't empty.");
                    else
                    {
                        g.board.setCell(point, g.players.get(getSender()));
                        g.swapNextPlayer(getSender());
                        r.addAll(g.board.toRows());
                        
                        if (g.board.won(g.players.get(getSender()))) // Player won
                        {
                            r.add("Player " + getSender() + " won!");
                            games.removeGame(getSender());
                        }
                    }
                }
            }
        }
        catch (InvalidPosition e)
        {
            r.add("Invalid position.");
        }

        return r;
    }
}