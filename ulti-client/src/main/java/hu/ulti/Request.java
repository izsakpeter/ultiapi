package hu.ulti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Request {
	
	public static JSONObject joinReq(int id) {
		JSONObject res = null;
		String url = "http://localhost:8888/start?id=" + id;

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
	
	public static JSONObject changeOrder(int id, boolean isColoredOrder) {
		
		JSONObject res = null;
		String url = "http://localhost:8888/order?id=" + id +"&iscolororder=" + isColoredOrder;

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
}
