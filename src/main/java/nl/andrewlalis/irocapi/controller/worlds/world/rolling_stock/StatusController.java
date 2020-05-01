package nl.andrewlalis.irocapi.controller.worlds.world.rolling_stock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.andrewlalis.irocapi.dao.WorldRepository;
import nl.andrewlalis.irocapi.dto.rolling_stock.StatusPayload;
import nl.andrewlalis.irocapi.model.World;
import nl.andrewlalis.irocapi.model.rolling_stock.Status;
import nl.andrewlalis.irocapi.service.StatusRecordingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller that handles receiving status updates from various detectors throughout worlds.
 */
@RestController
@RequestMapping("/worlds/{token}/rolling_stocks/{uuid}/status")
@RequiredArgsConstructor
@Slf4j
public class StatusController {
	private final WorldRepository worldRepository;
	private final StatusRecordingService statusRecordingService;

	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Object> post(
			@PathVariable String token,
			@PathVariable String uuid,
			@RequestBody StatusPayload payload
	) {
		log.info("Received status post");
		World world = this.worldRepository.findByToken(token)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		Status status = this.statusRecordingService.saveStatus(world, uuid, payload);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
}
