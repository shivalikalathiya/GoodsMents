package com.example.goodsments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class signin extends Activity {

    EditText edtemail, edtpassword;
    Button signIn;
    TextView signup, txtforgotpassword;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView btngoogle;
    // ImageView btnfacebook;

    CallbackManager mCallbackManager;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        // AccessToken accessToken = AccessToken.getCurrentAccessToken();
        //if (accessToken != null && !accessToken.isExpired()){
        //  startActivity(new Intent(signin.this,MainActivity2.class));
        //}

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));


        mAuth = FirebaseAuth.getInstance();
        Button signin = findViewById(R.id.signIn);
        edtemail = findViewById(R.id.edtemail);
        edtpassword = findViewById(R.id.edtpassword);
        signup = findViewById(R.id.signup);
        //btnfacebook=findViewById(R.id.btnfacebook);
        txtforgotpassword = findViewById(R.id.txtforgotpassword);

        btngoogle = findViewById(R.id.btngoogle);
        database = FirebaseDatabase.getInstance("https://goodsment-29f77-default-rtdb.firebaseio.com/");

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtemail.getText().toString();
                String password = edtpassword.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(com.example.goodsments.signin.this, MainActivity.class));
                            Toast.makeText(com.example.goodsments.signin.this, "Login Successful..", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(com.example.goodsments.signin.this, "Login Failed..", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signin.this, signup.class));
            }
        });
        txtforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signin.this, forgotpassword.class));
            }
        });

        //goggle

        btngoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signin.this, GoogleSigninActivity.class);
                startActivity(intent);
            }
        });


        // Initialize Facebook Login button
        //mCallbackManager = CallbackManager.Factory.create();
        //btnfacebook.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //public void onClick(View view) {
        //  LoginManager.getInstance().logInWithReadPermissions(signin.this,Arrays.asList("email", "public_profile"));
        //LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
        //  @Override
        //public void onSuccess(LoginResult loginResult) {
        //  Log.d(TAG, "facebook:onSuccess:" + loginResult);
        //   handleFacebookAccessToken(loginResult.getAccessToken());
        //}

        //@Override
        //public void onCancel() {
        //   Log.d(TAG, "facebook:onCancel");
        //}

        // @Override
        //public void onError(FacebookException error) {
        //  Log.d(TAG, "facebook:onError", error);
        //}
        //});;
        //}
        //});


        //}


        //private void handleFacebookAccessToken(AccessToken token) {
        //Log.d(TAG, "handleFacebookAccessToken:" + token);

        //AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        //mAuth.signInWithCredential(credential)
        // .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        //@Override
        //public void onComplete(@NonNull Task<AuthResult> task) {
        //if (task.isSuccessful()) {
        // Sign in success, update UI with the signed-in user's information
        //  Log.d(TAG, "signInWithCredential:success");
        //    user = mAuth.getCurrentUser();
        //      assert user!=null;
        //        users users1 = new users();
        //          database.getReference().child("user").child(user.getUid()).setValue(users1);
        //            Intent intent = new Intent(signin.this,MainActivity.class);
        //              intent.putExtra("name", Objects.requireNonNull(user.getDisplayName()).toString());
        //                startActivity(intent);
        //              } else {
        //                    // If sign in Uils, display a message to the user.
        //                      Log.w(TAG, "signInWithCredential:failure", task.getException());
        //                        Toast.makeText(signin.this, "Authentication failed.",
        //                                  Toast.LENGTH_SHORT).show();
//
        //             }
        //           }
        //         });
        //}

    }
}