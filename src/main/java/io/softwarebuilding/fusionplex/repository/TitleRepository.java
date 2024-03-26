package io.softwarebuilding.fusionplex.repository;

import io.softwarebuilding.fusionplex.entity.Title;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@JaversSpringDataAuditable
public interface TitleRepository extends JpaRepository<Title, UUID> {

}
