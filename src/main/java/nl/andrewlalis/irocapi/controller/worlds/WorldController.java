package nl.andrewlalis.irocapi.controller.worlds;

import lombok.RequiredArgsConstructor;
import nl.andrewlalis.irocapi.dao.WorldRepository;
import nl.andrewlalis.irocapi.dto.WorldResponse;
import nl.andrewlalis.irocapi.model.World;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for requests for a particular world.
 */
@RestController
@RequestMapping(path = "/worlds/{token}")
@RequiredArgsConstructor
public class WorldController {
    private final WorldRepository worldRepository;

    @GetMapping
    public ResponseEntity<WorldResponse> get(@PathVariable String token) {
        Optional<World> optionalWorld = this.worldRepository.findByToken(token);
        if (optionalWorld.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new WorldResponse(optionalWorld.get()), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@PathVariable String token) {
        Optional<World> optionalWorld = this.worldRepository.findByToken(token);
        if (optionalWorld.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.worldRepository.delete(optionalWorld.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
