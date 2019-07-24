package com.searchbook.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.searchbook.service.entity.RateEntity;
import com.searchbook.service.repository.RateRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "실시간검색 API", tags = "RateController")
@RestController
public class RateController {
	@Autowired
	private RateRepository rateRepository;

	@ApiOperation("검색증가")
	@RequestMapping(value = "/rate/search/{keyword}", method = RequestMethod.GET)
	private void GetOne(@PathVariable String keyword) {
		try {
			RateEntity rateEntity = rateRepository.findByKeyword(keyword);
			if (rateEntity == null) {
				rateRepository.save(new RateEntity(keyword, 1));
			} else {
				rateEntity.setNumber(rateEntity.getNumber() + 1);
				rateRepository.save(rateEntity);
			}
		} catch (Exception e) {

		}
	}

	@ApiOperation("순위목록")
	@RequestMapping(value = "/rate/", method = RequestMethod.GET)
	private List<RateEntity> Get() {
		List<RateEntity> result = new ArrayList<RateEntity>();

		try {
			result = rateRepository.findTop10ByOrderByNumberDesc();
		} catch (Exception e) {

		}

		return result;
	}
}
