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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dangky_Activity extends AppCompatActivity {

    EditText user1;
    EditText pass1;
    EditText sdt;
    MaterialButton dky;
    TextView txttaikhoan;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky_main);
        mAuth = FirebaseAuth.getInstance();
        setupUI();
        setupListener();

        txttaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Dangnhap_Activity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void setupUI(){
        user1 = (EditText) findViewById(R.id.username1);
        pass1 = (EditText) findViewById(R.id.password1);
        sdt = (EditText) findViewById(R.id.phonenumber);
        dky = (MaterialButton) findViewById(R.id.btDangky);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        txttaikhoan = (TextView) findViewById(R.id.txtcotaikhoan);

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
        dky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkUser()){
                    progressBar.setVisibility(View.VISIBLE);
                    String email, password;
                    email = String.valueOf(user1.getText());
                    password = String.valueOf(pass1.getText());



                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {
                                        Toast.makeText(Dangky_Activity.this, "Tạo tài khoản thành công!",
                                                Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), Dangnhap_Activity.class);
                                        startActivity(i);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Dangky_Activity.this, "Tên đăng nhập đã tồn tại hoặc kiểm tra lại kết nối mạng!",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }

            }
        });
    }

    private boolean checkUser(){
        boolean isValid = true;
        if(isEmpty(user1)){
            user1.setError("Tên đăng nhập không được để trống!");
            isValid = false;
        }else{
            if(!isEmail(user1)){
                user1.setError("Vui lòng nhập đúng định dạng Email!");
                isValid = false;
            }
        }

        if(isEmpty(pass1)){
            pass1.setError("Mật khẩu không được để trống!");
            isValid = false;
        } else if (pass1.length() <= 6) {
            pass1.setError("Mật khẩu phải dài hơn 6 kí tự!");
            isValid = false;
        }
        return isValid;
    }
}
