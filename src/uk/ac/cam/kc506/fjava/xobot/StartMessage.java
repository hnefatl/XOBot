package uk.ac.cam.kc506.fjava.xobot;

import java.util.List;
import java.util.ArrayList;

// Message: xo start <player>
public class StartMessage extends Message
{
    private String otherPlayer;

    public StartMessage(String sender, String otherPlayer)
    {
        super(sender);
        this.otherPlayer = otherPlayer;
    }

    @Override
    public List<String> getResponse(GameManager games)
    {
        List<String> r = new ArrayList<>();

        if (games.hasGame(getSender()))
        {
            r.add(getSender() + " is already in a game.");
        }
        else if (games.hasGame(otherPlayer))
        {
            r.add(otherPlayer + " is already in a game.");
        }
        else
        {
            String busyPlayer = games.addGame(getSender(), otherPlayer);
            if (busyPlayer != null)
            {
                r.add(busyPlayer + " is already in a game.");
            }
            else
            {
                r.add("Game between " + getSender() + " and " + otherPlayer + " started.");
                r.addAll(games.getGame(getSender()).board.toRows());
            }
        }

        return r;
    }
}