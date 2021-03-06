package com.synerise.sdk.sample.ui.cart.adapter.item;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.synerise.sdk.sample.R;
import com.synerise.sdk.sample.ui.cart.OnCartItemListener;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private final LayoutInflater inflater;
    private final List<CartItem> cartItems = new ArrayList<>();
    private final OnCartItemListener listener;

    public CartAdapter(LayoutInflater inflater, List<CartItem> cartItems, OnCartItemListener listener) {
        this.inflater = inflater;
        if (cartItems != null) this.cartItems.addAll(cartItems);
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(itemView, new CartViewHolder.OnCartItemRemoved() {
            @Override
            public void onSingleItemRemoved(int position) {
                CartItem cartItem = cartItems.get(position);
                cartItem.decrementQuantity();
                if (cartItem.getQuantity() == 0) {
                    listener.onItemRemoved(cartItem);
                    notifyItemRemoved(position);
                } else {
                    listener.onItemQuantityReduced(cartItem);
                    notifyItemChanged(position);
                }
            }

            @Override
            public void onAllItemsRemoved(int position) {
                listener.onItemRemoved(cartItems.get(position));
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.populateData(cartItems.get(position));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
