package com.example.my_application.View;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_application.Controller.UrlReader;
import com.example.my_application.Model.User;
import com.example.my_application.R;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        TextView id_text = (TextView) findViewById(R.id.id_git);
        TextView login_text = (TextView) findViewById(R.id.login_git);
        TextView bio_text = (TextView) findViewById(R.id.bio_git);
        TextView creat_date_text = (TextView) findViewById(R.id.date_git);
        ImageView  profile_image = (ImageView) findViewById(R.id.avatar);
        User user = (User) getIntent().getSerializableExtra("user");
        System.out.println("start to show profile of "+user);
        id_text.setHint(""+user.getId_user());
        login_text.setHint(user.getLogin());
        bio_text.setHint(user.getBio());
        creat_date_text.setHint(user.getCreation_date());
        Picasso.get().load(user.getAvatar_url()).into(profile_image);


    }
}