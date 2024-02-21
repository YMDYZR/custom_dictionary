package com.example.demo.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.example.demo.model.Tag;
import com.example.demo.model.Term;
import com.example.demo.model.TermTag;
import com.example.demo.repository.TagRepository;
import com.example.demo.repository.TermRepository;
import com.example.demo.repository.TermTagJoinRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
	private final TermRepository termRepository;
	private final TagRepository tagRepository;
	private final TermTagJoinRepository joinRepository;

	@Override
	public void run(String... args) throws Exception {
		//JSONファイルのパス
		//用語テーブルのデータ
		ClassPathResource termResource = new ClassPathResource("terms.json");
		//タグテーブルのデータ
		ClassPathResource tagResource = new ClassPathResource("tags.json");
		//中間テーブルのデータ
		ClassPathResource joinResource = new ClassPathResource("terms_tags_join.json");

		//ファイルの内容を読み込む
		byte[] termJsonData = StreamUtils.copyToByteArray(termResource.getInputStream());
		byte[] tagJsonData = StreamUtils.copyToByteArray(tagResource.getInputStream());
		byte[] joinJsonData = StreamUtils.copyToByteArray(joinResource.getInputStream());

		//JSONデータをjavaのオブジェクトに変換
		ObjectMapper termMapper = new ObjectMapper();
		List<Term> term = termMapper.readValue(termJsonData, new TypeReference<List<Term>>() {
		});
		ObjectMapper tagMapper = new ObjectMapper();
		List<Tag> tag = tagMapper.readValue(tagJsonData, new TypeReference<List<Tag>>() {
		});
		ObjectMapper joinMapper = new ObjectMapper();
		List<TermTag> join = joinMapper.readValue(joinJsonData, new TypeReference<List<TermTag>>() {
		});

		//オブジェクトをデータベースに保存
		termRepository.saveAll(term);
		tagRepository.saveAll(tag);
		joinRepository.saveAll(join);
	}
}
