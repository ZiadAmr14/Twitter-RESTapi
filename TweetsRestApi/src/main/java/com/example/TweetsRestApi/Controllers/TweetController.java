package com.example.TweetsRestApi.Controllers;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tweet")
public class TweetController{


    private JSONArray initialize() throws IOException, JSONException {
        JSONArray containerized = new JSONArray();
        JSONObject obj = new JSONObject(convertToString());
        containerized = obj.getJSONArray("container");
        return containerized;
    }

    @GetMapping("listAll")
    public String listAll() throws IOException, JSONException {
        JSONArray res = initialize();
        return res.toString();
    }

    @GetMapping("getUsers")
    public List<String>  getUsers() throws IOException, JSONException {
        JSONArray containerized;
        List<String> users = new ArrayList<>();
        containerized = initialize();
        for (int i = 0, l = containerized.length(); i < l; i++)
        {
            String temp = containerized.getJSONObject(i).getString("user");
            users.add(temp);
        }
        return users;

    }

    @RequestMapping(value="/getUser/{sn}",method=RequestMethod.GET)
    public String getUser(@PathVariable String sn) throws IOException, JSONException {
        JSONArray containerized;
        JSONObject Output;
        containerized = initialize();
        List<String> temp = new ArrayList<>();
        for(int i=0;i<containerized.length();i++)
        {
            Output = containerized.getJSONObject(i).getJSONObject("user");
            if (Output.getString("screen_name").equals(sn))
                return Output.toString();
        }

        return "";

    }

    @RequestMapping(value="/getTweet/{id}",method=RequestMethod.GET)
    public String getTweet(@PathVariable int id) throws IOException, JSONException {
        JSONArray containerized;
        JSONObject Output;
        containerized = initialize();
        List<String> temp = new ArrayList<>();
        for(int i=0;i<containerized.length();i++)
        {
            Output = containerized.getJSONObject(i);
            if (Output.getInt("tid")==id)
                return Output.toString();
        }

        return "";

    }



    private static String convertToString() throws IOException {
        String str = "";
        try {
            InputStream inputStream = new FileInputStream("twitter.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String l = bufferedReader.readLine();
            StringBuilder stringBuilder = new StringBuilder();

            while (l != null) {
                stringBuilder.append(l).append("\n");
                l = bufferedReader.readLine();
            }

            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }









}
