package automannn.com.testmvp.model;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import automannn.com.testmvp.R;
import automannn.com.testmvp.adapter.MyLinearLayoutAdapter;
import automannn.com.testmvp.adapter.ViewHolder;
import automannn.com.testmvp.app.BaseApplication;
import automannn.com.testmvp.entity.MainBottomItem;
import automannn.com.testmvp.entity.SerializableObject;
import automannn.com.testmvp.model.api.ILinearLayoutListModel;
import automannn.com.testmvp.model.api.IListModel;

public class MainBottomItemModel implements IListModel<MainBottomItem>,ILinearLayoutListModel {
    private List<MainBottomItem> dataList;
    private ViewGroup viewGroup;

    private WindowManager windowManager;

    private MyLinearLayoutAdapter<MainBottomItem> myLinearLayoutAdapter;

    @Override
    public List<MainBottomItem> getDataList() {
        return dataList;
    }

    @Override
    public void setDataList(List<MainBottomItem> list) {
        dataList = list;
    }

    @Override
    public ListAdapter getAdapter() {
        myLinearLayoutAdapter = new MyLinearLayoutAdapter<MainBottomItem>(dataList, R.layout.main_bottom_layout_item,viewGroup) {
            @Override
            public ViewHolder bindView(MainBottomItem obj, View viewFrame) {
                ViewHolder viewHolder = new ViewHolder(viewFrame);
                viewHolder.setText(R.id.icon_text,obj.getText());
                Glide.with(BaseApplication.getContext()).load(obj.getIconUrl()).into((ImageView) viewHolder.getView(R.id.icon));
                return viewHolder;
            }

            @Override
            public WindowManager getWindowManager() {
                return windowManager;
            }
        };
        return myLinearLayoutAdapter;
    }

    @Override
    public void myNotifyDataChanged() {
        myLinearLayoutAdapter.notifyDataSetChanged();
    }

    @Override
    public SerializableObject getData(int position) {
        return dataList.get(position);
    }

    @Override
    public void init() {

    }

    @Override
    public void setRootView(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @Override
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;

    }
}
