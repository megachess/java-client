import java.util.logging.Level;
import java.util.logging.Logger;

import com.megachess.client.MegaChessClient;
import com.megachess.client.data.AskChallenge;
import com.megachess.client.data.GameOver;
import com.megachess.client.data.YourTurn;

/**
 * Main Class
 * 
 * @author nmarsollier
 */
public class PlayMegaChess {
	private static final String TOKEN = "2d6574e6-ec31-4ee2-a69f-e5ea33e866bb";
	private static Logger log = Logger.getLogger(MegaChessClient.class.getName());
	MegaChessClient chessClient;
	
	public static void main(String[] args) {
		new PlayMegaChess().play();
	}

	private void play() {
		chessClient = new MegaChessClient(TOKEN);
		chessClient.setOnConnect(() -> connect());
		chessClient.setOnTurnChange((turn) -> turnChange(turn));
		chessClient.setOnAskChallenge((ask) -> askChallenge(ask));
		chessClient.setOnGameOver((gameOver) -> gameOver(gameOver));
		
		chessClient.startGaming();		
	}

	private void gameOver(GameOver gameOver) {
		chessClient.stopGaming();
	}

	private void connect() {
		log.log(Level.INFO, "Connect");
		// TODO: Nothing done, after connect, i disconnect
		chessClient.stopGaming();
	}

	private void askChallenge(AskChallenge onAskChallenge) {
		log.log(Level.INFO, "AskChallenge");
	}

	private void turnChange(YourTurn onTurnChange) {
		log.log(Level.INFO, "TurnChange");
	}
}
