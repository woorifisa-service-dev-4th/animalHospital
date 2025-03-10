package dev.spring.petclinic.domain.owner.service;




import java.util.List;
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
	public Page<Owner> findPaginatedAllOwners(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return ownerRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Page<Owner> findPaginatedByLastName(String lastName, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return ownerRepository.findByLastNameContainingIgnoreCase(lastName, pageable); // ✅ Pet 정보 포함
	}

}

