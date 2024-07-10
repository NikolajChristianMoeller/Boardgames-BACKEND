package org.example.boardgamesbackend.player;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PlayerController {

    private final PlayerService PlayerService;

    public PlayerController(PlayerService PlayerService) {
        this.PlayerService = PlayerService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> findAll () {
        return ResponseEntity.ok(PlayerService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getPlayerById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(PlayerService.getPlayerById(id), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Player> getPlayerByName(@PathVariable Long id) {
        var Player = PlayerService.getPlayerByName(id);
        return new ResponseEntity<>(Player, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> postPlayer(@RequestBody Player request) {
        try {
            return new ResponseEntity<>(PlayerService.createPlayer(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity putPlayer(@PathVariable Long id, @RequestBody Player request) {
        try {
            return new ResponseEntity<>(PlayerService.updatePlayer(id, request), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path= "/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id) {
        try {
            return PlayerService.deletePlayer(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
}
