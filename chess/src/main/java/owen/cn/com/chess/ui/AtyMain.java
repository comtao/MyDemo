package owen.cn.com.chess.ui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import owen.cn.com.chess.Constant;

/**
 * Created by tao.lai on 2015/9/19 0019.
 */
public class AtyMain extends Activity {
    private static String TAG = "AtyMain";

    private GameView gameView;
    private WelcomeView welcomeView;
    private WhichView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //设置横屏模式
        setVolumeControlStream(AudioManager.STREAM_MUSIC);  //游戏过程中只允许多媒体音量,而不允许通化音量

        initPm();
       //goWelcomeView();
        goGameView();
    }

    /**Handler handler =  new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    wv = WhichView.GAME_VIEW;
                    goGameView();
                    break;
            }
        }
    };*/

    private void goWelcomeView() {
        if (welcomeView == null) {
            welcomeView = new WelcomeView(this);
        }
        setContentView(welcomeView);
        wv = WhichView.WELCOME_VIEW;
    }

    private void goGameView() {
        if (gameView == null) {
            gameView = new GameView(this);
        }
        setContentView(gameView);
        wv = WhichView.GAME_VIEW;
    }

    private void initPm() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int tempHeight = (int) (Constant.height = dm.heightPixels);
        int tempWidth = (int) (Constant.width = dm.widthPixels);

        if (tempHeight > tempWidth) {
            Constant.height = tempHeight;
            Constant.width = tempWidth;
        } else {
            Constant.height = tempWidth;
            Constant.width = tempHeight;
        }

        float zoomX = Constant.width / 480;
        float zoomY = Constant.height / 800;
        if (zoomX > zoomY) {
            Constant.xZoom = Constant.yZoom = zoomY;
        } else {
            Constant.xZoom = Constant.yZoom = zoomX;
        }
    }
}
