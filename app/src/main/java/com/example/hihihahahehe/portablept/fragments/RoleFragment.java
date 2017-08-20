package com.example.hihihahahehe.portablept.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hihihahahehe.portablept.R;
import com.example.hihihahahehe.portablept.models.FaceBookModel;
import com.example.hihihahahehe.portablept.utils.RealmHandle;
import com.example.hihihahahehe.portablept.utils.ScreenManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by valky on 8/7/2017.
 */

public class RoleFragment extends Fragment {
    private FragmentManager fm;

    @BindView(R.id.tv_trainer)
    TextView tvTrainer;

    @BindView(R.id.tv_trainee)
    TextView tvTrainee;

    public RoleFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_roles, container, false);

        setupUI(view);
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        onClick();
    }

    private void onClick() {
        fm = getActivity().getSupportFragmentManager();

        tvTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScreenManager.replaceFragment(fm,
                        new FirstScreenFragment(), R.id.layout_container_main, false);
            }
        });

        tvTrainee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScreenManager.replaceFragment(fm,
                        new FirstScreenFragment(), R.id.layout_container_main, false);
            }
        });

    }
}
