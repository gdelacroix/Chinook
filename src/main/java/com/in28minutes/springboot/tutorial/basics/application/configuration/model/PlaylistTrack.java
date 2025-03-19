package com.in28minutes.springboot.tutorial.basics.application.configuration.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.io.Serializable;

@Entity
@Table(name = "PlaylistTrack")
public class PlaylistTrack implements Serializable {

    @EmbeddedId
    private PlaylistTrackId id;

    @ManyToOne
    @MapsId("playlistId")
    private Playlist playlist;

    @ManyToOne
    @MapsId("trackId")
    private Track track;

    // Constructeurs, getters et setters
    public PlaylistTrack() {
        super();
    }

    public PlaylistTrack(Playlist playlist, Track track) {
        super();

        this.playlist = playlist;
        this.track = track;
        this.id = new PlaylistTrackId(playlist.getPlaylistId(), track.getId());
    }

    public PlaylistTrackId getId() {
        return id;
    }

    public void setId(PlaylistTrackId id) {
        this.id = id;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @Override
    public String toString() {
        return "PlaylistTrack{" +
                "playlist=" + playlist.getName() +
                ", track=" + track.getName() +
                '}';
    }


     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistTrack that = (PlaylistTrack) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
