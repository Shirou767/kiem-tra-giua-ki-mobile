package com.example.myfood_nnbinh;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfood_nnbinh.database.Food_NNBinh;

public class FoodDetailActivity_NNBinh extends AppCompatActivity {

    private TextView tvName, tvDescription, tvPrice, tvSize;
    private ImageView ivFoodImage;
    private Food_NNBinh dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail_nnbinh);

        initViews();
        dbHelper = new Food_NNBinh(this);

        int foodId = getIntent().getIntExtra("food_id", -1);
        if (foodId == -1) {
            Toast.makeText(this, "Không tìm thấy món ăn", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadFoodDetail(foodId);
    }

    private void initViews() {
        tvName = findViewById(R.id.textViewFoodName);
        tvDescription = findViewById(R.id.textViewFoodDescription);
        tvPrice = findViewById(R.id.textViewFoodPrice);
        tvSize = findViewById(R.id.textViewFoodSize);
        ivFoodImage = findViewById(R.id.imageViewFood);
    }

    private void loadFoodDetail(int foodId) {
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                "SELECT * FROM Food WHERE id = ?",
                new String[]{String.valueOf(foodId)}
        );

        if (cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            double price = cursor.getDouble(3);
            String size = cursor.getString(4);
            int restaurantId = cursor.getInt(5);

            tvName.setText(name);
            tvDescription.setText(description);
            tvPrice.setText("Giá: " + price + " VNĐ");
            tvSize.setText("Kích thước: " + size);

            int imageResId = R.drawable.pho;
            if (name.contains("Bún bò")) imageResId = R.drawable.bun_bo;
            else if (name.contains("Cơm tấm")) imageResId = R.drawable.com_tam;
            else if (name.contains("Bánh mì")) imageResId = R.drawable.banh_mi;
            else if (name.contains("Phở")) imageResId = R.drawable.pho;
            else if (name.contains("Bánh cuốn")) imageResId = R.drawable.banh_cuon;
            else if (name.contains("Gỏi cuốn")) imageResId = R.drawable.goi_cuon;
            else if (name.contains("Lẩu Thái")) imageResId = R.drawable.lau_thai;
            else if (name.contains("Bánh xèo")) imageResId = R.drawable.banh_xeo;
            else if (name.contains("Chè Thái")) imageResId = R.drawable.che_thai;
            else if (name.contains("Súp cua")) imageResId = R.drawable.sup_cua;

            ivFoodImage.setImageResource(imageResId);
        } else {
            Toast.makeText(this, "Không tìm thấy thông tin món ăn", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }
}
