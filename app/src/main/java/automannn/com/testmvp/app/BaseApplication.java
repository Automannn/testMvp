package automannn.com.testmvp.app;


import android.app.Application;
import android.content.Context;


public class BaseApplication extends Application {
    private static Context context;


    //创建
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }

}
