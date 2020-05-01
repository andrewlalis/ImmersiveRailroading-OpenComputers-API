package nl.andrewlalis.irocapi.controller.worlds.world;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.andrewlalis.irocapi.dao.WorldRepository;
import nl.andrewlalis.irocapi.dao.rolling_stock.RollingStockRepository;
import nl.andrewlalis.irocapi.dto.rolling_stock.RollingStockResponse;
import nl.andrewlalis.irocapi.model.World;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller for the collection of rolling stock that belongs to a world.
 */
@RestController
@RequestMapping(path = "/worlds/{token}/rolling_stocks")
@RequiredArgsConstructor
@Slf4j
public class RollingStocksController {
	private final WorldRepository worldRepository;
	private final RollingStockRepository rollingStockRepository;

	@GetMapping
	public Page<RollingStockResponse> get(
			@PathVariable String token,
			@SortDefault.SortDefaults({
					@SortDefault(sort = "updatedAt")
			})
			Pageable pageable
	) {
		World world = this.worldRepository.findByToken(token)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "World not found."));
		return this.rollingStockRepository.findAllByWorld(world, pageable)
				.map(RollingStockResponse::new);
	}
}
