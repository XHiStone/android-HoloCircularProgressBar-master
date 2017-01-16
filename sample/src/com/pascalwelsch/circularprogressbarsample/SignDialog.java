package com.pascalwelsch.circularprogressbarsample;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * android-HoloCircularProgressBar-master
 * com.pascalwelsch.circularprogressbarsample
 *
 * @Author: xie
 * @Time: 2017/1/12 17:24
 * @Description:
 */


public class SignDialog extends Dialog {
    private OnSignListener listener;
    private TextView tip;

    public SignDialog(Context context) {
        super(context, R.style.NormalDialog);
        View v = View.inflate(context, R.layout.layout_sign_dialog, null);
        setContentView(v);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.verticalMargin = 0.1f;
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP);
        dialogWindow.setAttributes(lp);
        tip = (TextView) v.findViewById(R.id.tv_sing_tip);
        Button btn = (Button) v.findViewById(R.id.btn_sing_ok);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick();
            }
        });
    }

    public void setOnSignListener(OnSignListener listener) {
        this.listener = listener;
    }

    public void setTipText(String text) {
        tip.setText(text);
    }

    public interface OnSignListener {
        void onClick();
    }

}
