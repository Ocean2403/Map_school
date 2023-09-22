package com.example.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore db;
    EditText Name, Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        Name = findViewById(R.id.Register_User);
        Email = findViewById(R.id.Register_Email);
        Password = findViewById(R.id.Register_Password);
    }

    public void Register_at_register(View view) {
        final String name = Name.getText().toString();
        final String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Email.setError("Vui lòng điền email");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Password.setError("Vui lòng điền mật khẩu");
            return;
        }
        if (password.length() < 6) {
            Password.setError("Tối thiểu 6 ký tự");
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            String id = user.getUid();
                            Map<String, Object> user = new HashMap<>();
                            user.put("Email", email);
                            user.put("Ten", name);
                            db.collection("NguoiDung").document(id).set(user);
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                            finishAffinity();
                        } else {
                            Toast.makeText(Register.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, "Đăng ký thất bại" + e, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void Login_at_register(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}