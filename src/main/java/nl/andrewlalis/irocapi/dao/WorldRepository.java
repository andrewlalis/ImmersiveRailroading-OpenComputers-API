package nl.andrewlalis.irocapi.dao;

import nl.andrewlalis.irocapi.model.World;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorldRepository extends BaseEntityPagedRepository<World> {
    List<World> findAllByName(String name);
    Optional<World> findByToken(String token);
}
