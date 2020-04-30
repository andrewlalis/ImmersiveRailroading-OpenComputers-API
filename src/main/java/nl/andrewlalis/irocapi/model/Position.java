package nl.andrewlalis.irocapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * A simple three-dimensional position that can be embedded with other data for an entity.
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
	private double x;
	private double y;
	private double z;
}
