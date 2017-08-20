package com.example.hihihahahehe.portablept.details;


import android.hardware.camera2.params.Face;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hihihahahehe.portablept.R;
import com.example.hihihahahehe.portablept.events.OnLoginEvent;
import com.example.hihihahahehe.portablept.models.FaceBookModel;
import com.example.hihihahahehe.portablept.utils.RealmHandle;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientDetailsFragment extends Fragment {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_city)
    EditText edtCity;
    @BindView(R.id.edt_birth)
    EditText edtBirthDay;
    @BindView(R.id.edt_gen)
    EditText edtGen;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    private FaceBookModel faceBookModel;

    public ClientDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_profile, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ButterKnife.bind(this, view);
        setOnClickItem();
        loadInfo();
    }

    public void loadInfo() {
        faceBookModel = RealmHandle.getData();
        if(faceBookModel != null){
            Picasso.with(getContext()).load(faceBookModel.getImg()).transform(new CropCircleTransformation()).into(ivAvatar);
            String firstName;
            String lastName;
            if(faceBookModel.getFirst_Name() == null){
                firstName = "";
            } else firstName = faceBookModel.getFirst_Name();

            if(faceBookModel.getLast_Name() == null){
                lastName = "";
            } else lastName = faceBookModel.getLast_Name();
            edtName.setText(lastName + " " + firstName);
            edtEmail.setText(faceBookModel.getEmail());
            edtBirthDay.setText(faceBookModel.getBirthday());
            edtCity.setText(faceBookModel.getLocation());
            if(faceBookModel.getGender().equals("male")){
                edtGen.setText("Nam");
            } else if(faceBookModel.getGender().equals("female")){
                edtGen.setText("Nữ");
            }
        }
    }

    private void setOnClickItem() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });
    }
}