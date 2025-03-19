package com.in28minutes.springboot.tutorial.basics.application.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Track;
import com.in28minutes.springboot.tutorial.basics.application.configuration.service.ITrackService;
import com.in28minutes.springboot.tutorial.basics.application.configuration.service.IAlbumService;
import com.in28minutes.springboot.tutorial.basics.application.configuration.service.IGenreService;
import com.in28minutes.springboot.tutorial.basics.application.configuration.service.IMediaTypeService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class TrackController {
    @Autowired
    private ITrackService trackService;
    @Autowired
    private IAlbumService albumService;
    @Autowired
    private IGenreService genreService;
    @Autowired
    private IMediaTypeService mediaTypeService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - yyyy-MM-dd
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("/tracks")
    public String listAll(ModelMap model) {
        model.put("tracks", trackService.getAllTracks());
        return "tracks"; // Vue JSP : tracks.jsp
    }

    @RequestMapping("/tracks/add")
    public String showAddTrackPage(ModelMap model) {
        model.addAttribute("track", new Track());
        model.addAttribute("albums", albumService.getAllAlbums()); // Liste des albums
        model.addAttribute("genres", genreService.getGenres()); // Liste des genres
        model.addAttribute("mediaTypes", mediaTypeService.getMediaTypes()); // Liste des types de média
        model.addAttribute("title", "Ajouter Piste");
        return "addtrack"; // Vue JSP : addtrack.jsp
    }

    @RequestMapping(value = "/tracks/add", method = RequestMethod.POST)
    public String addTrack(ModelMap model, @ModelAttribute Track track, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("albums", albumService.getAllAlbums());
            model.addAttribute("genres", genreService.getGenres());
            model.addAttribute("mediaTypes", mediaTypeService.getMediaTypes());
            return "addtrack";
        }
        trackService.saveTrack(track);
        return "redirect:/tracks";
    }

    @RequestMapping(value = "/tracks/update", method = RequestMethod.GET)
    public String showUpdateTrackPage(@RequestParam long id, ModelMap model) {
        Track track = trackService.getTrackById(id).orElse(null);
        if (track == null) {
            return "redirect:/tracks";
        }
        model.put("track", track);
        model.addAttribute("albums", albumService.getAllAlbums());
        model.addAttribute("genres", genreService.getGenres());
        model.addAttribute("mediaTypes", mediaTypeService.getMediaTypes());
        return "addtrack"; // Vue JSP : updatetrack.jsp
    }

    @RequestMapping(value = "/tracks/update", method = RequestMethod.POST)
    public String updateTrack(ModelMap model, @ModelAttribute Track track, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("albums", albumService.getAllAlbums());
            model.addAttribute("genres", genreService.getGenres());
            model.addAttribute("mediaTypes", mediaTypeService.getMediaTypes());
            return "addtrack";
        }
        trackService.updateTrack(track);
        return "redirect:/tracks";
    }

    @RequestMapping(value = "/tracks/delete", method = RequestMethod.GET)
    public String deleteTrack(@RequestParam long id) {
        trackService.deleteTrack(id);
        return "redirect:/tracks";
    }

    @GetMapping("/tracks/search")
    @ResponseBody
    public List<Map<String, Object>> searchTracks(@RequestParam String query) {
        // Recherche des pistes correspondant à la requête
        List<Track> tracks = trackService.searchTracks(query);

       // Retourner une liste de Map<String, Object> pour chaque piste
    return tracks.stream()
                 .map(track -> {
                     Map<String, Object> trackMap = new HashMap<>();
                     trackMap.put("id", track.getId());
                     trackMap.put("name", track.getName());
                     return trackMap;
                 })
                 .collect(Collectors.toList());
    }

}
