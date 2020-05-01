package nl.andrewlalis.irocapi.model.rolling_stock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.andrewlalis.irocapi.model.BaseEntity;
import nl.andrewlalis.irocapi.model.Position;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Represents a snapshot of a rolling stock's various physical properties at a given point in time.
 */
@Entity
@NoArgsConstructor
@Getter
public class Status extends BaseEntity {
	@ManyToOne(optional = false)
	private RollingStock rollingStock;

	@Column(updatable = false)
	private double weight;

	@Column(updatable = false)
	private double speed;

	@Column(updatable = false)
	private int passengers;

	@Column(updatable = false)
	private String direction;

	@Column(updatable = false)
	private String tag;

	@Embedded
	private Position position;

	public Status(RollingStock rollingStock, double weight, double speed, int passengers, String direction, String tag, Position position) {
		this.rollingStock = rollingStock;
		this.weight = weight;
		this.speed = speed;
		this.passengers = passengers;
		this.direction = direction;
		this.tag = tag;
		this.position = position;
	}
}
