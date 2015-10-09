package owen.cn.com.demo;

import android.app.Application;

/**
 * Created by tao.lai on 2015/9/18 0018.
 */
public class GlobalContext extends Application{
    private static GlobalContext instance;

    public static GlobalContext getInstance(){
        if(instance == null){
            instance = new GlobalContext();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
