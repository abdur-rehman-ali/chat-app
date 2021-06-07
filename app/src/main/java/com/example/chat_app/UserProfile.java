package com.example.chat_app;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class UserProfile extends AppCompatActivity {

    TextView username, email;
    ImageView profileImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Targeting user
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        profileImage = findViewById(R.id.profileImage);

        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentuser!=null)
        {
            String current_username = currentuser.getDisplayName();
            String current_email = currentuser.getEmail();
            Uri image_url = currentuser.getPhotoUrl();
            username.setText(current_username);
            email.setText(current_email);
            Picasso.with(getApplicationContext()).load(image_url).into(profileImage);

        }
    }
}