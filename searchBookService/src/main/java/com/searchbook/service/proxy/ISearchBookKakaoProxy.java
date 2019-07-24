package com.searchbook.service.proxy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
public interface ISearchBookKakaoProxy {
	public List<String> getBookA(String query, String sort, String page, String size, String target) throws IOException, URISyntaxException;
}
