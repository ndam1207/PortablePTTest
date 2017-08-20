package com.example.hihihahahehe.portablept.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hihihahahehe.portablept.R;
import com.example.hihihahahehe.portablept.models.HotSportsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valky on 8/17/2017.
 */

public class HotSportsAdapter extends RecyclerView.Adapter<HotSportsAdapter.HotSportsViewHolder> {
    private List<HotSportsModel> hotSportsModelList = new ArrayList<>();
    private Context context;
    private View.OnClickListener onClickListener;

    public void setOnItemClick(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public HotSportsAdapter(List<HotSportsModel> hotSportsModelList, Context context) {
        this.hotSportsModelList = hotSportsModelList;
        this.context = context;
    }

    @Override
    public HotSportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_hot_sport, parent, false);
        view.setOnClickListener(onClickListener);
        return new HotSportsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotSportsViewHolder holder, int position) {
        holder.setData(hotSportsModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return hotSportsModelList.size();
    }


    public class HotSportsViewHolder extends RecyclerView.ViewHolder {
        TextView tvSportName;
        ImageView ivSportImage;

        View view;
        public HotSportsViewHolder(View itemView) {
            super(itemView);
            tvSportName = (TextView) itemView.findViewById(R.id.tv_sport_name);
            ivSportImage = (ImageView) itemView.findViewById(R.id.iv_sport_image);

            view = itemView;
        }

        public void setData(HotSportsModel hotSportsModel){
            if (hotSportsModel != null){
                tvSportName.setText("Boxing");
                ivSportImage.setImageResource(R.drawable.sample_sport_image);
            }
        }
    }

}
