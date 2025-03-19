package com.in28minutes.springboot.tutorial.basics.application.configuration.service;


import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Playlist;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Track;
import com.in28minutes.springboot.tutorial.basics.application.configuration.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PlaylistService implements IPlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

 

    @Override
    // Récupérer toutes les playlists
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }
    @Override
    public Page<Playlist> getAllPlaylists(Pageable pageable) {
        return playlistRepository.findAll(pageable);
    }

    @Override
    // Récupérer une playlist par son ID
    public Optional<Playlist> getPlaylistById(Long id) {
        return playlistRepository.findById(id);
    }

    @Override
    // Créer ou mettre à jour une playlist
    public void savePlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public void updatePlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public void addPlaylist(String name) {
        playlistRepository.save(new Playlist(name));
    }

    @Override
    // Supprimer une playlist
    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    // Ajouter un track à une playlist
    public void addTrackToPlaylist(Playlist playlist, Track track) {
        playlist.getTracks().add(track);
        playlistRepository.save(playlist);
    }

    @Override
    // Retirer un track d'une playlist
    public void removeTrackFromPlaylist(Playlist playlist, Track track) {
        playlist.getTracks().remove(track);
        playlistRepository.save(playlist);
    }
}
