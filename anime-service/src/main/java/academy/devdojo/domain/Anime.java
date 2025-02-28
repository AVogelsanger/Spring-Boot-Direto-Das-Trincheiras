package academy.devdojo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Anime {
    private Long id;
    private String name;

    private static List<Anime> animes = new ArrayList<>();
    static {
        var naruto = new Anime(1L, "Naruto");
        var onePiece = new Anime(2L, "One Piece");
        var attackOnTitan = new Anime(3L, "Attack on Titan");
        var myHeroAcademia = new Anime(4L, "My Hero Academia");
        var fullmetalAlchemist = new Anime(5L, "Fullmetal Alchemist");
        animes.addAll(List.of(naruto, onePiece, attackOnTitan, myHeroAcademia, fullmetalAlchemist));
    }

    public static List<Anime> getAnimes() {
        return animes;
    }
}
