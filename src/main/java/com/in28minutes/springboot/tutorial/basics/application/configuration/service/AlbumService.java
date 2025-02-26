package com.in28minutes.springboot.tutorial.basics.application.configuration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Album;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Artist;
import com.in28minutes.springboot.tutorial.basics.application.configuration.repository.AlbumRepository;
import com.in28minutes.springboot.tutorial.basics.application.configuration.repository.ArtistRepository;

@Service
public class AlbumService implements IAlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<Album> getAlbumById(long id) {
        return albumRepository.findById(id);
    }

    @Override
    public void saveAlbum(Album album) {
        albumRepository.save(album);
    }

    @Override
    public void updateAlbum(Album album) {
        albumRepository.save(album);
    }

    @Override
    public void deleteAlbum(long id) {
        Optional<Album> album = albumRepository.findById(id);
        album.ifPresent(albumRepository::delete);
    }

    @Override
    public void addAlbum(String title, Artist artist) {
        albumRepository.save(new Album(title, artist));
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

}
