package com.example.demo.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UniteResponse {
	private Long termId;
	private String termName;
	private String summary;
	private String description;
	private List<String> tagName;

}
