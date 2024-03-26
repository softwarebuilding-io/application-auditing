package io.softwarebuilding.fusionplex.repository;

import io.softwarebuilding.fusionplex.entity.Genre;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@JaversSpringDataAuditable
public interface GenreRepository extends JpaRepository<Genre, UUID> {

    Optional<Genre> findByName( String name );

}
