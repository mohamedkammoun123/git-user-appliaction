package com.example.my_application.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my_application.Model.User;
import com.example.my_application.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends android.widget.BaseAdapter {

    private Context context;
    private List<User> userList;
    private LayoutInflater inflater;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public User getItem(int pos_user) {
        return userList.get(pos_user);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.adapter_item,null);
        User user = getItem(i);
        TextView loginNameView = (TextView) view.findViewById(R.id.login_follower);
        ImageView profile_following_avatar = (ImageView) view.findViewById(R.id.profile_following_avatar);
        loginNameView.setText(user.getLogin());
        Picasso.get().load(user.getAvatar_url()).into(profile_following_avatar);
        return view;
    }
}
