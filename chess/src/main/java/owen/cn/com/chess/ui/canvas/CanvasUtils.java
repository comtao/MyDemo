package owen.cn.com.chess.ui.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

import owen.cn.com.chess.Constant;
import owen.cn.com.chess.R;
import owen.cn.com.chess.entity.Ponint;
import owen.cn.com.chess.utils.ImgUtils;

/**
 * Created by tao.lai on 2015/9/29 0029.
 */
public class CanvasUtils {
    private static CanvasUtils instance;

    private Bitmap btnStart,btnReStart,piecesBlcak,piecesRed;

    private List<Ponint> mChessBoard;  //棋盘各个位置坐标

    public static CanvasUtils getInstance(){
        if(instance == null){
            instance = new CanvasUtils();
        }
        return instance;
    }

    public void initBitmap(Context ctx){
        btnStart = ImgUtils.getInstance().getBitmapFromRes(ctx, R.mipmap.start);
        btnReStart = ImgUtils.getInstance().getBitmapFromRes(ctx,R.mipmap.re_start);
        piecesBlcak = ImgUtils.getInstance().getBitmapFromRes(ctx,R.mipmap.pieces_black);
        piecesRed = ImgUtils.getInstance().getBitmapFromRes(ctx,R.mipmap.pieces_red);
    }

    /**
     * 画棋盘
     * @param paint
     * @param canvas
     * @param xStart
     * @param yStart
     */
    public void drawChessBoard(Paint paint,Canvas canvas, float xStart, float yStart) {
        initBoardPoint();

        paint.setColor(Color.parseColor("#666666"));
        paint.setStrokeWidth(3);
        Ponint start,end;

        //横线
        for (int i = 0; i < mChessBoard.size(); i = i + 3) {
            start = mChessBoard.get(i);
            end = mChessBoard.get(i + 2);
            canvas.drawLine(start.getX(), start.getY(), end.getX(), end.getY(), paint);
        }
        //竖线
        for (int i = 0; i < 3; i++) {
            start = mChessBoard.get(i);
            end = mChessBoard.get(i + 6);
            canvas.drawLine(start.getX(), start.getY(), end.getX(), end.getY(), paint);
        }
        //对角线
        for (int i = 0; i < 3; i = i + 2) {
            start = mChessBoard.get(i);
            end = mChessBoard.get(8 - i);
            canvas.drawLine(start.getX(), start.getY(), end.getX(), end.getY(), paint);
        }
    }

    /**
     * 画功能菜单
     * @param paint
     * @param canvas
     */
    public void drawMenu(Paint paint, Canvas canvas) {
        canvas.drawBitmap(btnStart,
                Constant.boardMagin,
                Constant.height - Constant.boardMagin - btnStart.getHeight(), paint);
        canvas.drawBitmap(btnReStart,
                Constant.width - Constant.boardMagin - btnReStart.getWidth(),
                Constant.height - Constant.boardMagin - btnStart.getHeight(), paint);
    }

    /**
     * 初始化棋盘位置坐标
     */
    private void initBoardPoint() {
        mChessBoard = new ArrayList<Ponint>();
        mChessBoard.add(
                new Ponint(
                        Constant.xStart + Constant.boardMagin,
                        Constant.yStart + Constant.boardMagin));
        mChessBoard.add(
                new Ponint(
                        Constant.width / 2,
                        Constant.yStart + Constant.boardMagin));
        mChessBoard.add(
                new Ponint(
                        Constant.width - Constant.boardMagin,
                        Constant.yStart + Constant.boardMagin));
        mChessBoard.add(
                new Ponint(
                        Constant.xStart + Constant.boardMagin,
                        Constant.width / 2));
        mChessBoard.add(
                new Ponint(
                        Constant.width / 2,
                        Constant.width / 2));
        mChessBoard.add(
                new Ponint(
                        Constant.width - Constant.boardMagin,
                        Constant.width / 2));
        mChessBoard.add(
                new Ponint(
                        Constant.xStart + Constant.boardMagin,
                        Constant.width - Constant.boardMagin));
        mChessBoard.add(
                new Ponint(
                        Constant.width / 2,
                        Constant.width - Constant.boardMagin));
        mChessBoard.add(
                new Ponint(
                        Constant.width - Constant.boardMagin,
                        Constant.width - Constant.boardMagin));
    }

    /**
     * 初始化棋子
     */
    public void initPieces(Paint paint, Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            canvas.drawBitmap(piecesBlcak,
                    mChessBoard.get(i).getX() - piecesBlcak.getWidth() / 2,
                    mChessBoard.get(i).getY() - piecesBlcak.getHeight() / 2,
                    paint);
            mChessBoard.get(i).setHasPieces(true);
            mChessBoard.get(i).setPiecesType(Ponint.PIECES_BLACK);
            canvas.drawBitmap(piecesRed,
                    mChessBoard.get(i + 6).getX() - piecesRed.getWidth() / 2,
                    mChessBoard.get(i + 6).getY() - piecesRed.getHeight() / 2,
                    paint);
            mChessBoard.get(i + 6).setHasPieces(true);
            mChessBoard.get(i + 6).setPiecesType(Ponint.PIECES_RED);
        }
    }



    public byte getEventOption(float x, float y) {
        byte rs = -1;
        if (x > Constant.boardMagin
                && x < (Constant.boardMagin + btnStart.getWidth())
                && y > (Constant.height - Constant.boardMagin - btnStart.getHeight())
                && y < (Constant.height - Constant.boardMagin)) {
            rs = Constant.GAME_START;
        } else if (x > (Constant.width - Constant.boardMagin - btnReStart.getWidth())
                && x < (Constant.width - Constant.boardMagin)
                && y > (Constant.height - Constant.boardMagin - btnReStart.getHeight())
                && y < (Constant.height - Constant.boardMagin)) {
            rs = Constant.GMAE_RE_START;
        }
        return rs;
    }
}
