package api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import api.SpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Error;
import pojo.Playlist;

public class RestResource {
	static String access_token ="BQDeEo2XXF-YYh-TOJIH1fMyDmxfVKwRd1kox-DUmWLFNIMn0G5EXoIzrps4XqaYqAAWBomPj69dWc1xjI-eA7AZXi7Gwxy_6-y64cJAXzuHaCymKxsnidXTtPbZrvUOZh7z8HSMrfESUgVjP7IYAqfreCweB6fNYu8sDR6X7-ec5YCJxTKZrO9YIFYDZ6XM0Nj8PXA1Uprn8geuYGX6plNF0vAOTaSosWmsaaKc9UMT6Hd3rQ8U-vrA4n0zzO706Qek6wBDho5VI4oIWw";
	
	public static Response post(String path,String access_token, Playlist requestPayload)
	{
		
		return given().spec(SpecBuilder.getRequestSpec()).
				header("Authorization", "Bearer "+access_token).
				body(requestPayload).

			when().
				post(path).
			then().spec(SpecBuilder.getResponseSpec()).

				extract().response();
	}
	
	public static Response postToken(HashMap<String , String> formParams)
	{
	return given(SpecBuilder.getTokenRequestSpec()).
		formParams(formParams).
	when().post("/api/token").
	then().spec(SpecBuilder.getResponseSpec()).
		extract().
		response();
	}
	

	public static Response get(String path, String access_token)
	{
		return given().spec(SpecBuilder.getRequestSpec()).
				header("Authorization", "Bearer "+access_token).

				when().
					get(path).
				then().spec(SpecBuilder.getResponseSpec()).
				extract().response();
	}

	public static Response put(String path,String access_token,Playlist requestPayload)
	{
		return given().spec(SpecBuilder.getRequestSpec()).
		header("Authorization", "Bearer "+access_token).
		body(requestPayload).
	when().
		put(path).
	then().spec(SpecBuilder.getResponseSpec()).extract().response();

	}
	
	public static Response error(String path,String access_token,Playlist requestPayload)
	{
		return given().spec(SpecBuilder.getRequestSpec()).
				header("Authorization", "Bearer "+access_token).
				body(requestPayload).
			when().
				post(path).
			then().spec(SpecBuilder.getResponseSpec()).extract().response();
	}
	
}
