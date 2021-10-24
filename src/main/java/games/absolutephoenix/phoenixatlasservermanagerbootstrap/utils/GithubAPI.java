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

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GithubAPI {
    public static String getLatest(String url) {
        String json = null;
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            json = EntityUtils.toString(result.getEntity());
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
