package com.example.mvvmdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.auth.model.UserData;
import com.example.mvvmdemo.databinding.ActivityUserListImageBinding;
import com.example.mvvmdemo.databinding.ProductItemBinding;
import com.example.mvvmdemo.databinding.UserItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<UserData> userData ;
    ImageView imageView;

    public UserAdapter(Context context, ArrayList<UserData> userData) {
        this.context = context;
        this.userData = userData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.user_item,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         UserData data = userData.get(position);
         holder.binding.txtItem.setText(data.getTitle());
         Glide.with(context)
                 .load(R.drawable.ic_avtar_white)
                 .into(holder.binding.ivIcon);

    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       UserItemBinding binding;
        public ViewHolder(@NonNull UserItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}