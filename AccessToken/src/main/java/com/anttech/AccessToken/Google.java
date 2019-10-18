package com.anttech.AccessToken;

import java.io.IOException;
import java.util.Scanner;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.anttech.google.Google2Api;

public class Google {
	private static final String NETWORK_NAME = "Google";
	private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json";
	private static final Token EMPTY_TOKEN = null;
	private static final String SCOPE = "https://mail.google.com/ https://www.googleapis.com/auth/userinfo.email";

	public static void main(String[] args) throws IOException {
		String apiKey = "597680871786-tlotpt7t0bag7d05oann1fo5tpqjoiov.apps.googleusercontent.com";
		String apiSecret = "i2_IpRgmzijUN9A42ly6_X2j";
		String callbackUrl = "https://nhungwebsite.com/mypath/oauth2callback";
		OAuthService service = new ServiceBuilder().provider(Google2Api.class).apiKey(apiKey).apiSecret(apiSecret)
				.callback(callbackUrl).scope(SCOPE).build();
		
		Scanner in = new Scanner(System.in);

		System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
		System.out.println();
		Verifier verifier = null;
		System.out.println("Fetching the Authorization URL...");
		String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
		System.out.println("Got the Authorization URL!");
		System.out.println("Now go and authorize Scribe here:");
		System.out.println(authorizationUrl);
		System.out.println("And paste the authorization code here");
		System.out.print(">>");
		String ver = in.nextLine();
		verifier = new Verifier(ver);
		System.out.println();

		System.out.println("Trading the Request Token for an Access Token...");
		System.out.println(verifier);
		Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
		System.out.println("Got the Access Token!");
		System.out.println("(if your curious it looks like this: " + accessToken + " )");
		System.out.println();

		System.out.println("Now we're going to access a protected resource...");
		OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getCode());
		System.out.println(response.getBody());

		System.out.println();
		System.out.println("Thats it man! Go and build something awesome with Scribe! :)");

	}
}
