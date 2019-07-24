package com.searchbook.service.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.searchbook.service.proxy.SearchBookKakaoProxy;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "도서정보 API", tags = "BookController")
@RestController
public class BookController {
	private SearchBookKakaoProxy searchBookKakaoProxy = new SearchBookKakaoProxy();

	@ApiOperation("도서검색")
	@RequestMapping(value="/book/{query}/{sort}/{page}/{size}/{target}", method=RequestMethod.GET)
	public String GetA(
			@PathVariable String query, 
			@PathVariable String sort, 
			@PathVariable String page, 
			@PathVariable String size, 
			@PathVariable String target) throws IOException, URISyntaxException {
		
		String result = "";
		String currenPage = page;
		try {
			String start = "{\"documents\":[";
			String middle = "],\"meta\":";
			String end = "}";
			
			result = result + start;
			List<String> response = new ArrayList<String>();
			while (true) {
				response = searchBookKakaoProxy.getBookA(query, sort, currenPage, size, target);
				
				String doc = response.get(1);
				result = result + doc + ","; 
				
				String meta = response.get(0);
				JSONParser metaP = new JSONParser();
				JSONObject metaObj = (JSONObject)metaP.parse(meta);
				String isEnd = metaObj.get("is_end").toString();
				
				if (isEnd.equals("true")) {
					result = result.substring(0, result.length()-1);
					result = result + middle;
					result = result + meta;
					break;
				}
				
				String startPage = currenPage;
				int to = Integer.parseInt(startPage) + 1;
				int from = to;
				String nextPage = Integer.toString(from);
				currenPage = nextPage;
			}
			result = result + end;
		} catch (Exception e) {
			//result = e.getMessage();
			result = "";
		}
		
		return result;
    }
}
