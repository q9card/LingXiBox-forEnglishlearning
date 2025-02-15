package com.example.lxbox.controller.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxbox.R;
import com.example.lxbox.model.db.User_db_helper;

public class RegistActivity extends AppCompatActivity {
    private EditText regist_et_pw;
    private EditText regist_et_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_regest);
    //组件初始化
        regist_et_name = findViewById(R.id.regist_et_name);
        regist_et_pw = findViewById(R.id.regist_et_pw);
        findViewById(R.id.regist_toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击注册按钮
        findViewById(R.id.regist_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = regist_et_name.getText().toString();
                String password = regist_et_pw.getText().toString();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Toast.makeText(RegistActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }else {
                    int row = User_db_helper.getInstance(RegistActivity.this).register(username, password, 1);
                    if (row>0){
                        Toast.makeText(RegistActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }

            }
        });


    }



}