package mk.ukim.finki.lab1b.repository.jpa;

import mk.ukim.finki.lab1b.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepositoryJPA extends JpaRepository<Album,Long> {
}
