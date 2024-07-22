package org.example.boardgamesbackend.result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReqResultDTO {
    private Long playerId;
    private Long boardgameId;
    private LocalDate resultDate;
    private Double victories;
    private Integer points;
}
