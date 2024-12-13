package mk.ukim.finki.lab1b.web.controllers;


import mk.ukim.finki.lab1b.model.Song;
import mk.ukim.finki.lab1b.service.AlbumService;
import mk.ukim.finki.lab1b.service.ArtistService;
import mk.ukim.finki.lab1b.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error,@RequestParam(required = false) Long albumId, Model model){
        if(albumId!=null && albumId!=-1) model.addAttribute("songs",songService.listSongsByAlbum(albumId));
        else model.addAttribute("songs",songService.listSongs());
        model.addAttribute("error",error);
        model.addAttribute("albums",albumService.findAll());
        return "listSongs";
    }

    @PostMapping("/add")
    public String saveOrUpdateSong(@RequestParam(required = false) Long id,
                                   @RequestParam String title,
                                   @RequestParam String trackId,
                                   @RequestParam String genre,
                                   @RequestParam int releaseYear,
                                   @RequestParam Long albumId){

        //id = null ako pesnata e nova   14to baranjeeee
        songService.save(id,title,trackId,genre,releaseYear,albumId);
        return "redirect:/songs";
    }
    @GetMapping("/add-form")
    public String getAddSongPage(Model model){
        model.addAttribute("albums",albumService.findAll());
        return "add-song";
    }

    @GetMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId,Model model){
        Optional<Song> songToEdit = songService.findById(songId);

        if(songToEdit.isEmpty()) return "redirect:/songs?error=choose a valid song to edit";

        model.addAttribute("song",songToEdit.get());
        model.addAttribute("albums",albumService.findAll());
        return "add-song";
    }



    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        songService.deleteById(id);
        return "redirect:/songs";
    }
    @PostMapping("/performers/delete/{songId}/{performerId}")
    public String deletePerformerFromSong(@PathVariable Long songId, @PathVariable Long performerId) {
        // Remove the performer from the song
        songService.removeArtistFromSong(songId, performerId);

        // Redirect back to the song edit page
        return "redirect:/songs/edit/" + songId;
    }




    @GetMapping("/details/{id}")
    public String songDetails(@PathVariable Long id,Model model){
        Song songToEdit = songService.findById(id).orElseThrow();
        model.addAttribute("song",songToEdit);
        return "songDetails";
    }
}
