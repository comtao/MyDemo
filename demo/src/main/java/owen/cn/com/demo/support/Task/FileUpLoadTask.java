package owen.cn.com.demo.support.Task;

import java.util.HashMap;
import java.util.Map;

import owen.cn.com.demo.support.http.HttpUtil;
import owen.cn.com.demo.support.http.MyAsyncTask;

/**
 * Created by tao.lai on 2015/9/18 0018.
 */
public class FileUpLoadTask extends MyAsyncTask<Void, Long, String> {
    private static final String TAG = "FileUpLoadTask";

    private String url;
    private String filePath;
    private TaskListener listener;
    private Map<String,String> params;

    public FileUpLoadTask(String url,String filePath,TaskListener listener,String ...params){
        this.url = url;
        this.filePath = filePath;
        this.listener = listener;
        if(this.params == null){
            this.params = new HashMap<String,String>();
        }
        this.params.put("Token","");
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
            rs = HttpUtil.getInstance().executeUploadTask(this.url,
                    this.params, this.filePath, "userfile", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (listener != null) {
            listener.onCompleteExecute(result);
        }
    }
}
