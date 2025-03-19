package com.in28minutes.springboot.tutorial.basics.application.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.stereotype.Controller;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Genre;

import com.in28minutes.springboot.tutorial.basics.application.configuration.service.IGenreService;

import java.text.SimpleDateFormat;

import java.util.Date;

@Controller
public class GenreController {
    @Autowired

    private IGenreService genreService;

    @InitBinder

    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("/genres")
    public String listAll(ModelMap model) {
        // List<Genre> listGenres = genreRepo.findByOrderByGenreIdAsc();

        model.put("genres", genreService.getGenres());
        return "genres";
    }

    @RequestMapping("/genres/add")
    public String showAddGenre(ModelMap model) {
        model.addAttribute("genre", new Genre());
        model.addAttribute("title", "Ajouter Genre");
        return "addgenre";
    }

    @RequestMapping(value = "/genres/add", method = RequestMethod.POST)
    public String addGenre(ModelMap model, Genre genre, BindingResult result) {
        if (result.hasErrors()) {
            return "addgenre";
        }
        genreService.addGenre(genre.getName());
        return "redirect:/genres";
    }

    @RequestMapping(value = "/genres/update", method = RequestMethod.POST)
    public String updateGenre(ModelMap model, Genre genre, BindingResult result) {
        if (result.hasErrors()) {
            return "updategenre";
        }
        genreService.updateGenre(genre);
        return "redirect:/genres";
    }

    @RequestMapping(value = "/genres/delete", method = RequestMethod.POST)
    public String deleteGenre(ModelMap model, @RequestParam long id) {
        genreService.deleteGenre(id);
        return "redirect:/genres";
    }

}
