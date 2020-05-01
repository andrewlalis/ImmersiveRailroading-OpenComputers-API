package nl.andrewlalis.irocapi.controller.worlds.world;

import lombok.RequiredArgsConstructor;
import nl.andrewlalis.irocapi.dao.WorldRepository;
import nl.andrewlalis.irocapi.dao.rolling_stock.RollingStockRepository;
import nl.andrewlalis.irocapi.dto.rolling_stock.RollingStockResponseWithLastStatus;
import nl.andrewlalis.irocapi.model.rolling_stock.RollingStock;
import nl.andrewlalis.irocapi.model.rolling_stock.Status;
import nl.andrewlalis.irocapi.service.StatusFetchingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller for interacting with a single piece of rolling stock.
 */
@RestController
@RequestMapping(path = "/worlds/{token}/rolling_stocks/{uuid}")
@RequiredArgsConstructor
public class RollingStockController {
	private final WorldRepository worldRepository;
	private final RollingStockRepository rollingStockRepository;
	private final StatusFetchingService statusFetchingService;

	@GetMapping
	public ResponseEntity<RollingStockResponseWithLastStatus> get(@PathVariable String token, @PathVariable String uuid) {
		this.worldRepository.findByToken(token)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "World not found."));
		RollingStock rollingStock = this.rollingStockRepository.findByUuid(uuid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rolling stock not found."));
		Status lastStatus = this.statusFetchingService.getLastStatus(rollingStock).orElse(null);
		return new ResponseEntity<>(new RollingStockResponseWithLastStatus(rollingStock, lastStatus), HttpStatus.OK);
	}
}
