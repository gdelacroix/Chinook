package com.in28minutes.springboot.tutorial.basics.application.configuration.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.LongFunction;

@Entity
@Table(name = "Playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistId;

    private String name;

    


    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "PlaylistTrack", joinColumns = { @JoinColumn(name = "playlistId") }, inverseJoinColumns = {
            @JoinColumn(name = "TrackId") })
    private Set<Track> tracks = new HashSet<>();

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlaylistTrack> playlistTracks = new HashSet<>();

    // Getters et setters
    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PlaylistTrack> getPlaylistTracks() {
        return playlistTracks;
    }

    public void setPlaylistTracks(Set<PlaylistTrack> playlistTracks) {
        this.playlistTracks = playlistTracks;
    }

    // Constructeurs
    public Playlist() {
        super();
    }

    public Playlist(String name) {
        super();
        this.name = name;
    }

    // toString()
    @Override
    public String toString() {
        return "Playlist [playlistId=" + playlistId + ", name=" + name + "]";
    }
}
