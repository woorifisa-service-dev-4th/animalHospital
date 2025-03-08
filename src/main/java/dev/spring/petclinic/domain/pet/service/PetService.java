package dev.spring.petclinic.domain.pet.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.spring.petclinic.domain.owner.domain.Owner;
import dev.spring.petclinic.domain.pet.domain.Pet;
import dev.spring.petclinic.domain.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PetService {
	private final PetRepository petRepository;

	@Transactional(readOnly = true)
	public List<Pet> getPetsByOwner(Owner owner) {
		return petRepository.findByOwner(owner);
	}

}
