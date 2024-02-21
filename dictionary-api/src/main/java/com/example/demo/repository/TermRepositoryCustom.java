package com.example.demo.repository;

import java.util.List;

import com.example.demo.response.SearchResponse;

public interface TermRepositoryCustom {
	List<SearchResponse> findTermsByTermLike(List<String> keywords);
}
