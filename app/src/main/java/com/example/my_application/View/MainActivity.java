package com.example.my_application.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_application.Controller.UserController;
import com.example.my_application.Model.User;
import com.example.my_application.R;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialButton search_button = (MaterialButton) findViewById(R.id.search_button);
        EditText login_edit_text = (EditText) findViewById(R.id.search_bar);
        MaterialButton relation_button = (MaterialButton) findViewById(R.id.id_relation);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = login_edit_text.getText().toString();
                UserController userController = new UserController(login);
                User user = userController.fromJsonToUser();
                if (user == null){
                    System.out.println("no user is found with login = "+login);
                    Intent noUserFoundIntent = new Intent(getApplicationContext(), NoUserActivity.class);
                    startActivity(noUserFoundIntent);
                }
                else{
                    System.out.println("response from url is "+user);
                    Intent profileActivityIntent = new Intent(getApplicationContext(), ProfileActivity.class);
                    profileActivityIntent.putExtra("user",user);
                    startActivity(profileActivityIntent);
                    userController.getFollower(user.getFollowing_url());
                }

            }
        });
        relation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = login_edit_text.getText().toString();
                UserController userController = new UserController(login);
                User user = userController.fromJsonToUser();
                if (user == null){
                    System.out.println("no user is found with login = "+login);
                    Intent noUserFoundIntent = new Intent(getApplicationContext(), NoUserActivity.class);
                    startActivity(noUserFoundIntent);
                }
                else{
                    System.out.println("response from url = "+user.getFollowing_url());
                    User [] followers_user = userController.getFollower(user.getFollowing_url());
                    Intent gitFollowerIntent = new Intent(getApplicationContext(), GitFollowerActivity.class);
                    gitFollowerIntent.putExtra("user",user);
                    gitFollowerIntent.putExtra("followers_user",followers_user);
                    startActivity(gitFollowerIntent);
                }
            }
        });

    }
}