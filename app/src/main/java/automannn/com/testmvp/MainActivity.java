package automannn.com.testmvp;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import automannn.com.testmvp.adapter.MyLinearLayoutAdapter;
import automannn.com.testmvp.presenter.MainActivityPresenter;
import automannn.com.testmvp.view.api.ILinearListView;
import automannn.com.testmvp.view.api.IListView;
import automannn.com.testmvp.view.api.LinearLayoutListView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainActivityPresenter> implements IListView,ILinearListView {

    @BindView(R.id.main_bottom_container)
    public LinearLayoutListView linearLayoutListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void onPrepare() {
        currentPresenter.init();
    }
    @Override
    protected MainActivityPresenter initPresent() {
        return new MainActivityPresenter(this);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }
    @Override
    public void myOnItemClick(int postion) {

    }

    @Override
    public void setAdapter(Object adapter) {
        linearLayoutListView.setAdapter((MyLinearLayoutAdapter) adapter);
    }

    @Override
    public void search() {
        //donothing
    }

    @Override
    public ViewGroup getLinearLayoutListComponent() {
        return linearLayoutListView;
    }

    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    public void showToast(String msg, int type) {
        Toast.makeText(this,msg,type).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
