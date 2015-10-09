package owen.cn.com.chess.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/**
 * Created by tao.lai on 2015/9/21 0021.
 */
public class ImgUtils {
    private static final String TAG = "ImageUtils";

    private static ImgUtils instance;

    public static ImgUtils getInstance(){
        if(instance == null){
            instance = new ImgUtils();
        }
        return instance;
    }

    public Bitmap scaleImg(Bitmap bm,float scaleRatio){
        int width = bm.getWidth(); //图片宽度
        int height = bm.getHeight();//图片高度
        Matrix matrix = new Matrix();
        matrix.postScale((float)scaleRatio, (float)scaleRatio);//图片等比例缩小为原来的scaleRatio倍
        Bitmap bmResult = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);//声明位图
        return bmResult;
    }

    public Bitmap getBitmapFromRes(Context ctx,int resId){
        return BitmapFactory.decodeResource(ctx.getResources(),resId);
    }
}
