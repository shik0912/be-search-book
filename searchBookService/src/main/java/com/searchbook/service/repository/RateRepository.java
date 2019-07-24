package com.searchbook.service.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.searchbook.service.entity.RateEntity;

public interface RateRepository extends PagingAndSortingRepository<RateEntity, Long> {
	RateEntity findByKeyword(String keyword);
	List<RateEntity> findTop10ByOrderByNumberDesc();
}
