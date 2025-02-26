package com.in28minutes.springboot.tutorial.basics.application.configuration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Artist;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Employee;
import com.in28minutes.springboot.tutorial.basics.application.configuration.repository.ArtistRepository;

@Service
public class ArtistService implements IArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Optional<Artist> getArtistById(long id) {
        return artistRepository.findById(id);
    }

    @Override
    public void saveArtist(Artist artist) {
        artistRepository.save(artist);
    }

    @Override
    public void updateArtist(Artist artist) {
        artistRepository.save(artist);
    }

    @Override
    public void deleteArtist(long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            artistRepository.delete(artist.get());
        }

    }
}
