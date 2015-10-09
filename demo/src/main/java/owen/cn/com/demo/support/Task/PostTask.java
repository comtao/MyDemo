package owen.cn.com.demo.support.Task;

import owen.cn.com.demo.support.http.HttpMethod;

/**
 * Created by tao.lai on 2015/9/18 0018.
 */
public class PostTask extends BaseTask{

    public PostTask(boolean hasToken,HttpMethod httpMethod,String url,
                    TaskListener listener,String ...params){
        super(hasToken,httpMethod,url,listener,params);
    }

    public PostTask(String url,TaskListener listener,String ...params){
        this(true,HttpMethod.Post,url,listener,params);
    }
}
