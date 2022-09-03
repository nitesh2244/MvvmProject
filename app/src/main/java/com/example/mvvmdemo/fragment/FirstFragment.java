package com.example.mvvmdemo.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.databinding.FragmentFirstBinding;
import com.example.mvvmdemo.utils.AppUtils;
import com.google.android.gms.safetynet.VerifyAppsConstants;


public class FirstFragment extends Fragment {

    FragmentFirstBinding binding;
    private Activity activity;
    private final int FRAGMENT_CODE = 100;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Context context;
    private String tag;

    private String mParam1;
    private String mParam2;

    public FirstFragment() {

    }

    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()),R.layout.fragment_first,container,false);
        binding.btnSend.setOnClickListener(view -> {
            if(isValidate()){
                String data = binding.edtData.getText().toString().trim();
                Bundle bundle = new Bundle();
                bundle.putString("Data",data);
//              FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                SecondFragment secondFragment = new SecondFragment();
                secondFragment.setArguments(bundle);
                secondFragment.setTargetFragment(FirstFragment.this,FRAGMENT_CODE);
                AppUtils.addFragmentBackStack(getFragmentManager(),secondFragment,SecondFragment.TAG,true);
//              fragmentManager.beginTransaction().replace(R.id.flFragment,secondFragment).commit();
            }

        });

        return binding.getRoot();
    }

    public boolean isValidate() {
        if (TextUtils.isEmpty(binding.edtData.getText().toString().trim())) {
            binding.edtData.setError(getResources().getString(R.string.please_enter_name));
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK ) {
            String display = data.getStringExtra("return");
            binding.textResult.setText(display);
        }
    }

}

