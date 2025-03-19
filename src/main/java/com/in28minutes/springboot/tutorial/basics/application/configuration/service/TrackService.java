package com.in28minutes.springboot.tutorial.basics.application.configuration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Album;
import com.in28minutes.springboot.tutorial.basics.application.configuration.repository.AlbumRepository;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Track;
import com.in28minutes.springboot.tutorial.basics.application.configuration.repository.TrackRepository;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Genre;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.MediaType;
import com.in28minutes.springboot.tutorial.basics.application.configuration.repository.GenreRepository;
import com.in28minutes.springboot.tutorial.basics.application.configuration.repository.MediaTypeRepository;

@Service
public class TrackService implements ITrackService {

    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MediaTypeRepository mediaTypeRepository;

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Optional<Track> getTrackById(Long id) {
        return trackRepository.findById(id);
    }

    @Override
    public void addTrack(String name, long albumId, long mediaTypeId, long genreId, String composer,
            Integer milliseconds,
            Integer bytes, BigDecimal unitPrice) {
        Optional<Album> album = albumRepository.findById(albumId);
        Optional<Genre> genre = genreRepository.findById(genreId);
        Optional<MediaType> mediaType = mediaTypeRepository.findById(mediaTypeId);
        Track track = new Track(name, album.get(), mediaType.get(), genre.get(), composer, milliseconds, bytes,
                unitPrice);
        trackRepository.save(track);
    }

    @Override
    public void saveTrack(Track track) {
        trackRepository.save(track);
    }

    @Override
    public void updateTrack(Track track) {
        trackRepository.save(track);
    }

    @Override
    public void deleteTrack(Long id) {
        trackRepository.deleteById(id);
    }
    @Override
    public List<Track> searchTracks(String query) {
        return trackRepository.findByNameContainingIgnoreCase(query);
    }


    @Override
     public Set<Track> getTracksByIds(List<Long> trackIds) {
        return trackRepository.findAllById(trackIds)
                .stream()
                .collect(Collectors.toSet());
    }

}
