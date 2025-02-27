package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/animes")
@Slf4j
public class AnimeController {

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
    public List<Anime> listAllHeroes() {
        return Anime.getAnimes();
    }

    @GetMapping("filterName")
    public List<Anime> findByName(@RequestParam(required = false) String name) {
        var animes = Anime.getAnimes();
        if (name == null) return animes;

        return animes.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
    }

    @GetMapping("{id}")
    public Anime findById(@PathVariable Long id) {
        return Anime.getAnimes()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst().orElse(null);
    }
}
