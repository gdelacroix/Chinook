package com.in28minutes.springboot.tutorial.basics.application.configuration.service;


import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Playlist;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Track;

public interface IPlaylistService {
    List<Playlist> getAllPlaylists();

    Page<Playlist> getAllPlaylists(Pageable pageable);

    Optional<Playlist> getPlaylistById(Long id);

    void updatePlaylist(Playlist playlist);

    void addPlaylist(String name);

    void deletePlaylist(Long id);

    void savePlaylist(Playlist playlist);

    void addTrackToPlaylist(Playlist playlist, Track track);

    void removeTrackFromPlaylist(Playlist playlist, Track track);
}