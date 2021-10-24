package games.absolutephoenix.phoenixatlasservermanagerbootstrap;

/*
The MIT License (MIT)

Copyright (c) 2021 Absolute_Phoenix

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
documentation files (the "Software"), to deal in the Software without restriction, including without limitation the 
rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit 
persons to whom the Software is furnished to do so, subject to the following conditions:
1: All products using this code may not make revenue.
2: Any project using this project or significant portions of this project must give credit to the original copyright holder.
*/

import com.bulenkov.darcula.DarculaLaf;
import games.absolutephoenix.phoenixatlasservermanagerbootstrap.config.IniConfig;
import games.absolutephoenix.phoenixatlasservermanagerbootstrap.json.ReleaseJson;
import games.absolutephoenix.phoenixatlasservermanagerbootstrap.reference.BootstrapSettings;
import games.absolutephoenix.phoenixatlasservermanagerbootstrap.userinterface.MainFrame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.io.IOException;
import java.util.List;

public class PhoenixAtlasServerManagerBootstrap {
    public static List<ReleaseJson> releases;

    public static void main(String[] args) {
        try {
            IniConfig.LoadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }

        releases = ReleaseJson.ReleasesList("https://api.github.com/repos/" + BootstrapSettings.ProjectAuthor + "/" + BootstrapSettings.ProjectName + "/releases");
        for(int x= 0; x < releases.size(); x++)
            System.out.println(releases.get(x).release.name);

        if(BootstrapSettings.DarkMode)
        {
            BasicLookAndFeel darcula = new DarculaLaf();
            try {
                UIManager.setLookAndFeel(darcula);
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        }

        MainFrame mainFrame = new MainFrame();

    }

}
