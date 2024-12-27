package mk.ukim.finki.lab1b.repository.jpa;

import mk.ukim.finki.lab1b.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepositoryJPA extends JpaRepository<Artist,Long> {
}
