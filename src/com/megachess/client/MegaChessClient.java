package com.megachess.client;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.megachess.client.data.AcceptChallenge;
import com.megachess.client.data.AskChallenge;
import com.megachess.client.data.Connect;
import com.megachess.client.data.GameOver;
import com.megachess.client.data.Move;
import com.megachess.client.data.YourTurn;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * WebSocket client for megachess
 * 
 * @author nmarsollier
 *
 */
public class MegaChessClient extends WebSocketListener {
	private static final String URL = "wss://mega-chess.herokuapp.com/service";	
	private Gson gson = new Gson();
	private OkHttpClient client;
	WebSocket webSocket;
	Logger log = Logger.getLogger(MegaChessClient.class.getName());

	private final String token;
	private ConnectEventHandler onConnect;
	private AskChallengeEventHandler onAskChallenge;
	private TurnChangeEventHandler onTurnChange;
	private GameOverEventHandler onGameOver;
	
	@FunctionalInterface
	public static interface ConnectEventHandler {
		public void onConnect();
	}

	@FunctionalInterface
	public static interface AskChallengeEventHandler {
		public void onAskChallenge(AskChallenge askChallenge);
	}

	@FunctionalInterface
	public static interface TurnChangeEventHandler {
		public void onTurnChange(YourTurn yourTurn);
	}

	@FunctionalInterface
	public static interface GameOverEventHandler {
		public void onGameOver(GameOver gameOver);
	}
	
	public MegaChessClient(String token) {
		this.token = token;
	}
	
	public void startGaming() {
		client = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).pingInterval(10, TimeUnit.SECONDS)
				.build();

		Request request = new Request.Builder().url(URL).build();

		client.newWebSocket(request, this);
	}

	public void stopGaming() {
		client.dispatcher().executorService().shutdown();
		if (webSocket != null) {
			webSocket.close(1001, null);
		}
	}

	@Override
	public void onOpen(WebSocket webSocket, Response response) {
		super.onOpen(webSocket, response);

		Connect connect = new Connect(token);
		webSocket.send(gson.toJson(connect));
	}

	public void acceptChallenge(String boardId) {
		log.log(Level.INFO, "Challenge someone asking for a challenge!");
		if (webSocket != null) {
			webSocket.send(gson.toJson(new AcceptChallenge(boardId)));
		}
	}

	public void move(String boardId, String turnToken, int fromRow, int fromCol, int toRow, int toCol) {
		Move generatedMove = new Move(boardId, turnToken, fromRow, fromCol, toRow, toCol);
		if (webSocket != null) {
			webSocket.send(gson.toJson(generatedMove));
		}
	}

	@Override
	public void onMessage(WebSocket webSocket, String rawPayload) {
		this.webSocket = webSocket;
		if (rawPayload.contains("connect_ok")) {
			if (onConnect != null) {
				onConnect.onConnect();
			}
		} else if (rawPayload.contains("ask_challenge")) {
			AskChallenge askChallenge = gson.fromJson(rawPayload, AskChallenge.class);
			if (onAskChallenge != null) {
				onAskChallenge.onAskChallenge(askChallenge);
			}
		} else if (rawPayload.contains("your_turn")) {
			YourTurn yourTurn = gson.fromJson(rawPayload, YourTurn.class);
			if (onTurnChange != null) {
				onTurnChange.onTurnChange(yourTurn);
			}
		} else if (rawPayload.contains("game_over")) {
			GameOver gameOver = gson.fromJson(rawPayload,  GameOver.class);
			if(onGameOver != null) {
				onGameOver.onGameOver(gameOver);
			}
		}
	}

	@Override
	public void onMessage(WebSocket webSocket, ByteString bytes) {
		if (bytes == null) {
			return;
		}
		log.log(Level.SEVERE, "BINARY MESSAGE: {} ", bytes.hex());
	}

	@Override
	public void onClosing(WebSocket webSocket, int code, String reason) {
		if (webSocket != null) {
			webSocket.close(1000, null);
		}
		log.log(Level.INFO, "CLOSE: {} {}", new Object[] { code, reason });
	}

	public void setOnAskChallenge(AskChallengeEventHandler onAskChallenge) {
		this.onAskChallenge = onAskChallenge;
	}
	public void setOnConnect(ConnectEventHandler onConnect) {
		this.onConnect = onConnect;
	}
	public void setOnTurnChange(TurnChangeEventHandler onTurnChange) {
		this.onTurnChange = onTurnChange;
	}
	public void setOnGameOver(GameOverEventHandler onGameOver) {
		this.onGameOver = onGameOver;
	}
}
