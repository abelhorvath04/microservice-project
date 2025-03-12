package com.ha1_microservice.group9.repository;

import com.ha1_microservice.group9.entity.Measurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * Repository-Interface for managing measurement entities.
 * Offers CRUD and Paging/Sorting-Functionalities for measurement entities.
 * Defines user specific queries.
 *
 * Annotated with @RepositoryRestRessource -> automatically as REST-Ressource available
 */
@RepositoryRestResource(collectionResourceRel = "measurements", path = "measurements")
public interface MeasurementRepository extends PagingAndSortingRepository<Measurement, Long>,
        CrudRepository<Measurement, Long> {
    //List<Measurement> findById(@Param("id") int id);
    //List<Measurement> findByTemperature(@Param("temperature") double temperature);

    @Override
    <S extends Measurement> S save(S entity);

    @Override
    <S extends Measurement> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Measurement> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    List<Measurement> findAll();

    @Override
    List<Measurement> findAllById(Iterable<Long> longs);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Measurement entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends Measurement> entities);

    @Override
    void deleteAll();

    @Override
    Iterable<Measurement> findAll(Sort sort);

    @Override
    Page<Measurement> findAll(Pageable pageable);
}

