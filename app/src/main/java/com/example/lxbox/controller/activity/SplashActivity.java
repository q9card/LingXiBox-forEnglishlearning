package com.example.lxbox.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxbox.R;

/*
打开app的初入加载页面
jiangqingnan
 */
public class SplashActivity extends AppCompatActivity {
    private TextView tvCountdown;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 4000; // 设置倒计时时长，单位为毫秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        //初始化控件
        tvCountdown = findViewById(R.id.splash_countdown);
        //启动倒计时
        startCountdown();
    }

    private void startCountdown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillis = l;
                int secondsRemaining = (int)(l/1000);
                tvCountdown.setText(secondsRemaining+"s");
            }

            @Override
            public void onFinish() {
            //跳转到登录页面
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));

            //倒计时结束后的其他操作
            finish();
            }
        }.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}









