package com.example.silas.morsechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by silas on 1/14/2018.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText loginEmail, loginPass;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = (EditText) findViewById(R.id.loginEm);
        loginPass = (EditText) findViewById(R.id.loginPassword);

        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference().child("Users");

    }
    public void loginClicked(View view){
        String email = loginEmail.getText().toString().trim();
        String pass = loginPass.getText().toString().trim();

        if(!TextUtils.isEmpty(email)&& !TextUtils.isEmpty(pass))
        {
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        checkUserExists();
                    }
                }
            });
        }
    }
    public void checkUserExists()
    {
        final String user_id = mAuth.getCurrentUser().getUid();
        mDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.hasChild(user_id))
            {
                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(loginIntent);
            }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
