package mk.ukim.finki.lab1b.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import mk.ukim.finki.lab1b.model.Album;
import mk.ukim.finki.lab1b.model.Artist;
import mk.ukim.finki.lab1b.model.Song;
import mk.ukim.finki.lab1b.repository.jpa.AlbumRepositoryJPA;
import mk.ukim.finki.lab1b.repository.jpa.ArtistRepositoryJPA;
import mk.ukim.finki.lab1b.repository.jpa.SongRepositoryJPA;
import mk.ukim.finki.lab1b.service.SongService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ISongService implements SongService {
    private final SongRepositoryJPA songRepository;
    private final ArtistRepositoryJPA artistRepository;
    private final AlbumRepositoryJPA albumRepository;
    @PersistenceContext
    private final EntityManager entityManager;

    public ISongService(SongRepositoryJPA songRepository, ArtistRepositoryJPA artistRepository, AlbumRepositoryJPA albumRepository, EntityManager entityManager) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> listSongsByAlbum(Long albumId) {
        return songRepository.findAllByAlbum_Id(albumId);
    }


    @Override
    public void findAndAddArtistToSong(Long artistId, Long songId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(RuntimeException::new);
        Song song = songRepository.findById(songId).orElseThrow(RuntimeException::new);
        this.AddArtistToSong(artist,song);
        songRepository.save(song);
    }

    public void AddArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        songRepository.save(song);
    }

    @Override
    public Optional<Song> findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public Optional<Song> findById(long id) {
        return songRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        songRepository.deleteById(id);
    }

    @Override
    public void save(Long id,String title, String trackId, String genre, int releaseYear, Long albumId) {
        Album album = albumRepository.findById(albumId).orElseThrow();
        if (id==null){
            Song newSong = new Song(trackId,title,genre,releaseYear);
            newSong.setAlbum(album);
            songRepository.save(newSong);
        }else {
            Song song = songRepository.findById(id).orElseThrow();
            song.setAlbum(album);
            song.setTitle(title);
            song.setTrackId(trackId);
            song.setGenre(genre);
            song.setReleaseYear(releaseYear);
            boolean isManaged = entityManager.contains(song);
            System.out.println("Is entity managed? " + isManaged);
            songRepository.save(song);
        }
    }

    @Override
    public void removeArtistFromSong(Long songId, Long performerId) {
        Song song = songRepository.findById(songId).orElseThrow();
        Artist artist = artistRepository.findById(performerId).orElseThrow();
        song.getPerformers().removeIf(artist1 -> artist1.getId().equals(artist.getId()));
        songRepository.save(song);

    }
}
