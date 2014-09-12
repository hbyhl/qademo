package com.example.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpOperations {
	private static HttpClient httpClient = new DefaultHttpClient(); 
	public static void sendPost(String str) throws Exception {
		// TODO Auto-generated method stub
		String url = "http://10.1.78.117:8080/qademo/sound";
		// POST的URL
		HttpPost httppost = new HttpPost(url);
		// 建立HttpPost对象
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 建立一个NameValuePair数组，用于存储欲传送的参数
		params.add(new BasicNameValuePair("question", str));
		// 添加参数
		httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		// 设置编码
		HttpResponse response = new DefaultHttpClient().execute(httppost);
		// 发送Post,并返回一个HttpResponse对象
		if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
			String result = EntityUtils.toString(response.getEntity());
			// 得到返回的字符串
			System.out.println(result);
			// 打印输出
		}
	}

	public static void sendGet(String str) throws Exception {
		
		String url = "http://10.1.78.117:8080/master/hello?str=1";
        try {  
            // Get请求  
            HttpGet httpget = new HttpGet(url);  
            // 设置参数  
            
            httpget.setURI(new URI(httpget.getURI().toString()+str));  
            // 发送请求  
            HttpResponse httpresponse = httpClient.execute(httpget);  

           
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (URISyntaxException e) {  
            e.printStackTrace();  
        }  
	}

	public static void main(String[] args) throws Exception {
		System.out.println("niaho");
//		sendPost("nihao");
	}
}
