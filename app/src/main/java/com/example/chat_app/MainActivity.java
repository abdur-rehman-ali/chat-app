package com.example.chat_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chat_app.Model.UserProfileData;
import com.example.chat_app.Model.users;
import com.example.chat_app.Model.usersAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        displayUsers();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);






    }

    private void displayUsers() {
        ListView listView = findViewById(R.id.list);
        ArrayList<users> usersList = new ArrayList<users>();
        usersList.add(new users("Abdur Rehman Ali","alijoyia911@gmail.com","asKk1MzoXdUdoVUx8D7Eptb9aCm1","https://lh3.googleusercontent.com/a-/AOh14Gjj7FmaByauPgwPFkvs8zy2QjDiqPM3SbJMfo5G=s96-c"));
        usersList.add(new users("Abdur Rehman Ali","alijoyia911@gmail.com","asKk1MzoXdUdoVUx8D7Eptb9aCm1","https://lh3.googleusercontent.com/a-/AOh14Gjj7FmaByauPgwPFkvs8zy2QjDiqPM3SbJMfo5G=s96-c"));
        usersList.add(new users("Abdur Rehman Ali","alijoyia911@gmail.com","asKk1MzoXdUdoVUx8D7Eptb9aCm1","https://lh3.googleusercontent.com/a-/AOh14Gjj7FmaByauPgwPFkvs8zy2QjDiqPM3SbJMfo5G=s96-c"));
        usersList.add(new users("Abdur Rehman Ali","alijoyia911@gmail.com","asKk1MzoXdUdoVUx8D7Eptb9aCm1","https://lh3.googleusercontent.com/a-/AOh14Gjj7FmaByauPgwPFkvs8zy2QjDiqPM3SbJMfo5G=s96-c"));
        usersList.add(new users("Abdur Rehman Ali","alijoyia911@gmail.com","asKk1MzoXdUdoVUx8D7Eptb9aCm1","https://lh3.googleusercontent.com/a-/AOh14Gjj7FmaByauPgwPFkvs8zy2QjDiqPM3SbJMfo5G=s96-c"));
        usersList.add(new users("Abdur Rehman Ali","alijoyia911@gmail.com","asKk1MzoXdUdoVUx8D7Eptb9aCm1","https://lh3.googleusercontent.com/a-/AOh14Gjj7FmaByauPgwPFkvs8zy2QjDiqPM3SbJMfo5G=s96-c"));
        usersList.add(new users("Abdur Rehman Ali","alijoyia911@gmail.com","asKk1MzoXdUdoVUx8D7Eptb9aCm1","https://lh3.googleusercontent.com/a-/AOh14Gjj7FmaByauPgwPFkvs8zy2QjDiqPM3SbJMfo5G=s96-c"));

        usersAdapter mUserAdapter = new usersAdapter(this,usersList);
        listView.setAdapter(mUserAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.sign_out:
                signOut();
                break;
            case R.id.profile:
                showProfile();
            default:
                break;
        }

        return true;
    }

    private void showProfile() {
        Intent intent = new Intent(getApplicationContext(),UserProfile.class);
        startActivity(intent);
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this,SignInActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}