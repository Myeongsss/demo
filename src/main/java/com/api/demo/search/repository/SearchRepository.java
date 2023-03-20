package com.api.demo.search.repository;


import com.api.demo.search.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends JpaRepository<Search, String> {

    Search findByKeyword(String keyword);
}
