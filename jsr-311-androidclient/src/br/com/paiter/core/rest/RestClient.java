package br.com.paiter.core.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;

public class RestClient {

	private static final int TIMEOUT_CONNECTION = 3000;
	private static final int TIMEOUT_SOCKET = 5000;
	
	private String server;
	private ObjectMapper objectMapper;
	
	
	
	public RestClient(String server) {
		this.server = server;
		objectMapper = new ObjectMapper();
	}

	public String getOrDeleteBaseURI(String uriPath, boolean get) throws IOException{
		
		String result = "";
		
		try{
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT_CONNECTION);
			
			HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT_SOCKET);
			
			DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
			
			HttpRequestBase getRequest;
			if(get){
				getRequest = new HttpGet(getBase() + uriPath);
			}else{
				getRequest = new HttpDelete(getBase() + uriPath);
			}
			
			getRequest.addHeader("accept","application/json");
			HttpResponse response = httpClient.execute(getRequest);
			result = getResult(response).toString();
			httpClient.getConnectionManager().shutdown();
		}
		catch(Throwable ex){
			IOException io = new IOException("error");
			io.initCause(ex);
			throw io;
		}
		
		return result;
	}
	
	public String postOrPutBaseURI(String jsonContent, String uriPath, boolean post){
		
		String result = "";
		try{
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT_CONNECTION);
			HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT_SOCKET);
			DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
			
			HttpEntityEnclosingRequestBase postRequest;
			if(post){
				postRequest = new HttpPost(getBase() + uriPath);
			}else{
				postRequest = new HttpPut(getBase() + uriPath);
			}
			StringEntity input = new StringEntity(jsonContent);
			input.setContentType("application/json");
			HttpResponse response = httpClient.execute(postRequest);
			result = getResult(response).toString();
			httpClient.getConnectionManager().shutdown();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	private StringBuilder getResult(HttpResponse response)
		throws IllegalStateException, IOException{
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(response.getEntity().getContent())),1024);
		String output = "";
		while((output = br.readLine()) != null){
			result.append(output);
		}
		return result;
	}

	private String getBase() {
		return server;
	}
	
	public String postObject(Object object, String strURI){
		String result = "";
		try{
			result = postOrPutBaseURI(objectMapper.writeValueAsString(object), strURI, true);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public <T> T getObject(String strUrl){
		try{
			String json = getOrDeleteBaseURI(strUrl, true);
			System.out.println(json);
			return objectMapper.readValue(json, new TypeReference<T>() {
			});
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		return null;
	}

	public <T> List<T> getList(String strUrl, Class<T> clazz){
		try{
			String json = getOrDeleteBaseURI(strUrl, true);
			List<T> list;	
			TypeFactory typeFactory = TypeFactory.defaultInstance();
			list = objectMapper.readValue(json, typeFactory.constructCollectionType(ArrayList.class, clazz));
			return list;
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		return new ArrayList<T>();
	}

}
