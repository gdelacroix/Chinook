package com.in28minutes.springboot.tutorial.basics.application.configuration.service;

import java.math.BigDecimal;
import java.util.Date;

import java.util.List;

import java.util.Optional;
import java.util.Set;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Track;

public interface ITrackService {

    List<Track> getAllTracks();

    Optional<Track> getTrackById(Long id);

    void updateTrack(Track track);

    void addTrack(String name, long albumId, long mediaTypeId, long genreId, String composer, Integer milliseconds,
            Integer bytes, BigDecimal unitPrice);

    void deleteTrack(Long id);

    void saveTrack(Track track);

    List<Track> searchTracks(String query);
    Set<Track> getTracksByIds(List<Long> trackIds);

}