package uk.ac.cam.kc506.fjava.xobot;

import java.util.List;
import java.util.ArrayList;

public class Board
{
    public final int SIZE = 3;

    // Each cell is a player or null
    private Player[][] board;

    public Board()
    {
        // Initialise board cells to null
        board = new Player[SIZE][SIZE];
    }

    public Player getCell(Position p) throws InvalidPosition
    {
        if (p.x >= 0 && p.x < SIZE && p.y >= 0 && p.y < SIZE)
            return board[p.y][p.x];
        else
            throw new InvalidPosition(p);
    }
    public void setCell(Position p, Player v) throws InvalidPosition
    {
        if (p.x >= 0 && p.x < SIZE && p.y >= 0 && p.y < SIZE)
            board[p.y][p.x] = v;
        else
            throw new InvalidPosition(p);
    }

    public Player won()
    {
        if (won(Player.X))
            return Player.X;
        else if (won(Player.O))
            return Player.O;
        else
            return null;
    }
    public boolean won(Player p)
    {
        // Check rows
        for (int y = 0; y < SIZE; ++y)
        {
            boolean won = true;
            for (int x = 0; x < SIZE; ++x)
            {
                if (board[y][x] != p)
                    won = false;
            }
            if (won)
                return true;
        }
        // Check columns
        for (int x = 0; x < SIZE; ++x)
        {
            boolean won = true;
            for (int y = 0; y < SIZE; ++y)
            {
                if (board[y][x] != p)
                    won = false;
            }
            if (won)
                return true;
        }
        // Check diagonals
        boolean won = true;
        for (int t = 0; t < SIZE; ++t)
        {
            if (board[t][t] != p)
                won = false;
        }
        if (won)
            return true;
        for (int t = 0; t < SIZE; ++t)
        {
            if (board[SIZE - 1 - t][t] != p)
                won = false;
        }
        if (won)
            return true;

        return false;
    }

    public List<String> toRows()
    {
        List<String> results = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();

        // Top number row
        sb.append(" ");
        for (int x = 0; x < SIZE; ++x)
            sb.append(x + 1);
        results.add(sb.toString());
        sb = new StringBuilder();

        // Content rows
        for (int y = 0; y < SIZE; ++y)
        {
            sb.append(y + 1);
            for (int x = 0; x < SIZE; ++x)
            {
                if (board[y][x] == Player.X)
                    sb.append("X");
                else if (board[y][x] == Player.O)
                    sb.append("O");
                else
                    sb.append(" ");
            }   
            results.add(sb.toString());
            sb = new StringBuilder();
        }
        
        return results;
    }
}