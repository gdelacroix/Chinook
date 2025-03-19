package com.in28minutes.springboot.tutorial.basics.application.configuration.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    @Query("SELECT t FROM Track t JOIN t.playlists p WHERE p.playlistId = :playlistId")
    List<Track> findTracksByPlaylistId(@Param("playlistId") Long playlistId);
    // List<Track> findTracksByPlaylistId(Long playlistId);

    List<Track> findByNameContainingIgnoreCase(String name);
    Set<Track> findAllByIdIn(List<Long> ids);
}
