package com.in28minutes.springboot.tutorial.basics.application.configuration.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Album;
import com.in28minutes.springboot.tutorial.basics.application.configuration.service.IAlbumService;
import com.in28minutes.springboot.tutorial.basics.application.configuration.service.IArtistService;

@Controller
public class AlbumController {

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private IArtistService artistService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // S'il y avait des champs Date dans Album, on pourrait les formater ici.
        // Exemple (à activer si nécessaire) :
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("/albums")
    public String listAll(ModelMap model) {
        model.put("albums", albumService.getAllAlbums());
        return "albums"; // Vue JSP : albums.jsp
    }

    @RequestMapping("/albums/add")
    public String showAddAlbumPage(ModelMap model) {
        model.addAttribute("album", new Album());
        model.addAttribute("artists", artistService.getAllArtists()); // Liste des artistes
        model.addAttribute("title", "Ajouter Album");
        return "addalbum"; // Vue JSP : addalbum.jsp
    }

    @RequestMapping(value = "/albums/add", method = RequestMethod.POST)
    public String addAlbum(ModelMap model, @ModelAttribute Album album, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("artists", artistService.getAllArtists());
            return "addalbum";
        }
        albumService.saveAlbum(album);
        return "redirect:/albums";
    }

    @RequestMapping(value = "/albums/update", method = RequestMethod.GET)
    public String showUpdateAlbumPage(@RequestParam long id, ModelMap model) {
        Optional<Album> album = albumService.getAlbumById(id);
        if (album == null) {
            return "redirect:/albums";
        }
        model.put("album", album);
        model.addAttribute("artists", artistService.getAllArtists());
        model.addAttribute("title", "Mettre à jour Album");
        return "addalbum"; // Réutilise le même formulaire
    }

    @RequestMapping(value = "/albums/update", method = RequestMethod.POST)
    public String updateAlbum(ModelMap model, @ModelAttribute Album album, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("artists", artistService.getAllArtists());
            return "addalbum";
        }
        albumService.updateAlbum(album);
        return "redirect:/albums";
    }

    @RequestMapping(value = "/albums/delete", method = RequestMethod.GET)
    public String deleteAlbum(@RequestParam int id) {
        albumService.deleteAlbum(id);
        return "redirect:/albums";
    }
}
