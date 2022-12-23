package com.example.my_application.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.my_application.Adapter.UserAdapter;
import com.example.my_application.Model.User;
import com.example.my_application.R;

import java.util.ArrayList;
import java.util.List;

public class ListFollowingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_following);
        System.out.println("start list following activity");
        ListView follower_list = (ListView) findViewById(R.id.list_follower);
        User[] followers_user = (User []) getIntent().getSerializableExtra("followers_user");
        List<User> list_followers_user = new ArrayList<>();
        for (int index_follower = 0; index_follower < followers_user.length; index_follower++) {
            System.out.println("follower nb "+index_follower+" in the list follower is ");
            System.out.println(followers_user[index_follower]);
            list_followers_user.add(followers_user[index_follower]);
        }
        follower_list.setAdapter(new UserAdapter(this,list_followers_user));
        follower_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("following user nb "+i+" is clicked");
                System.out.println("follower selected  = "+followers_user[i]);
                Intent followerProfileIntent = new Intent(getApplicationContext(), profile_following.class);
                followerProfileIntent.putExtra("follower_selected",followers_user[i]);
                System.out.println("start activity profile for user selected = "+followers_user[i]);
                startActivity(followerProfileIntent);
            }
        });


    }
}