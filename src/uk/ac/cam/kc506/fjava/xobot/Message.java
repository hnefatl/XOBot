package uk.ac.cam.kc506.fjava.xobot;

import java.util.List;
import java.util.ArrayList;

public abstract class Message
{
    private String sender;

    public Message(String sender)
    {
        this.sender = sender;
    }

    public abstract List<String> getResponse(GameManager games);

    public String getSender()
    {
        return sender;
    }

    public static Message parse(String input)
    {
        // Input eg. "/192.168.0.0:xo stop"

        String[] segments = input.replaceFirst("/", "").split("[/:\\ ]");
        if (segments.length < 3)
            return null;
        if (!segments[1].toLowerCase().equals(Constants.BOT_INVOKE))
            return null;

        String ip = segments[0];
        
        String command = segments[2].toLowerCase();
        if (command.equals(Constants.START_COMMAND))
        {
            if (segments.length >= 4)
                return new StartMessage(ip, segments[3]);
        }
        else if (command.equals(Constants.STOP_COMMAND))
        {
            return new StopMessage(ip);
        }
        else if (segments.length >= 4) // Try to parse a "play" implicit command - that is, just a position
        {
            try
            {
                int x = Integer.parseUnsignedInt(segments[2]) - 1;
                int y = Integer.parseUnsignedInt(segments[3]) - 1;
                return new PlayMessage(ip, new Position(x, y));
            }
            catch (NumberFormatException e) {} // Allow it to fall through
        }
        //else if (command.equals(Constants.HELP_COMMAND))
        //{
        //}
        //else
        return new HelpMessage(ip);
    }
}