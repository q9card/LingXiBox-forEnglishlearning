package com.example.lxbox.model.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lxbox.model.entity.UserInfo;

public class User_db_helper extends SQLiteOpenHelper {
    private static User_db_helper sHelper;
    private static final String DB_NAME = "user.db";   //数据库名
    private static final int VERSION = 1;    //版本号

    public User_db_helper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
       super(context,name,factory,version);
    }

    public  synchronized static User_db_helper getInstance(Context context){
        if (null==sHelper){
            sHelper = new User_db_helper(context,DB_NAME,null,VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建user_table表
        db.execSQL("create table user_table(" +
                "user_id integer primary key autoincrement, " +
                "username text," +       //用户名
                "password text," +      //密码
                "register_type integer" +       // 注册类型   0---用户   1---管理员
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
    登录对数据库的操作
     */
    @SuppressLint("Range")
    public UserInfo login(String username) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        UserInfo userInfo = null;
        String sql = "select user_id,username,password,register_type  from user_table where username=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext()) {
            int user_id = cursor.getInt(cursor.getColumnIndex("user_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            int register_type = cursor.getInt(cursor.getColumnIndex("register_type"));
            userInfo = new UserInfo(user_id, name, password, register_type);
        }
        cursor.close();
        db.close();
        return userInfo;
    }

    /*
    注册对数据库的操作
     */
    public int register(String username, String password, int register_type) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("username", username);
        values.put("password", password);
        values.put("register_type", register_type);
        String nullColumnHack = "values(null,?,?,?)";
        //执行
        int insert = (int) db.insert("user_table", nullColumnHack, values);
        db.close();
        return insert;
    }
    /**
     * 根据用户唯一 _id来修改密码
     */
    public int updatePwd(int _id, String password) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("password", password);
        // 执行SQL
        int update = db.update("user_table", values, " _id=?", new String[]{_id+""});
        // 关闭数据库连接
        db.close();
        return update;

    }
    /**
     * 根据用户 唯一_id删除用户
     */
    public int delete(String _id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("user_table", " _id=?", new String[]{_id});
        // 关闭数据库连接
        db.close();
        return delete;
    }
}
