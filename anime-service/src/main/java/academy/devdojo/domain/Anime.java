package academy.devdojo.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Anime {
    private Long id;
    private String name;

    public Anime(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<Anime> getAnimes() {
        var naruto = new Anime(1L, "Naruto");
        var onePiece = new Anime(2L, "One Piece");
        var attackOnTitan = new Anime(3L, "Attack on Titan");
        var myHeroAcademia = new Anime(4L, "My Hero Academia");
        var fullmetalAlchemist = new Anime(5L, "Fullmetal Alchemist");

        return List.of(naruto, onePiece, attackOnTitan, myHeroAcademia, fullmetalAlchemist);
    }
}
