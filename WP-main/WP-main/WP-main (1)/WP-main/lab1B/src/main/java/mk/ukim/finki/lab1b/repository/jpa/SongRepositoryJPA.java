package mk.ukim.finki.lab1b.repository.jpa;

import mk.ukim.finki.lab1b.model.Artist;
import mk.ukim.finki.lab1b.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepositoryJPA extends JpaRepository<Song,Long> {
    Optional<Song> findByTrackId(String trackId);
    List<Song> findAllByAlbum_Id(Long album_id);

}
