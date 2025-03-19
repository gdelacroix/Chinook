import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.PlaylistTrack;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.PlaylistTrackId;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Playlist;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Track;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, PlaylistTrackId> {
    
    List<PlaylistTrack> findByPlaylist(Playlist playlist);
    Page<PlaylistTrack> findAll(Pageable pageable);
}


