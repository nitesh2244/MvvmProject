package com.example.mvvmdemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.auth.model.MProduct;
import com.example.mvvmdemo.databinding.ProductItemBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<MProduct.Data> dataList;

    public ProductAdapter(Context context, ArrayList<MProduct.Data> dataList ) {
        this.context =context;
        this.dataList = dataList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.product_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      MProduct.Data mProduct = dataList.get(position);
      holder.binding.txtItem.setText(new Gson().toJson(mProduct));


    }

    @Override
    public int getItemCount() {
            return dataList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ProductItemBinding binding;
        public ViewHolder(@NonNull ProductItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

}
