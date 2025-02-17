package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Config {
    GamePanel gp;
    public Config(GamePanel gp)
    {
        this.gp = gp;
    }

    public void saveConfig()
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            // Full Screen
            if(gp.fullScreenOn == true)
            {
                bw.write("On");
            }
            if(gp.fullScreenOn == false)
            {
                bw.write("Off");
            }
            bw.newLine();

            //Music Volume
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            //SE Volume
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();
            if (s == null || s.trim().isEmpty()) {
                System.out.println("❌ ERROR: Config file is missing full screen setting!");
                s = "Off"; // Default to Off
            }

            //Full Screen
            gp.fullScreenOn = s.equals("On");

            //Music Volume
            s = br.readLine();
            if (s == null || s.trim().isEmpty()) {
                System.out.println("❌ ERROR: Config file is missing music volume!");
                s = "5"; // Default volume
            }
            gp.music.volumeScale = Integer.parseInt(s);

            //SE Volume
            s = br.readLine();
            if (s == null || s.trim().isEmpty()) {
                System.out.println("❌ ERROR: Config file is missing SE volume!");
                s = "5"; // Default volume
            }
            gp.se.volumeScale = Integer.parseInt(s);

            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("❌ ERROR: Config file not found! Creating a new one.");
            saveConfig(); // Create default config
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
