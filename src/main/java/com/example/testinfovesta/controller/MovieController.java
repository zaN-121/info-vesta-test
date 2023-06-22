package com.example.testinfovesta.controller;

import com.example.testinfovesta.model.Movie;
import com.example.testinfovesta.model.dto.MovieRequest;
import com.example.testinfovesta.repository.IGenreRepository;
import com.example.testinfovesta.service.MovieService;
import com.example.testinfovesta.util.specification.MovieSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MovieController {
    @Autowired
    public MovieService movieService;
    @Autowired
    public IGenreRepository genreRepository;
    @GetMapping("/")
    String mapHome(
            @RequestParam(name = "search", defaultValue = "", required = false) String search,
            @RequestParam(name = "genre", defaultValue = "", required = false) String genre,
            Model model) {
        Specification<Movie> specification = Specification.where(null);

        if (!search.equals("") && !genre.equals("")) {
            specification = MovieSpecification.withTitleAndGenreName(search, genre);
        } else if (!search.equals("") && genre.equals("")) {
            specification = MovieSpecification.withTitle(search);
        }
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("movies", movieService.findAll(specification));
        return "home";
    }

    @GetMapping("/movie/add")
    String addMovie(Model model) {
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("selectedGenres");
        return "add-movie";
    }

    @PostMapping("/movie/add")
    String  addNewMovie(@RequestBody MovieRequest movieRequest, Model model) {
        movieService.add(movieRequest);
        return "redirect:/";
    }

    @GetMapping("/movie/edit/{id}")
    String editMovieForm(@PathVariable Integer id, Model model) {
        model.addAttribute("movieId", id);
        model.addAttribute("genres", genreRepository.findAll());
        return "edit-movie";
    }

    @PutMapping("/movie/edit/{id}")
    String editMovie(@PathVariable Integer id, @RequestBody MovieRequest movieRequest) {
        movieService.updateById(movieRequest, id);
        return "redirect:/";
    }

    @DeleteMapping("movie/delete/{id}")
    String deleteMovie(@PathVariable Integer id) {
        movieService.deleteById(id);
        return "redirect:/";
    }
}
