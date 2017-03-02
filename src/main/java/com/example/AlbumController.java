package com.example;

import org.springframework.web.bind.annotation.*;

/**
 * Created by hillaryskye on 3/1/17.
 */

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumRepository repository;

    public AlbumController(AlbumRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Album> getAllAlbums() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Album create(@RequestBody Album album) {
        this.repository.save(album);
        return album;
    }
}
