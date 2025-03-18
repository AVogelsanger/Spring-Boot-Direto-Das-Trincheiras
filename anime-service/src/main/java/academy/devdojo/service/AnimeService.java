package academy.devdojo.service;

import academy.devdojo.domain.Anime;
import academy.devdojo.domain.Producer;
import academy.devdojo.repository.AnimeHardCodeRepository;
import academy.devdojo.repository.ProducerHardCodeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class AnimeService {

    private AnimeHardCodeRepository repository;

    public AnimeService() {
        this.repository = new AnimeHardCodeRepository();
    }


    public List<Anime> findAll() {
        return repository.findAll();
    }

    public List<Anime> findAllHeroes(String name) {
        return name == null ? repository.findAll() : repository.findByName(name);
    }

    public Anime findByIdOrThrownNotFound(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));
    }

    public Anime save(Anime anime) {
        return repository.save(anime);
    }

    public void delete(Long id) {
        var anime = findByIdOrThrownNotFound(id);
        repository.delete(anime);
    }

    public void update(Anime animeToUpdate) {
        assertAnimeExists(animeToUpdate.getId());
        repository.update(animeToUpdate);
    }

    public void assertAnimeExists(Long id) {
        findByIdOrThrownNotFound(id);
    }
}
