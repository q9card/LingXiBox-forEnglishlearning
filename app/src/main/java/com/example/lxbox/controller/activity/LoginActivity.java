package com.example.lxbox.controller.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxbox.R;
import com.example.lxbox.model.db.User_db_helper;
import com.example.lxbox.model.entity.UserInfo;

public class LoginActivity extends AppCompatActivity {
    private EditText login_et_name;
    private EditText login_et_pw;
    private CheckBox checkBox;
    private SharedPreferences mSharedPreferences;//用于记住密码
    private boolean is_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        //初始化控件
        login_et_name = findViewById(R.id.login_et_name);
        login_et_pw = findViewById(R.id.login_et_pw);
        checkBox = findViewById(R.id.login_checkbox);

        //获取sp实例
        mSharedPreferences = getSharedPreferences("user",MODE_PRIVATE);

        //是否勾选了记住密码
        is_login = mSharedPreferences.getBoolean("is_login",false);
        if (is_login){
            String username = mSharedPreferences.getString("username",null);
            String password = mSharedPreferences.getString("password",null);
            login_et_pw.setText(username);
            login_et_pw.setText(password);
            checkBox.setChecked(true);
        }


        //点击到跳转注册
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册页面
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });
        //登录
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = login_et_name.getText().toString();
                String password = login_et_pw.getText().toString();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                } else {

                    UserInfo login = User_db_helper.getInstance(LoginActivity.this).login(username);
                    if (login != null) {
                        if (username.equals(login.getUsername()) && password.equals(login.getPassword())) {
                            //保存是否勾选记住密码
                            SharedPreferences.Editor edit = mSharedPreferences.edit();
                            edit.putBoolean("is_login", is_login);
                            edit.putString("username", username);
                            edit.putString("password", password);
                            //提交记住密码到SP
                            edit.commit();

                            //保存用户名和密码
                            UserInfo.setUserInfo(login);

                            //登录成功
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "该账号暂未注册", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        //密码点击事件
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                is_login = isChecked;
            }
        });

    }
}