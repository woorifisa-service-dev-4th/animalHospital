package dev.spring.petclinic.domain.owner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.spring.petclinic.domain.owner.domain.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

	// ✅ ID로 Owner 조회 시 Pet도 함께 로드
	@EntityGraph(attributePaths = {"pets"})
	Optional<Owner> findById(Long id);

	// ✅ Owner 조회 시 Pet도 함께 로드
	@EntityGraph(attributePaths = {"pets"})
	Page<Owner> findAll(Pageable pageable);

	// ✅ 특정 LastName으로 검색할 때 Pet도 함께 로드
	@EntityGraph(attributePaths = {"pets"})
	Page<Owner> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);
}
