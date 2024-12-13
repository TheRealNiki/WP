package mk.ukim.finki.lab1b.service;

import mk.ukim.finki.lab1b.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService{
    List<Song> listSongs();
    List<Song> listSongsByAlbum(Long albumId);
    void findAndAddArtistToSong(Long artistId, Long songId);
    Optional<Song> findByTrackId(String trackId);
    Optional<Song> findById(long id);
    void deleteById(long id);
    void save(Long id,String title, String trackId,String genre,int releaseYear, Long albumId);
    void removeArtistFromSong(Long songId, Long performerId);
}
