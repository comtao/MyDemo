package owen.cn.com.demo.utils;

import android.graphics.BitmapFactory;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by tao.lai on 2015/9/18 0018.
 */
public class ImageUtils {

    public static boolean isThisBitmapCanRead(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int width = options.outWidth;
        int height = options.outHeight;
        if (width == -1 || height == -1) {
            return false;
        }
        return true;
    }

}
