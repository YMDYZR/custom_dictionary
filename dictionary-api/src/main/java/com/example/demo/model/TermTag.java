package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TERM_TAG")
public class TermTag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TERM_TAG_ID")
	private Long termTagId;

	@ManyToOne
	@JoinColumn(name = "TERM_ID")
	private Term term;

	@ManyToOne
	@JoinColumn(name = "TAG_ID")
	private Tag tag;
}
