package org.example.boardgamesbackend.result;

import org.example.boardgamesbackend.boardgame.Boardgame;
import org.example.boardgamesbackend.boardgame.BoardgameRepository;
import org.example.boardgamesbackend.errorhandling.exception.NotFoundException;
import org.example.boardgamesbackend.errorhandling.exception.ValidationException;
import org.example.boardgamesbackend.player.Player;
import org.example.boardgamesbackend.player.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultService {
    ResultRepository resultRepository;
    BoardgameRepository boardgameRepository;
    PlayerRepository playerRepository;

    public ResultService(ResultRepository resultRepository, BoardgameRepository boardgameRepository, PlayerRepository playerRepository) {
        this.resultRepository = resultRepository;
        this.boardgameRepository = boardgameRepository;
        this.playerRepository = playerRepository;
    }

    public List<ResResultDTO> findAll() {
        return resultRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<ResResultDTO> findById(Long id) {
        if (id == null || id < 0) {
            throw new ValidationException("Id must be provided");
        }

        Optional<Result> resultOptional = resultRepository.findById(id);

        if (resultOptional.isEmpty()) {
            throw new NotFoundException("Result not found, provided id: " + id);
        }

        return resultOptional.map(this::toDTO);
    }

    void validateAndSetPlayerAndBoardgame(ReqResultDTO reqResultDTO, Result result) {
        if (reqResultDTO == null) {
            throw new ValidationException("Request body cannot be null");
        }

        if (reqResultDTO.getPlayerId() == null || reqResultDTO.getPlayerId() < 0) {
            throw new ValidationException("Invalid player id,  provided id:" + reqResultDTO.getPlayerId());
        }

        if (reqResultDTO.getBoardgameId() == null || reqResultDTO.getBoardgameId() < 0) {
            throw new ValidationException("Invalid boardgame id, provided id:" + reqResultDTO.getBoardgameId());
        }

        Player player = playerRepository.findById(reqResultDTO.getPlayerId())
                .orElseThrow(() -> new NotFoundException("Player not found with provided id: " + reqResultDTO.getPlayerId()));

        Boardgame boardgame = boardgameRepository.findById(reqResultDTO.getBoardgameId())
                .orElseThrow(() -> new NotFoundException("Boardgame not found with provided id: " + reqResultDTO.getBoardgameId()));

        result.setPlayer(player);
        result.setBoardgame(boardgame);
    }

    public ResResultDTO createResult(ReqResultDTO reqResultDTO) {
        Result result = new Result();

        validateAndSetPlayerAndBoardgame(reqResultDTO, result);

        result.setResultDate(reqResultDTO.getResultDate());
        result.setVictories(reqResultDTO.getVictories());
        result.setPoints(reqResultDTO.getPoints());

        Result savedResult;

        try {
            savedResult = resultRepository.save(result);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving the result", e);
        }
        return toDTO(savedResult);
    }

    public ResResultDTO deleteResult(Long id) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Result not found with provided id: " + id));

        resultRepository.deleteById(id);

        return toDTO(result);
    }

    public ResResultDTO updateResult(Long id, ReqResultDTO reqResultDTO) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Result not found with provided id: " + id));

        validateAndSetPlayerAndBoardgame(reqResultDTO, result);

        result.setResultDate(reqResultDTO.getResultDate());
        result.setVictories(reqResultDTO.getVictories());
        result.setPoints(reqResultDTO.getPoints());

        Result savedResult;

        try {
            savedResult = resultRepository.save(result);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving the result", e);
        }
        return toDTO(savedResult);
    }

    public ResResultDTO toDTO(Result result) {
        ResResultDTO resResultDTO = new ResResultDTO();
        resResultDTO.setId(result.getId());
        resResultDTO.setBoardgameName(result.getBoardgame().getName());
        resResultDTO.setResultDate(result.getResultDate());

        resResultDTO.setPlayerName(result.getPlayer().getName());

        return resResultDTO;
    }
}
