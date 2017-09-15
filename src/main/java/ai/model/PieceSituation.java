package ai.model;

import ai.consts.Urgency;
import consts.Direction;

/**
 * 某一个棋子的局势
 *
 * @author luyun
 * @version AI polishing
 * @since 2017.09.14
 */
public class PieceSituation {

    /**
     * X轴坐标
     */
    private int x;

    /**
     * Y轴坐标
     */
    private int y;

    /**
     * 判断的方向
     */
    private Direction direction;

    /**
     * 紧急程度
     *
     * @see Urgency
     */
    private int urgency;

    /**
     * 下一个点位X坐标
     */
    private int nextX;

    /**
     * 下一个点位Y坐标
     */
    private int nextY;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }


}
