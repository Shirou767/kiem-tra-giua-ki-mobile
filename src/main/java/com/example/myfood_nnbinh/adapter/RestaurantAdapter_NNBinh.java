
package com.example.myfood_nnbinh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_nnbinh.R;
import com.example.myfood_nnbinh.model.Restaurant_NNBinh;

import java.util.ArrayList;

public class RestaurantAdapter_NNBinh extends RecyclerView.Adapter<RestaurantAdapter_NNBinh.ViewHolder> {

    private final ArrayList<Restaurant_NNBinh> restaurantList;
    private final OnRestaurantClickListener listener;

    public interface OnRestaurantClickListener {
        void onRestaurantClick(int restaurantId);
    }

    public RestaurantAdapter_NNBinh(ArrayList<Restaurant_NNBinh> restaurantList, OnRestaurantClickListener listener) {
        this.restaurantList = restaurantList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant_nnbinh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant_NNBinh restaurant = restaurantList.get(position);
        holder.tvName.setText(restaurant.getName());
        holder.tvAddress.setText(restaurant.getAddress());
        holder.cardView.setOnClickListener(v -> listener.onRestaurantClick(restaurant.getId()));
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvRestaurantName_NNBinh);
            tvAddress = itemView.findViewById(R.id.tvRestaurantAddress_NNBinh);
            cardView = itemView.findViewById(R.id.cardViewRestaurant_NNBinh);
        }
    }
}
