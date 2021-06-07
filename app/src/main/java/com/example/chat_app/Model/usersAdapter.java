package com.example.chat_app.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chat_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class usersAdapter extends ArrayAdapter<users> {
    Context mcontext;
    public usersAdapter(@NonNull Context context,  @NonNull List<users> objects) {
        super(context, 0, objects);
        mcontext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        users users = getItem(position);

        if(convertView==null)
        {
            convertView= LayoutInflater.from(mcontext).inflate(R.layout.main_screen_list_item_layout,parent,false);
        }

        TextView title = convertView.findViewById(R.id.title);
        TextView subTitle = convertView.findViewById(R.id.subtitle);
        ImageView icon = convertView.findViewById(R.id.icon);

        title.setText(users.getName());
        subTitle.setText(users.getEmail());

        Picasso.with(getContext()).load(users.getUri()).into(icon);


        return convertView;
    }
}
