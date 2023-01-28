package com.example.goodsments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private EditText edtemail;
    private Button btnsend;
    private  String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);


        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        mAuth = FirebaseAuth.getInstance();
        Button btnsend = findViewById(R.id.btnsend);
        edtemail = findViewById(R.id.edtemail);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }
    private void validateData(){
        email = edtemail.getText().toString();
        if (email.isEmpty()){
            edtemail.setError("Required");
        }else{
            forgotpass();
        }
    }

    private void forgotpass() {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(forgotpassword.this,"Cheak Your Email",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(forgotpassword.this,signin.class));
                    finish();
                }else{
                    Toast.makeText(forgotpassword.this, "Something went Wrong"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


//        btnsend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email = edtemail.getText().toString();
//                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            startActivity(new Intent(forgotpassword.this,cnewpassword.class));
//                            Toast.makeText(forgotpassword.this,"password reset email sent..!",Toast.LENGTH_LONG).show();
//                        }else {
//                            Toast.makeText(forgotpassword.this,"please enter your valid email Id",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//            }
//        });
//    }





