package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.request.AnimePutRequest;
import academy.devdojo.response.AnimeGetResponse;
import academy.devdojo.response.AnimePostResponse;
import academy.devdojo.service.AnimeService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/animes")
@Slf4j
public class AnimeController {

    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;

    private final AnimeService service;

    public AnimeController() {
        this.service = new AnimeService();
    }


    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAll() {
        var animes = service.findAll();
        var animeGetResponseList = MAPPER.toAnimeGetResponseList(animes);

        return ResponseEntity.ok(animeGetResponseList);
    }

    @GetMapping("exerc")
    public ResponseEntity<List<AnimeGetResponse>> listAllHeroes(@RequestParam(required = false) String name) {

        var animes = service.findAllHeroes(name);
        var animeGetResponseList = MAPPER.toAnimeGetResponseList(animes);

        return ResponseEntity.ok(animeGetResponseList);
    }

    @GetMapping("filterName")
    public ResponseEntity<List<AnimeGetResponse>> findByName(@RequestParam(required = false) String name) {
        log.debug("Request received to list all animes, param name '{}'", name);

        var animes = service.findAllHeroes(name);
        var animeGetResponseList = MAPPER.toAnimeGetResponseList(animes);
        if (name == null) return ResponseEntity.ok(animeGetResponseList);

        var response = animeGetResponseList.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
        log.debug("Request to find anime by id: '{}'", id);

        var anime = service.findByIdOrThrownNotFound(id);
        var animeGetResponse = MAPPER.toAnimeGetResponse(anime);

        return ResponseEntity.ok(animeGetResponse);
    }


    @PostMapping()
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        log.debug("Request to save anime: '{}'", request);
        var anime = MAPPER.toAnime(request);

        var aniemSaved = service.save(anime);

        var animeGetResponse = MAPPER.toAnimePostResponse(aniemSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(animeGetResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete anime by id '{}'", id);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }


    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest request) {
        log.debug("Request to update anime '{}'", request);

        var animeUpdated = MAPPER.toAnime(request);
        service.update(animeUpdated);

        return ResponseEntity.noContent().build();
    }
}
