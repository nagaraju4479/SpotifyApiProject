package api;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.time.Instant;
import java.util.HashMap;

import javax.management.RuntimeErrorException;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TokenManager {
	
	private static String access_token;
	private static Instant expiry_time;
	
	public static String  getToken()
	{
		try {
			if(access_token == null || Instant.now().isAfter(expiry_time))
			{
				System.out.println("Renew the token...");
				Response response = renewToken();
				access_token=response.path("access_token");
				int timeinseconds=response.path("expires_in");
				expiry_time=Instant.now().plusSeconds(timeinseconds - 300);
			}
			
			else
			{
			System.out.println("Good to use access token");	
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		
		return access_token;
		
	}
	
	public static Response renewToken()
	{
		HashMap<String, String> formParams = new HashMap<String , String>();
		formParams.put("client_id", "8b1cc380024c49eb9c3605a0fe5bc059");
		formParams.put("client_secret", "c7fd3b203f784822844620b07859a28f");
		formParams.put("grant_type", "refresh_token");
		formParams.put("refresh_token", "AQAZamO8ssO2A3ld-Q1yCdbBKQpwmihMPu4N8xkgrGOl_Yv4rwE89dvU4_Mr-9ZNK69ZSrHJfjixXlZGM64sh0Jow2RsAY5-yo8QreW3-cQMI7nMXFx07tg5auC3Itilb1E");
		
		
		Response response = RestResource.postToken(formParams);
		
		if(response.statusCode() != 200)
		{
			throw new RuntimeException("Renew token is failed");
		}
		
		return response;
		
	}

}
