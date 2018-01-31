package com.example.silas.morsechat;

import android.content.Intent;
import android.os.Message;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
    private EditText myMessage;
    private DatabaseReference mDatabase;
    private RecyclerView mMessageList, mConversationList;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        myMessage = findViewById(R.id.editMess);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Messages");
       mMessageList = (RecyclerView) findViewById(R.id.messRec);

        mMessageList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mMessageList.setLayoutManager(linearLayoutManager);


        mAuth = FirebaseAuth.getInstance();
        //if current user is == to null then we redirect them to the register activity
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null)
                {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);

                }
            }
        };
    }
    public void signOutClicked(View view)
    {
        mAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);

    }

    public void sentMess(View view)
    {

        mCurrentUser = mAuth.getCurrentUser();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());


        final String messageValue = myMessage.getText().toString().trim();

        if(!TextUtils.isEmpty(messageValue))
        {
            final DatabaseReference newMessage = mDatabase.push();
            mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    newMessage.child("content").setValue(messageValue);
                    newMessage.child("username").setValue(dataSnapshot.child("Name").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            mMessageList.scrollToPosition(mMessageList.getAdapter().getItemCount());
            myMessage.setText("");
        }
        mMessageList.scrollToPosition(mMessageList.getAdapter().getItemCount());
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        FirebaseRecyclerAdapter<com.example.silas.morsechat.Message, MessageViewHolder> FBRA = new FirebaseRecyclerAdapter<com.example.silas.morsechat.Message, MessageViewHolder>(
                com.example.silas.morsechat.Message.class,
                R.layout.singlemessagelayout,
                MessageViewHolder.class,
                mDatabase) {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, com.example.silas.morsechat.Message model, int position) {
                viewHolder.setContent(model.getContent());
                viewHolder.setUsername(model.getUsername());

            }
        };
        mMessageList.setAdapter(FBRA);
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public MessageViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setContent(String content){
            TextView messContent = (TextView) mView.findViewById(R.id.messageText);
            messContent.setText(content);

        }

        public void setUsername(String username)
        {
            TextView username_content = (TextView) mView.findViewById(R.id.username);
            username_content.setText(username);
        }

    }
}
