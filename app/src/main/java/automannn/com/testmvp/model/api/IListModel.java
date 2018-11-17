package automannn.com.testmvp.model.api;

import java.util.List;

import automannn.com.testmvp.entity.SerializableObject;


public interface IListModel<T> extends IModel {
    //获得列表数据源
    List<T> getDataList();
    //初始化列表数据源
    void setDataList(List<T> list);
    //获得适配器
    Object getAdapter();
    //适配器更新
    void myNotifyDataChanged();
    //获得列表数据源中的第几项
    SerializableObject getData(int position);
    //一些其它的初始化
    void init();
}
