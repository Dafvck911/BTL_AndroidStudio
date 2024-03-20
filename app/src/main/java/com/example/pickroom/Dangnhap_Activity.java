package com.example.pickroom;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dangnhap_Activity extends AppCompatActivity {

    EditText user;
    EditText pass;
    MaterialButton dangnhap;
    TextView tk;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap_main);
        mAuth = FirebaseAuth.getInstance();
        setupUI();
        setupListener();
    }

    private void setupUI(){
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        dangnhap = (MaterialButton) findViewById(R.id.dangnhap);
        tk = (TextView) findViewById(R.id.txtTaikhoan);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private void setupListener(){
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkUser()){
                    progressBar.setVisibility(View.VISIBLE);
                    String email, password;
                    email = String.valueOf(user.getText());
                    password = String.valueOf(pass.getText());

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(i);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Dangnhap_Activity.this, "Sai tài khoản hoặc mật khẩu!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

        tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dangnhap_Activity.this, Dangky_Activity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private boolean checkUser(){
        boolean isValid = true;
        if(isEmpty(user)){
            user.setError("Tên đăng nhập không được để trống!");
            isValid = false;
        }else {
            if(!isEmail(user)){
                user.setError("Nhập đúng định dạng Email!");
                isValid = false;
            }
        }

        if(isEmpty(pass)){
            pass.setError("Mật khẩu không được để trống!");
        }
        return isValid;
    }
}