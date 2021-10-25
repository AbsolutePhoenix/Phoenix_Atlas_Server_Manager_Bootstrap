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

import games.absolutephoenix.phoenixatlasservermanagerbootstrap.config.IniConfig;
import games.absolutephoenix.phoenixatlasservermanagerbootstrap.json.GitHubAuthJson;
import games.absolutephoenix.phoenixatlasservermanagerbootstrap.reference.BootstrapSettings;
import games.absolutephoenix.phoenixatlasservermanagerbootstrap.userinterface.AuthFrame;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GithubAPI {
    public static void authenticate() throws IOException {
        BootstrapSettings.AccessToken = "";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://github.com/login/device/code");
        httpPost.addHeader("Accept", "application/json");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("client_id", "e3d37da7ecebe3b8b89c"));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse response = httpClient.execute(httpPost);
        String answer = EntityUtils.toString(response.getEntity());
        GitHubAuthJson gitHubAuthJson = new GitHubAuthJson(answer);
        response.close();
        httpClient.close();
        AuthFrame authFrame = new AuthFrame(gitHubAuthJson.user_code, gitHubAuthJson.verification_uri);

        boolean resume = false;
        long timer = System.currentTimeMillis();
        long totalTimer = System.currentTimeMillis();

        while (!resume)
        {
            if (System.currentTimeMillis() - timer > ((long)gitHubAuthJson.interval * 1000) + 500)
            {
                timer += ((long)gitHubAuthJson.interval * 1000) + 500;
                checkAuth(gitHubAuthJson.device_code);
                if(!BootstrapSettings.AccessToken.equals("")) {
                    authFrame.dispose();
                    resume = true;
                }
            }

            if (System.currentTimeMillis() - totalTimer > ((long)gitHubAuthJson.expires_in * 1000) + 500)
            {
                System.out.println(System.currentTimeMillis() - totalTimer);
                System.out.println(gitHubAuthJson.expires_in * 1000);
                System.out.println("Timer Expired");
                System.exit(0);
            }
        }
    }
    private static void checkAuth(String device_code) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://github.com/login/oauth/access_token");
        httpPost.addHeader("Accept", "application/json");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("client_id", "e3d37da7ecebe3b8b89c"));
        params.add(new BasicNameValuePair("device_code", device_code));
        params.add(new BasicNameValuePair("grant_type", "urn:ietf:params:oauth:grant-type:device_code"));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String answer = EntityUtils.toString(response.getEntity());
        response.close();
        httpClient.close();

        if(!answer.contains("authorization_pending")) {
            BootstrapSettings.AccessToken = new JSONObject(answer).getString("access_token");
            IniConfig.SaveConfig();
        }

    }
    public static boolean checkCredentials() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://api.github.com/rate_limit");
        httpGet.addHeader("Content-Type", "application/json");
        httpGet.addHeader("Authorization", "token " + BootstrapSettings.AccessToken);
        CloseableHttpResponse result = httpClient.execute(httpGet);
        String answer = EntityUtils.toString(result.getEntity());
        result.close();
        httpClient.close();
        if(answer.contains("Bad credentials"))
            return false;
        else
            return true;

    }
    public static String getLatest() {
        String json = null;
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet("https://api.github.com/repos/" + BootstrapSettings.ProjectAuthor + "/" + BootstrapSettings.ProjectName + "/releases");
            httpGet.addHeader("content-type", "application/json");
            httpGet.addHeader("Authorization", "token " + BootstrapSettings.AccessToken);
            CloseableHttpResponse result = httpClient.execute(httpGet);
            json = EntityUtils.toString(result.getEntity());
            result.close();
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
