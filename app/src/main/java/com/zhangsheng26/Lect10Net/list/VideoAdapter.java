package com.zhangsheng26.Lect10Net.list;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangsheng26.Lect10Net.WebActivity;
import com.zhangsheng26.Lect10Net.bean.VideoInfo;
import com.zhangsheng26.Lect10Net.ImageLoader;
import com.zs26app.R;

import java.util.List;

public class VideoAdapter extends BaseAdapter {
        private  String TAG="VideoAdapter";
        private List<VideoInfo>mDataList;
        private Context mContext;
        private LayoutInflater mInflater;
    public VideoAdapter(List<VideoInfo> list, Context context) {
        this.mDataList = list;
        this.mContext = context;
        mInflater=LayoutInflater.from(mContext);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "---getView()" + position + "convertView:" + convertView);
        ViewHolder holder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.item_movie, null);
            holder = new ViewHolder();
            holder.itemLayout=convertView.findViewById(R.id.layout_item);
            holder.iconIV = convertView.findViewById(R.id.iv_icon);
            holder.tltTV = convertView.findViewById(R.id.tv_title);
            holder.profileTV=convertView.findViewById(R.id.tv_profile);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final VideoInfo item = mDataList.get(position);
        holder.tltTV.setText(item.getTitle());
        holder.profileTV.setText(item.getProfile());
        ImageLoader.getInstance().load(holder.iconIV, item.getThumbPath());
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, WebActivity.class);
                intent.putExtra(WebActivity.WEB_URL,item.getFilePath());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        int count=(null==mDataList ? 0 : mDataList.size());
        Log.i(TAG,"---getCount()"+count);
        return count;
    }
    @Override
    public VideoInfo getItem(int position) {
        Log.i(TAG,"---getItem()"+position);
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.i(TAG,"---getItemId()"+position);
        return position;
    }
    public void setData(List<VideoInfo> list) {
        mDataList.clear();
        if (null!=list){
            mDataList.addAll(list);
        }
    }
    private class  ViewHolder{
        View itemLayout;
        ImageView iconIV;
        TextView tltTV;
        TextView profileTV;
    }
}


