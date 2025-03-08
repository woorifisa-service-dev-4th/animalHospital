package dev.spring.petclinic.domain.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.spring.petclinic.domain.pet.domain.PetType;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {
}
