package com.example.my_application.View;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_application.AIDLService.AIDLColorService;
import com.example.my_application.Controller.UserController;
import com.example.my_application.IAIDLColorInterface;
import com.example.my_application.Model.User;
import com.example.my_application.R;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    IAIDLColorInterface iAidlColorService ;
    private static final String TAG = "MainActivity";

    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iAidlColorService = IAIDLColorInterface.Stub.asInterface(iBinder);
            Log.d(TAG,"Remote config Service Connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton search_button = (MaterialButton) findViewById(R.id.search_button);
        EditText login_edit_text = (EditText) findViewById(R.id.search_bar);
        MaterialButton relation_button = (MaterialButton) findViewById(R.id.id_relation);

        Intent intent = new Intent("AIDLColorService");
        System.out.println("start to set intent package of AIDL Service");
        intent.setPackage("com.example.my_application");
        System.out.println("end of call set intent function ");
        bindService(intent,myConnection,BIND_AUTO_CREATE);

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
                try {
                    System.out.println("try to use getColor from Serv");
                    int color = iAidlColorService.getColor();
                    relation_button.setBackgroundColor(color);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
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