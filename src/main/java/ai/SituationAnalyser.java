package ai;

import ai.model.OverallSituation;
import ai.model.PieceSituation;
import consts.Direction;
import consts.PieceType;

import java.util.LinkedList;
import java.util.List;

/**
 * 局势分析算法
 *
 * @author luyun
 * @version AI polishing
 * @since 2017.09.14
 */
public class SituationAnalyser {

    public OverallSituation analysis(int[][] board) {

        OverallSituation os = new OverallSituation();

        /*
         * 分析棋盘所有棋子的局势信息
         */
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {

                int pieceType = board[x][y];
                if (PieceType.HELP == pieceType || PieceType.INIT == pieceType) {
                    continue;
                }

                os.add(pieceType, getPieceSituation(x, y, board));
            }
        }

        return os;
    }


    /**
     * 获取指定棋子的局势信息
     */
    private List<PieceSituation> getPieceSituation(int x, int y, int[][] board) {

        List<PieceSituation> psl = new LinkedList<PieceSituation>();
        for (Direction d : Direction.values()) {
            PieceSituation ds = getPointSituationInDirection(x, y, board, d);
            psl.add(ds);
        }

        return psl;
    }

    /**
     * 核心算法
     * 计算指定位置的棋子的局势
     */
    private PieceSituation getPointSituationInDirection(int x, int y, int[][] board, Direction d) {

        // 1.init situation
        PieceSituation ps = new PieceSituation();
        ps.setX(x);
        ps.setY(y);
        ps.setDirection(d);

        // TODO 2.analysis
        return ps;
    }
}
