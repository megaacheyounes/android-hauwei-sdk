package com.synerise.sdk.sample.ui.dev;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void populateData(T data);
}
