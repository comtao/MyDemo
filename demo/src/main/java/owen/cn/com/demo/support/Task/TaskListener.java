package owen.cn.com.demo.support.Task;

import owen.cn.com.demo.Configs;

/**
 * Created by tao.lai on 2015/9/18 0018.
 */
public class TaskListener {

    public void onComplete(String rsStr){

    }

    public void onPreExecute(){

    }

    public void onFail(){

    }

    public void onCompleteExecute(String rsStr){
        if(Configs.RESPONSE_FAIL.equals(rsStr)){
            onFail();
            return ;
        }
        onComplete(rsStr);
    }
}
