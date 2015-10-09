package owen.cn.com.chess.engine;

/**
 * Created by tao.lai on 2015/9/21 0021.
 */
public class ActionUtil {
    private static final String TAG = "ActionUtil";

    private static ActionUtil instance;

    public static ActionUtil getInstance(){
        if(instance == null){
            instance = new ActionUtil();
        }
        return instance;
    }
}
