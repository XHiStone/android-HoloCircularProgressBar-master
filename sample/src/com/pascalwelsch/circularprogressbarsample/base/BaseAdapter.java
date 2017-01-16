package com.pascalwelsch.circularprogressbarsample.base;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
*@Title: BaseAdapter
*@Description:  描述
*@date 2016/9/22 16:48
*@auther xie
*/
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    protected List<T> mList;
    protected LayoutInflater mInflater;
    protected Context mContext;

    public BaseAdapter(Context context) {
        mList = new ArrayList<T>();
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
    }

    public void clearData() {
        mList.clear();
    }

    public void addAllData(List<T> list) {
        if (!isEmpty(list)) {
            mList.addAll(list);
        }
    }
    
    public void setList(List<T> list){
            mList=list;
    	this.notifyDataSetChanged();
    }
    
    public void addAllDataAndNorify(List<T> list) {
        this.addAllData(list);
        this.notifyDataSetChanged();
    }

    public List<T> getData() {
        return mList;
    }

    @Override
    public int getCount() {
    	if(isEmpty(mList))
    		return 0;
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    
    protected String getString(int id){
    	return mContext.getResources().getString(id);
    }

    public boolean isEmpty(List<?> list) {
        return (list == null || list.size() == 0);
    }

}
