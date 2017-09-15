package entity;

import consts.PieceType;

public class ChessInfo implements PieceType {

    public static final int CHESS_BOARD_SIZE = 15;

    private int[][] chessboard = new int[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE];

    ChessInfo() {
        init();
    }

    private void init() {
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            chessboard[i] = new int[CHESS_BOARD_SIZE];
            for (int j = 0; j < CHESS_BOARD_SIZE; j++) {
                chessboard[i][j] = INIT;
            }
        }
        System.out.println("Array initialize succeed.....");
    }

    public void clear() {
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            for (int j = 0; j < CHESS_BOARD_SIZE; j++) {
                chessboard[i][j] = INIT;
            }
        }
        System.out.println("Array clear succeed......");
    }

    /**
     * 清除掉棋盘上的帮助提示闪烁黄点
     */
    public void clearHelpTips() {
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            for (int j = 0; j < CHESS_BOARD_SIZE; j++) {
                if (chessboard[i][j] == HELP) {
                    chessboard[i][j] = INIT;
                }
            }
        }
        System.out.println("Array clear succeed......");
    }

    public int[][] getChessboard() {
        return chessboard;
    }

}
