package uk.ac.cam.kc506.fjava.xobot;

public class Tests
{
    public static String testAll()
    {
        String tmp;

        tmp = testBoard();
        if (!tmp.equals(""))
            return tmp;

        tmp = testGameManager();
        if (!tmp.equals(""))
            return tmp;

        tmp = testMessage();
        if (!tmp.equals(""))
            return tmp;

        return "";
    }

    public static String testMessage()
    {
        {
            Message m = Message.parse("/127.0.0.1:xo help");
            if (!(m instanceof HelpMessage))
                return "M1";
        }
        {
            Message msg = Message.parse("/a:xo help");
            GameManager m = new GameManager();
            for (String s : msg.getResponse(m))
                System.out.println(s);
        }
        {
            Message msg = Message.parse("/a:xo start b");
            GameManager m = new GameManager();
            for (String s : msg.getResponse(m))
                System.out.println(s);
            if (!m.hasGame("a") || !m.hasGame("b") || m.getGame("a") != m.getGame("b"))
                return "M3a";
            
            msg = Message.parse("/b:xo 1 1");
            if (!(msg instanceof PlayMessage))
                return "M3b";
            for (String s : msg.getResponse(m))
                System.out.println(s);

            msg = Message.parse("/a:xo 1 1");
            if (!(msg instanceof PlayMessage))
                return "M3c";
            for (String s : msg.getResponse(m))
                System.out.println(s);

            msg = Message.parse("/b:xo 1 2");
            if (!(msg instanceof PlayMessage))
                return "M3d";
            for (String s : msg.getResponse(m))
                System.out.println(s);
        }
        return "";
    }

    public static String testBoard()
    {
        try
        {
            {
                Board b = new Board();
                b.setCell(new Position(0, 0), Player.X);
                b.setCell(new Position(1, 0), Player.X);
                b.setCell(new Position(2, 0), Player.X);
                if (b.won() != Player.X)
                    return "Board1";
            }
            {
                Board b = new Board();
                b.setCell(new Position(0, 0), Player.O);
                b.setCell(new Position(1, 1), Player.O);
                b.setCell(new Position(2, 2), Player.O);
                b.setCell(new Position(0, 1), Player.X);
                b.setCell(new Position(2, 1), Player.X);
                if (b.won() != Player.O)
                    return "Board2";
            }

            return "";
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
    }

    public static String testGameManager()
    {
        {
            GameManager m = new GameManager();
            m.addGame("a", "b");
            if (!m.hasGame("a") || !m.hasGame("b") || m.getGame("a") != m.getGame("b"))
                return "GM1";
        }
        {
            GameManager m = new GameManager();
            m.addGame("a", "b");
            m.removeGame("a");
            if (m.hasGame("a") || m.hasGame("b"))
                return "GM2";
        }
        return "";
    }
}