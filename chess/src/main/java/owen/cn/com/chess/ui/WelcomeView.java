package owen.cn.com.chess.ui;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import owen.cn.com.chess.Constant;
import owen.cn.com.chess.R;
import owen.cn.com.chess.utils.ImgUtils;
import owen.cn.com.chess.utils.L;

/**
 * Created by tao.lai on 2015/9/21 0021.
 */
public class WelcomeView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "WelComeView";

    private AtyMain atyMain;
    private Paint paint;
    private int currentAlpha = 0;
    private int sleepSpan = 50; //动画时延 ms
    private Bitmap currentBitmap ;
    private Bitmap[] bitmaps = new Bitmap[2];

    private int currentX;  //图片位置
    private int currentY;  //

    public WelcomeView(AtyMain aty) {
        super(aty);
        this.atyMain = aty;
        this.getHolder().addCallback(this);

        paint = new Paint();
        paint.setAntiAlias(true);

        bitmaps[0] = ImgUtils.getInstance().getBitmapFromRes(atyMain,R.mipmap.anthor);
        bitmaps[1] = BitmapFactory.decodeResource(atyMain.getResources(), R.mipmap.name);
    }

    public void onDraw(Canvas canvas){
        paint.setColor(Color.BLACK);
        paint.setAlpha(255);
        canvas.drawRect(0,0, Constant.width,Constant.height,paint);
        if(currentBitmap == null){
            return;
        }
        paint.setAlpha(currentAlpha);
        canvas.drawBitmap(currentBitmap,currentX,currentY,paint);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread(){
            @SuppressLint("WrongCall")
            public void run(){
                for(Bitmap bm : bitmaps){
                    currentBitmap = bm;
                    currentX = (int) (Constant.width / 2 - bm.getWidth() / 2);
                    currentY = (int) (Constant.height / 2 - bm.getHeight() / 2);
                    for(int i=255; i > -10; i=i-10){
                        currentAlpha = i;
                        if(currentAlpha < 0){
                            currentAlpha = 0;
                        }
                        SurfaceHolder sHolder = WelcomeView.this.getHolder();
                        Canvas canvas = sHolder.lockCanvas();
                        try{
                            synchronized (sHolder){
                                onDraw(canvas);
                            }
                        }catch (Exception e){
                            L.e(TAG,"somethings wrong");
                        }finally {
                            if(canvas != null){
                                sHolder.unlockCanvasAndPost(canvas);
                            }
                        }
                        try{
                            if(i == 255){
                                Thread.sleep(1000);
                            }
                            Thread.sleep(sleepSpan);
                        }catch (Exception e){
                            L.e(TAG,"");
                        }
                    }
                }
                //atyMain.handler.sendEmptyMessage(0);
            }
        }.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {


    }
}
