package mk.ukim.finki.lab1b.service;

import mk.ukim.finki.lab1b.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService{
    List<Artist> listArtists();
    Optional<Artist> ArtistFindById(Long id);
}
