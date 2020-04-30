package nl.andrewlalis.irocapi.model.rolling_stock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.andrewlalis.irocapi.model.BaseEntity;
import nl.andrewlalis.irocapi.model.World;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a single rolling stock, such as a locomotive or wagon.
 */
@Entity
@NoArgsConstructor
@Getter
public class RollingStock extends BaseEntity {
	@Column(nullable = false, unique = true)
	private String uuid;

	@Column(nullable = false)
	@Setter
	private String jsonRollingStockId;

	@Column(nullable = false)
	@Setter
	private String name;

	@Column
	@Setter
	private int cargoSize;

	@ManyToOne(optional = false)
	private World world;

	@OneToMany(mappedBy = "rollingStock", orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<Status> statuses;

	public RollingStock(World world, String uuid, String jsonRollingStockId, String name, int cargoSize) {
		this.world = world;
		this.uuid = uuid;
		this.jsonRollingStockId = jsonRollingStockId;
		this.name = name;
		this.cargoSize = cargoSize;
		this.statuses = new HashSet<>();
	}
}
