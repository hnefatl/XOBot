package uk.ac.cam.kc506.fjava.xobot;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class XOBot
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println(Tests.testAll());
            return;
        }
        else if (args.length != 2)
        {
            System.out.println("Arguments: <ip> <port>");
            return;
        }

        try
        {
            String ip = args[0];
            int port = Integer.parseInt(args[1]);

            GameManager gameManager = new GameManager();

            while (true)
            {
                try
                {
                    try (Socket conn = new Socket(ip, port))
                    {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

                        writer.write("XOBot started. Type \"xo help\" for usage information.");
                        writer.flush();

                        while (true)
                        {
                            String input = reader.readLine();
                            System.out.println(input);
                            Message msg = Message.parse(input);
                            if (msg == null)
                                continue;                    

                            List<String> response = msg.getResponse(gameManager);
                            if (response == null)
                                continue;

                            StringBuilder sb = new StringBuilder();
                            sb.append("_\n");
                            for (String s : response)
                                sb.append(s + "\n");
                            writer.write(sb.toString());
                            writer.flush();
                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (NumberFormatException e)
        {
            System.err.println("Invalid port.");
            e.printStackTrace();
        }
    }
}