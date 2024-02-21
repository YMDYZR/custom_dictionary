package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Term;

public interface TermRepository extends JpaRepository<Term, Long>, TermRepositoryCustom {

}
