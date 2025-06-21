package com.example.myfood_nnbinh;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_nnbinh.adapter.RestaurantAdapter_NNBinh;
import com.example.myfood_nnbinh.database.Food_NNBinh;
import com.example.myfood_nnbinh.model.Restaurant_NNBinh;

import java.util.ArrayList;
public class HomeActivity_NNBinh extends AppCompatActivity implements RestaurantAdapter_NNBinh.OnRestaurantClickListener {
    RecyclerView recyclerView;
    ArrayList<Restaurant_NNBinh> restaurantNnBinhArrayList;
    RestaurantAdapter_NNBinh adapter_nnBinh;
    Food_NNBinh dbhelper;

    @Override
    public void onRestaurantClick(int restaurantId) {
        Intent intent = new Intent(HomeActivity_NNBinh.this, FoodListActivity_NNBinh.class);
        intent.putExtra("restaurant_id", restaurantId);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_nnbinh);

        recyclerView = findViewById(R.id.recyclerViewRestaurant_NNBinh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbhelper = new Food_NNBinh(this);
        restaurantNnBinhArrayList = new ArrayList<>();

        Cursor cursor = dbhelper.getReadableDatabase().rawQuery("SELECT * FROM Restaurant", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String address = cursor.getString(2);
                String image = cursor.getString(3);
                restaurantNnBinhArrayList.add(new Restaurant_NNBinh(id, name, address, image));
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "không có nhà hàng nào", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        adapter_nnBinh = new RestaurantAdapter_NNBinh(restaurantNnBinhArrayList, this);
        recyclerView.setAdapter(adapter_nnBinh);
    }
}


