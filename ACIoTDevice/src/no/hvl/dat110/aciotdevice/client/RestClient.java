package no.hvl.dat110.aciotdevice.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String logpath = "/accessdevice/log/";
	private static String host = "http://localhost:8080";

	public void doPostAccessEntry(String message) {
		
			HttpClient client = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(host+logpath))
	                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(new AccessMessage(message))))
	                .build();
			
			try {
				client.send(request, HttpResponse.BodyHandlers.ofString());
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
			
		// implement a HTTP POST on the service to post the message
		
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {

		AccessCode code = null;
		
		try {
			URL url = new URL(host+codepath);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestMethod("GET");
			con.setDoInput(true);
			code = new Gson().fromJson(new String(con.getInputStream().readAllBytes()), AccessCode.class);
			con.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// implement a HTTP GET on the service to get current access code
		
		return code;
	}
}
