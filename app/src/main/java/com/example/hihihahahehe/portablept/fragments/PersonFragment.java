package com.example.hihihahahehe.portablept.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hihihahahehe.portablept.R;
import com.example.hihihahahehe.portablept.details.ClientDetailsFragment;
import com.example.hihihahahehe.portablept.utils.RealmHandle;
import com.example.hihihahahehe.portablept.utils.ScreenManager;
import com.example.hihihahahehe.portablept.models.FaceBookModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {
    private static final String TAG = PersonFragment.class.toString();
    @BindView(R.id.cv_information)
    CardView cvInformation;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.cv_my_pack)
    CardView cvMyPack;
    private ClientDetailsFragment clientProfile;
    private ManagerPackFragment managerPackFragment;
    private FaceBookModel faceBookModel;

    public PersonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        initView(view);
        return view;
    }

    public void initView(View view) {
        ButterKnife.bind(this, view);
        setOnClickItem();
        loadData();
    }

    public void loadData() {
        if (RealmHandle.getData() != null) {
            faceBookModel = RealmHandle.getData();

            Picasso.with(getContext()).load(faceBookModel.getImg()).transform(new CropCircleTransformation()).into(ivAvatar);
            String firstName;
            String lastName;
            if (faceBookModel.getFirst_Name() == null) {
                firstName = "";
            } else firstName = faceBookModel.getFirst_Name();

            if (faceBookModel.getLast_Name() == null) {
                lastName = "";
            } else lastName = faceBookModel.getLast_Name();
            tvName.setText(lastName + " " + firstName);
        }
    }

    public void setOnClickItem() {
        cvInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientProfile = new ClientDetailsFragment();
                ScreenManager.replaceFragment(getActivity().getSupportFragmentManager(), clientProfile, R.id.layout_container, true);
            }
        });

        cvMyPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managerPackFragment = new ManagerPackFragment();
                ScreenManager.replaceFragment(getActivity().getSupportFragmentManager(), managerPackFragment, R.id.layout_container, true);
            }
        });
    }
}
