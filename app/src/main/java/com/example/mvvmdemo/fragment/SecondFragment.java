package com.example.mvvmdemo.fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {
    FragmentSecondBinding binding;
    public static final String TAG = SecondFragment.class.getSimpleName();
    private String s;
    private Context context;
    private Activity activity;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

//    public SecondFragment(Context context, Activity activity) {
//        this.context = context;
//        this.activity = activity;
//    }

    public static SecondFragment newInstance(String param1) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
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
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()),R.layout.fragment_second,container,false);
        Bundle bundle = getArguments();
        if(bundle!=null){
            s = bundle.getString("Data");
            binding.textResult.setText(s);
        }
        binding.btnSend.setOnClickListener(view -> {
            passdata();
        });

        return binding.getRoot();

    }


    private void passdata(){
        if(isValidate()){
            String name = binding.edtData.getText().toString();
            getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK,  new Intent().putExtra("return",name));
            getFragmentManager().popBackStack();
            getFragmentManager().beginTransaction().addToBackStack(null);

        }
    }

    public boolean isValidate() {
        if (TextUtils.isEmpty(binding.edtData.getText().toString().trim())) {
            binding.edtData.setError(getResources().getString(R.string.please_enter_text));
            return false;
        } else {
            return true;
        }

    }

}