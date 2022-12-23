package com.example.my_application.Controller;

import com.example.my_application.Model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class UserController {

    String url = "https://api.github.com/users/";
    String login ;
    Logger LOGGER = Logger.getLogger(UserController.class.getName());
    public UserController(String login) {
        this.login = login;
    }

    public User fromJsonToUser(){
        User user=new User(0,null,null,null,null);
        String url_user = url+login;
        try {
            String data = new UrlReader().execute(url_user).get();
            JSONObject user_json = new JSONObject(data);
            user.setId_user((int) user_json.get("id"));
            user.setAvatar_url((String) user_json.get("avatar_url"));
            user.setLogin((String) user_json.get("login"));
            user.setCreation_date((String) user_json.get("created_at"));
            user.setNb_follower((int) user_json.get("followers"));
            user.setNb_following((int) user_json.get("following"));
            if (!user_json.isNull("bio")){
                user.setBio((String) user_json.get("bio"));
            }
            else{
                user.setBio("empty bio");
            }
            String following_url = url_user+"/following";
            user.setFollowing_url("https://api.github.com/users/mohamedkammoun123/following");
            System.out.println("another methode de dbg with following url");
            user.setFollowing_url(following_url);
            user.setFollower_url((String) user_json.get("followers_url"));


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (user.getId_user() == 0){
            return null;
        }
        return user;
    }
    public User [] getFollower(String url_follower){
        try {
            String data = new UrlReader().execute(url_follower).get();
            LOGGER.info("start to get list json from this url = "+url_follower);
            JSONArray followerJsonList = new JSONArray(data);
            User [] followers_users = new User[followerJsonList.length()];
            for (int index_json =0;index_json<followerJsonList.length();index_json++){
                User user=new User(0,null,null,null,null);
                JSONObject user_json = followerJsonList.getJSONObject(index_json);
                LOGGER.warning("the user json is ");
                LOGGER.info(user_json.toString());
                user.setId_user((int) user_json.get("id"));
                user.setAvatar_url((String) user_json.get("avatar_url"));
                user.setLogin((String) user_json.get("login"));
                LOGGER.info("\u001B[32m" + "Get user nb = "+index_json+" = " + "\u001B[0m");
                LOGGER.info("\u001B[32m" + user + "\u001B[0m");
                followers_users[index_json] = user;
            }
            LOGGER.info("The final result of follower list of this user is =");
            for(int i=0;i<followerJsonList.length();i++){
                System.out.println(followers_users[i]);
            }
            return followers_users;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }



}
