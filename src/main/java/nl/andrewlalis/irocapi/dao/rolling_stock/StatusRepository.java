package nl.andrewlalis.irocapi.dao.rolling_stock;

import nl.andrewlalis.irocapi.dao.BaseEntityPagedRepository;
import nl.andrewlalis.irocapi.model.rolling_stock.RollingStock;
import nl.andrewlalis.irocapi.model.rolling_stock.Status;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends BaseEntityPagedRepository<Status> {
	Optional<Status> findDistinctFirstByRollingStock(RollingStock rollingStock);
}
