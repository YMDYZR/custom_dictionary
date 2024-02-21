package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Tag;
import com.example.demo.model.Term;
import com.example.demo.model.TermTag;
import com.example.demo.response.SearchResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class TermRepositoryImpl implements TermRepositoryCustom {

	//JPAの標準機能。
	@PersistenceContext
	private EntityManager entityManager;

	//keywordsをもとにクエリ実行
	@Override
	public List<SearchResponse> findTermsByTermLike(List<String> keywords) {
		//クエリの構築。
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<SearchResponse> query = cb.createQuery(SearchResponse.class);
		//テーブルどうしの結合
		Root<Term> term = query.from(Term.class);
		Join<Term, TermTag> j = term.join("termTag");
		Join<TermTag, Tag> tag = j.join("tag");

		//AND条件を作成
		List<Predicate> predicates = new ArrayList<>();
		for (String keyword : keywords) {
			predicates.add(cb.like(term.get("termName"), "%" + keyword + "%"));
		}

		//条件をWHERE句に設定
		query.where(cb.and(predicates.toArray(new Predicate[0])));

		//取得したいデータ。
		query.multiselect(term.get("termId"), term.get("termName"), term.get("summary"), term.get("description"),
				tag.get("tagName"));

		TypedQuery<SearchResponse> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();

	}
}
