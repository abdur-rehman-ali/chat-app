package com.example.chat_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chat_app.Adapters.UserAdapter;
import com.example.chat_app.Model.UserProfileData;
import com.example.chat_app.Model.users;
import com.example.chat_app.Model.usersAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView name,email;
    FirebaseFirestore db;
    FirebaseDatabase database;
    ArrayList<UserProfileData> users;
    UserAdapter userAdapter;
    RecyclerView userslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db=FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();
        users = new ArrayList<>();
        userAdapter=new UserAdapter(this,users);

        userslist=findViewById(R.id.recycler_view);
        userslist.setAdapter(userAdapter);

        displayData();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void displayData() {

        database.getReference().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    UserProfileData user = postSnapshot.getValue(UserProfileData.class);
                    if(!user.getUuid().equals(FirebaseAuth.getInstance().getUid()))
                        users.add(user);

                }
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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

    @Override
    protected void onStart() {
        super.onStart();

    }
}