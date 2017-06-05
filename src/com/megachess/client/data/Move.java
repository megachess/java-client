package com.megachess.client.data;

import com.google.gson.annotations.SerializedName;

/**
 * Move data
 * 
 * @author nmarsollier
 *
 */
public class Move extends BaseMessage<Move.MoveData> {

	public Move(String boardId, String turnToken, int fromRow, int fromCol, int toRow, int toCol) {
		super("move", new MoveData(boardId, turnToken, fromRow, fromCol, toRow, toCol));
	}

	public static class MoveData {
		@SerializedName("board_id")
		String boardId;
		@SerializedName("turn_token")
		String turnToken;
		@SerializedName("from_row")
		int fromRow;
		@SerializedName("from_col")
		int fromCol;
		@SerializedName("to_row")
		int toRow;
		@SerializedName("to_col")
		int toCol;

		public MoveData(String boardId, String turnToken, int fromRow, int fromCol, int toRow, int toCol) {
			this.boardId = boardId;
			this.turnToken = turnToken;
			this.fromCol = fromCol;
			this.fromRow = fromRow;
			this.toCol = toCol;
			this.toRow = toRow;
		}

		public String getBoardId() {
			return boardId;
		}

		public void setBoardId(String boardId) {
			this.boardId = boardId;
		}

		public String getTurnToken() {
			return turnToken;
		}

		public void setTurnToken(String turnToken) {
			this.turnToken = turnToken;
		}

		public int getFromRow() {
			return fromRow;
		}

		public void setFromRow(int fromRow) {
			this.fromRow = fromRow;
		}

		public int getFromCol() {
			return fromCol;
		}

		public void setFromCol(int fromCol) {
			this.fromCol = fromCol;
		}

		public int getToRow() {
			return toRow;
		}

		public void setToRow(int toRow) {
			this.toRow = toRow;
		}

		public int getToCol() {
			return toCol;
		}

		public void setToCol(int toCol) {
			this.toCol = toCol;
		}

	}
}
