package com.example.mvvmdemo.auth.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.adapter.ChildAdapter;
import com.example.mvvmdemo.adapter.ParentAdapter;
import com.example.mvvmdemo.auth.model.MChild;
import com.example.mvvmdemo.auth.model.MParent;
import com.example.mvvmdemo.databinding.ActivityNestedRecyclerViewBinding;

import java.util.ArrayList;

public class NestedRecyclerViewActivity extends AppCompatActivity implements ParentAdapter.OnItemClickListener {
    ActivityNestedRecyclerViewBinding binding;
    private final ArrayList<MParent> mParents = new ArrayList<>();
    private final ArrayList<MChild> mChildren = new ArrayList<>();

    @Override
    public void onParentAdapterClick(int position, int parentPosition) {
       // Toast.makeText(this, "child"+position+"parent"+parentPosition, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_nested_recycler_view);
        for(int i =0; i<10; i++){
            mParents.add(new MParent("",""));
        }
        for(int i=0 ;i<10; i++){
            mChildren.add(new MChild(R.drawable.ic_facebook,"",i));

        }

        for (int i = 0; i < 10; i++) {
            Log.i(TAG, "onCreate: "+mChildren.get(i).getParentPosition());
        }

        initRecyclerView();
    }
    private void initRecyclerView() {
        binding.rvParent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvParent.setHasFixedSize(true);
        binding.rvParent.setItemAnimator(new DefaultItemAnimator());
        ParentAdapter parentAdapter = new ParentAdapter(this ,mParents, mChildren,this);
        binding.rvParent.setAdapter(parentAdapter);
        parentAdapter.notifyDataSetChanged();


    }


}