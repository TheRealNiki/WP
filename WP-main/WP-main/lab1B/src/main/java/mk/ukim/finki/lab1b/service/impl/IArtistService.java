package mk.ukim.finki.lab1b.service.impl;

import mk.ukim.finki.lab1b.model.Artist;
import mk.ukim.finki.lab1b.repository.jpa.ArtistRepositoryJPA;
import mk.ukim.finki.lab1b.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IArtistService implements ArtistService {
    private final ArtistRepositoryJPA repository;

    public IArtistService(ArtistRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public List<Artist> listArtists() {
        return repository.findAll();
    }

    @Override
    public Optional<Artist> ArtistFindById(Long id) {
        return repository.findById(id);
    }
}
