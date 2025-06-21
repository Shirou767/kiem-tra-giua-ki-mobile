// FoodListActivity_NNBinh.java
package com.example.myfood_nnbinh;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_nnbinh.adapter.FoodAdapter_NNBinh;
import com.example.myfood_nnbinh.database.Food_NNBinh;
import com.example.myfood_nnbinh.model.MFood_NNBinh;

import java.util.ArrayList;

public class FoodListActivity_NNBinh extends AppCompatActivity implements FoodAdapter_NNBinh.OnFoodClickListener {

    RecyclerView recyclerView;
    ArrayList<MFood_NNBinh> foodList;
    FoodAdapter_NNBinh adapter;
    Food_NNBinh dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list_nnbinh);

        recyclerView = findViewById(R.id.recyclerViewFood_NNBinh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new Food_NNBinh(this);
        foodList = new ArrayList<>();

        int restaurantId = getIntent().getIntExtra("restaurant_id", -1);
        if (restaurantId == -1) {
            Toast.makeText(this, "Không tìm thấy nhà hàng", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("SELECT * FROM Food WHERE restaurant_id = ?", new String[]{String.valueOf(restaurantId)});
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                double price = cursor.getDouble(3);
                String size = cursor.getString(4);
                int restId = cursor.getInt(5);
                String image = cursor.getString(6);

                foodList.add(new MFood_NNBinh(id, name, description, price, size, restId, image));
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "Nhà hàng này chưa có món ăn nào", Toast.LENGTH_SHORT).show();
        }
        cursor.close();

        adapter = new FoodAdapter_NNBinh(foodList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFoodClick(int foodId) {
        Intent intent = new Intent(FoodListActivity_NNBinh.this, FoodDetailActivity_NNBinh.class);
        intent.putExtra("food_id", foodId);
        startActivity(intent);
    }
}