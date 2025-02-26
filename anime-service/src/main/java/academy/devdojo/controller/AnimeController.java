package academy.devdojo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
