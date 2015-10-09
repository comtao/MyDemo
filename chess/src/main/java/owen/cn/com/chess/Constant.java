package owen.cn.com.chess;

/**
 * Created by tao.lai on 2015/9/21 0021.
 */
public class Constant {

    public static float width;
    public static float height;

    public static float xZoom;
    public static float yZoom;

    public static float xStart = 0;
    public static float yStart = 0;

    public static float boardMagin = 48;  //棋盘边距

    public static final byte GAME_START    = 0x0; //游戏开始
    public static final byte GMAE_RE_START = 0x1; //游戏重新开始
    public static final byte SELECT_PIECES = 0x2; //选中要移动到棋子
    public static final byte MOVE_PIECES   = 0x3; //移动棋子到新位置
}
