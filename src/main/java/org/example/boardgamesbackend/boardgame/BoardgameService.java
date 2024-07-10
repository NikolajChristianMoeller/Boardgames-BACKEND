package org.example.boardgamesbackend.boardgame;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardgameService {

    final BoardgameRepository BoardgameRepository;

    public BoardgameService(BoardgameRepository BoardgameRepository) {
        this.BoardgameRepository = BoardgameRepository;
    }

    public Boardgame getBoardgameById(Long id) {
        return BoardgameRepository.findById(id).orElseThrow(() -> new RuntimeException("Boardgame with id " + id + " not found."));
    }

    public List<Boardgame> getAllBoardgames() {
        try {
            return BoardgameRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error while getting all Boardgames in service", e);
        }

    }

    public Boardgame getBoardgameByName(Long id) {
        return BoardgameRepository.findById(id).orElseThrow(() -> new RuntimeException("Boardgame with the boardgame " + id + " not found."));
    }

    public Boardgame createBoardgame(Boardgame newBoardgame) {
        try {
            return BoardgameRepository.save(newBoardgame);
        } catch (Exception e) {
            throw new ServiceException("Error while creating Boardgame in service", e);
        }
    }

    public Boardgame updateBoardgame(Long id, Boardgame updatedBoardgame) {
        Boardgame BoardgameToUpdate = BoardgameRepository.findById(id).orElseThrow(() -> new RuntimeException("Boardgame with id " + id + " not found."));
        if (updatedBoardgame.getId() == id) {
            BoardgameToUpdate.setName(updatedBoardgame.getName());

            return BoardgameRepository.save(BoardgameToUpdate);
        } else {
            throw new RuntimeException("Error while updating Boardgame in service.");
        }
    }

    public ResponseEntity<String> deleteBoardgame(Long id){
        Optional<Boardgame> BoardgameToDelete = BoardgameRepository.findById(id);
        if(BoardgameToDelete.isPresent()) {
            BoardgameRepository.deleteById(id);
            return ResponseEntity.ok().body("Boardgame with id " + id + " deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Error while deleting Boardgame in service:" + " " + "Boardgame with id " + id + " not found.");
        }
    }

    public List<BoardgameDTO> findAll() {
        return BoardgameRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private BoardgameDTO toDTO(Boardgame Boardgame) {
        BoardgameDTO BoardgameDTO = new BoardgameDTO();
        BoardgameDTO.setId(Boardgame.getId());
        BoardgameDTO.setName(Boardgame.getName());
        return BoardgameDTO;
    }
}
