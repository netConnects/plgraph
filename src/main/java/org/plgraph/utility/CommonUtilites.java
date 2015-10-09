package org.plgraph.utility;

public class CommonUtilites {
	public static String GOOGLE_CLIENT_ID = "813136001231-has6hk2vsn7cq586k9aop9fem2rlcbhf.apps.googleusercontent.com";
	public static String GOOGLE_CLIENT_SECRET = "k9NSWCzK1PwhElsZvoJXGAyZ";
	public static String GOOGLE_REDIRECT_URI = "http://plgraph.netconnects.in/plgraph/rest/oauth2callback";
	public static String GOOGLE_GRANT_TYPE = "authorization_code";
	public static String GOOGLE_USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
	public static String GOOGLE_TOKEN_URL = "https://accounts.google.com/o/oauth2/token";

	public static String createGoogleAuthorization(String code) {
		String url = "code=" + code
                + "&client_id=" + GOOGLE_CLIENT_ID
                + "&client_secret=" + GOOGLE_CLIENT_SECRET
                + "&redirect_uri=" + GOOGLE_REDIRECT_URI
                + "&grant_type=" + GOOGLE_GRANT_TYPE;
		
		return url;
	}
}
