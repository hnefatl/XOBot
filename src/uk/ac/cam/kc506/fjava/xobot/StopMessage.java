package uk.ac.cam.kc506.fjava.xobot;

import java.util.List;
import java.util.ArrayList;

// Message: xo stop
public class StopMessage extends Message
{
    public StopMessage(String sender)
    {
        super(sender);
    }

    @Override
    public List<String> getResponse(GameManager games)
    {
        List<String> r = new ArrayList<>();

        Game g = games.getGame(getSender());
        if (g != null)
        {
            r.add("Game between " + g.playerOne + " and "  + g.playerTwo + " stopped.");
        }

        games.removeGame(getSender());

        return r;
    }
}