package nl.andrewlalis.irocapi.dto.rolling_stock;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.andrewlalis.irocapi.model.Position;

/**
 * The payload that's sent to this API by computers that detect rolling stock.
 * Note that the stock's UUID, as well as the world's token, are passed via URL
 * parameters.
 */
@Data
@NoArgsConstructor
public class StatusPayload {
	// First the basic attributes of the rolling stock.
	private String jsonRollingStockId;
	private String name;
	private int cargoSize;

	// Then the attributes that vary with each status reading.
	private double speed;
	private double weight;
	private int passengers;
	private String direction;
	private String tag;
	private Position position;
}
