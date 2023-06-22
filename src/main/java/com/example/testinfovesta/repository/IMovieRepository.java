package com.example.testinfovesta.repository;

import com.example.testinfovesta.model.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer>, JpaSpecificationExecutor<Movie> {
    List<Movie> findAll(Specification<Movie> spec);
}
