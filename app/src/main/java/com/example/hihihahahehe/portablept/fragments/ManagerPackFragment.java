package com.example.hihihahahehe.portablept.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hihihahahehe.portablept.R;
import com.example.hihihahahehe.portablept.adapters.PackAdapter;
import com.example.hihihahahehe.portablept.models.FaceBookModel;
import com.example.hihihahahehe.portablept.models.JSONModel.PackJSONModel;
import com.example.hihihahahehe.portablept.models.PackModel;
import com.example.hihihahahehe.portablept.networks.RetrofitFactory;
import com.example.hihihahahehe.portablept.networks.services.GetPacks;
import com.example.hihihahahehe.portablept.utils.RealmHandle;
import com.example.hihihahahehe.portablept.utils.ScreenManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManagerPackFragment extends Fragment {
    private FragmentManager fm;
    private List<PackModel> packModelList = new ArrayList<>();
    private FaceBookModel faceBookModel;

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.fab_add_pack)
    FloatingActionButton fabAddPack;
    @BindView(R.id.rv_packs)
    RecyclerView rvPacks;

    public ManagerPackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_pack, container, false);

        setupUI(view);
        loadData();
        return view;
    }

    private void setupListPack() {
        rvPacks.setAdapter(new PackAdapter(packModelList, getContext()));
        rvPacks.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void loadData() {
        final GetPacks getPacks = RetrofitFactory.getInstance().create(GetPacks.class);
        faceBookModel = RealmHandle.getData();
        getPacks.getPacks().enqueue(new Callback<List<PackJSONModel>>() {
            @Override
            public void onResponse(Call<List<PackJSONModel>> call, Response<List<PackJSONModel>> response) {
                if(response != null){
                    Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
                }
                for(PackJSONModel packJSONModel : response.body()){
                    PackModel packModel = new PackModel();
                    packModel.setPackName(packJSONModel.getPackName());
                    packModel.setCoachName(faceBookModel.getLast_Name() + " " + faceBookModel.getFirst_Name());
                    packModel.setGoal(packJSONModel.getPurpose());
                    packModel.setCost(packJSONModel.getPrice());
                    packModel.setDuration(packJSONModel.getDuration());

                    packModelList.add(packModel);
                }
            }

            @Override
            public void onFailure(Call<List<PackJSONModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Không thể kết nối đến server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        setupListPack();
        setOnClickItem();
    }

    private void setOnClickItem() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        fabAddPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScreenManager.openFragment(getActivity().getSupportFragmentManager(), new CreatePackFragment(), R.id.layout_container, true);
            }
        });
    }

}
