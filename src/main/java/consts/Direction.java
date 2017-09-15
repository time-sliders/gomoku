package consts;

/**
 * 局势判断时的推算方向
 *
 * @author luyun
 * @version AI polishing
 * @since 2017.09.14
 */
public enum Direction {

    /**
     * X轴平行方向
     * 正向量:X+1   y+0
     * 反向量:x-1   y-0
     */
    X(1, 0),

    /**
     * Y轴平行方向
     * 正向量:X+0   y+1
     * 反向量:x-0   y-1
     */
    Y(0, 1),

    /**
     * X为正 Y为正 的区间 的中分斜线
     * 正向量:X+1   y+1
     * 反向量:x-1   y-1
     */
    XY(1, 1),

    /**
     * X为负 Y为正 的区间 的中分斜线
     * 正向量:X-1   y+1
     * 反向量:x+1   y-1
     */
    _XY(-1, 1);

    /**
     * X轴正向量值
     */
    int xVectorValue;

    /**
     * Y轴正向量值
     */
    int yVectorValue;

    Direction(int xVectorValue, int yVectorValue) {
        this.xVectorValue = xVectorValue;
        this.yVectorValue = yVectorValue;
    }

    public int getX() {
        return xVectorValue;
    }

    public int getY() {
        return yVectorValue;
    }
}