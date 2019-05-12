package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebAPI {
	public static String call() {
//		String entryPoint = "http://httpbin.org/get";
		String entryPoint = "https://www.geocoding.jp/api/?q=%e6%97%a5%e6%9c%ac%e3%82%a2%e3%82%a4%e3%83%93%e3%83%bc%e3%82%a8%e3%83%a0%e6%9c%ac%e7%a4%be";
		HttpURLConnection con = null;
		InputStream in = null;
		BufferedReader reader = null;

		String result = "default";

		try {
			URL url = new URL(entryPoint);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			con.connect();

			int status = con.getResponseCode();

			System.out.println("HTTPステータス:" + status);

			if (status == HttpURLConnection.HTTP_OK) {
				in = con.getInputStream();

				reader = new BufferedReader(new InputStreamReader(in));

				StringBuilder output = new StringBuilder();
				String line;

				while ((line = reader.readLine()) != null) {
					output.append(line);
				}
//				System.out.println(output.toString());
				result = output.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (con != null) {
					con.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
