package com.ha1_microservice.group9.repository;

import com.ha1_microservice.group9.entity.Sensor;
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
 * Repository-Interface for managing sensor entities.
 * Offers CRUD and Paging/Sorting-Functionalities for sensor entities.
 * Defines user specific queries.
 *
 * Annotated with @RepositoryRestRessource -> automatically as REST-Ressource available
 */

@RepositoryRestResource(collectionResourceRel = "sensors", path = "sensors")
public interface SensorRepository extends PagingAndSortingRepository<Sensor, Long>,
        CrudRepository<Sensor, Long> {
    //List<Sensor> findById(@Param("id") int id);

    @Override
    <S extends Sensor> S save(S entity);

    @Override
    <S extends Sensor> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Sensor> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    List<Sensor> findAll();

    @Override
    List<Sensor> findAllById(Iterable<Long> longs);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Sensor entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends Sensor> entities);

    @Override
    void deleteAll();

    @Override
    Iterable<Sensor> findAll(Sort sort);

    @Override
    Page<Sensor> findAll(Pageable pageable);
}

