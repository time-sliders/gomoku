package ai.model;

import consts.PieceType;

import java.util.LinkedList;
import java.util.List;

/**
 * 当前棋盘的整体局势
 *
 * @author luyun
 * @version AI polishing
 * @since 2017.09.14
 */
public class OverallSituation {

    /**
     * 电脑棋子的局势信息
     */
    private List<PieceSituation> cs = new LinkedList<PieceSituation>();

    /**
     * 用户棋子的局势信息
     */
    private List<PieceSituation> us = new LinkedList<PieceSituation>();

    public void add(int pieceType, List<PieceSituation> psl) {
        switch (pieceType) {
            case PieceType.USER:
                us.addAll(psl);
                break;
            case PieceType.COMPUTER:
                cs.addAll(psl);
                break;
        }
    }
}
