package owen.cn.com.chess;

import android.app.Application;
import android.content.Context;

/**
 * Created by tao.lai on 2015/9/29 0029.
 */
public class GlobalContext extends Application {

    private static GlobalContext instance;

    public Context ctx;

    public static GlobalContext getInstance(){
        if(instance == null){
            instance = new GlobalContext();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.ctx = this;
    }
}
