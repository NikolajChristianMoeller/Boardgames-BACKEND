package org.example.boardgamesbackend.player;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

        final PlayerRepository PlayerRepository;

        public PlayerService(PlayerRepository PlayerRepository) {
            this.PlayerRepository = PlayerRepository;
        }

        public Player getPlayerById(Long id) {
            return PlayerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player with id " + id + " not found."));
        }

        public List<Player> getAllPlayers() {
            try {
                return PlayerRepository.findAll();
            } catch (Exception e) {
                throw new ServiceException("Error while getting all Players in service", e);
            }

        }

        public Player getPlayerByName(Long id) {
            return PlayerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player with the player " + id + " not found."));
        }

        public Player createPlayer(Player newPlayer) {
            try {
                return PlayerRepository.save(newPlayer);
            } catch (Exception e) {
                throw new ServiceException("Error while creating Player in service", e);
            }
        }

        public Player updatePlayer(Long id, Player updatedPlayer) {
            Player PlayerToUpdate = PlayerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player with id " + id + " not found."));
            if (updatedPlayer.getId() == id) {
                PlayerToUpdate.setName(updatedPlayer.getName());

                return PlayerRepository.save(PlayerToUpdate);
            } else {
                throw new RuntimeException("Error while updating Player in service.");
            }
        }

        public ResponseEntity<String> deletePlayer(Long id){
            Optional<Player> PlayerToDelete = PlayerRepository.findById(id);
            if(PlayerToDelete.isPresent()) {
                PlayerRepository.deleteById(id);
                return ResponseEntity.ok().body("Player with id " + id + " deleted successfully.");
            } else {
                return ResponseEntity.status(404).body("Error while deleting Player in service:" + " " + "Player with id " + id + " not found.");
            }
        }

        public List<PlayerDTO> findAll() {
            return PlayerRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
        }

        private PlayerDTO toDTO(Player Player) {
            PlayerDTO PlayerDTO = new PlayerDTO();
            PlayerDTO.setId(Player.getId());
            PlayerDTO.setName(Player.getName());
            return PlayerDTO;
        }
    
}
