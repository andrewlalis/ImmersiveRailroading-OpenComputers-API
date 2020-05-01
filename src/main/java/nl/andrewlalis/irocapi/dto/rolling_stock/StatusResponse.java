package nl.andrewlalis.irocapi.dto.rolling_stock;

import lombok.Data;
import nl.andrewlalis.irocapi.model.Position;
import nl.andrewlalis.irocapi.model.rolling_stock.Status;

/**
 * The object that's returned when the user requests a rolling stock's status.
 */
@Data
public class StatusResponse {
	private double weight;
	private double speed;
	private int passengers;
	private String direction;
	private String tag;
	private Position position;

	public StatusResponse(Status status) {
		this.weight = status.getWeight();
		this.speed = status.getSpeed();
		this.passengers = status.getPassengers();
		this.direction = status.getDirection();
		this.tag = status.getTag();
		this.position = status.getPosition();
	}
}
