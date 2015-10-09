package owen.cn.com.chess.ui;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import owen.cn.com.chess.Constant;
import owen.cn.com.chess.ui.canvas.CanvasUtils;
import owen.cn.com.chess.utils.L;
import owen.cn.com.chess.utils.T;

/**
 * Created by tao.lai on 2015/9/19 0019.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "GameView";

    private AtyMain atyMain;
    private Paint paint;

    private int boxMagin = 100;
    private boolean started = false;   //是否开始

    private SurfaceHolder mHolder;
    private Canvas canvas;


    public GameView(AtyMain aty) {
        super(aty);
        this.atyMain = aty;
        this.getHolder().addCallback(this);//设置生命周期回调接口的实现者
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @SuppressLint("WrongCall")
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.mHolder = holder;
        this.canvas = null;
        try{
            canvas = mHolder.lockCanvas();
            synchronized (mHolder){
                onDraw(canvas);
            }
        }finally {
            if(canvas != null){
                mHolder.unlockCanvasAndPost(canvas);
            }
        }
        new Thread() {
            @SuppressLint("WrongCall")
            public void run() {
                onDrawCanvas();
            }
        }.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        L.i(TAG, "surfaceChanged...");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        L.i(TAG, "surfaceDestroyed...");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(0XF5DAD1);
        paint.setAlpha(255);
        canvas.drawRect(0, 0, Constant.width, Constant.height, paint);

        L.i(TAG,"start drawLine...");
        initGameView(canvas);
    }

    @SuppressLint("WrongCall")
    private void onDrawCanvas()
    {
        try {
            canvas = mHolder.lockCanvas(null);
            synchronized (mHolder) {
                onDraw(canvas);//绘制
            }
        } finally {
            if (canvas != null) {
                //并释放锁
                mHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private void initGameView(Canvas canvas){
        CanvasUtils.getInstance().initBitmap(atyMain);
        CanvasUtils.getInstance().drawChessBoard(paint, canvas, Constant.xStart, Constant.yStart);
        CanvasUtils.getInstance().drawMenu(paint, canvas);
        CanvasUtils.getInstance().initPieces(paint,canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.i(TAG,"MotionEvent  action_down");
                handleEvent(e.getX(), e.getY());
            break;
            case MotionEvent.ACTION_MOVE:
                L.i(TAG,"MotionEvent  action_move");
                break;
            case MotionEvent.ACTION_UP:
                L.i(TAG,"MotionEvent  action_up");
                break;
            default:
                L.i(TAG,"MotionEvent  others");
                break;
        }
        return false;
    }

    private void handleEvent(float xEvent, float yEvent) {
        L.i(TAG, "EventOption = " + CanvasUtils.getInstance().getEventOption(xEvent, yEvent));
        switch (CanvasUtils.getInstance().getEventOption(xEvent, yEvent)) {
            case Constant.GAME_START:
                T.showShort(atyMain,"game to be start...");
                break;
            case Constant.GMAE_RE_START:
                T.showShort(atyMain,"game to be reStart...");
                break;
            default:
                L.e(TAG,"undefine event...");
                break;
        }
    }
}
