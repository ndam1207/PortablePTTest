package com.example.hihihahahehe.portablept.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hihihahahehe.portablept.R;

/**
 * Created by hieuv on 8/9/2017.
 */

public class RegisterFragment extends Fragment {

    public RegisterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_account, container, false);

        return view;
    }
}
