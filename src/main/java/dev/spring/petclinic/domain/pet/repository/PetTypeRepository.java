package dev.spring.petclinic.domain.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.spring.petclinic.domain.pet.domain.PetType;

import java.awt.font.OpenType;
import java.util.Optional;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {
    Optional<PetType> findByName(String name);

}
