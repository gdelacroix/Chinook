package com.in28minutes.springboot.tutorial.basics.application.configuration.service;

import java.util.List;
import java.util.Optional;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Album;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Artist;

public interface IAlbumService {

    List<Album> getAllAlbums();

    Optional<Album> getAlbumById(long id);

    void saveAlbum(Album album);

    void updateAlbum(Album album);

    void deleteAlbum(long id);

    void addAlbum(String title, Artist artist);

    List<Artist> getAllArtists();
}
