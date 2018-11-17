package automannn.com.testmvp.view.api;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import automannn.com.testmvp.adapter.MyLinearLayoutAdapter;


public class LinearLayoutListView extends LinearLayoutAdapterView<MyLinearLayoutAdapter> {
    public LinearLayoutListView(Context context) {
        super(context);
    }

    public LinearLayoutListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LinearLayoutListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //设置适配器后，默认让其自动刷新。 注意这里的刷新逻辑也是完全自己定义的
    //其大致的过程就是： linearLayout.addView(childrenView);  根据数据源中的大小自动适配
    @Override
    public void setAdapter(MyLinearLayoutAdapter var1) {
       currAdapter = var1;
       currAdapter.notifyDataSetChanged();
    }
    //该方法实际就是作为了视图与适配器的中转中心，视图时逻辑的入口，逻辑的实现位于适配器中
    @Override
    public void setOnItemClickListener(OnItemClickDo onItemClickDo) {
        currAdapter.setOnItemClick(onItemClickDo);
    }
}
