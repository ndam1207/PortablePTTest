package com.example.hihihahahehe.portablept.fragments;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hihihahahehe.portablept.R;
import com.example.hihihahahehe.portablept.adapters.HotCoachesAdapter;
import com.example.hihihahahehe.portablept.adapters.HotSportsAdapter;
import com.example.hihihahahehe.portablept.models.HotCoachesModel;
import com.example.hihihahahehe.portablept.models.HotSportsModel;
import com.example.hihihahahehe.portablept.models.JSONModel.PackJSONModel;
import com.example.hihihahahehe.portablept.networks.RetrofitFactory;
import com.example.hihihahahehe.portablept.networks.services.GetPacks;

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
public class HomeFragment extends Fragment {
    @BindView(R.id.tv_hot)
    TextView tvHot;
    @BindView(R.id.home_pager)
    ViewPager homePager;


    RecyclerView rvHotCoaches;

    @BindView(R.id.tv_hot_sports)
    TextView tvHotSports;
    RecyclerView rvHotSports;

    private List<HotCoachesModel> hotCoachesModelList = new ArrayList<>();
    private List<HotSportsModel> hotSportsModelList = new ArrayList<>();

    private HotCoachesAdapter hotCoachesAdapter;
    private HotSportsAdapter hotSportsAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvHotCoaches = (RecyclerView) view.findViewById(R.id.rv_hot_coaches);
        rvHotSports = (RecyclerView) view.findViewById(R.id.rv_hot_sports);

        loadData();
        setupUI(view);

        return view;
    }

    private void loadData() {
        final GetPacks getPacks = RetrofitFactory.getInstance().create(GetPacks.class);
        getPacks.getPacks().enqueue(new Callback<List<PackJSONModel>>() {
            @Override
            public void onResponse(Call<List<PackJSONModel>> call, Response<List<PackJSONModel>> response) {
                for(PackJSONModel packJSONModel : response.body()){
                    HotCoachesModel hotCoachesModel = new HotCoachesModel();
                    hotCoachesModel.setName(packJSONModel.getCoach());
                    hotCoachesModelList.add(hotCoachesModel);
                }
                hotCoachesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PackJSONModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load data into HomeFragment", Toast.LENGTH_SHORT);
            }
        });
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);

        hotCoachesAdapter = new HotCoachesAdapter(hotCoachesModelList, getContext());
        rvHotCoaches.setAdapter(hotCoachesAdapter);

        HotSportsModel hotSportsModel = new HotSportsModel();
        hotSportsModelList.add(hotSportsModel);
        hotSportsAdapter = new HotSportsAdapter(hotSportsModelList, getContext());
        rvHotSports.setAdapter(hotSportsAdapter);

        final LinearLayoutManager linearLayoutManagerCoaches = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        final LinearLayoutManager linearLayoutManagerSports = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        rvHotCoaches.setLayoutManager(linearLayoutManagerCoaches);
        rvHotSports.setLayoutManager(linearLayoutManagerSports);
    }
}
