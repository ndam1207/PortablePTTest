package com.example.hihihahahehe.portablept.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hihihahahehe.portablept.R;
import com.example.hihihahahehe.portablept.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PackFragment extends Fragment {
    public static final String TAG = PackFragment.class.toString();
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    public PackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pack, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        ButterKnife.bind(this, view);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        String[] listPack = {
                "Fitness",
                "Zumba",
                "Kickfit",
                "Boxing"
        };

        for(int i = 0; i < listPack.length; i++){
            Utils.addTab(tabLayout,listPack[i]);
        }
    }
}
