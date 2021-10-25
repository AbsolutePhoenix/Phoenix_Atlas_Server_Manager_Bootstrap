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

import games.absolutephoenix.phoenixatlasservermanagerbootstrap.reference.BootstrapSettings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class AuthFrame extends JFrame {

    public AuthFrame(String accessCode, String accessUrl) {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Github Authentication");
        try {setIconImage(ImageIO.read(new URL(BootstrapSettings.LogoPath)));} catch (IOException e) {e.printStackTrace();}
        setSize(new Dimension(500, 300));
        setLocationRelativeTo(null);
        setLayout(null);
        JLabel githubLogo = null;
        try {githubLogo = new JLabel(new ImageIcon(ImageIO.read(new URL("https://github.githubassets.com/images/modules/logos_page/Octocat.png")).getScaledInstance(75, 75, Image.SCALE_SMOOTH)));} catch (IOException e) {e.printStackTrace();}
        assert githubLogo != null;
        githubLogo.setBounds(10, 10, 75, 75);
        add(githubLogo);
        JLabel title = new JLabel("Github Authentication");
        title.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        title.setBounds(githubLogo.getX() + githubLogo.getWidth(), 10, 405, 25);
        add(title);
        JLabel notes = new JLabel("<html>This utility requires GitHub authentication to download the program you are trying to use. Click the button below to access the webpage and enter the displayed code. Once Authenticated this window will close itself.</html>");
        notes.setBounds(title.getX(), title.getHeight() + 15, 390, 60);
        add(notes);
        JLabel code = new JLabel(accessCode);
        code.setFont(new Font(Font.DIALOG, Font.BOLD, 72));
        code.setBorder(BorderFactory.createBevelBorder(1));
        code.setHorizontalAlignment(SwingConstants.CENTER);
        code.setBounds(10, 110, 465, 72);
        add(code);
        JButton button = new JButton("Access Authentication Website");
        button.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        button.setFocusable(false);
        button.setBounds(code.getX(), code.getHeight() + code.getY() + 10, code.getWidth(), code.getHeight() - 20);
        button.addActionListener(e -> {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(accessUrl));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(button);
        setVisible(true);

    }
}
