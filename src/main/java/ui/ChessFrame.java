package ui;

import entity.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChessFrame extends JFrame {

    private static final long serialVersionUID = -1551250420100406800L;

    /**
     * 棋子大小,直径
     */
    public static final int BLOCK_SIZE = 20;

    private GamePanel gamePanel;

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    private ClientContext clientContext;

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    public ChessFrame() {
        super();
    }

    public void launch() {
        System.out.println("start initialize ChessFrame.....");

        this.setTitle(">>>>五子棋<<<<");
        this.setSize(400, 350);
        this.setContentPane(createContentPanel());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int val = JOptionPane.showConfirmDialog(ChessFrame.this, "exit?");
                if (val == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        System.out.println("ChessFrame initialize succeed.....");
    }

    private JPanel createContentPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(BorderLayout.SOUTH, new JLabel("人机五子棋", JLabel.RIGHT));
        p.add(BorderLayout.WEST, createWestPanel());
        p.add(BorderLayout.CENTER, gamePanel);
        return p;
    }

    private JPanel createWestPanel() {
        JPanel pane = new JPanel(new BorderLayout());
        pane.add(BorderLayout.SOUTH, createBottonPane());
        return pane;
    }

    private JPanel createBottonPane() {
        JPanel pane = new JPanel(new GridLayout(4, 1, 5, 5));
        JButton restart = new JButton("restart");
        JButton help = new JButton("help");

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.restart();
            }
        });

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.help();
            }
        });

        pane.add(restart);
        pane.add(help);
        return pane;
    }
}
