package ui;

import entity.ChessInfo;
import entity.ComputerThink;
import entity.GamePanel;

import javax.swing.*;

public class ClientContext {


    public void show() {
        chessFrame.setVisible(true);
    }

    private ChessFrame chessFrame;

    public void setChessFrame(ChessFrame chessFrame) {
        this.chessFrame = chessFrame;
    }

    public void gameProcedure(int x, int y) {

        gamePanel.getChessInfo().clearFlicker();

        if (ComputerThink.judgeTriumph(x, y, gamePanel.getChessInfo().getChessboard(),
                ChessInfo.ISUSER)) {
            System.out.println("winner");
            JOptionPane.showMessageDialog(chessFrame, "you winner!!");
            gamePanel.getChessInfo().clear();
            gamePanel.setIsStarted();
            gamePanel.setIsUserStep();
            return;
        }

        int[] locationPoint = ComputerThink.judgeSituation(gamePanel.getChessInfo().getChessboard());
        gamePanel.getChessInfo().getChessboard()[locationPoint[0]][locationPoint[1]] = ChessInfo.ISCOMPUTER;

        if (ComputerThink.judgeTriumph(locationPoint[0], locationPoint[1],
                gamePanel.getChessInfo().getChessboard(), ChessInfo.ISCOMPUTER)) {
            JOptionPane.showMessageDialog(chessFrame, "game over...!");
            gamePanel.getChessInfo().clear();
            gamePanel.setIsStarted();
            gamePanel.setIsUserStep();
            return;
        }

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
                gamePanel.setIsStarted();
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
        gamePanel.getChessInfo().getChessboard()[location[0]][location[1]] = ChessInfo.ISHELP;
    }
}
