package automannn.com.testmvp.view.api;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import automannn.com.testmvp.R;
import automannn.com.testmvp.app.BaseApplication;
import automannn.com.testmvp.util.DbPxUtils;


//改类的本质任然是一个ViewGroup， 通过继承LinearLayout进行扩展
public abstract class LinearLayoutAdapterView<T extends Adapter> extends LinearLayout{
    //与该视图匹配的视图适配器
    T currAdapter;

    public LinearLayoutAdapterView(Context context) {
        super(context);
    }
    public LinearLayoutAdapterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LinearLayoutAdapterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //该抽象方法用于设置适配器
    public abstract void setAdapter(T var1);
    //该抽象方法用于设置每个子视图的点击事件监听，通过自定义的静态抽象内部类作为参数进行扩展
    public abstract void setOnItemClickListener(OnItemClickDo onItemClickDo);

    public abstract static class OnItemClickDo {
        //该参数用于辅助记录上一次点击的按钮位置
        private Integer currPosition;
        //该抽象函数用于完成自定一的业务逻辑
       public abstract void exec(View view,int position);
       //该函数用于执行最终的操作，是一个模板方法，具有两个步骤，第一个是完成固定的视图刷新逻辑，第二个是完成扩展的业务逻辑控制
       public void run(View view,int position){
           changeView(view,position);
           exec(view,position);
       }
       //完成视图切换的逻辑，是固定的，用户不可见
       private void changeView(View view,int position){
           //当上一次没有进行操作的时候
           if (currPosition==null){
               //找到要扩大的图片
              ImageView imageView= view.findViewById(R.id.icon);
              //一些缩放逻辑。。  由于基础不行，生产了很多的坑
               view.setLayoutParams(new LayoutParams(view.getLayoutParams().width,210 ));
               RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 150);
               imageView.setLayoutParams(lp);
               TextView textView = view.findViewById(R.id.icon_text);
               textView.setTextSize(15.0f);
               currPosition=position;
           }else {
               //当上一次的点击与这一次的点击相同的时候
               if (currPosition == position){
                   //do nothing
               }else {
                   //先撤销该操作
                   ViewGroup viewGroup = (ViewGroup) view.getParent();
                   View preView= viewGroup.getChildAt(currPosition);
                   ((TextView)preView.findViewById(R.id.icon_text)).setTextSize(11.0f);
                   RelativeLayout.LayoutParams lbb=new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, DbPxUtils.dip2px(BaseApplication.getContext(),40));
                   ((ImageView) preView.findViewById(R.id.icon)).setLayoutParams(lbb);
                   LayoutParams ly= (LayoutParams) view.getLayoutParams();
                   ly.gravity= Gravity.CENTER_VERTICAL;
                   preView.setLayoutParams(new LayoutParams(ly));
                   //之后将新的换上
                   ImageView imageView= view.findViewById(R.id.icon);
                   view.setLayoutParams(new LayoutParams(view.getLayoutParams().width,210 ));
                   RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 150);
                   imageView.setLayoutParams(lp);
                   TextView textView = view.findViewById(R.id.icon_text);
                   textView.setTextSize(15.0f);
                   currPosition=position;
               }
           }

       }

    }
}
