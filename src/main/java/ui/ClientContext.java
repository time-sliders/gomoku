package ui;

import consts.PieceType;
import entity.ComputerThink;
import entity.GamePanel;

import javax.swing.*;

public class ClientContext implements PieceType {


    public void show() {
        chessFrame.setVisible(true);
    }

    private ChessFrame chessFrame;

    public void setChessFrame(ChessFrame chessFrame) {
        this.chessFrame = chessFrame;
    }

    /**
     * 玩家落子之后的处理逻辑
     * 1.判断用户是否胜利,如果用户胜利-> 游戏结束
     * 2.电脑判断局势,并选择落子
     * 3.判断电脑是否胜利,如果电脑胜利-> 游戏结束
     *
     * @param x 玩家落子X坐标
     * @param y 玩家落子Y坐标
     */
    public void gameProcedure(int x, int y) {

        gamePanel.getChessInfo().clearHelpTips();

        /*
         * 1.判断用户是否胜利,如果用户胜利-> 游戏结束
         */
        if (ComputerThink.judgeTriumph(x, y, gamePanel.getChessInfo().getChessboard(), PieceType.USER)) {
            JOptionPane.showMessageDialog(chessFrame, "you winner!!");
            gameEnd();
            return;
        }

        /*
         * 2.电脑判断局势,并选择落子
         */
        int[] cp = ComputerThink.judgeSituation(gamePanel.getChessInfo().getChessboard());
        gamePanel.getChessInfo().getChessboard()[cp[0]][cp[1]] = PieceType.COMPUTER;

        /*
         * 3.判断电脑是否胜利,如果电脑胜利-> 游戏结束
         */
        if (ComputerThink.judgeTriumph(cp[0], cp[1], gamePanel.getChessInfo().getChessboard(), PieceType.COMPUTER)) {
            JOptionPane.showMessageDialog(chessFrame, "game over...!");
            gameEnd();
            return;
        }

        gamePanel.setIsUserStep();
    }

    private void gameEnd(){
        gamePanel.getChessInfo().clear();
        gamePanel.gameEnd();
        gamePanel.setIsUserStep();
    }

    private GamePanel gamePanel;

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    boolean restart() {
        if (gamePanel.getIsStarted()) {
            int value = JOptionPane.showConfirmDialog(chessFrame, "restart");
            if (value == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(chessFrame, "restart");
                gamePanel.getChessInfo().clear();
                gamePanel.gameEnd();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    void help() {
        int[] location = ComputerThink.judgeSituation(gamePanel.getChessInfo().getChessboard());
        gamePanel.getChessInfo().getChessboard()[location[0]][location[1]] = PieceType.HELP;
    }
}
