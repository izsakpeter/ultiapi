package hu.ulti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;

public class Request {
	
	private static JSONObject request(String url) {
		JSONObject res = null;
		
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(response.toString());

			res = new JSONObject(response.toString());

		} catch (Exception e) {
			// TODO: handle exception
		}

		return res;
	}

	public static JSONObject joinReq(int id) {
		String url = "http://localhost:8888/start?id=" + id;
		return request(url);
	}

	public static JSONObject changeOrder(int id, boolean isColoredOrder) {
		String url = "http://localhost:8888/order?id=" + id + "&iscolororder=" + isColoredOrder;
		return request(url);
	}

	public static JSONObject setStartingValue(int id, int value) {
		String url = "http://localhost:8888/startingValue?id=" + id + "&value=" + value;
		return request(url);
	}
	
	//public static JSONObject call(int id, List<Integer> call, List<Integer> talon) {
	public static void call(int id, List<Integer> call, List<Integer> talon) {
		String url = "http://localhost:8888/call?id=" + id + "&call=" + call + "&talonid=" + talon;
		System.out.println(url + " url");
		//return request(url);
	}
}
