import entity.GamePanel;
import ui.ChessFrame;
import ui.ClientContext;

public class startGame {

	public static void main(String[] args) {
		
		ClientContext clientContext = new ClientContext();
		GamePanel gamePanel = new GamePanel();
		ChessFrame chessFrame = new ChessFrame();
		chessFrame.setGamePanel(gamePanel);
		chessFrame.launch();
		
		gamePanel.setClientContext(clientContext);
		chessFrame.setClientContext(clientContext);
		
		clientContext.setChessFrame(chessFrame);
		clientContext.setGamePanel(gamePanel);

		clientContext.show();
	}
}