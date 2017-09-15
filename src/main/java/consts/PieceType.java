package consts;

/**
 * 棋子类型
 */
public interface PieceType {

    /**
     * 空地
     */
    int INIT = 0;

    /**
     * 用户落子
     */
    int USER = 1;

    /**
     * 电脑落子
     */
    int COMPUTER = 2;

    /**
     * 帮助的闪烁点位
     */
    int HELP = 9;

}
