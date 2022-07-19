package com.shopping.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service
public class ExrateService {

	public Map<String,Double> getexrate() {
		Map<String,Double> exMap= new HashMap<>();
        try{
            URL rterinfo = new URL("https://tw.rter.info/capi.php");
            URLConnection connection = rterinfo.openConnection();
            HttpURLConnection htCon = (HttpURLConnection) connection;
            int code = htCon.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK)
            {
            	
            	String[] exarray= {"USDTWD","USDJPY","USDCNY"};
                BufferedReader in=new BufferedReader(new InputStreamReader(htCon.getInputStream()));
                String bufread = in.readLine();
               
                JSONObject inputLine = JSON.parseObject(bufread);
                for(String s:exarray) {
                		
                String s1=inputLine.get(s).toString();
                Double d1=Double.valueOf(s1.substring(s1.lastIndexOf(":")+1,s1.length()-1));
                exMap.put(s,d1);
                
                }
             
            }
         
    }catch (Exception e) {}
        return exMap; }
	public Date  getdate(Date d) { 
		
		int n  =(int) (Math.random() * 1);///////not random
		
		Date dt =addDays(d, n);  System.out.print(d.toString());
		
		return dt;
	}
	
	private static Date addDays(Date d, int days)
	    {
	        d.setTime(d.getTime() + days * 1000 * 60 * 60 * 24);
	        return d;
	    }
}
