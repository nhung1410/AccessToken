package com.anttech.google;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class Google2Example {

	private static final String NETWORK_NAME = "Google";

	private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo?alt=json";

	private static final String SCOPE = "https://mail.google.com/ https://www.googleapis.com/auth/userinfo.email";

	private static final Token EMPTY_TOKEN = null;

	public static final char SPACE = ' ';

	public static void main(String[] args) {

		String apiKey = MyConstants.GOOGLE_CLIENT_ID;
		String apiSecret = MyConstants.GOOGLE_CLIENT_SECRET;
		String callbackUrl = MyConstants.GOOGLE_REDIRECT_URL;

		// Tạo OAuthService cho Google OAuth 2.0
		OAuthService service = new ServiceBuilder().provider(Google2Api.class).apiKey(apiKey).apiSecret(apiSecret)
				.callback(callbackUrl).scope(SCOPE).build();

		Scanner in = new Scanner(System.in);

		System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
		System.out.println();
		Verifier verifier = null;

		Token accessToken = null;

		System.out.println("Fetching the Authorization URL...");
		String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
		System.out.println("Got the Authorization URL!");
		System.out.println("Now go and authorize Scribe here:");
		System.out.println();

		// Copy URL này và chạy trên trình duyệt.
		System.out.println(authorizationUrl);
		System.out.println();

		// Copy Authorization Code trên URL của trình duyệt và dán vào Console.
		System.out.println("Paste the authorization code here");
		System.out.print(">>");
		verifier = new Verifier(in.nextLine());
		accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
		OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println();
		System.out.println(response.getCode());
		System.out.println(response.getBody());
		Pattern pattern = Pattern.compile("[{},,:]");
		String[] results = pattern.split(response.getBody());
		System.out.println("id: " + results[2].replace("\"", ""));
		System.out.println("email: " + results[4].replace("\"", ""));
		in.close();
	}

}
