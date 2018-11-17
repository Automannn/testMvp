package automannn.com.testmvp.model.api;

import android.view.View;

import automannn.com.testmvp.adapter.ViewHolder;


public interface ISingleModel<T> extends IModel{
    //通知模型更新
    void myNotifyDataSetChanged();
    //初始化视图更新绑定
    void initModelViewHolder(View rootView);
    //从模型同步到视图
    void M2VbindModelViewHolder();
    //从视图同步到模型
    void V2MbindModelViewHolder();
    //获得当前的模型
    T getdata();
    //一些初始化工作
    void init();
    //设置当前模型
    void setCurrentData(T o);
    //获得逻辑上的适配器
    Object getAdapter();
    //viewholder的包装类
    class ModelViewHolder{
        ViewHolder viewHolder;
        public ModelViewHolder(View rootView){
            viewHolder = new ViewHolder(rootView);
        }

        public ViewHolder getViewHolder(){
            return  this.viewHolder;
        }

    }
}
