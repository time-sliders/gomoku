package entity;

import consts.PieceType;
import ui.ChessFrame;
import ui.ClientContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

public class GamePanel extends JScrollPane implements MouseListener, MouseMotionListener {

    private static final long serialVersionUID = -6285672550511215010L;

    private ChessInfo chessInfo = new ChessInfo();

    private boolean isUserStep = true;

    private boolean isStarted = false;

    private ClientContext clientContext;

    private int flicker = 1;

    private ReentrantLock lock = new ReentrantLock();

    /**
     * 棋盘按钮点击事件
     *
     * @param e 事件
     */
    public void mousePressed(MouseEvent e) {
        lock.lock();
        try {
            //当前没有轮到用户落子
            if (!isUserStep) {
                return;
            }

            int x = (e.getX() - 10) / 20;//点击的X坐标
            int y = (e.getY() - 10) / 20;//点击的Y坐标

            System.out.println(x + "\t" + y);

            //点击超过棋盘范围不做处理
            if (x >= chessInfo.getChessboard().length
                    || y >= chessInfo.getChessboard()[x].length) {
                return;
            }

            //只能点击没有被下过的点
            if (chessInfo.getChessboard()[x][y] != PieceType.INIT
                    && chessInfo.getChessboard()[x][y] != PieceType.HELP) {
                return;
            }

            isStarted = true;
            System.out.println(String.format("user press:x->%s\ty->%s", x, y));

            //设置棋盘当前点为用户已经落子
            chessInfo.getChessboard()[x][y] = PieceType.USER;

            /*
             * 用户落子之后的处理
             * 1.判断用户是否胜利,如果用户胜利-> 游戏结束
             * 2.电脑判断局势,并选择落子
             * 3.判断电脑是否胜利,如果电脑胜利-> 游戏结束
             */
            clientContext.gameProcedure(x, y);
            isUserStep = false;
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    public GamePanel() {
        addMouseListener(this);
        /*
         * 设置每隔0.1秒刷新棋盘界面一次
         */
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, 100);
        System.out.println("GamePanel initialize succeed.....");
    }

    public void paint(Graphics g) {

        super.paint(g);

        /*
         * 棋盘底色渲染
         */
        g.setColor(Color.orange);
        g.drawRect(ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE * (ChessInfo.CHESS_BOARD_SIZE - 1), ChessFrame.BLOCK_SIZE * (ChessInfo.CHESS_BOARD_SIZE - 1));
        g.fillRect(ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE * (ChessInfo.CHESS_BOARD_SIZE - 1), ChessFrame.BLOCK_SIZE * (ChessInfo.CHESS_BOARD_SIZE - 1));

        /*
         * 画棋盘方格
         */
        g.setColor(Color.black);
        for (int i = 1; i <= ChessInfo.CHESS_BOARD_SIZE; i++) {
            g.drawLine(i * ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE, i * ChessFrame.BLOCK_SIZE, ChessInfo.CHESS_BOARD_SIZE * ChessFrame.BLOCK_SIZE);
            g.drawLine(ChessFrame.BLOCK_SIZE, i * ChessFrame.BLOCK_SIZE, ChessInfo.CHESS_BOARD_SIZE * ChessFrame.BLOCK_SIZE, i * ChessFrame.BLOCK_SIZE);
        }

        /*
         * 画棋子
         */
        for (int i = 0; i < chessInfo.getChessboard().length; i++) {
            for (int j = 0; j < chessInfo.getChessboard()[i].length; j++) {
                if (chessInfo.getChessboard()[j][i] == PieceType.USER) {
                    /*
                     * 用户棋子
                     */
                    g.setColor(Color.BLACK);
                    g.fillOval(j * ChessFrame.BLOCK_SIZE + ChessFrame.BLOCK_SIZE / 2, i * ChessFrame.BLOCK_SIZE + ChessFrame.BLOCK_SIZE / 2,
                            ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE);
                } else if (chessInfo.getChessboard()[j][i] == PieceType.COMPUTER) {
                    /*
                     * 电脑棋子
                     */
                    g.setColor(Color.WHITE);
                    g.fillOval(j * ChessFrame.BLOCK_SIZE + ChessFrame.BLOCK_SIZE / 2, i * ChessFrame.BLOCK_SIZE + ChessFrame.BLOCK_SIZE / 2,
                            ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE);
                } else if (chessInfo.getChessboard()[j][i] == PieceType.HELP) {
                    /*
                     * 闪烁帮助棋子
                     */
                    g.setColor(flicker++ % 2 == 0 ? Color.yellow : Color.red);
                    g.fillOval(j * ChessFrame.BLOCK_SIZE + ChessFrame.BLOCK_SIZE / 2, i * ChessFrame.BLOCK_SIZE + ChessFrame.BLOCK_SIZE / 2,
                            ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE);
                }
            }
        }
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
        isStarted = false;
    }

    public boolean getIsStarted() {
        return isStarted;
    }

    public ChessInfo getChessInfo() {
        return chessInfo;
    }
}
