package nl.andrewlalis.irocapi.service;

import lombok.RequiredArgsConstructor;
import nl.andrewlalis.irocapi.dao.rolling_stock.StatusRepository;
import nl.andrewlalis.irocapi.model.rolling_stock.RollingStock;
import nl.andrewlalis.irocapi.model.rolling_stock.Status;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service for fetching that status of rolling stock in a meaningful way.
 */
@Service
@RequiredArgsConstructor
public class StatusFetchingService {
	private final StatusRepository statusRepository;

	/**
	 * Gets the most recent status for a given rolling stock.
	 * @param rollingStock The stock to get the status of.
	 * @return An optional object that either contains the last status, or is
	 * empty if none could be found.
	 */
	public Optional<Status> getLastStatus(RollingStock rollingStock) {
		return this.statusRepository.findDistinctFirstByRollingStock(rollingStock);
	}
}
