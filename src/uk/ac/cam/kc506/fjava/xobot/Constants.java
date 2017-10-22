package uk.ac.cam.kc506.fjava.xobot;

public class Constants
{
    public static final String BOT_INVOKE = "xo";
    public static final String START_COMMAND = "start";
    public static final String STOP_COMMAND = "stop";
    public static final String HELP_COMMAND = "help";

    public static final String UNKNOWN_COMMAND = "Unknown command. Use \"" + BOT_INVOKE + " " + HELP_COMMAND + "\" for help.";
    public static final String HELP_MESSAGE = "Usage: " + BOT_INVOKE + " ("+START_COMMAND+" <opponent ip>|"+STOP_COMMAND+"|"+HELP_COMMAND+"|<position to play>)";
}
