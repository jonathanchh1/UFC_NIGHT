package com.example.jonat.services;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonat.services.Adapters.CommentAdapter;
import com.example.jonat.services.Models.Comment;
import com.example.jonat.services.Models.Events;
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

/**
 * Created by jonat on 11/3/2017.
 */

public class CommentFragment extends Fragment {

    private static final String TAG_LOG = CommentFragment.class.getSimpleName();
    private DatabaseReference mPostReference;
    private DatabaseReference mCommentsReference;
    private ValueEventListener mPostListener;
    private String mPostKey;
    private CommentAdapter mAdapter;
    private EditText mCommentField;
    private Button mCommentButton;
    private ImageView userprofile;
    private RecyclerView mCommentsRecycler;
    private TextView mAuthorView;
    private TextView mTitleView;
    private FirebaseAuth mAuth;
    private FirebaseUser username;
    private AccessToken mToken;
    private User user = new User();
    private Events post = new Events();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.comment_section, container, false);

        // Get post intent
        mPostKey = getActivity().getIntent().getStringExtra(FacebookLoginActivity.getToken);
        if (mPostKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
        }

        mToken = AccessToken.getCurrentAccessToken();
        // Initialize Database

        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("posts").child(mPostKey);
        mCommentsReference = FirebaseDatabase.getInstance().getReference()
                .child("post-comments").child(mPostKey);
        // get reference to 'users' node
        mAuth = FirebaseAuth.getInstance();
        // store app title to 'app_title' node
        handleFacebookAccessToken(mToken);
        username = mAuth.getCurrentUser();
        // Initialize Views
        mTitleView = rootview.findViewById(R.id.post_title);
        userprofile = rootview.findViewById(R.id.post_author_photo);
        mAuthorView = rootview.findViewById(R.id.post_author);
        mCommentField = rootview.findViewById(R.id.field_comment_text);
        mCommentButton = rootview.findViewById(R.id.button_post_comment);
        mCommentsRecycler = rootview.findViewById(R.id.recycler_comments);
        mCommentsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postComment(username);
            }
        });
        mTitleView.setText(post.getBase_title());
        mAuthorView.setText(user.getUsername());

        return rootview;

    }

    @Override
    public void onStart() {
        super.onStart();
        // Listen for comments
        mAdapter = new CommentAdapter(getContext(), mCommentsReference);
        mCommentsRecycler.setAdapter(mAdapter);

    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mPostListener != null) {
            mPostReference.removeEventListener(mPostListener);
        }

        // Clean up comments listener
        mAdapter.cleanupListener();
    }

    private void postComment(FirebaseUser username) {
        final String uid = username.getUid();
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


    // [START auth_with_facebook]
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG_LOG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG_LOG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            User userInfo = new User();
                            if (user != null) {
                                String userProfile = String.valueOf(user.getPhotoUrl());
                                if (userProfile != null) {
                                    Picasso.with(getActivity()).load(userProfile).into(userprofile);
                                }
                                String mAuthor = user.getDisplayName();
                                userInfo.setUsername(mAuthor);
                                userInfo.setUserProfile(userProfile);
                                postComment(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG_LOG, "signInWithCredential:failure", task.getException());
                            }


                            // [END_EXCLUDE]
                        }
                    }
                });
    }

}
