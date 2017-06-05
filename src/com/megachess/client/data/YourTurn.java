package com.megachess.client.data;

import com.google.gson.annotations.SerializedName;
import com.megachess.client.data.YourTurn.YourTurnData;

/**
 * Your Turn data
 * 
 * @author nmarsollier
 *
 */
public class YourTurn extends BaseMessage<YourTurnData> {
	public YourTurn(String action, String boardId, String username, String board, String turnToken,
			String actualTurn) {
		super("your_turn", new YourTurnData(boardId, username, board, turnToken, actualTurn));
	}

	public static class YourTurnData {
		@SerializedName("board_id")
		String boardId;
		String username;
		String board;
		@SerializedName("turn_token")
		String turnToken;
		@SerializedName("actual_turn")
		String actualTurn;

		public YourTurnData(String boardId, String username, String board, String turnToken, String actualTurn) {
			this.board = board;
			this.boardId = boardId;
			this.username = username;
			this.turnToken = turnToken;
			this.actualTurn = actualTurn;
		}

		public String getBoardId() {
			return boardId;
		}

		public void setBoardId(String boardId) {
			this.boardId = boardId;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getBoard() {
			return board;
		}

		public void setBoard(String board) {
			this.board = board;
		}

		public String getTurnToken() {
			return turnToken;
		}

		public void setTurnToken(String turnToken) {
			this.turnToken = turnToken;
		}

		public String getActualTurn() {
			return actualTurn;
		}

		public void setActualTurn(String actualTurn) {
			this.actualTurn = actualTurn;
		}

	}

}
