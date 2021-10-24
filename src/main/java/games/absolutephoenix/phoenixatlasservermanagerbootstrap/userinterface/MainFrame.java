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
import games.absolutephoenix.phoenixatlasservermanagerbootstrap.reference.BootstrapSettings;
import sun.security.krb5.internal.crypto.Des;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private final Font TitleFont = new Font("Dialog", Font.BOLD, 24);
    private final Font StandardFont = new Font("Dialog", Font.PLAIN, 12);
    private final JLabel TitleLabel = new JLabel(BootstrapSettings.ProjectName.replace("_", " ").replace(".", " "));
    private final JLabel VersionLabel = new JLabel("Version Select:");
    private final JComboBox<Object> VersionSelect;
    private final JLabel IDInfoLabel = new JLabel("Release ID:");
    private final JLabel IDLabel = new JLabel("51902996");
    private final JLabel AuthorInfoLabel = new JLabel("Uploaded By:");
    private final JLabel AuthorLabel = new JLabel("AbsolutePhoenix");
    private final JLabel DescriptionInfoLabel = new JLabel("Version Description:");
    private final JTextArea DescriptionLable = new JTextArea("testing, the quick brown fox jumped over the lazy dog. the quick brown fox jumped over the lazy dog. the quick brown fox jumped over the lazy dog.");

    public MainFrame() {
        List<String> versions = new ArrayList<>();
        for(int x = 0; x < PhoenixAtlasServerManagerBootstrap.releases.size(); x++)
            versions.add(PhoenixAtlasServerManagerBootstrap.releases.get(x).release.name);
        VersionSelect = new JComboBox<>(versions.toArray());
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
        DescriptionLable.setEditable(false);
        DescriptionLable.setEnabled(false);
        DescriptionLable.setLineWrap(true);
        DescriptionLable.setWrapStyleWord(true);
        DescriptionLable.setBackground(new Color(0, 0, 0, 0));
        DescriptionLable.setForeground(Color.white);
        DescriptionLable.setBounds(DescriptionInfoLabel.getWidth() + 10, DescriptionInfoLabel.getY(), VersionSelect.getWidth(), 200);
        add(DescriptionLable);

    }
}
