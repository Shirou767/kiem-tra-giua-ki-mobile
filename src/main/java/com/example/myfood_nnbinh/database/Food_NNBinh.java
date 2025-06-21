package com.example.myfood_nnbinh.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Food_NNBinh extends SQLiteOpenHelper {
    public static final String DB_NAME = "Food_NNBinh.db";

    public Food_NNBinh(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
        db.execSQL("CREATE TABLE Restaurant(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, image TEXT)");
        db.execSQL("CREATE TABLE Food(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, price REAL, size TEXT, restaurant_id INTEGER, image TEXT)");

        db.execSQL("INSERT INTO User(username, password) VALUES ('admin1', '123'), ('admin2', '123'), ('user1', '111'), ('user2', '222'), ('binh', '123')");

        db.execSQL("INSERT INTO Restaurant(name, address, image) VALUES ('Quán A', '123 Lê Lợi', ''), ('Quán B', '456 Lý Thường Kiệt', ''), ('Quán C', '789 Trần Hưng Đạo', ''), ('Quán D', '10 Nguyễn Trãi', ''), ('Quán E', '20 CMT8', '')");

        db.execSQL("INSERT INTO Food(name, description, price, size, restaurant_id) VALUES " +
                "('Bún bò', 'Ngon tuyệt', 30000, 'Small', 1)," +
                "('Cơm tấm', 'Thơm ngon', 35000, 'Small', 1)," +
                "('Bánh mì', 'Giòn rụm', 20000, 'Small', 2)," +
                "('Phở', 'Đậm đà', 40000, 'Small', 2)," +
                "('Bánh cuốn', 'Truyền thống', 25000, 'Small', 3)," +
                "('Gỏi cuốn', 'Tươi mát', 15000, 'Small', 3 )," +
                "('Lẩu Thái', 'Chua cay', 100000, 'Small', 4 )," +
                "('Bánh xèo', 'Vàng ươm', 50000, 'Small', 4 )," +
                "('Chè Thái', 'Ngọt mát', 20000, 'Small', 5 )," +
                "('Súp cua', 'Bổ dưỡng', 30000, 'Small', 5 )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Restaurant");
        db.execSQL("DROP TABLE IF EXISTS Food");
        onCreate(db);
    }

    public boolean checkUser_NNBinh(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE username=? AND password=?", new String[]{username, password});
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }

    public boolean insertUser_NNBinh(String username, String password) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("INSERT INTO User(username, password) VALUES (?, ?)", new Object[]{username, password});
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
