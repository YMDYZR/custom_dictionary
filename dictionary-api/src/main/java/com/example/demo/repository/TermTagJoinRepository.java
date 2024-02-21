package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.TermTag;

public interface TermTagJoinRepository extends JpaRepository<TermTag, Long> {

}
