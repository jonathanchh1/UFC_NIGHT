package com.example.jonat.services;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonat.services.Adapters.CommentAdapter;
import com.example.jonat.services.Models.Comment;
import com.example.jonat.services.Models.User;
import com.facebook.AccessToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String LOG_TAG = CommentActivity.class.getSimpleName();
    public FirebaseUser firebaseUser;
    private DatabaseReference mCommentsReference;
    private FirebaseDatabase mFirebaseInstance;
    private CommentAdapter mAdapter;
    private EditText mCommentField;
    private Button mCommentButton;
    private ImageView userprofile;
    private RecyclerView mCommentsRecycler;
    private TextView mAuthorView;
    private FirebaseAuth mAuth;
    //private TextView mTitleView;
    private User userobject = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        String mPostKey = "comment_section";
        // Initialize Database
        mFirebaseInstance = FirebaseDatabase.getInstance();
        // get reference to 'users' node
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        mCommentsReference = mFirebaseInstance.getReference("user_comments")
                .child(mPostKey);

        handleFacebookAccessToken(AccessToken.getCurrentAccessToken());
        // get reference to 'users' node
        // store app title to 'app_title' node
        // Initialize Views
        // mTitleView = findViewById(R.id.post_title);
        userprofile = findViewById(R.id.post_author_photo);
        mAuthorView = findViewById(R.id.post_author);
        mCommentField = findViewById(R.id.field_comment_text);
        mCommentButton = findViewById(R.id.button_post_comment);
        mCommentsRecycler = findViewById(R.id.recycler_comments);
        mCommentsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mCommentButton.setOnClickListener(this);
        //  mTitleView.setText(post.getBase_title());
        onAuthSuccess(firebaseUser);

    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(LOG_TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(LOG_TAG, "signInWithCredential:success");
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            onAuthSuccess(currentUser);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(LOG_TAG, "signInWithCredential:failure", task.getException());
                        }


                        // [END_EXCLUDE]
                    }
                });
    }

    public void onAuthSuccess(FirebaseUser user) {
        if (user != null) {
            String mAuthor = user.getDisplayName();
            userobject.setUsername(mAuthor);
            Uri userprofiles = user.getPhotoUrl();

            if (userprofiles != null) {
                Picasso.with(getApplicationContext())
                        .load(userprofiles)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .into(userprofile);
            }
            mAuthorView.setText(userobject.getUsername());
            Log.d(LOG_TAG, "check link" + userprofiles);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Listen for comments
        FirebaseUser user = mAuth.getCurrentUser();
        onAuthSuccess(user);
        mAdapter = new CommentAdapter(getApplicationContext(), mCommentsReference);
        mCommentsRecycler.setAdapter(mAdapter);

    }

    @Override
    public void onStop() {
        super.onStop();
        // Clean up comments listener
    }

    private void postComment(FirebaseUser user) {
        final String uid = user.getUid();
        FirebaseDatabase.getInstance().getReference().child("users").child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user information
                        User user = dataSnapshot.getValue(User.class);
                        if (user != null) {
                            String authorName = user.username;
                            // Create new comment object
                            String userProfile = user.UserProfile;
                            String commentText = mCommentField.getText().toString();
                            Comment comment = new Comment(uid, authorName, commentText, userProfile);
                            // Push the comment, it will appear in the list
                            mCommentsReference.push().setValue(comment);

                            // Clear the field
                            mCommentField.setText(null);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }


    @Override
    public void onClick(View view) {
        postComment(firebaseUser);
    }
}