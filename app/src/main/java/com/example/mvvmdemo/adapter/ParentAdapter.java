package com.example.mvvmdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.auth.model.MChild;
import com.example.mvvmdemo.auth.model.MParent;
import com.example.mvvmdemo.databinding.ParentItemBinding;

import java.util.ArrayList;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> implements ChildAdapter.OnItemClickListenerChild {


    private Context context;
    private ArrayList<MParent> mParents;
    private ArrayList<MChild> mChildren;
    private final OnItemClickListener listener;
    private int childPosition;
    private ViewHolder holderGlobal;



    public ParentAdapter(Context context, ArrayList<MParent> mParents, ArrayList<MChild> mChildren, OnItemClickListener listener) {
        this.context = context;
        this.mParents = mParents;
        this.mChildren = mChildren;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ParentItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.parent_item,parent,false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holderGlobal = holder;
        MParent list = mParents.get(position);
        list.setHeading("Recommended for you");
        list.setTitle("Trending");
        holder.binding.textHeading.setText(list.getHeading());
        holder.binding.textJustForYou.setText(list.getTitle());
        holder.binding.textHeading.setOnClickListener(view -> {
            listener.onParentAdapterClick(childPosition, mChildren.get(position).getParentPosition());
            Toast.makeText(context, "PARENT POSITION" +"\n"+  position+" \n CH Position : "+childPosition, Toast.LENGTH_SHORT).show();

        });



        holder.binding.rvChild.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.binding.rvChild.setHasFixedSize(true);
        holder.binding.rvChild.setItemAnimator(new DefaultItemAnimator());
        mChildren.get(position).setParentPosition(position);
        ChildAdapter childAdapter = new ChildAdapter(context,mChildren,this);
        holder.binding.rvChild.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return mParents.size();
    }

    @Override
    public void onItemClick(int position) {
        if(holderGlobal!=null){
            childPosition  = position;
            holderGlobal.binding.textHeading.performClick();

        }




    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       ParentItemBinding binding;

        public ViewHolder(@NonNull ParentItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
    public interface OnItemClickListener {
        void onParentAdapterClick(int position, int parentPosition);
    }



}
