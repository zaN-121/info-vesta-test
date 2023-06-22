package com.example.testinfovesta.util.specification;

import com.example.testinfovesta.model.Genre;
import com.example.testinfovesta.model.Movie;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {
    public static Specification<Movie> withTitleAndGenreName(String title, String genreName) {
        return (root, query, builder) -> {
            Join<Movie, Genre> genreJoin = root.join("genres");
            Predicate titlePredicate = builder.like(root.get("title"), "%" + title + "%");
            Predicate genreNamePredicate = builder.equal(genreJoin.get("name"), genreName);
            return builder.and(titlePredicate, genreNamePredicate);
        };
    }
    public static Specification<Movie> withTitle(String title) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + title + "%");
    }

}
