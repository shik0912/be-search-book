package com.searchbook.service.proxy;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SearchBookKakaoProxy implements ISearchBookKakaoProxy {
	private String APIUrl = "https://dapi.kakao.com/v3/search/book?target=";
	
	public List<String> getBookA(String query, String sort, String page, String size, String target) throws IOException, URISyntaxException {
		List<String> result = new ArrayList<String>();
		try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			
			URIBuilder uriBuilder = new URIBuilder(APIUrl + target);
			uriBuilder.setParameter("query", query);
			uriBuilder.setParameter("sort", sort);
			uriBuilder.setParameter("page", page);
			uriBuilder.setParameter("size", size);
			
			URI uri = uriBuilder.build();
			HttpGet getRequest = new HttpGet(uri);
			getRequest.setHeader("Authorization", "KakaoAK 9456ede04f0708bd20206f7e57a3b422");

			HttpResponse httpResponse = httpClient.execute(getRequest);
			HttpEntity httpEntity = httpResponse.getEntity();
			
			String response = EntityUtils.toString(httpEntity, "UTF-8");
			
			JSONParser metaP = new JSONParser();
			JSONObject metaObj = (JSONObject)metaP.parse(response);
			result.add(metaObj.get("meta").toString());
			//metaObj = (JSONObject)metaP.parse(metaObj.get("meta").toString());
			//String isEnd = metaObj.get("is_end").toString();
			
			JSONParser docP = new JSONParser();
			JSONObject docObj = (JSONObject)docP.parse(response);
			
			String temp = docObj.get("documents").toString().substring(1);
			String doc = temp.substring(0, temp.length()-1);
			result.add(doc);
		}
	    catch (Exception e)
	    {
	    	result.add(e.getMessage());
	    }
		return result;
	}
}
