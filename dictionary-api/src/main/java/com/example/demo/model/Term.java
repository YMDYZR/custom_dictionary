package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TERM")
public class Term {
	//用語ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TERM_ID")
	private Long termId;

	//用語の名
	@NotBlank
	@Size(max = 40)
	@Column(name = "TERM_NAME")
	private String termName;

	//要約
	@Size(max = 40)
	@Column(name = "SUMMARY")
	private String summary;

	//詳細
	@Size(max = 2000)
	@Column(name = "DESCRIPTION")
	private String description;

	//中間テーブルとの結合。
	@OneToMany(mappedBy = "term")
	private List<TermTag> termTag = new ArrayList<>();
}
