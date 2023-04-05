package applicationApi;

import static io.restassured.RestAssured.given;

import api.RestResource;
import api.SpecBuilder;
import api.TokenManager;
import io.restassured.response.Response;
import pojo.Error;
import pojo.Playlist;

public class PlayListApi {
	
	public static Response post(Playlist requestPayload)
	{
		return RestResource.post("/users/31hrxdawqiqh37doj66q2wam7tmi/playlists", TokenManager.getToken(), requestPayload);

	}
	
	public static Response post(String invalid_token,Playlist requestPayload)
	{
		return RestResource.post("/users/31hrxdawqiqh37doj66q2wam7tmi/playlists", invalid_token, requestPayload);

	}
	
	public static Response get(String playListId)
	{
		return RestResource.get("/playlists/"+playListId, TokenManager.getToken());

	}

	public static Response put(String playlistId,Playlist requestPayload)
	{
		return RestResource.put("/playlists/"+playlistId, TokenManager.getToken(), requestPayload);
		

	}
	
	public static Response error(Playlist requestPayload)
	{
		return RestResource.error("/users/31hrxdawqiqh37doj66q2wam7tmi/playlists", TokenManager.getToken(), requestPayload);

	}
	
}
