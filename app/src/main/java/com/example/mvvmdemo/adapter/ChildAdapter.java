package com.example.mvvmdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.auth.model.MChild;
import com.example.mvvmdemo.databinding.ChildItemBinding;

import java.util.ArrayList;

public class ChildAdapter  extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MChild> mChildren;
    private OnItemClickListenerChild onItemClickListenerChild;

    public ChildAdapter(Context context, ArrayList<MChild> mChildren, OnItemClickListenerChild onItemClickListenerChild) {
        this.context = context;
        this.mChildren = mChildren;
        this.onItemClickListenerChild = onItemClickListenerChild;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChildItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.child_item,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MChild list = mChildren.get(position);
        list.setImage(R.drawable.ic_avtar_white);
        list.setName("Avatar");
        Glide.with(context)
                .load(list.getImage())
                .into(holder.binding.imgIcon);
        holder.binding.txtName.setText(list.getName());
        
        holder.binding.imgIcon.setOnClickListener(view -> {
          //  Toast.makeText(context, "clicked on horizontal" +"\n"+ position, Toast.LENGTH_SHORT).show();
            onItemClickListenerChild.onItemClick(position);
        });

    }
    @Override
    public int getItemCount() {
        return mChildren.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ChildItemBinding binding;
        public ViewHolder(@NonNull ChildItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    public interface OnItemClickListenerChild {
        void onItemClick(int position);
    }

}
