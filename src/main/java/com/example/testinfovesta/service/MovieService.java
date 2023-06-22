package com.example.testinfovesta.service;

import com.example.testinfovesta.Exception.NotFoundException;
import com.example.testinfovesta.model.Genre;
import com.example.testinfovesta.model.Movie;
import com.example.testinfovesta.model.dto.MovieRequest;
import com.example.testinfovesta.repository.IGenreRepository;
import com.example.testinfovesta.repository.IMovieRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService implements IService<Movie, MovieRequest> {
    public IMovieRepository movieRepository;
    public IGenreRepository genreRepository;
    public ModelMapper modelMapper;
    @Autowired
    public MovieService(IMovieRepository movieRepository, IGenreRepository genreRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Movie add(MovieRequest movieRequest) {
        System.out.println(movieRequest.toString());
        Movie movie = modelMapper.map(movieRequest, Movie.class);
        movie.setGenres(new ArrayList<>());
        movie.setTitle(movieRequest.getTitle());
        System.out.println(movie.toString());
        Optional<Genre> genre = Optional.empty();

        if (movieRequest.getGenresIds().size() != 0) {
            for (Integer genresId : movieRequest.getGenresIds()) {
                genre = genreRepository.findById(genresId);
                if (genre.isPresent()) {
                    movie.getGenres().add(genre.get());
                } else {
                    throw new NotFoundException("Genre Not Found");
                }
            }
        }

        movie = movieRepository.save(movie);

        return movie;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            throw new NotFoundException("Movie Not Found");
        }

        movieRepository.delete(movie.get());
    }

    @Override
    public Movie updateById(MovieRequest movieRequest, Integer id) {
        Optional<Movie> oldMovie = movieRepository.findById(id);
        if (oldMovie.isEmpty()) {
            throw new NotFoundException("Movie Not Found");
        }
        Movie newMovie = modelMapper.map(movieRequest, Movie.class);

        Movie movie = oldMovie.get();
        movie.setTitle(newMovie.getTitle());
        movie.setSummary(newMovie.getSummary());
        movie.setDirector(newMovie.getDirector());

        Optional<Genre> genre = Optional.empty();

        if (movieRequest.getGenresIds().size() != 0) {
            for (Integer genresId : movieRequest.getGenresIds()) {
                genre = genreRepository.findById(genresId);
                if (genre.isPresent()) {
                    movie.getGenres().add(genre.get());
                } else {
                    throw new NotFoundException("Genre Not Found");
                }
            }
        }

        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findAll(Specification<Movie> movieSpecification) {
        return movieRepository.findAll(movieSpecification);
    }

    @Override
    public Movie getById(Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            throw new RuntimeException("Movie Not Found");
        }

        return movie.get();
    }
}
