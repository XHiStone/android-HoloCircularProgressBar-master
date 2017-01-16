package com.pascalwelsch.circularprogressbarsample;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

/**
 * android-HoloCircularProgressBar-master
 * com.pascalwelsch.circularprogressbarsample
 *
 * @Author: xie
 * @Time: 2017/1/13 10:07
 * @Description:
 */


public class QRCodeDialog extends Dialog {
    public QRCodeDialog(Context context) {
        super(context,R.style.NormalDialog);
        View v = View.inflate(context, R.layout.layout_qrcode_dialog, null);
        setContentView(v);
    }
}
