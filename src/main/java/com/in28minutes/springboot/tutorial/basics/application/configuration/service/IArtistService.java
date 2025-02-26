package com.in28minutes.springboot.tutorial.basics.application.configuration.service;

import java.util.List;
import java.util.Optional;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Artist;

public interface IArtistService {

    List<Artist> getAllArtists();

    Optional<Artist> getArtistById(long id);

    void saveArtist(Artist artist);

    void updateArtist(Artist album);

    void deleteArtist(long id);

}
