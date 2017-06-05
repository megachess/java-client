package com.megachess.client.data;

import com.google.gson.annotations.SerializedName;
import com.megachess.client.data.AskChallenge.AskChallengeData;

/**
 * Ask challenge data
 * 
 * @author nmarsollier
 */
public class AskChallenge extends BaseMessage<AskChallengeData> {

	public AskChallenge(AskChallengeData askData) {
		super("ask_challenge", askData);
	}

	public static class AskChallengeData {
		String message;
		@SerializedName("board_id")
		String boardId;
		String username;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
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

	}
}
