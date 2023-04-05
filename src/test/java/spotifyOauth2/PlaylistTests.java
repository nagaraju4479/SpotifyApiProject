package spotifyOauth2;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.testng.annotations.Test;
import applicationApi.PlayListApi;
import io.restassured.response.Response;
import pojo.Error;
import pojo.Playlist;

public class PlaylistTests {
	

	@Test
	public void createPlayList()
	{

		Playlist requestPayload = new Playlist().
				setName("BestSongsPlaylist3").				
				setDescription("MyDescription").
				setPublic(false);
		
		
		Response response =PlayListApi.post(requestPayload);
		Playlist responsePayload = response.as(Playlist.class);
		assertThat(response.statusCode(), equalTo(201));
		
		assertThat(responsePayload.getName(),equalTo(requestPayload.getName()));
		assertThat(responsePayload.getDescription(),equalTo(requestPayload.getDescription()));
		assertThat(responsePayload.getPublic(),equalTo(requestPayload.getPublic()));
	}
	
	@Test
	public void getAPlayList()
	{	
		
		Response response = PlayListApi.get("6fgADr6PLXPn3SNpjBU3Lw");
		Playlist responseBody = response.as(Playlist.class);
		assertThat(response.statusCode(), equalTo(200));
		assertThat(responseBody.getName(),equalTo("updated Playlist"));
		assertThat(responseBody.getDescription(), equalTo("New playlist description"));
		assertThat(responseBody.getPublic(), equalTo(false));
		
		}
	
	@Test
	public void updateAPlayList()
	{
		

		Playlist requestPayload = new Playlist().
				setName("MyPlaylsit2").
				setDescription("MyDescription").
				setPublic(false);
		
		Response response = PlayListApi.put("7oCwfSrIwQGzlNwoT3WGxF", requestPayload);
		assertThat(response.statusCode(), equalTo(200));
	
	}
	
	@Test
	public void invalidPayloadTest()
	{

		Playlist requestPayload = new Playlist().
				setName("").
				setDescription("MyDescription").
				setPublic(false);
		
		Response response = PlayListApi.error(requestPayload);
		Error error = response.as(Error.class);
		assertThat(response.statusCode(), equalTo(400));
		
		assertThat(error.getError().getStatus(),equalTo(400));
		assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));
		
	
	}
	@Test
	public void invalidTokenTest()
	{
		Playlist requestPayload = new Playlist().
				setName("testName").
				setDescription("abcd").
				setPublic(false);
		
		Response response = PlayListApi.post("12345", requestPayload);
		Error errorResponse = response.as(Error.class);
		assertThat(response.statusCode(), equalTo(401));
		
		assertThat(errorResponse.getError().getStatus(), equalTo(401));
		assertThat(errorResponse.getError().getMessage(), equalTo("Invalid access token"));
		
		
	}
	

}
