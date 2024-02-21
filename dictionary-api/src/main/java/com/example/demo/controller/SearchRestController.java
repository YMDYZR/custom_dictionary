package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.TagRepositoryImpl;
import com.example.demo.repository.TermRepositoryImpl;
import com.example.demo.request.SearchRequestModel;
import com.example.demo.response.SearchResponse;
import com.example.demo.response.UniteResponse;
import com.example.demo.service.SearchServiceImpl;

import lombok.RequiredArgsConstructor;

//@RestControllerをつけたクラス内のメソッドの戻り値がHTTPのレスポンスボディとして返される。
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SearchRestController {

	private final SearchServiceImpl service;
	private final TermRepositoryImpl termQuery;
	private final TagRepositoryImpl tagQuery;

	@PostMapping("/keywords")
	public Set<UniteResponse> search(@RequestBody SearchRequestModel request) {
		//入力されたワードを分割し取得
		String keyword = request.getKeyWord();
		List<String> keywords = service.separetedKeyword(keyword);

		//タグで検索するか、用語のLIKE検索をするか
		boolean tagSearch = request.isTag();

		//検索
		List<SearchResponse> response;
		if (tagSearch) {
			//クエリ実行結果
			response = tagQuery.findTermsByTag(keywords);
		} else {
			response = termQuery.findTermsByTermLike(keywords);
		}
		//タグを複数含むコメントを一つにまとめる
		return service.uniteResponse(response);

	}

	//TODO:バリデーションチェックの代わりに例外処理。
	//@ExceptionHandler

}
