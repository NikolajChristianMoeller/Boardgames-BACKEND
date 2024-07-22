package org.example.boardgamesbackend.result;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.boardgamesbackend.boardgame.Boardgame;
import org.example.boardgamesbackend.player.Player;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private LocalDate resultDate;
    private Double victories;
    private Integer points;

    @ManyToOne(fetch = FetchType.EAGER)
    private Player player;
    @ManyToOne(fetch = FetchType.EAGER)
    private Boardgame boardgame;
}
