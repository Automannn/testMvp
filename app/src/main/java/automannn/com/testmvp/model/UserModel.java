package automannn.com.testmvp.model;

import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import automannn.com.testmvp.R;
import automannn.com.testmvp.adapter.ViewHolder;
import automannn.com.testmvp.entity.User;
import automannn.com.testmvp.model.api.ISingleModel;


public class UserModel implements ISingleModel<User> {
    private User data;

    private ModelViewHolder modelViewHolder;

    @Override
    public User getdata() {
        return data;
    }

    @Override
    public Adapter getAdapter() {
        return null;
    }

    @Override
    public void init() {

    }



    @Override
    public void setCurrentData(User o) {
        data =o;
    }

    @Override
    public void myNotifyDataSetChanged() {
        modelViewHolder.getViewHolder().invalidate();
    }

    @Override
    public void initModelViewHolder(View rootView) {
        modelViewHolder = new ModelViewHolder(rootView);
    }

    @Override
    public void M2VbindModelViewHolder() {
        ViewHolder viewHolder = modelViewHolder.getViewHolder();
        viewHolder.setText(R.id.user_nickname,data.getNickName());
        viewHolder.setText(R.id.user_realname,data.getRealName());
        viewHolder.setText(R.id.user_sex,data.getSex());
        viewHolder.setText(R.id.user_age,data.getAge()==null?"":data.getAge()+"");
        viewHolder.setText(R.id.user_email,data.getEmail());
    }

    @Override
    public void V2MbindModelViewHolder() {
        ViewHolder viewHolder = modelViewHolder.getViewHolder();
        data.setNickName(((TextView)viewHolder.getView(R.id.user_nickname)).getText().toString());
        data.setRealName(((TextView)viewHolder.getView(R.id.user_realname)).getText().toString());
        data.setSex(((TextView)viewHolder.getView(R.id.user_sex)).getText().toString());
        data.setAge(Integer.valueOf(((TextView)viewHolder.getView(R.id.user_age)).getText().toString()));
        data.setEmail(((TextView)viewHolder.getView(R.id.user_email)).getText().toString());
    }

}
