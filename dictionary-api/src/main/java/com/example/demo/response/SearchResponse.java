package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchResponse {
	private Long termId;
	private String termName;
	private String summary;
	private String description;
	private String tagName;
	
}
