package com.example.hihihahahehe.portablept.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hihihahahehe.portablept.R;
import com.example.hihihahahehe.portablept.models.FaceBookModel;
import com.example.hihihahahehe.portablept.models.JSONModel.PackJSONModel;
import com.example.hihihahahehe.portablept.networks.RetrofitFactory;
import com.example.hihihahahehe.portablept.networks.services.AddPack;
import com.example.hihihahahehe.portablept.utils.RealmHandle;
import com.example.hihihahahehe.portablept.utils.ScreenManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreatePackFragment extends Fragment {
    private static final String TAG = CreatePackFragment.class.toString();
    private FaceBookModel faceBookModel;
    @BindView(R.id.edt_pack_name)
    EditText edtPackName;
    @BindView(R.id.edt_pack_des)
    EditText edtPackDes;
    @BindView(R.id.edt_pack_price)
    EditText edtPackPrice;
    @BindView(R.id.bt_cancel)
    TextView btCancel;
    @BindView(R.id.bt_create)
    TextView btCreate;

    public CreatePackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_pack, container, false);
        setupUI(view);
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);

        setOnClickItem();
    }

    private void setOnClickItem() {
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPackDes.clearComposingText();
            }
        });

        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AddPack addPack = RetrofitFactory.getInstance().create(AddPack.class);

                faceBookModel = RealmHandle.getData();
                String phoneNumber = faceBookModel.getPhone();
                String purpose = String.valueOf(edtPackDes.getText());
                String packName = String.valueOf(edtPackName.getText());
                String coach = faceBookModel.getLast_Name() + " " + faceBookModel.getFirst_Name();
                String price = edtPackPrice.getText().toString() + " VND";
                String duration = " ";
                //TODO get duration

                PackJSONModel model = new PackJSONModel(phoneNumber,purpose,packName,coach,price,duration);
                Log.d(TAG, model.toString());
                addPack.addPack(model).enqueue(new Callback<PackJSONModel>() {
                    @Override
                    public void onResponse(Call<PackJSONModel> call, Response<PackJSONModel> response) {
                        Toast.makeText(getContext(), "Success !", Toast.LENGTH_SHORT).show();
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack();
                    }

                    @Override
                    public void onFailure(Call<PackJSONModel> call, Throwable t) {
                        Toast.makeText(getContext(), "Fail to create new Pack!", Toast.LENGTH_SHORT).show();
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack();
                    }
                });
            }
        });
    }
}
