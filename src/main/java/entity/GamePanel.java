package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.TimerTask;
import java.util.Timer;

import javax.swing.JScrollPane;

import ui.ChessFrame;
import ui.ClientContext;

public class GamePanel extends JScrollPane implements MouseListener,MouseMotionListener{

	private static final long serialVersionUID = -6285672550511215010L;
	
	private ChessInfo chessInfo = new ChessInfo();
	
	public ChessInfo getChessInfo() {
		return chessInfo;
	}
	
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
		g.setColor(Color.cyan);
		g.drawRect(ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE*20, ChessFrame.BLOCK_SIZE*20);
		g.fillRect(ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE*20, ChessFrame.BLOCK_SIZE*20);
		g.setColor(Color.black);
		for(int i=1; i<=21; i++) {
			g.drawLine(i*ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE, i*ChessFrame.BLOCK_SIZE, 21*ChessFrame.BLOCK_SIZE);
			g.drawLine(ChessFrame.BLOCK_SIZE, i*ChessFrame.BLOCK_SIZE, 21*ChessFrame.BLOCK_SIZE, i*ChessFrame.BLOCK_SIZE);
		}
		
		for(int i = 0; i <chessInfo.getChessboard().length; i++) {
			for(int j = 0 ; j <chessInfo.getChessboard()[i].length; j++) {
				if(chessInfo.getChessboard()[j][i] == ChessInfo.ISUSER) {
					g.setColor(Color.BLACK);
					g.fillOval(j*ChessFrame.BLOCK_SIZE+ChessFrame.BLOCK_SIZE/2, i*ChessFrame.BLOCK_SIZE+ChessFrame.BLOCK_SIZE/2,
							ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE);
				} else if(chessInfo.getChessboard()[j][i] == ChessInfo.ISCOMPUTER) {
					g.setColor(Color.WHITE);
					g.fillOval(j*ChessFrame.BLOCK_SIZE+ChessFrame.BLOCK_SIZE/2, i*ChessFrame.BLOCK_SIZE+ChessFrame.BLOCK_SIZE/2, 
							ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE);
				} else if(chessInfo.getChessboard()[j][i] == ChessInfo.ISHELP){
					g.setColor(Color.red);
					if(flicker++%2==0) {
						g.setColor(Color.yellow);
					}
					g.fillOval(j*ChessFrame.BLOCK_SIZE+ChessFrame.BLOCK_SIZE/2, i*ChessFrame.BLOCK_SIZE+ChessFrame.BLOCK_SIZE/2, 
							ChessFrame.BLOCK_SIZE, ChessFrame.BLOCK_SIZE);
				}
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		int x = (e.getX() - 10)/20;
		int y = (e.getY() - 10)/20;
		
		if(x >= chessInfo.getChessboard().length || y >= chessInfo.getChessboard()[x].length){
			return;
		}
		
		isStarted = true;

		if(isUserStep) {
			if(chessInfo.getChessboard()[x][y] == ChessInfo.ISNULL || chessInfo.getChessboard()[x][y] == ChessInfo.ISHELP){
				System.out.println(chessInfo.getChessboard()[x][y]+"\tisNullLocation?\t"+(chessInfo.getChessboard()[x][y] == ChessInfo.ISNULL));
				chessInfo.getChessboard()[x][y] = ChessInfo.ISUSER;
				isUserStep=false;
				clientContext.gameProcedure(x,y);
			} else {
				System.out.println(chessInfo.getChessboard()[x][y]+"isNullLocation?\t"+(chessInfo.getChessboard()[x][y] == ChessInfo.ISNULL));
			}
		} else {
			System.out.println("isUserStep?\t"+ false);
		}
	}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	private ClientContext clientContext;
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	
	private boolean isUserStep=true;
	public void setIsUserStep(){
		this.isUserStep = true;
	}

	private boolean isStarted = false;
	public void setIsStarted() {
		this.isStarted = false;
	}
	public boolean getIsStarted() {
		return isStarted;
	}
}
