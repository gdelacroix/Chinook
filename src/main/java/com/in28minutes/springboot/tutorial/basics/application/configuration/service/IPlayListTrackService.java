package com.in28minutes.springboot.tutorial.basics.application.configuration.service;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Playlist;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Track;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IPlayListTrackService {
    void addTrackToPlaylist(Long playlistId, Long trackId);
    void removeTrackFromPlaylist(Long playlistId, Long trackId);
    Set<Track> getTracksByPlaylistId(Long playlistId);
}
