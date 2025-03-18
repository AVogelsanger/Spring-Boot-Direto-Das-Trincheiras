package academy.devdojo.repository;

import academy.devdojo.domain.Anime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimeHardCodeRepository {

    private static final List<Anime> ANIMES = new ArrayList<>();

    static {
        var naruto = Anime.builder().id(1L).name("Naruto").build();
        var onePiece = Anime.builder().id(2L).name("One Piece").build();
        var attackOnTitan = Anime.builder().id(3L).name("Attack on Titan").build();
        var myHeroAcademia = Anime.builder().id(4L).name("My Hero Academia").build();
        var fullmetalAlchemist = Anime.builder().id(5L).name("Fullmetal Alchemist").build();
        ANIMES.addAll(List.of(naruto, onePiece, attackOnTitan, myHeroAcademia, fullmetalAlchemist));
    }

    public List<Anime> findAll() {
        return ANIMES;
    }

    public Optional<Anime> findById(Long id) {
        return ANIMES.stream().filter(anime -> anime.getId().equals(id)).findFirst();
    }

    public List<Anime> findByName(String name) {
        return ANIMES.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
    }

    public Anime save(Anime anime) {
        ANIMES.add(anime);
        return anime;
    }

    public void delete(Anime anime) {
        ANIMES.remove(anime);
    }

    public void update(Anime anime) {
        delete(anime);
        save(anime);
    }
}
