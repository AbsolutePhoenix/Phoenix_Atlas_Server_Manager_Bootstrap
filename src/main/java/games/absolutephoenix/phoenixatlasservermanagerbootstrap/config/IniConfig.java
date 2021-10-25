package games.absolutephoenix.phoenixatlasservermanagerbootstrap.config;

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

import games.absolutephoenix.phoenixatlasservermanagerbootstrap.reference.BootstrapSettings;
import org.ini4j.Config;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

public class IniConfig {
    public static void SaveConfig() throws IOException {
        if(!new File("ManagerConfig/Bootstrap.ini").exists()) {
            //noinspection ResultOfMethodCallIgnored
            new File("ManagerConfig/").mkdir();
            //noinspection ResultOfMethodCallIgnored
            new File("ManagerConfig/Bootstrap.ini").createNewFile();
        }

        Wini ini = new Wini();
        Config config = new Config();
        config.setMultiSection(true);
        config.setMultiOption(true);
        ini.setConfig(config);
        ini.add("General", "FrameName", BootstrapSettings.FrameName);
        ini.add("General", "DarkMode", BootstrapSettings.DarkMode);
        ini.add("General", "ProjectAuthor", BootstrapSettings.ProjectAuthor);
        ini.add("General", "ProjectName", BootstrapSettings.ProjectName);
        ini.add("General", "LogoPath", BootstrapSettings.LogoPath);
        ini.add("General", "BypassAndUpdate", BootstrapSettings.BypassAndUpdate);
        ini.add("GitHub", "AccessToken", BootstrapSettings.AccessToken);
        ini.add("Executable", "ExecutableName", BootstrapSettings.ExecutableName);
        ini.add("Executable", "CMDLaunchArguments", BootstrapSettings.CMDLaunchArguments);
        ini.add("Executable", "ProgramLaunchArguments", BootstrapSettings.ProgramLaunchArguments);

        ini.store(new File("ManagerConfig/Bootstrap.ini"));
    }

    public static void LoadConfig() throws IOException {

        if(!new File("ManagerConfig/Bootstrap.ini").exists())
            SaveConfig();

        Wini ini = new Wini();
        Config config = new Config();
        config.setMultiSection(true);
        config.setMultiOption(true);
        config.setComment(true);
        ini.setConfig(config);
        ini.load(new File("ManagerConfig/Bootstrap.ini"));

        BootstrapSettings.FrameName = ini.get("General", "FrameName");
        BootstrapSettings.DarkMode = ini.get("General", "DarkMode", boolean.class);
        BootstrapSettings.ProjectAuthor = ini.get("General", "ProjectAuthor");
        BootstrapSettings.ProjectName = ini.get("General", "ProjectName");
        BootstrapSettings.LogoPath = ini.get("General", "LogoPath");
        BootstrapSettings.BypassAndUpdate = ini.get("General", "BypassAndUpdate", boolean.class);
        BootstrapSettings.AccessToken = ini.get("GitHub", "AccessToken");
        BootstrapSettings.ExecutableName = ini.get("Executable", "ExecutableName");
        BootstrapSettings.CMDLaunchArguments = ini.get("Executable", "CMDLaunchArguments");
        BootstrapSettings.ProgramLaunchArguments = ini.get("Executable", "ProgramLaunchArguments");
    }
}
