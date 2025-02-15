package com.example.lxbox.controller.activity.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lxbox.R;
import com.example.lxbox.controller.activity.LoginActivity;
import com.example.lxbox.controller.activity.TestActivity;
import com.example.lxbox.model.db.User_db_helper;
import com.example.lxbox.model.entity.UserInfo;

public class Myinfo_fragment extends Fragment {

    private View rootview;
    private TextView nameshow;
    private TextView uidshow;
    private ImageView profile_picture;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment初始化这个视图
        rootview = inflater.inflate(R.layout.fragment_myinfo_fragment, container, false);
        //初始化组件
        nameshow = rootview.findViewById(R.id.nameshow);
        uidshow = rootview.findViewById(R.id.uidshow);
        profile_picture = rootview.findViewById(R.id.profile_picture);

        //监听按钮“测测我的”
        rootview.findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到TestActivity
                getActivity().finish();
                Intent intent = new Intent(getActivity(), TestActivity.class);
                startActivity(intent);
            }
        });


        //监听按钮点击退出
        rootview.findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹窗
                new AlertDialog.Builder(getActivity())
                        .setTitle("退出?")
                        .setMessage("我们会想你的。")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //退出登录，返回登录页面
                                getActivity().finish();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });
        rootview.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹窗
                new AlertDialog.Builder(getActivity())
                        .setTitle("提示")
                        .setMessage("要注销账号？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //注销账号操作

                                //退出登录，返回登录页面
                                getActivity().finish();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);

                            }
                        })
                        .show();
            }
        });
        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //显示用户数据
        UserInfo userInfo = UserInfo.getUserInfo();
        if (null!=userInfo){
            nameshow.setText(userInfo.getUsername());
            uidshow.setText("uid:"+userInfo.get_id());
        }


    }
}
























