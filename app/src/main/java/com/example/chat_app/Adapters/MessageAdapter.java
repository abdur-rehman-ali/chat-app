package com.example.chat_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_app.Model.Messges;
import com.example.chat_app.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdapter extends  RecyclerView.Adapter{

    private static final int ITEM_SENT =1 ;
    private static final int ITEM_RECEIVE = 2;
    Context context;
    ArrayList<Messges>  messages;

    public MessageAdapter(Context context, ArrayList<Messges> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ITEM_SENT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_send, parent, false);
            return new sentViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_receive, parent, false);
            return new receiveViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Messges message = messages.get(position);

        if(holder.getClass() == sentViewHolder.class)
        {
            sentViewHolder viewHolder = (sentViewHolder) holder;
            viewHolder.sendMessage.setText(message.getMessage());

        }
        else
            {
                receiveViewHolder viewHolder = (receiveViewHolder) holder;
                viewHolder.receiveMessage.setText(message.getMessage());
            }


    }
    @Override
    public int getItemViewType(int position) {
        Messges message = messages.get(position);
        if(FirebaseAuth.getInstance().getUid().equals(message.getSenderID())) {
            return ITEM_SENT;
        } else {
            return ITEM_RECEIVE;
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class sentViewHolder extends RecyclerView.ViewHolder
    {

        TextView sendMessage ;
        public sentViewHolder(@NonNull View itemView) {
            super(itemView);
            sendMessage=itemView.findViewById(R.id.sendMessage);
        }
    }

    public class receiveViewHolder extends RecyclerView.ViewHolder
    {

        TextView receiveMessage;
        public receiveViewHolder(@NonNull View itemView) {

            super(itemView);
            receiveMessage=itemView.findViewById(R.id.receiveMessage);
        }
    }
}
