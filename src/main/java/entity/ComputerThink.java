package entity;

import java.util.Arrays;

public class ComputerThink {


    public static boolean judgeTriumph(int x, int y, int[][] chessboard, int isUserOrIsComputer) {
        System.out.println("chessServiceImplement Method " +
                "judgeTriumph(int x, int y, int[][] chessboard, int isUserOrIsComputer) invoking");
        boolean IsTriumph = false;
        int serialChessCount = 0;

        for (int coordsY = y; coordsY >= 0
                && chessboard[x][coordsY] == isUserOrIsComputer; coordsY--) {
            serialChessCount++;
        }
        for (int coordsY = y; coordsY < ChessInfo.CHESS_BOARD_SIZE
                && chessboard[x][coordsY] == isUserOrIsComputer; coordsY++) {
            serialChessCount++;
        }
        if ((serialChessCount - 1) >= ChessInfo.SERIAL_CHESS_NUMBER) {
            IsTriumph = true;
        }

        serialChessCount = 0;
        for (int coordsX = x; coordsX >= 0
                && chessboard[coordsX][y] == isUserOrIsComputer; coordsX--) {
            serialChessCount++;
        }
        for (int coordsX = x; coordsX < ChessInfo.CHESS_BOARD_SIZE
                && chessboard[coordsX][y] == isUserOrIsComputer; coordsX++) {
            serialChessCount++;
        }
        if ((serialChessCount - 1) >= ChessInfo.SERIAL_CHESS_NUMBER) {
            IsTriumph = true;
        }

        serialChessCount = 0;
        for (int coordsX = x, coordsY = y; coordsX >= 0 && coordsY >= 0
                && chessboard[coordsX][coordsY] == isUserOrIsComputer; coordsX--, coordsY--) {
            serialChessCount++;
        }
        for (int coordsX = x, coordsY = y; coordsX < ChessInfo.CHESS_BOARD_SIZE
                && coordsY < ChessInfo.CHESS_BOARD_SIZE && chessboard[coordsX][coordsY] == isUserOrIsComputer; coordsX++, coordsY++) {
            serialChessCount++;
        }
        if ((serialChessCount - 1) >= ChessInfo.SERIAL_CHESS_NUMBER) {
            IsTriumph = true;
        }

        serialChessCount = 0;
        for (int coordsX = x, coordsY = y; coordsX >= 0 && coordsY < ChessInfo.CHESS_BOARD_SIZE
                && chessboard[coordsX][coordsY] == isUserOrIsComputer; coordsX--, coordsY++) {
            serialChessCount++;
        }
        for (int coordsX = x, coordsY = y; coordsX < ChessInfo.CHESS_BOARD_SIZE
                && coordsY >= 0 && chessboard[coordsX][coordsY] == isUserOrIsComputer; coordsX++, coordsY--) {
            serialChessCount++;
        }
        if ((serialChessCount - 1) >= ChessInfo.SERIAL_CHESS_NUMBER) {
            IsTriumph = true;
        }
        System.out.println("------------------------------------------------------------");

        return IsTriumph;
    }

    public static int[] judgeSituation(int[][] chessboard) {
        int[] userSituation = getSituation(chessboard, ChessInfo.ISUSER);
        System.out.println("userSituation:\t\t" + Arrays.toString(userSituation));
        int[] computerSituation = getSituation(chessboard, ChessInfo.ISCOMPUTER);
        System.out.println("computerSituation:\t\t" + Arrays.toString(computerSituation));
        if (userSituation[0] > computerSituation[0]) {
            System.out.println("user is tailwind! computer decide disturb user!");
            return new int[]{userSituation[1], userSituation[2]};
        } else if (userSituation[0] == computerSituation[0] && userSituation[3] == 0 && computerSituation[3] == 2) {
            return new int[]{userSituation[1], userSituation[2]};
        } else {
            System.out.println("computer is tailwind!");
            return new int[]{computerSituation[1], computerSituation[2]};
        }
    }

