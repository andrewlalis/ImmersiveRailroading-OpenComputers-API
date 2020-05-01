package nl.andrewlalis.irocapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.andrewlalis.irocapi.dao.rolling_stock.RollingStockRepository;
import nl.andrewlalis.irocapi.dao.rolling_stock.StatusRepository;
import nl.andrewlalis.irocapi.dto.rolling_stock.StatusPayload;
import nl.andrewlalis.irocapi.model.World;
import nl.andrewlalis.irocapi.model.rolling_stock.RollingStock;
import nl.andrewlalis.irocapi.model.rolling_stock.Status;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Handles persisting any status updates that are received from computers.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StatusRecordingService {
	private final RollingStockRepository rollingStockRepository;
	private final StatusRepository statusRepository;

	/**
	 * Saves the data from the given payload, creating any new entities as needed.
	 * @param world The world in which the status was recorded.
	 * @param uuid The uuid of the rolling stock whose status was recorded.
	 * @param payload The payload to save.
	 * @return The new status.
	 */
	public Status saveStatus(World world, String uuid, StatusPayload payload) {
		RollingStock rollingStock = this.getOrUpdateRollingStock(world, uuid, payload);
		log.info("Status updated. UUID: {}, Name: {}, World: {}.", uuid, rollingStock.getName(), world.getName());
		return this.statusRepository.save(
				new Status(
						rollingStock,
						payload.getWeight(),
						payload.getSpeed(),
						payload.getPassengers(),
						payload.getDirection(),
						payload.getTag(),
						payload.getPosition()
				)
		);
	}

	/**
	 * Retrieves an existing rolling stock by its UUID and updates its data, or
	 * creates a whole new entity.
	 * @param world The world in which the rolling stock exists.
	 * @param uuid The UUID of the rolling stock.
	 * @param payload The posted data from the client, with some information
	 *                about the rolling stock.
	 * @return The rolling stock entity with the given UUID.
	 */
	private RollingStock getOrUpdateRollingStock(World world, String uuid, StatusPayload payload) {
		Optional<RollingStock> optionalRollingStock = this.rollingStockRepository.findByUuid(uuid);
		RollingStock rollingStock;
		if (optionalRollingStock.isEmpty()) {
			log.info("Registering a new rolling stock with UUID: {} and Name: {}.", uuid, payload.getName());
			return this.rollingStockRepository.save(
					new RollingStock(world, uuid, payload.getJsonRollingStockId(), payload.getName(), payload.getCargoSize())
			);
		} else {
			rollingStock = optionalRollingStock.get();
			rollingStock.setName(payload.getName());
			rollingStock.setJsonRollingStockId(payload.getJsonRollingStockId());
			rollingStock.setCargoSize(payload.getCargoSize());
			return this.rollingStockRepository.save(rollingStock);
		}
	}
}
