package com.anttech.restFb.me;

import com.anttech.restFb.Constants;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

public class SimpleMeExample {
	   public static void main(String[] args) {
	         
	        // Tạo đối tượng FacebookClient
	        @SuppressWarnings("deprecation")
			FacebookClient facebookClient= new DefaultFacebookClient(Constants.MY_ACCESS_TOKEN);
	         
	        // User là một class có sẵn của Restfb mô tả các thông tin của User
	        // Trong tình huống này chúng ta biết trước dữ liệu trả về là User.
	        User user = facebookClient.fetchObject("me", User.class);
	         
	        System.out.println("User="+ user);
	        System.out.println("Name="+user.getName());
	        System.out.println("Birthday= "+ user.getBirthday());
	        System.out.println("Uid="+user.getId());
	   }
}
