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
import games.absolutephoenix.phoenixatlasservermanagerbootstrap.utils.GithubAPI;
import games.absolutephoenix.phoenixatlasservermanagerbootstrap.utils.VersionCheck;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class PhoenixAtlasServerManagerBootstrap {
    public static List<ReleaseJson> releases;

    public static void main(String[] args) {

        try {
            IniConfig.LoadConfig();
            if(BootstrapSettings.DarkMode) {
                BasicLookAndFeel darcula = new DarculaLaf();
                UIManager.setLookAndFeel(darcula);
            }

            if(!GithubAPI.checkCredentials()) {
                GithubAPI.authenticate();
            }
            releases = ReleaseJson.ReleasesList();
            if (BootstrapSettings.BypassAndUpdate)
            {
                if(VersionCheck.update(PhoenixAtlasServerManagerBootstrap.releases.get(0).release.id)) {{
                    for (int x = 0; x < PhoenixAtlasServerManagerBootstrap.releases.get(0).release.assets.browser_download_url.size(); x++) {
                        try {
                            if(PhoenixAtlasServerManagerBootstrap.releases.get(0).release.assets.name.get(x).endsWith(".jar"))
                                FileUtils.copyURLToFile(
                                        new URL(PhoenixAtlasServerManagerBootstrap.releases.get(0).release.assets.browser_download_url.get(x)),
                                        new File("bin/program/program.jar")
                                );
                            else if(PhoenixAtlasServerManagerBootstrap.releases.get(0).release.assets.name.get(x).endsWith(".exe"))
                                FileUtils.copyURLToFile(
                                        new URL(PhoenixAtlasServerManagerBootstrap.releases.get(0).release.assets.browser_download_url.get(x)),
                                        new File("bin/program/program.exe")
                                );
                            else
                                FileUtils.copyURLToFile(
                                        new URL(PhoenixAtlasServerManagerBootstrap.releases.get(0).release.assets.browser_download_url.get(x)),
                                        new File(PhoenixAtlasServerManagerBootstrap.releases.get(0).release.assets.name.get(x))
                                );
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                    try {
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("ManagerVersion.txt", false));
                        bufferedWriter.write(PhoenixAtlasServerManagerBootstrap.releases.get(0).release.id + "");
                        bufferedWriter.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    Process process = Runtime.getRuntime().exec("cmd.exe /c start /wait " + BootstrapSettings.CMDLaunchArguments + " " + BootstrapSettings.ExecutableName + " " + BootstrapSettings.ProgramLaunchArguments);
                    process.waitFor();
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }else
            {
                new MainFrame();
            }

        } catch (IOException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }



    }

}
