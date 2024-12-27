package mk.ukim.finki.lab1b.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab1b.model.Album;
import mk.ukim.finki.lab1b.model.Artist;
import mk.ukim.finki.lab1b.model.Song;
import mk.ukim.finki.lab1b.repository.jpa.AlbumRepositoryJPA;
import mk.ukim.finki.lab1b.repository.jpa.ArtistRepositoryJPA;
import mk.ukim.finki.lab1b.repository.jpa.SongRepositoryJPA;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DataInitializer {

    private final AlbumRepositoryJPA albumRepository;
    private final SongRepositoryJPA songRepository;
    private final ArtistRepositoryJPA artistRepository;


    public DataInitializer(AlbumRepositoryJPA albumRepository, SongRepositoryJPA songRepository, ArtistRepositoryJPA artistRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    @PostConstruct
    public void init() {
        List<Album> albums = new ArrayList<>();
        albums.add( new Album("Abbey Road", "Rock", "1969"));
        albums.add( new Album("Thriller", "Pop", "1982"));
        albums.add( new Album("Kind of Blue", "Jazz", "1959"));
        albums.add( new Album("The Dark Side of the Moon", "Progressive Rock", "1973"));
        albums.add( new Album("Back in Black", "Hard Rock", "1980"));
        albumRepository.saveAll(albums);


        List<Song> songs = new ArrayList<>();
        songs.add(new Song("1", "Bohemian Rhapsody", "Rock", 1975));
        songs.add(new Song("2", "I Will Always Love You", "Pop", 1992));
        songs.add(new Song("3", "Jailhouse Rock", "Rock and Roll", 1957));
        songs.add(new Song("4", "Respect", "Soul", 1967));
        songs.add(new Song("5", "Thriller", "Pop", 1982));

        List<Album> albums_tmp = albumRepository.findAll();

        IntStream.range(0, songs.size()).forEach(i->{
            songs.get(i).setAlbum(albums_tmp.get(i));
            songRepository.save(songs.get(i));
        });

        List<Artist>artists = new ArrayList<>();
        artists.add(new Artist( "Freddie", "Mercury", "British singer, songwriter, and lead vocalist of the rock band Queen."));
        artists.add(new Artist( "Whitney", "Houston", "American singer and actress, known as 'The Voice' and one of the best-selling music artists of all time."));
        artists.add(new Artist("Elvis", "Presley", "American singer, known as the 'King of Rock and Roll'."));
        artists.add(new Artist( "Aretha", "Franklin", "American singer, songwriter, and pianist, known as the 'Queen of Soul'."));
        artists.add(new Artist( "Michael", "Jackson", "American singer, songwriter, and dancer, known as the 'King of Pop'."));
        artistRepository.saveAll(artists);
    }
}
