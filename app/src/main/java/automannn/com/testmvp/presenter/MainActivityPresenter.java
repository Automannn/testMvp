package automannn.com.testmvp.presenter;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;


import com.alibaba.fastjson.JSONArray;

import java.util.List;

import automannn.com.testmvp.MainActivity;
import automannn.com.testmvp.R;
import automannn.com.testmvp.entity.MainBottomItem;
import automannn.com.testmvp.entity.UnicodeResponse;
import automannn.com.testmvp.model.MainBottomItemModel;
import automannn.com.testmvp.retrofitApi.MainBottomApi;
import automannn.com.testmvp.retrofitApi.RetrofitConfigUtil;
import automannn.com.testmvp.view.api.LinearLayoutAdapterView;
import automannn.com.testmvp.widget.FabuPopupWindow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivityPresenter extends BasePresenter<MainBottomItemModel,MainActivity> {
    public MainActivityPresenter(MainActivity view) {
        super(view);
        currModel = new MainBottomItemModel();
    }
    @Override
    public void init() {
        currModel.setRootView(currView.getLinearLayoutListComponent());
        currModel.setWindowManager(currView.getWindowManager());

        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitConfigUtil.TEST_SERVER_LOCATION).addConverterFactory(GsonConverterFactory.create()).build();
        MainBottomApi mainBottomApi= retrofit.create(MainBottomApi.class);
        Call<UnicodeResponse> unicodeResponseCall= mainBottomApi.chaxun(new MainBottomItem(),6,0);
        unicodeResponseCall.enqueue(new Callback<UnicodeResponse>() {
            @Override
            public void onResponse(Call<UnicodeResponse> call, Response<UnicodeResponse> response) {
                if (response.body().getState()>500){
                }else {
                    List<MainBottomItem> list = new JSONArray((List<Object>) response.body().getData()).toJavaList(MainBottomItem.class);
                    currModel.setDataList(list);
                    currView.setAdapter(currModel.getAdapter());

                    currView.linearLayoutListView.setOnItemClickListener(new LinearLayoutAdapterView.OnItemClickDo() {
                        @Override
                        public void exec(View view,int position) {
                            if (position==2){
                                 FabuPopupWindow fabuPopupWindow =new FabuPopupWindow(currView, new FabuPopupWindow.OnPopWindowClickListener() {
                                     @Override
                                     public void onPopWindowClick(View view) {
                                         switch (view.getId()){
                                             case R.id.point_end_youji_img:
                                                 Toast.makeText(currView,"点击了游记",Toast.LENGTH_SHORT).show();
                                                 break;
                                             case R.id.point_end_gonglue_img:
                                                 Toast.makeText(currView,"点击了攻略",Toast.LENGTH_SHORT).show();
                                                 break;
                                         }
                                     }
                                });
                                fabuPopupWindow.show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<UnicodeResponse> call, Throwable t) {
                System.out.println(t.getCause());
            }
        });
    }
}
