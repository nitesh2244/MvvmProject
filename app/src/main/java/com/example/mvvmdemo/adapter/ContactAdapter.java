package com.example.mvvmdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.auth.model.MContact;
import com.example.mvvmdemo.databinding.ContactItemBinding;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MContact> mContacts;

    public ContactAdapter(Context context, ArrayList<MContact> mContacts) {
        this.context = context;
        this.mContacts = mContacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.contact_item,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MContact list = mContacts.get(position);
        holder.binding.txtName.setText(list.getName());
        holder.binding.txtNo.setText(list.getPhone());
        holder.binding.txtNo.setOnClickListener(view -> {
            Intent call = new Intent(Intent.ACTION_CALL);

        });
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ContactItemBinding binding;
        public ViewHolder(@NonNull ContactItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
