package owen.cn.com.demo.support.file;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import owen.cn.com.demo.utils.L;


/**
 * Created by tao.lai on 2015/9/18 0018.
 */
public class FileManager {

    public static boolean isExternalStorageMounted() {
        boolean canRead = Environment.getExternalStorageDirectory().canRead();
        boolean onlyRead = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED_READ_ONLY);
        boolean unMounted = Environment.getExternalStorageState().equals(
                Environment.MEDIA_UNMOUNTED);
        return !(!canRead || onlyRead || unMounted);
    }

    public static File createNewFileInSDCard(String absolutePath) {
        if (!isExternalStorageMounted()) {
            L.e("sdcard unavailiable");
            return null;
        }

        if (TextUtils.isEmpty(absolutePath)) {
            return null;
        }

        File file = new File(absolutePath);
        if (file.exists()) {
            return file;
        } else {
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }

            try {
                if (file.createNewFile()) {
                    return file;
                }
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }
}
