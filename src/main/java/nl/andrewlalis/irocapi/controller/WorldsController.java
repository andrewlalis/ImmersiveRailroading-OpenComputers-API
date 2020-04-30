package nl.andrewlalis.irocapi.controller;

import lombok.RequiredArgsConstructor;
import nl.andrewlalis.irocapi.dao.WorldRepository;
import nl.andrewlalis.irocapi.dto.WorldPayload;
import nl.andrewlalis.irocapi.dto.WorldResponse;
import nl.andrewlalis.irocapi.model.World;
import nl.andrewlalis.irocapi.util.RandomSequenceGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

/**
 * Controller that handles requests for the collection of worlds.
 */
@RestController
@RequestMapping(path = "/worlds")
@RequiredArgsConstructor
public class WorldsController {
    private final WorldRepository worldRepository;

    @GetMapping
    public Page<WorldResponse> get(Pageable pageable) {
        return this.worldRepository.findAll(pageable)
                .map(WorldResponse::new);
    }

    @PostMapping
    public ResponseEntity<WorldResponse> post(@RequestBody WorldPayload payload) {
        World world = this.worldRepository.save(
                new World(payload.getName(), RandomSequenceGenerator.generate(World.TOKEN_LENGTH))
        );
        return new ResponseEntity<>(new WorldResponse(world), HttpStatus.CREATED);
    }
}
