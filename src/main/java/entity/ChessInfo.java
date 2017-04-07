package entity;

public class ChessInfo {

    static final int CHESS_BOARD_SIZE = 21;
    static final int SERIAL_CHESS_NUMBER = 5;
    public static final int ISUSER = 1;
    public static final int ISCOMPUTER = -1;
    static final int ISNULL = 0;
    public static final int ISHELP = 2;

    private int[][] chessboard = new int[CHESS_BOARD_SIZE][];

    ChessInfo() {
        init();
    }

    private void init() {
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            chessboard[i] = new int[CHESS_BOARD_SIZE];
            for (int j = 0; j < CHESS_BOARD_SIZE; j++) {
                chessboard[i][j] = ISNULL;
            }
        }
        System.out.println("Array initialize succeed.....");
    }

    public void clear() {
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            for (int j = 0; j < CHESS_BOARD_SIZE; j++) {
                chessboard[i][j] = ISNULL;
            }
        }
        System.out.println("Array clear succeed......");
    }

    public void clearFlicker() {
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            for (int j = 0; j < CHESS_BOARD_SIZE; j++) {
                if (chessboard[i][j] == ISHELP) {
                    chessboard[i][j] = ISNULL;
                }
            }
        }
        System.out.println("Array clear succeed......");
    }

    public int[][] getChessboard() {
        return chessboard;
    }

}
