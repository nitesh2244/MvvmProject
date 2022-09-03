package com.example.mvvmdemo.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.adapter.SwipePagerAdapter;
import com.example.mvvmdemo.databinding.ActivityTabBinding;
import com.example.mvvmdemo.databinding.FragmentFirstBinding;
import com.example.mvvmdemo.databinding.FragmentSecondBinding;
import com.example.mvvmdemo.fragment.FirstFragment;
import com.example.mvvmdemo.fragment.SecondFragment;

public class TabActivity extends AppCompatActivity {
    FragmentSecondBinding fragmentSecondBinding;
    FragmentFirstBinding fragmentFirstBinding;
    ActivityTabBinding binding;
    Context context;
    private Fragment fragment = null;
    private Class fragmentClass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_tab);
      //  setupViewPager(binding.viewPager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentManager.beginTransaction().add(R.id.flFragment, new FirstFragment()).commit();


    }

//    private void setupViewPager(ViewPager viewPager) {
//     //   binding.tabLayout.setupWithViewPager(viewPager);
//        SwipePagerAdapter adapter = new SwipePagerAdapter(this.getSupportFragmentManager());
//        adapter.addFragment(new FirstFragment(), getResources().getString(R.string.first_fragment));
//        adapter.addFragment(new SecondFragment(), getResources().getString(R.string.second_fragment));
//        viewPager.setAdapter(adapter);
//
//    }


}