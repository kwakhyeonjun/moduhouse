package com.ssafy.vue.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subs")
public class SubscriptionController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	
	@GetMapping("/list")
	public String getSubscription(){
		StringBuilder sb = new StringBuilder();
		try {
			System.out.println("오긴함?");
			String SERVICE_KEY = "6JlZmbEO5QgSYtVec8fibBy7zBHv0zU6MIWXmYsAtyeZlg2ZmEyAsSKX7wS/898NRSi8t4CI+URJ4joR3OXf4g==";
			StringBuilder urlBuilder = new StringBuilder("http://openapi.reb.or.kr/OpenAPI_ToolInstallPackage/service/rest/ApplyhomeInfoSvc/getLttotPblancList");
			urlBuilder.append("?ServiceKey=" + URLEncoder.encode(SERVICE_KEY, "UTF-8")); /*Service Key*/
			urlBuilder.append("&startmont=202109"); // 공고 시작월
			urlBuilder.append("&endmonth=202111"); // 공고 종료월	
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
//			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			System.out.println(sb.toString());
			System.out.println("안옴????");
		} catch (Exception e) {
			 
		}
		return sb.toString();
	}
}
