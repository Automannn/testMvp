package automannn.com.testmvp.view.api;

public interface IListView extends IView {
    //点击列表的某一项
    void myOnItemClick(int postion);
    //设置适配器
    void setAdapter(Object adapter);
    //搜索某一项
    void search();
}
