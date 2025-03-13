package dev.spring.petclinic.domain.owner.service;


import java.util.Optional;

import dev.spring.petclinic.domain.owner.domain.Owner;
import dev.spring.petclinic.domain.owner.dto.OwnerResDto;
import dev.spring.petclinic.domain.owner.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OwnerService {

	private final OwnerRepository ownerRepository;

	public Owner save(Owner owner) {
		return ownerRepository.save(owner);
	}

	public void delete(Long id){
		ownerRepository.deleteById(id);
	}

	// Owner 추가
	public OwnerResDto createOwner(OwnerResDto owner) {
		Owner newOwner = Owner.builder()
			.firstName(owner.getFirstName())
			.lastName(owner.getLastName())
			.address(owner.getAddress())
			.city(owner.getCity())
			.telephone(owner.getTelephone())
			.build();
		Owner savedOwner = ownerRepository.save(newOwner);
		return OwnerResDto.of(
			savedOwner.getId(),
			savedOwner.getFirstName(),
			savedOwner.getLastName(),
			savedOwner.getAddress(),
			savedOwner.getCity(),
			savedOwner.getTelephone(),
			savedOwner.getPets()
		);
	}

	// 기존 Owner 수정
	public void updateOwner(Long id, OwnerResDto updatedOwner) {
		 ownerRepository.findById(id).ifPresent(owner -> {
			owner.updateOwnerInfo(
				updatedOwner.getFirstName(),
				updatedOwner.getLastName(),
				updatedOwner.getAddress(),
				updatedOwner.getCity(),
				updatedOwner.getTelephone()
			);
		});
	}

	public OwnerResDto getOwnerById(Long id) {
		Optional<Owner> owner = ownerRepository.findById(id);
		return owner.map(value -> OwnerResDto.of(
			value.getId(),
			value.getFirstName(),
			value.getLastName(),
			value.getAddress(),
			value.getCity(),
			value.getTelephone(),
			value.getPets()
		)).orElse(null);
	}



	public Owner findOwnerById(Long id) {
		return ownerRepository.findById(id).orElse(null);
	}
	// ID로 Owner 조회
	@Transactional(readOnly = true)
	public Optional<Owner> findById(Long id) {
		return ownerRepository.findById(id);

	}

	@Transactional(readOnly = true)
	public Boolean isExistOwner(Long id) {
		return ownerRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public Page<OwnerResDto> findPaginatedAllOwners(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return ownerRepository.findAll(pageable)
			.map(owner -> OwnerResDto.of(
				owner.getId(),
				owner.getFirstName(),
				owner.getLastName(),
				owner.getAddress(),
				owner.getCity(),
				owner.getTelephone(),
				owner.getPets()
			));
	}

	@Transactional(readOnly = true)
	public Page<OwnerResDto> findPaginatedByLastName(String lastName, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return ownerRepository.findByLastNameContainingIgnoreCase(lastName, pageable)
			.map(owner -> OwnerResDto.of(
				owner.getId(),
				owner.getFirstName(),
				owner.getLastName(),
				owner.getAddress(),
				owner.getCity(),
				owner.getTelephone(),
				owner.getPets()
			));
	}

}

