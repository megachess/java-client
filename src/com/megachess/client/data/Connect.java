package com.megachess.client.data;

import com.google.gson.annotations.SerializedName;
import com.megachess.client.data.Connect.ConnectData;

/**
 * Connect data
 * 
 * @author nmarsollier
 *
 */
public class Connect extends BaseMessage<ConnectData> {
	public Connect(String authToken) {
		super("connect", new ConnectData(authToken));
	}

	public static class ConnectData {
		@SerializedName("auth_token")
		private String token;

		public ConnectData(String authToken) {
			this.token = authToken;
		}

		String getToken() {
			return token;
		}

		void setToken(String token) {
			this.token = token;
		}

	}
}