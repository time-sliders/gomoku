package entity;

import ui.ChessFrame;
import ui.ClientContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class GamePanel extends JScrollPane implements MouseListener, MouseMotionListener {

    private static final long serialVersionUID = -6285672550511215010L;

    private ChessInfo chessInfo = new ChessInfo();

    private boolean isUserStep = true;

    private AtomicBoolean isStarted = new AtomicBoolean(false);

    private ClientContext clientContext;

    public GamePanel() {

        addMouseListener(this);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, 100);

        System.out.println("GamePanel initialize succeed.....");
    }

    private int flicker = 1;

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.orange);

        g.drawRect(ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE * 20, ChessFrame.BLOCK_SIZE * 20);
        g.fillRect(ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE * 20, ChessFrame.BLOCK_SIZE * 20);

        g.setColor(Color.black);

        for (int i = 1; i <= 21; i++) {
            g.drawLine(i * ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE, i * ChessFrame.BLOCK_SIZE, 21 * ChessFrame.BLOCK_SIZE);
            g.drawLine(ChessFrame.BLOCK_SIZE, i * ChessFrame.BLOCK_SIZE, 21 * ChessFrame.BLOCK_SIZE, i * ChessFrame.BLOCK_SIZE);
        }

        for (int i = 0; i < chessInfo.getChessboard().length; i++) {
            for (int j = 0; j < chessInfo.getChessboard()[i].length; j++) {
                if (chessInfo.getChessboard()[j][i] == ChessInfo.CHESS_USER) {
                    g.setColor(Color.BLACK);
                    g.fillOval(j * ChessFrame.BLOCK_SIZE + ChessFrame.BLOCK_SIZE / 2, i * ChessFrame.BLOCK_SIZE + ChessFrame.BLOCK_SIZE / 2,
                            ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE);
                } else if (chessInfo.getChessboard()[j][i] == ChessInfo.CHESS_COMPUTER) {
                    g.setColor(Color.WHITE);
                    g.fillOval(j * ChessFrame.BLOCK_SIZE + ChessFrame.BLOCK_SIZE / 2, i * ChessFrame.BLOCK_SIZE + ChessFrame.BLOCK_SIZE / 2,
                            ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE);
                } else if (chessInfo.getChessboard()[j][i] == ChessInfo.CHESS_HELP) {
                    g.setColor(Color.red);
                    if (flicker++ % 2 == 0) {
                        g.setColor(Color.yellow);
                    }
                    g.fillOval(j * ChessFrame.BLOCK_SIZE + ChessFrame.BLOCK_SIZE / 2, i * ChessFrame.BLOCK_SIZE + ChessFrame.BLOCK_SIZE / 2,
                            ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE);
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {

        if (!isUserStep) {
            return;
        }

        int x = (e.getX() - 10) / 20;
        int y = (e.getY() - 10) / 20;


        if (x >= chessInfo.getChessboard().length
                || y >= chessInfo.getChessboard()[x].length) {
            return;
        }


        if (chessInfo.getChessboard()[x][y] != ChessInfo.CHESS_INIT
                && chessInfo.getChessboard()[x][y] != ChessInfo.CHESS_HELP) {
            return;
        }

        isStarted.compareAndSet(false, true);

        System.out.println(String.format("user press:x->%s\ty->%s", x, y));

        chessInfo.getChessboard()[x][y] = ChessInfo.CHESS_USER;

        isUserStep = false;

        clientContext.gameProcedure(x, y);
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    public void setIsUserStep() {
        this.isUserStep = true;
    }

    public void gameEnd() {
        isStarted.compareAndSet(true, false);
    }

    public boolean getIsStarted() {
        return isStarted.get();
    }

    public ChessInfo getChessInfo() {
        return chessInfo;
    }
}
