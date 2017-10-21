package uk.ac.cam.kc506.fjava.xobot;

public class InvalidPosition extends Exception
{
    public Position position;

    public InvalidPosition(Position p)
    {
        this.position = p;
    }

    @Override
    public String getMessage()
    {
        return "Invalid position " + position.toString() + ".";
    }
}
