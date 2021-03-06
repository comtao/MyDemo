package owen.cn.com.demo.support.http;

import android.text.TextUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/**
 * Created by tao.lai on 2015/9/18 0018.
 */
public class Utility {
    private Utility() {
    }

    public static String encodeUrl(Map<String, String> param) {
        if (param == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Set<String> keys = param.keySet();
        boolean first = true;
        for (String key : keys) {
            String value = param.get(key);
            if (key.equals("Token")) {
                continue;
            } else if (!TextUtils.isEmpty(value) || key.equals("description")
                    || key.equals("url")) {
                if (first) {
                    first = false;
                } else {
                    sb.append("&");
                }
                try {
                    sb.append(URLEncoder.encode(key, "UTF-8")).append("=")
                            .append(URLEncoder.encode(param.get(key), "UTF-8"));
                } catch (UnsupportedEncodingException e) {

                }
            }
        }
        return sb.toString();
    }

    public static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {

            }
        }
    }

}
