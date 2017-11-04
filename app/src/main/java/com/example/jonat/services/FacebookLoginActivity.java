package com.example.jonat.services;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FacebookLoginActivity extends BaseActivity {

    // [END declare_auth]
    public static final String getToken = "token";
    private static final String TAG = FacebookLoginActivity.class.getSimpleName();
    private LoginButton mLoginButton;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_facebook_login);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        mLoginButton = findViewById(R.id.facebook_login);
        mLoginButton.setReadPermissions("email", "public_profile");
        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                //    handleFacebookAccessToken(loginResult.getAccessToken());
                Intent intent = new Intent(FacebookLoginActivity.this, MainActivity.class);
                intent.putExtra(getToken, loginResult.getAccessToken());
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        // [END initialize_fblogin]
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }
    // [END on_start_check_user]

    // [START on_activity_result]
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
    // [END on_activity_result]


    //  public void signOut() {
    //     mAuth.signOut();
    //  LoginManager.getInstance().logOut();
}


//        private void updateUI(FirebaseUser user) {
//          hideProgressDialog();
//        if (user != null) {
//       mStatusTextView.setText(getString(R.string.facebook_status_fmt, user.getDisplayName()));
//     mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

//   findViewById(R.id.facebook_login).setVisibility(View.GONE);
//} else {
//  mStatusTextView.setText(R.string.signed_out);
//mDetailTextView.setText(null);


//  }



