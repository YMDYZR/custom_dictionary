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

@Getter
@Setter
@Entity
@Table(name = "TAG")
public class Tag {
	//主キー
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TAG_ID")
	private Long tagId;

	//タグ名
	@NotBlank
	@Size(max = 40)
	@Column(name = "TAG_NAME")
	private String tagName;

	//中間テーブルとの結合。データ取得時には関連エンティティも一緒に取得できるfetch設定
	@OneToMany(mappedBy = "tag")
	private List<TermTag> termTag = new ArrayList<>();
}
