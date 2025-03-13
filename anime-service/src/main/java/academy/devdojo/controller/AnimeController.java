package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.request.AnimePutRequest;
import academy.devdojo.response.AnimeGetResponse;
import academy.devdojo.response.AnimePostResponse;
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

    @GetMapping("meus-favoritos")
    public List<String> name() {
        List<String> animesName = new ArrayList<>();
        animesName.add("Akira");
        animesName.add("Ghost of Shell");
        animesName.add("Seya");
        animesName.add("Zilion");

        return animesName;
    }

    @GetMapping
    public List<String> listAll() {
        log.info(Thread.currentThread().getName());
        return List.of("Ninja Kanui", "Kaijuu-8gou");
    }

    @GetMapping("exerc")
    public ResponseEntity<List<AnimeGetResponse>> listAllHeroes() {

        var animes = Anime.getAnimes();
        var animeGetResponseList = MAPPER.toAnimeGetResponseList(animes);

        return ResponseEntity.ok(animeGetResponseList);
    }

    @GetMapping("filterName")
    public ResponseEntity<List<AnimeGetResponse>> findByName(@RequestParam(required = false) String name) {
        log.debug("Request received to list all animes, param name '{}'", name);

        var animes = Anime.getAnimes();
        var animeGetResponseList = MAPPER.toAnimeGetResponseList(animes);
        if (name == null) return ResponseEntity.ok(animeGetResponseList);

        var response = animeGetResponseList.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
        log.debug("Request to find anime by id: '{}'", id);

        var animeGetResponse = Anime.getAnimes()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .map(MAPPER::toAnimeGetResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));

        return ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping("meu-jeito")
    public Anime saveAnime(@RequestBody String body) {
        var animes = Anime.getAnimes();
        try {
            JSONObject jsonObject = new JSONObject(body);
            String name = jsonObject.getString("name");
            Long id = animes.getLast().getId() + 1;
            Anime anime = new Anime(id, name);
            animes.add(anime);
            log.info("save: '{}'", body);
            return animes.getLast();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping()
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        log.debug("Request to save anime: '{}'", request);

        var anime = MAPPER.toAnime(request);
        Anime.getAnimes().add(anime);

        var response = MAPPER.toAnimePostResponse(anime);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete anime by id '{}'", id);

        var animeToDelete = Anime.getAnimes()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));

        Anime.getAnimes().remove(animeToDelete);

        return ResponseEntity.noContent().build();
    }


    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest request) {
        log.debug("Request to update anime '{}'", request);

        var animeToRemove = Anime.getAnimes()
                .stream()
                .filter(anime -> anime.getId().equals(request.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));

        var animeUpdated = MAPPER.toAnime(request);
        Anime.getAnimes().remove(animeToRemove);
        Anime.getAnimes().add(animeUpdated);

        return ResponseEntity.noContent().build();
    }
}
