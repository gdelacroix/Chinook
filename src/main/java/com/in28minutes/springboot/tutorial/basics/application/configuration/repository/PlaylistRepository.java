package com.in28minutes.springboot.tutorial.basics.application.configuration.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Playlist;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Track;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
     @Query("SELECT p FROM Playlist p JOIN p.tracks t WHERE t.id = :trackId")
    List<Playlist> findPlaylistsByTrackId(@Param("trackId") Long trackId);

 

}
