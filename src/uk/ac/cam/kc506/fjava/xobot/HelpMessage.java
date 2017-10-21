package uk.ac.cam.kc506.fjava.xobot;

import java.util.List;
import java.util.ArrayList;

// Message: xo help
public class HelpMessage extends Message
{
    public HelpMessage(String sender)
    {
        super(sender);
    }

    @Override
    public List<String> getResponse(GameManager state)
    {
        List<String> r = new ArrayList<>();
        r.add(Constants.HELP_MESSAGE);
        return r;
    }
}