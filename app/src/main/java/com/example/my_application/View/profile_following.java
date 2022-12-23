package com.example.my_application.View;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my_application.Model.User;
import com.example.my_application.R;
import com.squareup.picasso.Picasso;

public class profile_following extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("start profile following");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_following);
        ImageView avatar_profile_follower = (ImageView) findViewById(R.id.id_avatar_follower);
        System.out.println("start to get id profile from activity");
        TextView id_profile_follower = (TextView) findViewById(R.id.id_git_follower_profile);
        System.out.println("start to get login profile from activity");
        TextView login_profile_follower = (TextView) findViewById(R.id.login_git_follower_profile);
        User follower_user = (User) getIntent().getSerializableExtra("follower_selected");
        System.out.println("profile of user ="+follower_user);
        Picasso.get().load(follower_user.getAvatar_url()).into(avatar_profile_follower);
        System.out.println("start to set id of profile of user with id = "+follower_user.getId_user());
        id_profile_follower.setText(""+follower_user.getId_user());
        System.out.println("start to set the login of user with login = "+follower_user.getLogin());
        login_profile_follower.setText(follower_user.getLogin());
        System.out.println("success to get profile of user = "+follower_user);
    }
}