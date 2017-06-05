package com.megachess.client.data;

import com.google.gson.annotations.SerializedName;

public class GameOver extends BaseMessage<GameOver.GameOverData> {

	public GameOver(GameOverData gameOver) {
		super("game_over", gameOver);
		setData(gameOver);
	}

	public static class GameOverData {
		@SerializedName("black_score")
		int blackScore;

		@SerializedName("white_score")
		int whiteScore;

		@SerializedName("winner")
		String winner;

		@SerializedName("black_username")
		String blackUsername;

		@SerializedName("board")
		String board;

		@SerializedName("white_username")
		String whiteUsername;
	}
}
