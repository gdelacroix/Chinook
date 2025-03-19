package com.in28minutes.springboot.tutorial.basics.application.configuration.service;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Playlist;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Track;
import com.in28minutes.springboot.tutorial.basics.application.configuration.repository.PlaylistRepository;
import com.in28minutes.springboot.tutorial.basics.application.configuration.repository.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class PlayListTrackService implements IPlayListTrackService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Override
    @Transactional
    public void addTrackToPlaylist(Long playlistId, Long trackId) {
        Optional<Playlist> playlistOpt = playlistRepository.findById(playlistId);
        Optional<Track> trackOpt = trackRepository.findById(trackId);

        if (playlistOpt.isPresent() && trackOpt.isPresent()) {
            Playlist playlist = playlistOpt.get();
            Track track = trackOpt.get();

            playlist.getTracks().add(track);
            playlistRepository.save(playlist);
        }
    }

    @Override
    @Transactional
    public void removeTrackFromPlaylist(Long playlistId, Long trackId) {
        Optional<Playlist> playlistOpt = playlistRepository.findById(playlistId);
        Optional<Track> trackOpt = trackRepository.findById(trackId);

        if (playlistOpt.isPresent() && trackOpt.isPresent()) {
            Playlist playlist = playlistOpt.get();
            Track track = trackOpt.get();

            playlist.getTracks().remove(track);
            playlistRepository.save(playlist);
        }
    }

    @Override
    public Set<Track> getTracksByPlaylistId(Long playlistId) {
        return playlistRepository.findById(playlistId)
                .map(Playlist::getTracks)
                .orElse(null);
    }
}
