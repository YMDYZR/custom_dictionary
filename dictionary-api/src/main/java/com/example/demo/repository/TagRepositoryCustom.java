package com.example.demo.repository;

import java.util.List;

import com.example.demo.response.SearchResponse;

public interface TagRepositoryCustom {
	List<SearchResponse> findTermsByTag(List<String> keywords);

}
