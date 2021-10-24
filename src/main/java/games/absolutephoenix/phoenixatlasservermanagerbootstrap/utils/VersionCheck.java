package games.absolutephoenix.phoenixatlasservermanagerbootstrap.utils;

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

import java.io.*;

public class VersionCheck {
    public static boolean update(int releaseId)
    {
        String id = releaseId + "";
        boolean test = false;
        try {
            if (new File("ManagerVersion.txt").exists()) {
                BufferedReader reader = new BufferedReader(new FileReader("ManagerVersion.txt"));
                String checkID = reader.readLine();
                reader.close();
                if (id.equals(checkID)) {
                    System.out.println("Update Not Needed");
                } else {
                    test = true;
                    System.out.println("Update Needed");
                }
            } else {
                test = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return test;
    }
}
