package games.absolutephoenix.phoenixatlasservermanagerbootstrap.json;

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

import org.json.JSONObject;

public class GitHubAuthJson {
    public String device_code;
    public String user_code;
    public String verification_uri;
    public int expires_in;
    public int interval;

    public GitHubAuthJson(String json)
    {
        JSONObject jsonObject = new JSONObject(json);
        device_code = jsonObject.getString("device_code");
        user_code = jsonObject.getString("user_code");
        verification_uri = jsonObject.getString("verification_uri");
        expires_in = jsonObject.getInt("expires_in");
        interval = jsonObject.getInt("interval");
    }
}
