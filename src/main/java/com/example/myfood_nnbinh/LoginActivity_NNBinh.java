// LoginActivity_NNBinh.java
package com.example.myfood_nnbinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfood_nnbinh.database.Food_NNBinh;

public class LoginActivity_NNBinh extends AppCompatActivity {

    EditText edtUsername_NNBinh, edtPassword_NNBinh;
    Button btnLogin_NNBinh;
    TextView tvRegister_NNBinh;
    Food_NNBinh dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_nnbinh);

        edtUsername_NNBinh = findViewById(R.id.edtUsername_NNBinh);
        edtPassword_NNBinh = findViewById(R.id.edtPassword_NNBinh);
        btnLogin_NNBinh = findViewById(R.id.btnLogin_NNBinh);
        tvRegister_NNBinh = findViewById(R.id.tvRegister_NNBinh);

        dbHelper = new Food_NNBinh(this);

        btnLogin_NNBinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUsername_NNBinh.getText().toString();
                String pass = edtPassword_NNBinh.getText().toString();

                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity_NNBinh.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = dbHelper.checkUser_NNBinh(user, pass);
                    if (check) {
                        Toast.makeText(LoginActivity_NNBinh.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity_NNBinh.this, HomeActivity_NNBinh.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity_NNBinh.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvRegister_NNBinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity_NNBinh.this, RegisterActivity_NNBinh.class));
            }
        });
    }
}
