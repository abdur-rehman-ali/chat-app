package com.example.chat_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chat_app.Adapters.MessageAdapter;
import com.example.chat_app.Model.Messges;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class chatActivity extends AppCompatActivity {


    MessageAdapter adapter;
    ArrayList<Messges> messages;
    String senderRoom,receiverRoom;
    Button sendButton;
    EditText messageBox;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Name and ID of user
        String name = getIntent().getStringExtra("name");
        String receiverUid = getIntent().getStringExtra("uid");
        String senderUid = FirebaseAuth.getInstance().getUid();

        senderRoom = senderUid+receiverUid;
        receiverRoom  = receiverUid+senderUid;


        database = FirebaseDatabase.getInstance();
        sendButton = findViewById(R.id.sendMessage);
        messageBox = findViewById(R.id.messageBox);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageBox.getText().toString();

                Date date = new Date();
                Messges messageDetails = new Messges(message,senderUid,date.getTime());

                database.getReference().child("chats").child(senderRoom).child("message")
                        .setValue(messageDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        database.getReference().child("chats").child(receiverRoom).child("message")
                                .setValue(messageDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });
                    }
                });
            }
        });

        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        messages=new ArrayList<>();
        adapter=new MessageAdapter(this,messages);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}