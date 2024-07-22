package org.example.boardgamesbackend.result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ResResultDTO {
    private long id;
    private String playerName;
    private String boardgameName;
    private LocalDate resultDate;
    private Double victories;
}
