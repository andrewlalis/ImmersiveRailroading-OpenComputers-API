package nl.andrewlalis.irocapi.dao;

import nl.andrewlalis.irocapi.model.BaseEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaseEntityPagedRepository<E extends BaseEntity> extends PagingAndSortingRepository<E, Long> {
}
