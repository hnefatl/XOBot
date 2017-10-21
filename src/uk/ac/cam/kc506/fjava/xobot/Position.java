package uk.ac.cam.kc506.fjava.xobot;

public class Position
{
    public int x, y;

    public Position()
    {
        this.x = 0;
        this.y = 0;
    }
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}