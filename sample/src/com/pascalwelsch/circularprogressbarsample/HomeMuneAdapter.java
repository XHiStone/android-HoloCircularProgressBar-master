package com.pascalwelsch.circularprogressbarsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pascalwelsch.circularprogressbarsample.base.BaseAdapter;
import com.pascalwelsch.circularprogressbarsample.base.ViewHolder;

/**
 * android-HoloCircularProgressBar-master
 * com.pascalwelsch.circularprogressbarsample
 *
 * @Author: xie
 * @Time: 2017/1/11 15:27
 * @Description:
 */


public class HomeMuneAdapter extends BaseAdapter<MenuBean> {

    public HomeMuneAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_menu_layout, parent, false);
        TextView tvName = ViewHolder.get(convertView, R.id.tv_menu_name);
        tvName.setText(mList.get(position).getName());

        return convertView;
    }
}
