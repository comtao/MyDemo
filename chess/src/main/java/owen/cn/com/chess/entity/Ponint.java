package owen.cn.com.chess.entity;

/**
 * Created by tao.lai on 2015/9/30 0030.
 */
public class Ponint {
    public static final byte PIECES_BLACK = 0x1;
    public static final byte PIECES_RED   = 0x2;

    private float x;
    private float y;

    private boolean hasPieces;
    private byte piecesType;


    public Ponint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isHasPieces() {
        return hasPieces;
    }

    public void setHasPieces(boolean hasPieces) {
        this.hasPieces = hasPieces;
    }

    public byte getPiecesType() {
        return piecesType;
    }

    public void setPiecesType(byte piecesType) {
        this.piecesType = piecesType;
    }
}
