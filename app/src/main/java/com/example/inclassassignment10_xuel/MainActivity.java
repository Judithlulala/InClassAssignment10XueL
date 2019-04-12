package com.example.inclassassignment10_xuel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView displayEditText;
    private EditText inputEditText;
    private ArrayList<String> textList =new ArrayList<>();
    private FirebaseDatabase database= FirebaseDatabase.getInstance();
    private FirebaseAuth auth=FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener authListener;
    private DatabaseReference userRef;
    private DatabaseReference info = database.getReference("Information");

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser==null){
            startActivity(new Intent(MainActivity.this, LogIn_Activity.class));
        }}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        displayEditText=findViewById(R.id.display);
        inputEditText=findViewById(R.id.input);
        authListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if(user==null){
                    startActivity(new Intent(MainActivity.this, LogIn_Activity.class));

                }else{userRef=database.getReference(user.getUid());
                userRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });}


            }
        };

    }

    public void logout(View view) {
        startActivity(new Intent(MainActivity.this,LogIn_Activity.class));
        auth.signOut();
    }

    public void send(View view) {
        displayEditText.setText(inputEditText.getText().toString());
        info.push().setValue(inputEditText.getText().toString());

    }
}
