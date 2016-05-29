package com.mk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mk.beans.Locality;
import com.mk.dao.LocalityDao;
import com.mk.uibeans.UILocalitySuggestionItem;
import com.mk.utils.Constants;


@Controller
public class LocalityService {
	
	@Autowired
	private LocalityDao localityDao;
	
	public void setLocalityDao(LocalityDao localityDao) {
		this.localityDao = localityDao;
	}

//	@RequestMapping(value="/locality/search.do", method=RequestMethod.GET)
//	public @ResponseBody ResponseEntity<String> search(@RequestParam("q") String keywords) {
//		
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
//		 
//		String content = localityDao.find(5, keywords.split(" "));
//
//		return new ResponseEntity<String>(content, responseHeaders, HttpStatus.CREATED);
//	}
//
//	@RequestMapping(value="/locality/setLocality.do", method=RequestMethod.GET)
//	public @ResponseBody Boolean setLocality(@RequestParam("localityId") String localityId, HttpSession session) {
//		
//
//		return true;
//	}
	
	@RequestMapping(value="/locality/localitySuggestion.do", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody() List<UILocalitySuggestionItem>  getLocalitySearchSuggestion(@RequestParam("term") String queryString) {
		
		if("debug".equals(queryString))
		{
			return UILocalitySuggestionItem.getLocalitySuggestionDummyData();
		}
		else{
			List<Locality> localities = localityDao.searchLocality(Constants.DEFAULT_SEARCH_SIZE, queryString.split(" "));
			
			return UILocalitySuggestionItem.fillData(localities);
		}
		
		
	}
}
