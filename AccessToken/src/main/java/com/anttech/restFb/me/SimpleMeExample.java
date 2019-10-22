package com.anttech.restFb.me;

import java.io.IOException;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.types.User;

public class SimpleMeExample {
	public static User fetchFacebookUser(String accessToken) {

		FacebookClient client = new DefaultFacebookClient(accessToken, Version.VERSION_2_5);
		try {
			User user = client.fetchObject("me", User.class, Parameter.with("fields",
					"name,gender,birthday,hometown,first_name,birthday,email,education,languages,link,work" + ""));
			if (user != null) {

				return user;
			}
		} catch (FacebookException ex) {
		}

		return null;
	}
	public void authUser()
	{
		String domain = "http://nhungtool.com/";
		String app_Id = "407651573278405";
		String authUrl = "";
	}

	public static void main(String[] args) throws IOException {
		String accessToken = "EAAFywcc3psUBAEsdhNNPkVGZBZBZASG0egczWfWiMvxkBQpPF7uSPey0Q6H5GjUPXGwPsm3DAkMP7ek2LZB6ZC0lNlBJOl5KRU1wu9xiNo1ZA1RPmwnLG6EANER6TqeNm8aJOuvhwEwRtYzUVFTBgJeyBOlyOF2hZBYmZBC2PsBKV44Af6scjqxgBg0ZCtPVMNR4ZD";
		User me = fetchFacebookUser(accessToken);
		System.out.println(me.getName());
		System.out.println(me.getHometownName());
		System.out.println(me.getId());
		System.out.println(me.getGender());
		System.out.println(me.getLink());
	}

}
