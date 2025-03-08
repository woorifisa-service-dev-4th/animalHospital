package dev.spring.petclinic.domain.pet.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.spring.petclinic.domain.owner.domain.Owner;
import dev.spring.petclinic.domain.pet.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
	List<Pet> findByOwner(Owner owner);

}
