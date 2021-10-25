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

import games.absolutephoenix.phoenixatlasservermanagerbootstrap.utils.GithubAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ReleaseJson {

    public Release release;
    public class Release {

        public Release.Author author;
        public Release.Assets assets;

        public String url;
        public String assets_url;
        public String upload_url;
        public String html_url;
        public int id;
        public String node_id;
        public String tag_name;
        public String target_commitish;
        public String name;
        public boolean draft;
        public boolean prerelease;
        public String created_at;
        public String published_at;
        public String tarball_url;
        public String zipball_url;
        public String body;
        public class Author {
            public String login;
            public int id;
            public String node_id;
            public String avatar_url;
            public String gravatar_id;
            public String url;
            public String html_url;
            public String followers_url;
            public String following_url;
            public String gists_url;
            public String starred_url;
            public String subscriptions_url;
            public String organizations_url;
            public String repos_url;
            public String events_url;
            public String received_events_url;
            public String type;
            public boolean site_admin;
        }
        public class Assets {
            public Release.Assets.Uploader uploader;

            public List<String> url = new ArrayList<>();
            public List<Integer> id = new ArrayList<>();
            public List<String> node_id = new ArrayList<>();
            public List<String> name = new ArrayList<>();
            public List<String> label = new ArrayList<>();
            public List<String> content_type = new ArrayList<>();
            public List<String> state = new ArrayList<>();
            public List<Integer> size = new ArrayList<>();
            public List<Integer> download_count = new ArrayList<>();
            public List<String> created_at = new ArrayList<>();
            public List<String> updated_at = new ArrayList<>();
            public List<String> browser_download_url = new ArrayList<>();

            public class Uploader {
                public List<String> login = new ArrayList<>();
                public List<Integer> id = new ArrayList<>();
                public List<String> node_id = new ArrayList<>();
                public List<String> avatar_url = new ArrayList<>();
                public List<String> gravatar_id = new ArrayList<>();
                public List<String> url = new ArrayList<>();
                public List<String> html_url = new ArrayList<>();
                public List<String> followers_url = new ArrayList<>();
                public List<String> following_url = new ArrayList<>();
                public List<String> gists_url = new ArrayList<>();
                public List<String> starred_url = new ArrayList<>();
                public List<String> subscriptions_url = new ArrayList<>();
                public List<String> organizations_url = new ArrayList<>();
                public List<String> repos_url = new ArrayList<>();
                public List<String> events_url = new ArrayList<>();
                public List<String> received_events_url = new ArrayList<>();
                public List<String> type = new ArrayList<>();
                public List<Boolean> site_admin = new ArrayList<>();
            }
        }
    }

    public ReleaseJson(JSONObject releaseString){
        JSONObject latestJson = releaseString;

        release = new Release();
        release.author = release.new Author();
        release.assets = release.new Assets();
        release.assets.uploader = release.assets.new Uploader();

        release.url = latestJson.getString("url");
        release.assets_url = latestJson.getString("assets_url");
        release.upload_url = latestJson.getString("upload_url");
        release.html_url = latestJson.getString("html_url");
        release.id = latestJson.getInt("id");
        release.node_id = latestJson.getString("node_id");
        release.tag_name = latestJson.getString("tag_name");
        release.target_commitish = latestJson.getString("target_commitish");
        release.name = latestJson.getString("name");
        release.draft = latestJson.getBoolean("draft");
        release.prerelease = latestJson.getBoolean("prerelease");
        release.created_at = latestJson.getString("created_at");
        release.published_at = latestJson.getString("published_at");
        release.tarball_url = latestJson.getString("tarball_url");
        release.zipball_url = latestJson.getString("zipball_url");
        try {
            release.body = latestJson.getString("body");
        }catch (JSONException e)
        {
            release.body = "";
        }
        JSONObject authorJson = latestJson.getJSONObject("author");
        release.author.login = authorJson.getString("login");
        release.author.id = authorJson.getInt("id");
        release.author.node_id = authorJson.getString("node_id");
        release.author.avatar_url = authorJson.getString("avatar_url");
        release.author.gravatar_id = authorJson.getString("gravatar_id");
        release.author.url = authorJson.getString("url");
        release.author.html_url = authorJson.getString("html_url");
        release.author.followers_url = authorJson.getString("followers_url");
        release.author.following_url = authorJson.getString("following_url");
        release.author.gists_url = authorJson.getString("gists_url");
        release.author.starred_url = authorJson.getString("starred_url");
        release.author.subscriptions_url = authorJson.getString("subscriptions_url");
        release.author.organizations_url = authorJson.getString("organizations_url");
        release.author.repos_url = authorJson.getString("repos_url");
        release.author.events_url = authorJson.getString("events_url");
        release.author.received_events_url = authorJson.getString("received_events_url");
        release.author.type = authorJson.getString("type");
        release.author.site_admin = authorJson.getBoolean("site_admin");

        JSONArray assetsJsonArray = latestJson.getJSONArray("assets");

        release.assets.url.clear();
        release.assets.id.clear();
        release.assets.node_id.clear();
        release.assets.name.clear();
        release.assets.label.clear();
        release.assets.content_type.clear();
        release.assets.state.clear();
        release.assets.size.clear();
        release.assets.download_count.clear();
        release.assets.created_at.clear();
        release.assets.updated_at.clear();
        release.assets.browser_download_url.clear();
        release.assets.uploader.login.clear();
        release.assets.uploader.id.clear();
        release.assets.uploader.node_id.clear();
        release.assets.uploader.avatar_url.clear();
        release.assets.uploader.gravatar_id.clear();
        release.assets.uploader.url.clear();
        release.assets.uploader.html_url.clear();
        release.assets.uploader.followers_url.clear();
        release.assets.uploader.following_url.clear();
        release.assets.uploader.gists_url.clear();
        release.assets.uploader.starred_url.clear();
        release.assets.uploader.subscriptions_url.clear();
        release.assets.uploader.organizations_url.clear();
        release.assets.uploader.repos_url.clear();
        release.assets.uploader.events_url.clear();
        release.assets.uploader.received_events_url.clear();
        release.assets.uploader.type.clear();
        release.assets.uploader.site_admin.clear();

        for (int x = 0; x < assetsJsonArray.length(); x++) {
            JSONObject assetsJson = assetsJsonArray.getJSONObject(x);
            release.assets.url.add(assetsJson.getString("url"));
            release.assets.id.add(assetsJson.getInt("id"));
            release.assets.node_id.add(assetsJson.getString("node_id"));
            release.assets.name.add(assetsJson.getString("name"));
            try {
                release.assets.label.add(assetsJson.getString("label"));
            }catch (JSONException e){
                release.assets.label.add("");
            }
            release.assets.content_type.add(assetsJson.getString("content_type"));
            release.assets.state.add(assetsJson.getString("state"));
            release.assets.size.add(assetsJson.getInt("size"));
            release.assets.download_count.add(assetsJson.getInt("download_count"));
            release.assets.created_at.add(assetsJson.getString("created_at"));
            release.assets.updated_at.add(assetsJson.getString("updated_at"));
            release.assets.browser_download_url.add(assetsJson.getString("browser_download_url"));

            JSONObject uploaderJson = assetsJson.getJSONObject("uploader");

            release.assets.uploader.login.add(uploaderJson.getString("login"));
            release.assets.uploader.id.add(uploaderJson.getInt("id"));
            release.assets.uploader.node_id.add(uploaderJson.getString("node_id"));
            release.assets.uploader.avatar_url.add(uploaderJson.getString("avatar_url"));
            release.assets.uploader.gravatar_id.add(uploaderJson.getString("gravatar_id"));
            release.assets.uploader.url.add(uploaderJson.getString("url"));
            release.assets.uploader.html_url.add(uploaderJson.getString("html_url"));
            release.assets.uploader.followers_url.add(uploaderJson.getString("followers_url"));
            release.assets.uploader.following_url.add(uploaderJson.getString("following_url"));
            release.assets.uploader.gists_url.add(uploaderJson.getString("gists_url"));
            release.assets.uploader.starred_url.add(uploaderJson.getString("starred_url"));
            release.assets.uploader.subscriptions_url.add(uploaderJson.getString("subscriptions_url"));
            release.assets.uploader.organizations_url.add(uploaderJson.getString("organizations_url"));
            release.assets.uploader.repos_url.add(uploaderJson.getString("repos_url"));
            release.assets.uploader.events_url.add(uploaderJson.getString("events_url"));
            release.assets.uploader.received_events_url.add(uploaderJson.getString("received_events_url"));
            release.assets.uploader.type.add(uploaderJson.getString("type"));
            release.assets.uploader.site_admin.add(uploaderJson.getBoolean("site_admin"));
        }

    }
    public static List<ReleaseJson> ReleasesList()
    {
        List<ReleaseJson> releases = new ArrayList<>();
        JSONArray releaseArray = new JSONArray(GithubAPI.getLatest());
        for (int x = 0; x < releaseArray.length(); x++)
            releases.add(new ReleaseJson(releaseArray.getJSONObject(x)));

        return releases;
    }
}
