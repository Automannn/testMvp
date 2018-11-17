package automannn.com.testmvp.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;

import automannn.com.testmvp.R;


public class FabuPopupWindow extends PopupWindow  implements View.OnClickListener{
    private Activity activity;
    private OnPopWindowClickListener listener;

    private ImageView fabu, start_point_youji, start_point_gonglue,start_point_lvpai, end_point_youji, end_point_gonglue,end_point_lvpai;

    private float startPointYoujiY, startPointYoujiX;
    private float startPointGonglueY, startPointGonglueX;
    private float startPointLvpaiY, startPointLvpaiX;

    private float endPointYoujiY, endPointYoujiX;
    private int endPointGonglueY, endPointGonglueX;
    private int endPointLvpaiY, endPointLvpaiX;


    private View mMenuView;

    public FabuPopupWindow(Activity activity, OnPopWindowClickListener listener) {
        this.activity = activity;
        initView(activity, listener);
    }

    public void show() {
        Rect rect = new Rect();

        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int winHeight = activity.getWindow().getDecorView().getHeight();
        this.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, winHeight - rect.bottom);
    }

    @Override
    public void onClick(View view) {
        listener.onPopWindowClick(view);
        if (view.getId() == R.id.fabu) {
            exitAnimation();
        }
    }

    //接口
    public interface OnPopWindowClickListener {
        void onPopWindowClick(View view);
    }

    ///出去的动画
    private void exitAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 90, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(300);
        rotateAnimation.setInterpolator(new BounceInterpolator());
        fabu.startAnimation(rotateAnimation);


        TranslateAnimation sa = new TranslateAnimation(0, startPointYoujiX - endPointYoujiX,  0 , startPointYoujiY - endPointYoujiY);
        sa.setDuration(400);
        sa.setInterpolator(new BounceInterpolator());
        sa.setFillAfter(true);
        end_point_youji.startAnimation(sa);
        sa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                end_point_youji.setVisibility(View.GONE);
                dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        TranslateAnimation sa2 = new TranslateAnimation(0, startPointGonglueX - endPointGonglueX,  0 , startPointGonglueY - endPointGonglueY);
        sa2.setDuration(600);
        sa2.setInterpolator(new BounceInterpolator());
        end_point_gonglue.startAnimation(sa2);
        sa2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                end_point_gonglue.setVisibility(View.GONE);
                start_point_gonglue.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        TranslateAnimation sa3 = new TranslateAnimation(0, startPointLvpaiX - endPointLvpaiX,  0 , startPointLvpaiY - endPointLvpaiY);
        sa3.setDuration(800);
        sa3.setInterpolator(new BounceInterpolator());
        end_point_lvpai.startAnimation(sa3);
        sa3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                end_point_lvpai.setVisibility(View.GONE);
                start_point_lvpai.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    private void initView(Activity activity, OnPopWindowClickListener listener) {
        //设置按钮监听
        this.listener = listener;
        initViewSetting(activity);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        startAnimation();
    }


    //进去界面的动画
    private void startAnimation() {
        //最下面的添加按钮旋转按钮动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, 45, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(300);
        rotateAnimation.setInterpolator(new BounceInterpolator());
        rotateAnimation.setFillAfter(true);
        fabu.startAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                int location[] = new int[2];
                start_point_gonglue.getLocationOnScreen(location);
                startPointGonglueX = location[0];
                startPointGonglueY = location[1];

                int location1[] = new int[2];
                start_point_youji.getLocationOnScreen(location1);
                startPointYoujiX = location1[0];
                startPointYoujiY = location1[1];

                int location2[] = new int[2];
                start_point_lvpai.getLocationOnScreen(location2);
                startPointLvpaiX=location2[0];
                startPointLvpaiY=location2[1];

                int location3[] = new int[2];
                end_point_youji.getLocationOnScreen(location3);
                endPointYoujiX = location3[0];
                endPointYoujiY = location3[1];

                int location4[] = new int[2];
                end_point_gonglue.getLocationOnScreen(location4);
                endPointGonglueX = location4[0];
                endPointGonglueY = location4[1];

                int location5[] = new int[2];
                end_point_lvpai.getLocationOnScreen(location5);
                endPointLvpaiX=location5[0];
                endPointLvpaiY=location5[1];

                TranslateAnimation sa = new TranslateAnimation(0, endPointGonglueX - startPointGonglueX, 0, endPointGonglueY - startPointGonglueY);
                sa.setDuration(300);
                sa.setInterpolator(new BounceInterpolator());
                start_point_gonglue.startAnimation(sa);
                sa.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        start_point_gonglue.setVisibility(View.GONE);
                        start_point_youji.setVisibility(View.VISIBLE);
                        start_point_lvpai.setVisibility(View.VISIBLE);
                        end_point_gonglue.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                TranslateAnimation sa1 = new TranslateAnimation(0, endPointYoujiX - startPointYoujiX, 0, endPointYoujiY - startPointYoujiY);
                sa1.setDuration(600);
                sa1.setInterpolator(new BounceInterpolator());
                start_point_youji.startAnimation(sa1);
                sa1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        start_point_youji.setVisibility(View.GONE);
                        end_point_lvpai.setVisibility(View.VISIBLE);
                        end_point_youji.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                TranslateAnimation sa2 = new TranslateAnimation(0, endPointLvpaiX - startPointLvpaiX, 0, endPointLvpaiY - startPointLvpaiY);
                sa2.setDuration(900);
                sa2.setInterpolator(new BounceInterpolator());
                start_point_lvpai.startAnimation(sa2);
                sa2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        start_point_lvpai.setVisibility(View.GONE);
                        end_point_lvpai.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void initViewSetting(Activity context) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popup_add_devices, null);


        fabu = (ImageView) mMenuView.findViewById(R.id.fabu);
        fabu.setOnClickListener(this);

        end_point_gonglue = (ImageView) mMenuView.findViewById(R.id.point_end_gonglue_img);
        end_point_gonglue.setOnClickListener(this);

        end_point_youji = (ImageView) mMenuView.findViewById(R.id.point_end_youji_img);
        end_point_youji.setOnClickListener(this);

        end_point_lvpai = (ImageView) mMenuView.findViewById(R.id.point_end_lvpai_img);
        end_point_lvpai.setOnClickListener(this);

        start_point_youji = (ImageView) mMenuView.findViewById(R.id.point_start_youji);
        start_point_gonglue = (ImageView) mMenuView.findViewById(R.id.point_start_gonglue);
        start_point_lvpai = (ImageView) mMenuView.findViewById(R.id.point_start_lvpai);


    }
}
