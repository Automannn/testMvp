package automannn.com.testmvp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import automannn.com.testmvp.presenter.BasePresenter;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected T currentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        currentPresenter = initPresent();
        onPrepare();
    }

    protected abstract void onPrepare();

    protected abstract T initPresent();

    protected abstract int getLayout();

    protected abstract void initView();
}
