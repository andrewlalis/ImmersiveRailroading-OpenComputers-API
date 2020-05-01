package nl.andrewlalis.irocapi.dto.rolling_stock;

import lombok.Data;
import nl.andrewlalis.irocapi.dto.WorldResponse;
import nl.andrewlalis.irocapi.model.rolling_stock.RollingStock;

import java.time.format.DateTimeFormatter;

@Data
public class RollingStockResponse {
	private String uuid;
	private String jsonRollingStockId;
	private String name;
	private int cargoSize;
	private String createdAt;
	private String updatedAt;
	private WorldResponse world;

	public RollingStockResponse(RollingStock rollingStock) {
		this.uuid = rollingStock.getUuid();
		this.jsonRollingStockId = rollingStock.getJsonRollingStockId();
		this.name = rollingStock.getName();
		this.cargoSize = rollingStock.getCargoSize();
		this.createdAt = rollingStock.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		this.updatedAt = rollingStock.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		this.world = new WorldResponse(rollingStock.getWorld());
	}
}