    private static int[] getSituation(int[][] chessboard, int isUserOrComputer) {
        int[] situation = new int[4];

        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {

                if (chessboard[i][j] == isUserOrComputer) {
                    int[] direction1 = judgeRowSituation(i, j, 0, 1, chessboard, isUserOrComputer);
                    int[] direction2 = judgeRowSituation(i, j, 1, 1, chessboard, isUserOrComputer);
                    int[] direction3 = judgeRowSituation(i, j, 1, 0, chessboard, isUserOrComputer);
                    int[] direction4 = judgeRowSituation(i, j, 1, -1, chessboard, isUserOrComputer);
                    if (direction1[0] >= situation[0]) {
                        if (direction1[0] > situation[0]) {
                            situation = direction1;
                        } else {
                            if (situation[3] == 2 && direction1[3] == 0) {
                                situation = direction1;
                            }
                        }
                    }
                    if (direction2[0] >= situation[0]) {
                        if (direction2[0] > situation[0]) {
                            situation = direction2;
                        } else {
                            if (situation[3] == 2 && direction2[3] == 0) {
                                situation = direction2;
                            }
                        }
                    }
                    if (direction3[0] >= situation[0]) {
                        if (direction3[0] > situation[0]) {
                            situation = direction3;
                        } else {
                            if (situation[3] == 2 && direction3[3] == 0) {
                                situation = direction3;
                            }
                        }
                    }
                    if (direction4[0] >= situation[0]) {
                        if (direction4[0] > situation[0]) {
                            situation = direction4;
                        } else {
                            if (situation[3] == 2 && direction4[3] == 0) {
                                situation = direction4;
                            }
                        }
                    }
                }
            }
        }
        return situation;
    }

    private static int[] judgeRowSituation(int i, int j, int I_increment, int J_increment, int[][] chessboard, int isUserOrComputer) {
        int[] situation = new int[4];


        int maxRange = 0;

        for (int coordsX = i, coordsY = j; coordsY >= 0 && coordsX >= 0 && coordsX < ChessInfo.CHESS_BOARD_SIZE
                && coordsY < ChessInfo.CHESS_BOARD_SIZE && chessboard[coordsX][coordsY] != -isUserOrComputer; coordsX += I_increment, coordsY += J_increment) {
            maxRange++;
        }
        for (int coordsX = i, coordsY = j; coordsX >= 0 && coordsY >= 0 && coordsX < ChessInfo.CHESS_BOARD_SIZE
                && coordsY < ChessInfo.CHESS_BOARD_SIZE && chessboard[coordsX][coordsY] != -isUserOrComputer; coordsX -= I_increment, coordsY -= J_increment) {
            maxRange++;
        }
        if (--maxRange < ChessInfo.SERIAL_CHESS_NUMBER) {
            System.out.println("judge:\t" + i + ":" + j + "\t(" + I_increment + "," + J_increment + ")" + "\tfalse\t" + maxRange);
            situation[1] = i;
            situation[2] = j;
            return situation;
        } else {
            int maxSerialChess = 0;
            for (int coordsX = i, coordsY = j; coordsX >= 0 && coordsY >= 0 && coordsX < ChessInfo.CHESS_BOARD_SIZE
                    && coordsY < ChessInfo.CHESS_BOARD_SIZE && chessboard[coordsX][coordsY] == isUserOrComputer; coordsX += I_increment, coordsY += J_increment) {
                maxSerialChess++;
            }
            for (int coordsX = i, coordsY = j; coordsX >= 0 && coordsY >= 0 && coordsX < ChessInfo.CHESS_BOARD_SIZE
                    && coordsY < ChessInfo.CHESS_BOARD_SIZE && chessboard[coordsX][coordsY] == isUserOrComputer; coordsX -= I_increment, coordsY -= J_increment) {
                maxSerialChess++;
            }
            maxSerialChess--;
            System.out.println("judge:\t" + i + ":" + j + "|\t" + maxSerialChess + "\t|\t" + "(" + I_increment + "," + J_increment + ")");
            situation[0] = maxSerialChess;
            boolean isFoundLocation = false;
            for (int coordsX = i, coordsY = j; coordsX >= 0 && coordsY >= 0 && coordsX < ChessInfo.CHESS_BOARD_SIZE
                    && coordsY < ChessInfo.CHESS_BOARD_SIZE && chessboard[coordsX][coordsY] == isUserOrComputer; coordsX += I_increment, coordsY += J_increment) {
                if (coordsX + I_increment >= 0 && coordsY + J_increment >= 0 && coordsX + I_increment < ChessInfo.CHESS_BOARD_SIZE && coordsY + J_increment < ChessInfo.CHESS_BOARD_SIZE) {
                    if (chessboard[coordsX + I_increment][coordsY + J_increment] == ChessInfo.ISNULL || chessboard[coordsX + I_increment][coordsY + J_increment] == ChessInfo.ISHELP) {
                        situation[1] = coordsX + I_increment;
                        situation[2] = coordsY + J_increment;
                        isFoundLocation = true;
                        break;
                    }
                    if (chessboard[coordsX + I_increment][coordsY + J_increment] == -isUserOrComputer) {
                        situation[3] = 2;
                        break;
                    }
                }
            }
            for (int coordsX = i, coordsY = j; coordsX >= 0 && coordsY >= 0 && coordsX < ChessInfo.CHESS_BOARD_SIZE
                    && coordsY < ChessInfo.CHESS_BOARD_SIZE && chessboard[coordsX][coordsY] == isUserOrComputer; coordsX -= I_increment, coordsY -= J_increment) {
                if (coordsX - I_increment >= 0 && coordsY - J_increment >= 0 && coordsX - I_increment < ChessInfo.CHESS_BOARD_SIZE && coordsY - J_increment < ChessInfo.CHESS_BOARD_SIZE) {
                    if ((chessboard[coordsX - I_increment][coordsY - J_increment] == ChessInfo.ISNULL || chessboard[coordsX + I_increment][coordsY + J_increment] == ChessInfo.ISHELP) && !isFoundLocation) {
                        situation[1] = coordsX - I_increment;
                        situation[2] = coordsY - J_increment;
                        break;
                    }
                    if (chessboard[coordsX - I_increment][coordsY - J_increment] == -isUserOrComputer) {
                        situation[3] = 2;
                        break;
                    }
                }
            }
        }

        return situation;
    }
}
