package owen.cn.com.demo.support.Task;

import owen.cn.com.demo.support.http.HttpMethod;

/**
 * Created by tao.lai on 2015/9/18 0018.
 */
public class GetTask extends BaseTask {

    public GetTask(String url,
                   TaskListener listener, String... params) {
        super(HttpMethod.Get, url, listener, params);
    }

}
