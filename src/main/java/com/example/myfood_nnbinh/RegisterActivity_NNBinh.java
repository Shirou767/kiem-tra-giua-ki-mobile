package com.example.myfood_nnbinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfood_nnbinh.database.Food_NNBinh;
public class RegisterActivity_NNBinh extends AppCompatActivity {

    EditText edtUsername_NNBinh, edtPassword_NNBinh, edtRePassword_NNBinh;
    Button btnRegister_NNBinh;
    Food_NNBinh dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_nnbinh);

        edtUsername_NNBinh = findViewById(R.id.edtUsername_NNBinh);
        edtPassword_NNBinh = findViewById(R.id.edtPassword_NNBinh);
        edtRePassword_NNBinh = findViewById(R.id.edtRePassword_NNBinh);
        btnRegister_NNBinh = findViewById(R.id.btnRegister_NNBinh);

        dbHelper = new Food_NNBinh(this);

        btnRegister_NNBinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUsername_NNBinh.getText().toString();
                String pass = edtPassword_NNBinh.getText().toString();
                String rePass = edtRePassword_NNBinh.getText().toString();

                if (user.isEmpty() || pass.isEmpty() || rePass.isEmpty()) {
                    Toast.makeText(RegisterActivity_NNBinh.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(rePass)) {
                    Toast.makeText(RegisterActivity_NNBinh.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
                    boolean result = dbHelper.insertUser_NNBinh(user, pass);
                    if (result) {
                        Toast.makeText(RegisterActivity_NNBinh.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity_NNBinh.this, LoginActivity_NNBinh.class));
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity_NNBinh.this, "Tên tài khoản đã tồn tại hoặc có lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
