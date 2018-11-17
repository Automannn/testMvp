package automannn.com.testmvp.adapter;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.List;

import automannn.com.testmvp.app.BaseApplication;
import automannn.com.testmvp.view.api.LinearLayoutAdapterView;


public abstract class MyLinearLayoutAdapter<T> extends BaseAdapter {
    //需要适配的数据集
    private  final List<T> dataList;
    //适配的每一项的视图资源id
    private  final int viewframeRes;
    //代表当前视图的父容器
    private  final ViewGroup viewGroup;
    public MyLinearLayoutAdapter(List<T> datalist, int viewframeres, ViewGroup viewgroup) {
        dataList = datalist;
        viewframeRes =viewframeres;
        viewGroup = viewgroup;
    }
    @Override
    public int getCount() {
        return dataList.size();
    }
    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        throw new RuntimeException("Stub!");
    }
    //用于将实体对象与视图做绑定
    public abstract ViewHolder bindView(T obj, View viewFrame);
    //用于获得窗口的相关属性，动态计算布局
    public abstract WindowManager getWindowManager();

    @Override
    public void notifyDataSetChanged() {
        WindowManager windowManager = getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        if (getCount()==0)throw new IllegalArgumentException("未正确使用，请设置非空数据源");
        //当数据源大于五个的时候，以五个菜单标准平分该屏幕
        if (getCount()>=5){
            width = width/5;
        }else {
            //当数据源小于5个的时候，以所有的菜单平分该屏幕
            width=width/getCount();
        }
        for (T t:dataList){
            //对于绝对布局充当跟布局的时候，所有的宽高属性会失效，需动态设置
            View viewFrame=LayoutInflater.from(BaseApplication.getContext()).inflate(viewframeRes,null,false);
            View view =bindView(t,viewFrame).getRootView();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.gravity= Gravity.BOTTOM;
            view.setLayoutParams(lp);
            viewGroup.addView(view);
        }
        viewGroup.requestLayout();
        //强制父容器刷新
        viewGroup.invalidate();
    }
    //该方法是关键，用于设置每个item的监听。
    public void setOnItemClick(final LinearLayoutAdapterView.OnItemClickDo onItemClickDo){
        final List sizeList =new ArrayList();
        //本质是通过循环分别为每个子视图设置监听，然后通过抽象实现它的扩展。
        for (int i=0;i<getCount();i++){
            final  int j = i;
            View view = viewGroup.getChildAt(i);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickDo.run(view,j);
                    sizeList.add(false);
                }
            });

        }
    }
}
