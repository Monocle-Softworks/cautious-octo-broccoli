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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {
    private EditText name,email,password;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.editUser);
        email = (EditText) findViewById(R.id.editEmail);
        password = (EditText) findViewById(R.id.editPass);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

    }

    public void signUpClicked(View view)
    {
        final String name_content,password_content,email_content;
        name_content = name.getText().toString().trim();
        password_content = password.getText().toString().trim();
        email_content = email.getText().toString().trim();

        if(!TextUtils.isEmpty(email_content) && !TextUtils.isEmpty(name_content) && !TextUtils.isEmpty(password_content))
        {
            mAuth.createUserWithEmailAndPassword(email_content,password_content).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        String user_id = mAuth.getCurrentUser().getUid();
                        DatabaseReference current_user_db = mDatabase.child(user_id);
                        current_user_db.child("Name").setValue(name_content);
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    public void loginClicked(View view)
    {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class );
        startActivity(intent);
    }
}
