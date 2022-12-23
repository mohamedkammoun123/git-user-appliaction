package com.example.my_application.Model;
import java.io.Serializable;

public class User implements Serializable {
    int id_user;
    String login;
    String avatar_url;
    String bio;
    String creation_date;
    int nb_follower ;
    int nb_following ;
    String following_url ;
    String follower_url ;

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getFollower_url() {
        return follower_url;
    }

    public void setFollower_url(String follower_url) {
        this.follower_url = follower_url;
    }

    User [] follower_users;

    public User[] getFollower_users() { return follower_users; }

    public void setFollower_users(User[] follower_users) { this.follower_users = follower_users; }

    public int getNb_follower() {
        return nb_follower;
    }

    public void setNb_follower(int nb_follower) {
        this.nb_follower = nb_follower;
    }

    public int getNb_following() {
        return nb_following;
    }

    public void setNb_following(int nb_following) {
        this.nb_following = nb_following;
    }

    public User(int id_user, String login, String avatar_url, String bio, String creation_date) {
        this.id_user = id_user;
        this.login = login;
        this.avatar_url = avatar_url;
        this.bio = bio;
        this.creation_date = creation_date;
    }

    public User(){

    }

    public int getId_user() {
        return id_user;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getBio() {
        return bio;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", login='" + login + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", bio='" + bio + '\'' +
                ", creation_date='" + creation_date + '\'' +
                '}';
    }

}
