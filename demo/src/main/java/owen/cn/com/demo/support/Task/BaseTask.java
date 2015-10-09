package owen.cn.com.demo.support.Task;

import java.util.HashMap;
import java.util.Map;

import owen.cn.com.demo.GlobalContext;
import owen.cn.com.demo.support.http.HttpMethod;
import owen.cn.com.demo.support.http.HttpUtil;
import owen.cn.com.demo.support.http.MyAsyncTask;
import owen.cn.com.demo.utils.L;
import owen.cn.com.demo.utils.NetUtil;

/**
 * Created by tao.lai on 2015/9/18 0018.
 */
public class BaseTask extends MyAsyncTask<Void, Long, String>{
    private static final String TAG = "BaseTask";

    private String url;
    private Map<String, String> params;
    private TaskListener listener;
    private HttpMethod httpMethod;

    public BaseTask(HttpMethod httpMethod,String url,TaskListener listener,String ...params){
       this(true,httpMethod,url,listener,params);
    }

    public BaseTask(boolean hasToken, HttpMethod httpMethod, String url,
                    TaskListener listener, String... params) {
        this.url = url;
        this.listener = listener;
        this.httpMethod = httpMethod;
        if(this.params == null){
            this.params = new HashMap<String,String>();
        }
        for(int i=0; i<params.length;i=i+2){
          this.params.put(params[i],params[i+1]);
        }
        if(hasToken){
            this.params.put("Token", "");
        }
        if (!NetUtil.isConnnected(GlobalContext.getInstance())) {
            L.e(TAG, "网络未连接，请检查网络");
            return;
        }
        this.executeOnExecutor(MyAsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (listener != null) {
            listener.onPreExecute();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        String rs = "";
        try {
            rs = HttpUtil.getInstance().executeNormalTask(this.httpMethod,
                    this.url, this.params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        L.i(TAG, this.url + "\n resultStr =" + result);
        if (listener != null) {
            listener.onCompleteExecute(result);
        }
    }
}
