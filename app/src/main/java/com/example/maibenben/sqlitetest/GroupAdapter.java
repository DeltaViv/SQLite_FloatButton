package com.example.maibenben.sqlitetest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//import com.examplple.android.recyclerview.R;

/**
 * 分组 的 Adapter
 *
 * Created by Tnno Wu on 2018/03/05.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    private static final String TAG = GroupAdapter.class.getSimpleName();
    private View inflater;
    private Context mContext;

    private List<GroupDataBean> mList = new ArrayList<>();

    public GroupAdapter(List<GroupDataBean> list) {
        mList = list;
    }

//    public void setGroupDataList(List<GroupDataBean> list) {
//        mList = list;
//
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_recycle_item,parent,false);
        GroupViewHolder myviewholder = new GroupViewHolder(inflater);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        holder.tvArea.setText(mList.get(position).getArea());
        holder.tvTeam.setText(mList.get(position).getTeam());
        holder.tvImage.setImageResource(mList.get(position).getimageid());
        if (position == 0) {
            holder.tvArea.setVisibility(View.VISIBLE);
        } else {
            if (mList.get(position).getArea().equals(mList.get(position - 1).getArea())) {
                holder.tvArea.setVisibility(View.GONE);
            } else {
                holder.tvArea.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {

        TextView tvArea, tvTeam;
        ImageView tvImage;
        public GroupViewHolder(View itemView) {
            super(itemView);
            tvArea = (TextView) itemView.findViewById(R.id.tv_area);
            tvTeam = (TextView) itemView.findViewById(R.id.tv_team);
            tvImage = (ImageView) itemView.findViewById(R.id.tv_img);
        }
    }
}
