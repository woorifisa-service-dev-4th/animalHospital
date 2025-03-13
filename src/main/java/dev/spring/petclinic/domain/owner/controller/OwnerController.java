package dev.spring.petclinic.domain.owner.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import dev.spring.petclinic.domain.owner.domain.Owner;
import dev.spring.petclinic.domain.owner.dto.CustomPageResponse;
import dev.spring.petclinic.domain.owner.dto.OwnerResDto;
import dev.spring.petclinic.domain.owner.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
@Tag(name = "Owner API", description = "Owner 관련 API") // API 그룹 이름
public class OwnerController {
	private final OwnerService ownerService;
	/*

	@Operation(summary = "Owner 목록 조회", description = "검색값이 있으면 필터링하고, 없으면 전체 Owner 목록을 반환합니다.")
	@GetMapping
	public ResponseEntity<CustomPageResponse<OwnerResDto>> getAllOwners(
		@Parameter(description = "페이지 번호 (1부터 시작)") @RequestParam(defaultValue = "1") int page,
		@Parameter(description = "페이지 크기") @RequestParam(defaultValue = "5") int size,
		@Parameter(description = "검색할 Last Name (없으면 전체 조회)") @RequestParam(required = false) String lastName) {

		// Spring Data JPA는 0부터 시작하는 페이지 인덱스를 사용하므로 page-1 적용
		int pageIndex = Math.max(page - 1, 0);

		// lastName이 없으면 전체 조회, 있으면 필터링하여 조회
		Page<OwnerResDto> owners = (lastName == null || lastName.isBlank())
			? ownerService.findPaginatedAllOwners(pageIndex, size)  // 전체 조회
			: ownerService.findPaginatedByLastName(lastName, pageIndex, size); // 검색

		// 요청한 JSON 구조에 맞게 변환
		CustomPageResponse<OwnerResDto> response = new CustomPageResponse<>(
			owners,
			page,  // `1`부터 시작하는 페이지로 변환
			size,
			owners.getTotalPages(),
			owners.getTotalElements()
		);

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "특정 Owner 조회", description = "Owner ID를 입력받아 Owner 정보를 반환합니다.")
	@GetMapping("/{id}")
	public ResponseEntity<OwnerResDto> getOwnerById(
		@Parameter(description = "조회할 Owner의 ID") @PathVariable Long id) {
		return ResponseEntity.ok(ownerService.getOwnerById(id));
	}
*/
	@Operation(summary = "Owner 추가", description = "새로운 Owner를 추가합니다.")
	@PostMapping
	public ResponseEntity<OwnerResDto> createOwner(@RequestBody OwnerResDto owner) {
		return ResponseEntity.ok(ownerService.createOwner(owner));
	}

	@Operation(summary = "Owner 수정", description = "기존 Owner 정보를 수정합니다.")
	@PutMapping("/{id}")
	public ResponseEntity<OwnerResDto> updateOwner(
		@Parameter(description = "수정할 Owner의 ID") @PathVariable Long id,
		@RequestBody OwnerResDto updatedOwner) {
		ownerService.updateOwner(id, updatedOwner);
		Owner updated = ownerService.findOwnerById(id);
		OwnerResDto response = OwnerResDto.of(
			updated.getId(),
			updated.getFirstName(),
			updated.getLastName(),
			updated.getAddress(),
			updated.getCity(),
			updated.getTelephone(),
			updated.getPets()
		);
		return ResponseEntity.ok(response);
	}

}
