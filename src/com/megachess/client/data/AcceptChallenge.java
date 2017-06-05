package com.megachess.client.data;

import com.google.gson.annotations.SerializedName;
import com.megachess.client.data.AcceptChallenge.AcceptChallengeData;

/**
 * Accept challenge data
 * 
 * @author nmarsollier
 */
public class AcceptChallenge extends BaseMessage<AcceptChallengeData> {

	public AcceptChallenge(String boardId) {
		super("accept_challenge", new AcceptChallengeData(boardId));
	}

	public static class AcceptChallengeData {
		@SerializedName("board_id")
		String boardId;

		public AcceptChallengeData(String boardId) {
			this.boardId = boardId;
		}

		public String getBoardId() {
			return boardId;
		}

		public void setBoardId(String boardId) {
			this.boardId = boardId;
		}
	}
}