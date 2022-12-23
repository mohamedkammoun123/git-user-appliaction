package com.example.my_application.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_application.Model.User;
import com.example.my_application.R;

public class GitFollowerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_follower);
        TextView nb_follower_view = (TextView) findViewById(R.id.id_follower);
        TextView nb_following_view =(TextView) findViewById(R.id.id_following);
        User user = (User) getIntent().getSerializableExtra("user");
        System.out.println("start to show relations of "+user);
        nb_follower_view.setHint("follower: "+user.getNb_follower());
        nb_following_view.setHint("following: "+user.getNb_following());
        nb_following_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User [] followers_user = (User []) getIntent().getSerializableExtra("followers_user");
                Intent listFollowingIntent = new Intent(getApplicationContext(), ListFollowingActivity.class);
                listFollowingIntent.putExtra("followers_user",followers_user);
                startActivity(listFollowingIntent);
            }
        });


    }
}