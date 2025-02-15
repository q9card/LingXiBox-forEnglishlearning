package com.example.lxbox.controller.activity;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.lxbox.R;
import com.example.lxbox.controller.activity.fragment.AI_learn_fragment;
import com.example.lxbox.controller.activity.fragment.Home_page_fragment;
import com.example.lxbox.controller.activity.fragment.Myinfo_fragment;
import com.example.lxbox.controller.activity.fragment.Society_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Home_page_fragment homePageFragment;
    private AI_learn_fragment ai_learn_fragment;
    private Society_fragment society_Fragment;
    private Myinfo_fragment myinfo_fragment;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //初始化控件
        bottomNavigationView = findViewById(R.id.bottom_nvg);
        //底部导航栏点击切换事件
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.ngv_firstfra) {
                    selectedFragment(0);

                }else if(item.getItemId() == R.id.ngv_ai_learn){
                    selectedFragment(1);
                    
                } else if (item.getItemId() == R.id.ngv_sosiety) {
                    selectedFragment(2);
                    
                } else{
                    selectedFragment(3);
                    
                }
                return true;
            }
        });
        //默认第一个选中首页
        selectedFragment(0);

    }

    //选中的时候，content出现对应的部分（有待优化）
    private void selectedFragment(int position){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);

        if(position==0){
            if (homePageFragment==null){
                homePageFragment = new Home_page_fragment();
                fragmentTransaction.add(R.id.main_content,homePageFragment);
            }else {
                fragmentTransaction.show(homePageFragment);
            }
        }
        else if (position==1) {
            if (ai_learn_fragment==null){
                ai_learn_fragment = new AI_learn_fragment();
                fragmentTransaction.add(R.id.main_content,ai_learn_fragment);
            }else {
                fragmentTransaction.show(ai_learn_fragment);
            }
        }
        else if(position==2){
            if (society_Fragment==null){
                society_Fragment = new Society_fragment();
                fragmentTransaction.add(R.id.main_content,society_Fragment);
            }else {
                fragmentTransaction.show(society_Fragment);
            }
        }
        else {
            if (myinfo_fragment==null){
                myinfo_fragment = new Myinfo_fragment();
                fragmentTransaction.add(R.id.main_content,myinfo_fragment);
            }else {
                fragmentTransaction.show(myinfo_fragment);
            }
        }
        //提交
        fragmentTransaction.commit();
    }

//导航栏没有被选中的时候，隐藏未被选中的页面
    private void hideFragment(FragmentTransaction fragmentTransaction){
        if(homePageFragment !=null){
            fragmentTransaction.hide(homePageFragment);
        }
        if(ai_learn_fragment !=null){
            fragmentTransaction.hide(ai_learn_fragment);
        }
        if(society_Fragment !=null){
            fragmentTransaction.hide(society_Fragment);
        }
        if(myinfo_fragment !=null){
            fragmentTransaction.hide(myinfo_fragment);
        }
    }
}