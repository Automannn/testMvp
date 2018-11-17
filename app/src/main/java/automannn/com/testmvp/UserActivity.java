package automannn.com.testmvp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import automannn.com.testmvp.presenter.UserPresenter;
import automannn.com.testmvp.view.api.ISingleModelView;

public class UserActivity extends BaseActivity<UserPresenter> implements ISingleModelView {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPrepare() {
        currentPresenter.init();
    }

    @Override
    protected UserPresenter initPresent() {
        return new UserPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user;
    }

    @Override
    protected void initView() {
        button =findViewById(R.id.user_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPresenter.submit();
            }
        });
    }

    @Override
    public View getRootView() {
        return getWindow().getDecorView();
    }

    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
