package com.pascalwelsch.circularprogressbarsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * android-HoloCircularProgressBar-master
 * com.pascalwelsch.circularprogressbarsample
 *
 * @Author: xie
 * @Time: 2017/1/11 9:56
 * @Description:
 */


public class MenuLeftFragment extends Fragment {
    View view;
    ListView listView;
    List<MenuBean> list;

    private String[] name = {"我的订单", "酒店预定", "推荐有礼", "我的资料", "我要吐槽"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.layout_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) view.findViewById(R.id.lv_menu_name);
        list = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            MenuBean bean = new MenuBean(0, name[i], false);
            if (i == 0) bean.setSelected(true);
            list.add(bean);
        }
        HomeMuneAdapter adapter = new HomeMuneAdapter(getContext());
        adapter.setList(list);
        listView.setAdapter(adapter);
        
    }


}
