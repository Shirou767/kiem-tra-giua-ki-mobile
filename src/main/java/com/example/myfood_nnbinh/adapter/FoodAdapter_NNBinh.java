// FoodAdapter_NNBinh.java
package com.example.myfood_nnbinh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_nnbinh.R;
import com.example.myfood_nnbinh.model.MFood_NNBinh;

import java.util.ArrayList;

public class FoodAdapter_NNBinh extends RecyclerView.Adapter<FoodAdapter_NNBinh.ViewHolder> {

    private final ArrayList<MFood_NNBinh> foodList;
    private final OnFoodClickListener listener;

    public interface OnFoodClickListener {
        void onFoodClick(int foodId);
    }

    public FoodAdapter_NNBinh(ArrayList<MFood_NNBinh> foodList, OnFoodClickListener listener) {
        this.foodList = foodList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food_nnbinh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MFood_NNBinh food = foodList.get(position);
        holder.tvName.setText(food.getName());
        holder.tvPrice.setText("Giá: " + food.getPrice() + " đ");
        holder.cardView.setOnClickListener(v -> listener.onFoodClick(food.getId()));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvFoodName_NNBinh);
            tvPrice = itemView.findViewById(R.id.tvFoodPrice_NNBinh);
            cardView = itemView.findViewById(R.id.cardViewFood_NNBinh);
        }
    }
}