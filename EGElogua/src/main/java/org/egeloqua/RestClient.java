package org.egeloqua;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {
	private String authToken;
	private String baseUrl;       
	public RestClient(String user, String password, String url)
	{
		baseUrl = url;             
		String authString = user + ":" + password;
		authToken = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(authString.getBytes());           
	}             

	public String execute(String uri, String method) throws Exception {
		return execute(uri, method, null);
	}

	public String execute(String uri, String method, String body) throws Exception
	{
	   String response ="";
	   try
	   {           
		   URL url = new URL(baseUrl + uri);
		   HttpURLConnection conn = (HttpURLConnection) url.openConnection();                         
		   conn.setInstanceFollowRedirects(false);
		  conn.setRequestMethod(method.toString());
		  conn.setRequestProperty("Content-Type", "application/json");
		  conn.setRequestProperty("Accept", "application/json");
		  //System.out.println(authToken);
		  conn.setRequestProperty("Authorization", authToken);         
		          
		  if (method == "POST" || method == "PUT"){
		      if(null != body){
		          conn.setDoOutput(true);
		          final OutputStream os = conn.getOutputStream();
		          os.write(body.getBytes());
		          os.flush();
		          os.close();
		      }
		  }

		  InputStream is = conn.getInputStream();
		  BufferedReader rd = new BufferedReader(new InputStreamReader( is));
		  String line; 
		  while ((line = rd.readLine()) != null)
		  {
			  response += line;
		  }           
		  rd.close();
		  conn.disconnect();
	   }
	   catch (Exception e){
		   throw e;
	   }
	   return response;
	}
}
