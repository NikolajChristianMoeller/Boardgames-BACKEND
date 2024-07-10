package org.example.boardgamesbackend.boardgame;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boardgames")
public class BoardgameController {

    private final BoardgameService BoardgameService;

    public BoardgameController(BoardgameService BoardgameService) {
        this.BoardgameService = BoardgameService;
    }

    @GetMapping
    public ResponseEntity<List<BoardgameDTO>> findAll () {
        return ResponseEntity.ok(BoardgameService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getBoardgameById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(BoardgameService.getBoardgameById(id), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Boardgame> getBoardgameByName(@PathVariable Long id) {
        var Boardgame = BoardgameService.getBoardgameByName(id);
        return new ResponseEntity<>(Boardgame, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boardgame> postBoardgame(@RequestBody Boardgame request) {
        try {
            return new ResponseEntity<>(BoardgameService.createBoardgame(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity putBoardgame(@PathVariable Long id, @RequestBody Boardgame request) {
        try {
            return new ResponseEntity<>(BoardgameService.updateBoardgame(id, request), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path= "/{id}")
    public ResponseEntity<String> deleteBoardgame(@PathVariable Long id) {
        try {
            return BoardgameService.deleteBoardgame(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
