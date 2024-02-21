package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.response.SearchResponse;
import com.example.demo.response.UniteResponse;

@Service
public class SearchServiceImpl {

	/**
	 * 文字列をスペース区切りで分割し、文字列のリストとして返すメソッド。
	 * @param keyword 分割したい文字列
	 * @return キーワードのリスト
	 */
	public List<String> separetedKeyword(String keyword) {
		//null排除
		if (keyword.equals(null)) {
			throw new NullPointerException();
		}

		//キーワードのリストを返す。
		return Arrays.stream(keyword.split("\s"))
				.filter(n -> !n.isEmpty())
				.collect(Collectors.toList());
	}

	//タグを結合するメソッド
	public Set<UniteResponse> uniteResponse(List<SearchResponse> response) {
		//null排除
		if (response == null) {
			throw new NullPointerException();
		} else if (response.size() == 0) {
			return new HashSet<UniteResponse>();
		}

		//統合後のリスト
		Set<UniteResponse> afterResponse = new HashSet<>();

		//同じタグを
		Map<Long, List<String>> tags = new HashMap<>();
		for (SearchResponse oneRes : response) {
			if (oneRes == null) {
				continue;
			}
			if (!tags.containsKey(oneRes.getTermId())) {
				tags.put(oneRes.getTermId(), new ArrayList<>(Arrays.asList(oneRes.getTagName())));
			} else {
				List<String> tagAdd = tags.get(oneRes.getTermId());
				tagAdd.add(oneRes.getTagName());
				tags.put(oneRes.getTermId(), tagAdd);
			}
		}

		//全タグ
		for (Map.Entry<Long, List<String>> entry : tags.entrySet()) {
			response.indexOf(entry);
		}
		return afterResponse;
	}

}

/*
public List<SearchResponse> uniteResponse(List<SearchResponse> response) {
	//null排除
	if (response == null) {
		throw new NullPointerException();
	} else if (response.size() == 0) {
		return new ArrayList<SearchResponse>();
	}

	//termId順にソートする。
	Collections.sort(response, new Comparator<SearchResponse>() {
		@Override
		public int compare(SearchResponse res1, SearchResponse res2) {
			return res1.getTermId().compareTo(res2.getTermId());
		}
	});

	//統合後のリスト
	List<SearchResponse> afterResponse = new ArrayList<>();

	//同じコメントのタグを統合していく。
	//一番最初のコメントを初期値としてセット
	SearchResponse nowResponse = null;
	StringBuilder tags = new StringBuilder("");
	//以降のコメントも
	for (SearchResponse r : response) {
		if (nowResponse == null) {
			nowResponse = r;
			tags.append("・" + nowResponse.getTagName());
			continue;
		} else if (nowResponse.getTermId() == r.getTermId()) {
			//同じコメントでタグが異なる場合、それをセット
			tags.append("\r\n・" + r.getTagName());
			continue;
		} else {
			//別のコメントの場合
			//前のコメントを出力用のレスポンスに加える。
			nowResponse.setTagName(tags.toString());
			afterResponse.add(nowResponse);
			//次のコメントを取得していく。
			nowResponse = r;
			tags.setLength(0);
			tags.append("・" + r.getTagName());
			continue;
		}
	}
	//一番最後のコメントも忘れずにresponseにセット
	nowResponse.setTagName(tags.toString());
	afterResponse.add(nowResponse);

	return afterResponse;
}*/
