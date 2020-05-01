package nl.andrewlalis.irocapi.dao.rolling_stock;

import nl.andrewlalis.irocapi.dao.BaseEntityPagedRepository;
import nl.andrewlalis.irocapi.model.World;
import nl.andrewlalis.irocapi.model.rolling_stock.RollingStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RollingStockRepository extends BaseEntityPagedRepository<RollingStock> {
	Optional<RollingStock> findByUuid(String uuid);

	Page<RollingStock> findAllByWorld(World world, Pageable pageable);
}
