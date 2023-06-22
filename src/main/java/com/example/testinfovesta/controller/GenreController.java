package com.example.testinfovesta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/genre")
public class GenreController {
    @GetMapping
    String getGenres(Model model) {
        return "genre";
    }

    @GetMapping("/add")
    String addGenre(Model model) {
        try {

            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(0);
        }
        return "redirect:/";
    }
}
