package nl.andrewlalis.irocapi.dao;

import nl.andrewlalis.irocapi.model.BaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface BaseEntityRepository<E extends BaseEntity> extends CrudRepository<E, Long> {
}
