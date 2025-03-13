package dev.spring.petclinic.domain.pet.service;

import java.util.List;

import dev.spring.petclinic.domain.pet.domain.PetType;
import dev.spring.petclinic.domain.pet.dto.PetDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.spring.petclinic.domain.owner.domain.Owner;
import dev.spring.petclinic.domain.pet.domain.Pet;
import dev.spring.petclinic.domain.pet.dto.PetReqDto;
import dev.spring.petclinic.domain.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PetService {
	private final PetRepository petRepository;
	private final PetTypeService petTypeService;


	//createPet
	public Pet createPet(Owner owner, PetReqDto petDto) {
		Pet pet = new Pet();
		pet.setName(petDto.getName());
		pet.setBirthDate(petDto.getBirthDate());
		pet.setType(petTypeService.findByName(petDto.getType())
			.orElseThrow(() -> new RuntimeException("Invalid Pet Type")));
		pet.setOwner(owner);

		return petRepository.save(pet);
	}

	public Pet updatePet(Owner owner, Long petId, PetReqDto petDto) {
		Pet pet = petRepository.findById(petId)
			.orElseThrow(() -> new RuntimeException("Pet not found"));

		// 소유자 확인
		if (!pet.getOwner().getId().equals(owner.getId())) {
			throw new RuntimeException("Owner does not own this pet");
		}

		pet.setName(petDto.getName());
		pet.setBirthDate(petDto.getBirthDate());
		pet.setType(petTypeService.findByName(petDto.getType())
			.orElseThrow(() -> new RuntimeException("Invalid Pet Type")));

		return petRepository.save(pet);
	}

	@Transactional(readOnly = true)
	public List<Pet> getPetsByOwner(Owner owner) {
		return petRepository.findByOwner(owner);
	}

	@Transactional(readOnly = true)
	public Pet findById(Long id) {
		return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
	}

	public Pet convertPetEntity(PetDto petDto) {
		PetType petType = petTypeService.findByName(petDto.getType())
				.orElseThrow(() -> new RuntimeException("Invalid Pet Type: " + petDto.getType()));

		return Pet.builder()
				.name(petDto.getName())
				.type(petType) // PetType 설정
				.birthDate(petDto.getBirthDate())
				.build();
	}

	@Transactional
	public void save(Pet pet) {
		petRepository.save(pet);
	}
}
