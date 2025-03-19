package com.in28minutes.springboot.tutorial.basics.application.configuration.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Playlist;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Track;
import com.in28minutes.springboot.tutorial.basics.application.configuration.service.IPlaylistService;
import com.in28minutes.springboot.tutorial.basics.application.configuration.service.ITrackService;

import org.springframework.ui.Model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Pagination;

@Controller
public class PlaylistController {
    @Autowired
    private IPlaylistService playlistService;

    @Autowired
    private ITrackService trackService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    // @GetMapping("/playlists")
    // public String getAllPlaylists(@RequestParam(defaultValue = "0") int page,
    // @RequestParam(defaultValue = "10") int size, @PageableDefault(size = 10)
    // Pageable pageable, Model model) {

    // // Obtenez les playlists avec pagination
    // Page<Playlist> playlistPage = playlistService.getAllPlaylists(pageable);
    // List<Playlist> playlists = playlistPage.getContent();

    // List<Track> allTracks = playlists.stream()
    // .flatMap(playlist -> playlist.getTracks().stream()) // Transforme en liste de
    // titres
    // .collect(Collectors.toList());

    // int start = page * size;
    // int end = Math.min(start + size, allTracks.size());

    // List<Track> paginatedTracks = allTracks.subList(start, end);

    // // Créer une Page<Track> à partir de la sous-liste paginée
    // Page<Track> trackPage = new PageImpl<>(paginatedTracks, PageRequest.of(page,
    // size), allTracks.size());

    // model.addAttribute("playlists", playlists);
    // model.addAttribute("tracks", trackPage);
    // model.addAttribute("currentPage", playlistPage.getNumber());
    // model.addAttribute("totalPages", playlistPage.getTotalPages());

    // return "playlists"; // Retourne la vue JSP pour afficher les playlists
    // }

    @GetMapping("/playlists")
    public String getAllPlaylists(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size, // 50 morceaux max
            Model model) {

        // Récupère toutes les playlists
        List<Playlist> allPlaylists = playlistService.getAllPlaylists();
        List<Map<String, Object>> allTracks = new ArrayList<>();

            // Construire une liste contenant les morceaux avec leurs playlists associées
            for (Playlist playlist : allPlaylists) {
                for (Track track : playlist.getTracks()) {
                    Map<String, Object> trackData = new HashMap<>();
                    trackData.put("track", track);
                    trackData.put("playlistName", playlist.getName());
                    allTracks.add(trackData);
                }
            }

            // Pagination
            int start = page * size;
            int end = Math.min(start + size, allTracks.size());
            List<Map<String, Object>> paginatedTracks = (start < allTracks.size()) ? allTracks.subList(start, end) : new ArrayList<>();

            // Ajout des données au modèle
            model.addAttribute("tracks", paginatedTracks);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", (int) Math.ceil((double) allTracks.size() / size));

            return "playlists"; // Charge la vue playlists.jsp
    }

    // // Afficher la page d'ajout d'une playlist
    // @RequestMapping("/playlists/add")
    // public String showAddPlaylistPage(ModelMap model) {
    // model.addAttribute("playlist", new Playlist()); // Création d'une nouvelle
    // playlist vide
    // model.addAttribute("tracks", trackService.getAllTracks()); // Liste des
    // tracks disponibles
    // model.addAttribute("title", "Ajouter Playlist");
    // return "addplaylist"; // Vue JSP : addplaylist.jsp
    // }

    // // Ajouter une nouvelle playlist
    // @RequestMapping(value = "/playlists/add", method = RequestMethod.POST)
    // public String addPlaylist(ModelMap model, @ModelAttribute Playlist playlist,
    // BindingResult result) {
    // if (result.hasErrors()) {
    // model.addAttribute("tracks", trackService.getAllTracks());
    // model.addAttribute("title", "Ajouter Playlist");
    // return "addplaylist"; // Si erreur, retourner à la page d'ajout
    // }
    // playlistService.savePlaylist(playlist); // Sauvegarder la playlist
    // return "redirect:/playlists"; // Rediriger vers la liste des playlists
    // }

    @RequestMapping("/playlists/add")
    public String showAddPlaylistPage(ModelMap model) {
        model.addAttribute("playlist", new Playlist());
        model.addAttribute("tracks", trackService.getAllTracks());
        model.addAttribute("title", "Ajouter Playlist");
        return "addplaylist";
    }

    @RequestMapping(value = "/playlists/add", method = RequestMethod.POST)
    public String addPlaylist(@ModelAttribute Playlist playlist, @RequestParam List<Long> trackIds,
            ModelMap model) {

        System.out.println("Nom de la playlist : " + playlist.getName());
        System.out.println("Track IDs reçus : " + trackIds);

        // Récupérer les tracks à partir des IDs
        Set<Track> tracks = new HashSet<>(trackService.getTracksByIds(trackIds));
        playlist.setTracks(tracks);

        playlistService.savePlaylist(playlist);
        return "redirect:/playlists"; // Redirection après ajout
    }

    // Afficher la page de modification d'une playlist
    @RequestMapping(value = "/playlists/update", method = RequestMethod.GET)
    public String showUpdatePlaylistPage(@RequestParam long id, ModelMap model) {
        Playlist playlist = playlistService.getPlaylistById(id).orElse(null); // Récupérer la playlist à modifier
        if (playlist == null) {
            return "redirect:/playlists"; // Si la playlist n'existe pas, rediriger vers la liste
        }
        model.put("playlist", playlist); // Ajouter la playlist au modèle pour la modification
        model.addAttribute("tracks", trackService.getAllTracks()); // Liste des tracks disponibles
        model.addAttribute("title", "Modifier Playlist");
        return "addplaylist"; // Vue JSP : addplaylist.jsp pour modification
    }

    // Enregistrer les modifications d'une playlist
    @RequestMapping(value = "/playlists/update", method = RequestMethod.POST)
    public String updatePlaylist(ModelMap model, @ModelAttribute Playlist playlist, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("tracks", trackService.getAllTracks());
            model.addAttribute("title", "Modifier Playlist");
            return "addplaylist"; // Si erreur, retourner à la page de modification
        }
        playlistService.savePlaylist(playlist); // Sauvegarder les modifications de la playlist
        return "redirect:/playlists"; // Rediriger vers la liste des playlists
    }

    // Supprimer une playlist
    @RequestMapping(value = "/playlists/delete", method = RequestMethod.GET)
    public String deletePlaylist(@RequestParam long id) {
        playlistService.deletePlaylist(id); // Supprimer la playlist
        return "redirect:/playlists"; // Rediriger vers la liste des playlists
    }

}
