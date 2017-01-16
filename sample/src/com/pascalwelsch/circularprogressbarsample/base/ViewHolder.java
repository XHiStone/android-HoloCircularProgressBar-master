package com.pascalwelsch.circularprogressbarsample.base;

import android.util.SparseArray;
import android.view.View;

/**
*@Title: ViewHolder
*@Description:  描述
*@date 2016/9/22 16:48
*@auther xie
*/
@SuppressWarnings("unchecked")
public class ViewHolder {
	public static <T extends View> T get(View convertView, int id) {
		SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();
		if (viewHolder == null) {
			viewHolder = new SparseArray<View>();
			convertView.setTag(viewHolder);
		}
		View childView = viewHolder.get(id);
		if (childView == null) {
			childView = convertView.findViewById(id);
			viewHolder.put(id, childView);
		}
		return (T) childView;
	}
}
