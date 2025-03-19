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

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Artist;

import com.in28minutes.springboot.tutorial.basics.application.configuration.service.IArtistService;

import java.text.SimpleDateFormat;

import java.util.Date;

@Controller

public class ArtistController {

    @Autowired

    private IArtistService artistService;

    @InitBinder

    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("/artists")

    public String listAll(ModelMap model) {
        // List<Artist> listartists = ArtistRepo.findByOrderByLastNameAsc();

        model.put("artists", artistService.getAllArtists());
        return "artists";
    }

    @RequestMapping("/artists/add")

    public String showAddArtistPage(ModelMap model) {
        model.addAttribute("artist", new Artist());
        model.addAttribute("title", "Ajouter Artist");
        return "addartist";
    }

    @RequestMapping(value = "/artists/add", method = RequestMethod.POST)

    public String addArtist(ModelMap model, Artist Artist, BindingResult result) {

        if (result.hasErrors()) {

            return "addArtist";
        }

        artistService.saveArtist(Artist);
        return "redirect:/artists";
    }

    @RequestMapping(value = "/artists/update", method = RequestMethod.GET)

    public String showUpdateArtistPage(@RequestParam long id, ModelMap model) {
        Artist Artist = artistService.getArtistById(id).get();
        model.put("Artist", Artist);
        model.addAttribute("title", "Mettre à jour Employé");
        return "addArtist";
    }

    @RequestMapping(value = "/artists/update", method = RequestMethod.POST)

    public String updateArtist(ModelMap model, Artist Artist, BindingResult result) {

        if (result.hasErrors()) {
            return "addArtist";
        }

        artistService.updateArtist(Artist);
        return "redirect:/artists";
    }

    @RequestMapping(value = "/artists/delete", method = RequestMethod.GET)

    public String deleteArtist(@RequestParam long id) {
        artistService.deleteArtist(id);

        return "redirect:/artists";
    }
}
