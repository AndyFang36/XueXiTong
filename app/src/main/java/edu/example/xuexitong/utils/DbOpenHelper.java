package edu.example.xuexitong.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/** 默认就在数据库里创建2张表 */
public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "db_xuexitong";
    private static final int DB_VERSION = 1;

    public DbOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("DbOpenHelper", "onCreate...\nonCreate...");
        db.execSQL("CREATE TABLE IF NOT EXISTS user (user_id integer PRIMARY KEY AUTOINCREMENT, username varchar(16) NOT NULL, password varchar(16) NOT NULL, gender varchar(2) NOT NULL DEFAULT('未知'), introduction varchar(300))");
        db.execSQL("CREATE TABLE IF NOT EXISTS note (note_id integer PRIMARY KEY AUTOINCREMENT, user_id integer NOT NULL REFERENCES user(user_id), title varchar(16) NOT NULL, content varchar(1024) NOT NULL, creation_time varchar(32) NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("DbOpenHelper", "onUpgrade...\nonUpgrade...");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS note");
        onCreate(db);
    }
}
