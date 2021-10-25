package games.absolutephoenix.phoenixatlasservermanagerbootstrap.userinterface;

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

import games.absolutephoenix.phoenixatlasservermanagerbootstrap.PhoenixAtlasServerManagerBootstrap;
import games.absolutephoenix.phoenixatlasservermanagerbootstrap.config.IniConfig;
import games.absolutephoenix.phoenixatlasservermanagerbootstrap.reference.BootstrapSettings;
import games.absolutephoenix.phoenixatlasservermanagerbootstrap.utils.VersionCheck;
import org.apache.commons.io.FileUtils;
import org.ini4j.Ini;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFrame extends JFrame {
    private final Font TitleFont = new Font("Dialog", Font.BOLD, 24);
    private final JLabel TitleLabel = new JLabel(BootstrapSettings.ProjectName.replace("_", " ").replace(".", " "));
    private final JLabel VersionLabel = new JLabel("Version Select:");
    private final JComboBox<Object> VersionSelect;
    private final JLabel IDInfoLabel = new JLabel("Release ID:");
    private final JLabel IDLabel = new JLabel("");
    private final JLabel AuthorInfoLabel = new JLabel("Uploaded By:");
    private final JLabel AuthorLabel = new JLabel("");
    private final JLabel DescriptionInfoLabel = new JLabel("Version Description:");
    private final JLabel DescriptionLable = new JLabel("");
    private final JCheckBox AutoUpdate = new JCheckBox("Automatically update to most recent version and skip this screen", false);
    private final JButton LaunchButton = new JButton("Update and Launch Program.");

    public MainFrame() {
        List<String> versions = new ArrayList<>();
        for(int x = 0; x < PhoenixAtlasServerManagerBootstrap.releases.size(); x++)
            versions.add(PhoenixAtlasServerManagerBootstrap.releases.get(x).release.name);
        VersionSelect = new JComboBox<>(versions.toArray());
        IDLabel.setText(PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.id + "");
        AuthorLabel.setText(PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.assets.uploader.login.get(0));

        List<String> description = new ArrayList<>();
        description.add("<html>");
        String[] tmp = PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.body.split("\n");
        description.addAll(Arrays.asList(tmp));
        description.add("</html>");
        for(int x = 0; x < description.size(); x++) {
            description.set(x, description.get(x) + "<br>");
            if (description.get(x).startsWith("###"))
                description.set(x, description.get(x).replaceFirst("###", "<h2>").replace("<br>","</h2>"));
        }
        description.set(0, "<html>");
        StringBuilder descriptionOut = new StringBuilder();
        for (String s : description) descriptionOut.append(s);
        DescriptionLable.setText(descriptionOut.toString());
        AutoUpdate.setSelected(BootstrapSettings.BypassAndUpdate);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(BootstrapSettings.FrameName);
        try {setIconImage(ImageIO.read(new URL(BootstrapSettings.LogoPath)));} catch (IOException e) {e.printStackTrace();}
        setSize(new Dimension(500, 500));
        setPreferredSize(new Dimension(500, 500));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        BuildFrame();
        ActionListener();
    }
    private void BuildFrame() {
        TitleLabel.setFont(TitleFont);
        TitleLabel.setVerticalAlignment(SwingConstants.CENTER);
        TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        TitleLabel.setBorder(BorderFactory.createBevelBorder(1));
        TitleLabel.setBounds(0, 0, getContentPane().getWidth(), 45);
        add(TitleLabel);

        VersionLabel.setBounds(10, TitleLabel.getHeight() + 10, 150, 25);
        add(VersionLabel);
        VersionSelect.setBounds(VersionLabel.getWidth() + 10, VersionLabel.getY(), 500 - (VersionLabel.getWidth() + 35), 25);
        VersionSelect.setFocusable(false);
        add(VersionSelect);

        IDInfoLabel.setBounds(10, VersionLabel.getHeight() + VersionLabel.getY() + 10, VersionLabel.getWidth(), VersionLabel.getHeight());
        add(IDInfoLabel);
        IDLabel.setBounds(IDInfoLabel.getWidth() + 10, IDInfoLabel.getY(), VersionSelect.getWidth(), VersionSelect.getHeight());
        add(IDLabel);

        AuthorInfoLabel.setBounds(10, IDInfoLabel.getHeight() + IDInfoLabel.getY() + 10, VersionLabel.getWidth(), VersionLabel.getHeight());
        add(AuthorInfoLabel);
        AuthorLabel.setBounds(AuthorInfoLabel.getWidth() + 10, AuthorInfoLabel.getY(), VersionSelect.getWidth(), VersionSelect.getHeight());
        add(AuthorLabel);

        DescriptionInfoLabel.setBounds(10, AuthorInfoLabel.getHeight() + AuthorInfoLabel.getY() + 10, VersionLabel.getWidth(), VersionLabel.getHeight());
        add(DescriptionInfoLabel);
        DescriptionLable.setVerticalAlignment(SwingConstants.TOP);
        DescriptionLable.setBounds(DescriptionInfoLabel.getWidth() + 10, DescriptionInfoLabel.getY() + 4, VersionSelect.getWidth(), 200);
        add(DescriptionLable);

        AutoUpdate.setBounds(10, DescriptionLable.getHeight() + DescriptionLable.getY() + 10, VersionLabel.getWidth() + VersionSelect.getWidth(), 25);
        AutoUpdate.setFocusable(false);
        add(AutoUpdate);

        LaunchButton.setBounds(10, AutoUpdate.getY() + AutoUpdate.getHeight() + 5, VersionLabel.getWidth() + VersionSelect.getWidth(), 45);
        LaunchButton.setFocusable(false);
        add(LaunchButton);
    }

    private void ActionListener()
    {
        VersionSelect.addActionListener(e -> {
            IDLabel.setText(PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.id + "");
            AuthorLabel.setText(PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.assets.uploader.login.get(0));

            List<String> description = new ArrayList<>();
            description.add("<html>");
            String[] tmp = PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.body.split("\n");
            description.addAll(Arrays.asList(tmp));
            description.add("</html>");
            for(int x = 0; x < description.size(); x++) {
                description.set(x, description.get(x) + "<br>");
                if (description.get(x).startsWith("###"))
                    description.set(x, description.get(x).replaceFirst("###", "<h2>").replace("<br>","</h2>"));
            }
            description.set(0, "<html>");
            StringBuilder descriptionOut = new StringBuilder();
            for (String s : description) descriptionOut.append(s);

            DescriptionLable.setText(descriptionOut.toString());
        });

        LaunchButton.addActionListener(e -> {
            BootstrapSettings.BypassAndUpdate = AutoUpdate.isSelected();
            try {
                IniConfig.SaveConfig();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(VersionCheck.update(PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.id)) {{
                    for (int x = 0; x < PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.assets.browser_download_url.size(); x++) {
                        try {
                            if(PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.assets.name.get(x).endsWith(".jar"))
                                FileUtils.copyURLToFile(
                                        new URL(PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.assets.browser_download_url.get(x)),
                                        new File("bin/program/program.jar")
                                );
                            else if(PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.assets.name.get(x).endsWith(".exe"))
                                FileUtils.copyURLToFile(
                                        new URL(PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.assets.browser_download_url.get(x)),
                                        new File("bin/program/program.exe")
                            );
                            else
                                FileUtils.copyURLToFile(
                                        new URL(PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.assets.browser_download_url.get(x)),
                                        new File(PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.assets.name.get(x))
                                );
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("ManagerVersion.txt", false));
                bufferedWriter.write(PhoenixAtlasServerManagerBootstrap.releases.get(VersionSelect.getSelectedIndex()).release.id + "");
                bufferedWriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            dispose();
            try {
                Process process = Runtime.getRuntime().exec("cmd.exe /c start /wait " + BootstrapSettings.CMDLaunchArguments + " " + BootstrapSettings.ExecutableName + " " + BootstrapSettings.ProgramLaunchArguments);
                process.waitFor();
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }
}
