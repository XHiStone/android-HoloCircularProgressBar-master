package com.pascalwelsch.circularprogressbarsample;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.nineoldandroids.view.ViewHelper;
import com.pascalwelsch.holocircularprogressbar.HoloCircularProgressBar;

import java.lang.reflect.Field;

/**
 * android-HoloCircularProgressBar-master
 * com.pascalwelsch.circularprogressbarsample
 *
 * @Author: xie
 * @Time: 2017/1/10 15:41
 * @Description:
 */


public class HomeActivity extends FragmentActivity {
    DrawerLayout drawerLayout;
    ImageView imageView;
    TextView tv_num;
    HoloCircularProgressBar holoCircularProgressBar;
    ViewFlipper viewFlipper;
    float integral = 0.2f;
    private ObjectAnimator mProgressBarAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        viewFlipper = (ViewFlipper) findViewById(R.id.vf);
        View view = View.inflate(this, R.layout.layout_roll_msg, null);
        View view1 = View.inflate(this, R.layout.layout_roll_msg, null);
        viewFlipper.addView(view);
        viewFlipper.addView(view1);
        TextView tv = (TextView) view.findViewById(R.id.tv_home_msg);
        TextView tv1 = (TextView) view1.findViewById(R.id.tv_home_msg);
        tv.setText("您参与的报名活动200积分已经到账");
        tv1.setText(tv.getText().toString());
        imageView = (ImageView) findViewById(R.id.img_menu);
//        setDrawerLeftEdgeSize(this, drawerLayout, 0.1f);
        ImageView sign = (ImageView) findViewById(R.id.img_home_sign);
        LinearLayout qrcode = (LinearLayout) findViewById(R.id.ll_card_num);
        tv_num = (TextView) findViewById(R.id.tv_integral_num);
        holoCircularProgressBar = (HoloCircularProgressBar) findViewById(R.id.holoCircularProgressBar);

        holoCircularProgressBar.setMarkerProgress(0.8f);
        holoCircularProgressBar.setMarkerProgressText("保级");
        animate(holoCircularProgressBar, null, integral, 1000);

        final SignDialog dialog = new SignDialog(this);
        final QRCodeDialog qrCodeDialog = new QRCodeDialog(this);
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrCodeDialog.show();
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holoCircularProgressBar.setProgress(0.4f);
//                if (integral >= 1f) integral = 0;
                integral = ((integral * 10 + 1) % 11) / 10;
                dialog.setTipText("恭喜您获得100积分");
                dialog.show();
            }
        });


        dialog.setOnSignListener(new SignDialog.OnSignListener() {
            @Override
            public void onClick() {
                dialog.dismiss();
                animate(holoCircularProgressBar, null, integral, 1000);
            }
        });

        MyDrawerToggle myDrawerToggle = new MyDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(myDrawerToggle);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    private void animate(final HoloCircularProgressBar progressBar, final Animator.AnimatorListener listener,
                         final float progress, final int duration) {

        mProgressBarAnimator = ObjectAnimator.ofFloat(progressBar, "progress", progress);
        mProgressBarAnimator.setDuration(duration);

        mProgressBarAnimator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationCancel(final Animator animation) {
            }

            @Override
            public void onAnimationEnd(final Animator animation) {
//                progressBar.setProgress(progress);
                holoCircularProgressBar.setThumbStrokeText("钻石卡");
                if (progress >= holoCircularProgressBar.getMarkerProgress())
                    progressBar.setMarkerProgress(((progress * 10 + 2) % 11) / 10);

            }

            @Override
            public void onAnimationRepeat(final Animator animation) {
            }

            @Override
            public void onAnimationStart(final Animator animation) {
            }
        });
        if (listener != null) {
            mProgressBarAnimator.addListener(listener);
        }
        mProgressBarAnimator.reverse();
        mProgressBarAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                progressBar.setProgress((Float) animation.getAnimatedValue());
                tv_num.setText("" + (int) ((Float) animation.getAnimatedValue() * (898 / 0.6)));
            }
        });
//        progressBar.setMarkerProgress(progress);
        mProgressBarAnimator.start();
    }


    class MyDrawerToggle extends ActionBarDrawerToggle {


        public MyDrawerToggle(Activity activity, DrawerLayout drawerLayout,
                              @StringRes int openDrawerContentDescRes,
                              @StringRes int closeDrawerContentDescRes) {
            super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            super.onDrawerSlide(drawerView, slideOffset);
            View mContent = drawerLayout.getChildAt(0);
            View mMenu = drawerView;
            float scale = 1 - slideOffset;
            ViewHelper.setTranslationX(mContent,
                    mMenu.getMeasuredWidth() * (1 - scale));
            mContent.invalidate();
        }
    }

    public static void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) return;
        try {
            Field leftDraggerField = drawerLayout.getClass().getDeclaredField("mLeftDragger");
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (dm.widthPixels * displayWidthPercentage)));
        } catch (Exception e) {
        }
    }
}
