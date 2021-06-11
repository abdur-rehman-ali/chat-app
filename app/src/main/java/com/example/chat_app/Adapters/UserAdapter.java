package com.example.chat_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_app.Model.UserProfileData;
import com.example.chat_app.R;
import com.example.chat_app.UserProfile;
import com.example.chat_app.chatActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    Context context;
    ArrayList<UserProfileData> usersData;
    private TextView title;

    public UserAdapter(Context context, ArrayList<UserProfileData> usersData) {
        this.context = context;
        this.usersData = usersData;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_screen_list_item_layout,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
     UserProfileData user= usersData.get(position);
    holder.title.setText(user.getName());
    holder.subtitle.setText(user.getEmail());
    Picasso.with(context).load(user.getUri()).into(holder.icon);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, chatActivity.class);
            intent.putExtra("name",user.getName());
            intent.putExtra("uid",user.getUuid());
            context.startActivity(intent);
        }
    });


    }

    @Override
    public int getItemCount() {
        return usersData.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder
    {
        TextView title ;
        TextView subtitle;
        ImageView icon;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            subtitle=itemView.findViewById(R.id.subtitle);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
