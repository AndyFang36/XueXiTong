package edu.example.xuexitong.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.example.xuexitong.models.User;
import edu.example.xuexitong.utils.DbOpenHelper;

/**
 * 用户数据库操作类
 */
public class UserDAO {
    private final SQLiteDatabase sqliteDb;

    public UserDAO(Context context) {
        sqliteDb = (new DbOpenHelper(context)).getWritableDatabase();
    }

    /**
     * 添加用户
     */
    public void add(User user) {
        String sql = "INSERT INTO user (username, password, gender, introduction) VALUES(?, ?, ?, ?)";
        sqliteDb.execSQL(sql, new Object[]{
            user.getUsername(),
            user.getPassword(),
            user.getGender(),
            user.getIntroduction(),
        });
    }

    /**
     * 删除用户
     */
    public void delete(String username, String password) {
        sqliteDb.execSQL("DELETE FROM user WHERE username = ? AND password = ?", new Object[]{username, password});
    }

    /**
     * 更新用户信息
     */
    public void update(User user) {
        String sql = "UPDATE user SET username = ?, password = ?, gender = ?, introduction = ? WHERE user_id = ?";
        sqliteDb.execSQL(sql, new Object[]{
            user.getUsername(),
            user.getPassword(),
            user.getGender(),
            user.getIntroduction(),
            user.getUserId()
        });
    }

    /**
     * 获取所有用户
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cursor = sqliteDb.query("user", null, null, null, null, null, "username DESC");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int userId = cursor.getInt(cursor.getColumnIndex("user_id"));
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String gender = cursor.getString(cursor.getColumnIndex("gender"));
                String introduction = cursor.getString(cursor.getColumnIndex("introduction"));
                /* 添加到列表 */
                users.add(new User(userId, username, password, gender, introduction));
            }
            cursor.close();
        }
        return users;
    }
}