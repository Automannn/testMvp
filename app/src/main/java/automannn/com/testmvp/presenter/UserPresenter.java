package automannn.com.testmvp.presenter;

import automannn.com.testmvp.UserActivity;
import automannn.com.testmvp.entity.User;
import automannn.com.testmvp.model.UserModel;

public class UserPresenter extends BasePresenter<UserModel,UserActivity> {
    public UserPresenter(UserActivity view) {
        super(view);
        currModel= new UserModel();
    }

    //生命周期的核心业务逻辑在此处书完成
    @Override
    public void init() {
        //设置数据源(注意，该数据源有可能来自任意的位置)
        currModel.setCurrentData(new User());
        //初始化
        currModel.initModelViewHolder(currView.getRootView());
    }
    public void submit() {
        currModel.V2MbindModelViewHolder();
        currView.showToast(currModel.getdata().toString());
        //新建一个模型，模拟用于同步视图
        currModel.setCurrentData(new User());
        currModel.M2VbindModelViewHolder();
    }
}
