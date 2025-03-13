package dev.spring.petclinic.domain.pet.controller;

import dev.spring.petclinic.domain.owner.domain.Owner;
import dev.spring.petclinic.domain.owner.service.OwnerService;
import dev.spring.petclinic.domain.pet.domain.Pet;
import dev.spring.petclinic.domain.pet.domain.PetType;
import dev.spring.petclinic.domain.pet.dto.PetDto;
import dev.spring.petclinic.domain.pet.dto.PetReqDto;
import dev.spring.petclinic.domain.pet.service.PetService;
import dev.spring.petclinic.domain.pet.service.PetTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/owners/{ownerId}/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    @Operation(summary = "새로운 Pet 생성", description = "특정 Owner에게 새로운 Pet을 추가합니다.")
    @PostMapping
    public ResponseEntity<PetDto> createPet(
        @Parameter(description = "Owner ID") @PathVariable Long ownerId,
        @Valid @RequestBody PetReqDto petDto) {

        // Owner 존재 여부 확인
        var owner = ownerService.findById(ownerId)
            .orElseThrow(() -> new RuntimeException("Owner not found"));

        // Pet 생성
        Pet newPet = petService.createPet(owner, petDto);

        return ResponseEntity.ok(PetDto.from(newPet));
    }

    /**
     * ✅ 4️⃣ Pet 정보 수정
     * PUT /api/owners/{ownerId}/pets/{petId}
     */
    @Operation(summary = "Pet 정보 수정", description = "특정 Owner의 Pet 정보를 업데이트합니다.")
    @PutMapping("/{petId}")
    public ResponseEntity<PetDto> updatePet(
        @Parameter(description = "Owner ID") @PathVariable Long ownerId,
        @Parameter(description = "Pet ID") @PathVariable Long petId,
        @Valid @RequestBody PetReqDto petDto) {

        // Owner 존재 여부 확인
        var owner = ownerService.findById(ownerId)
            .orElseThrow(() -> new RuntimeException("Owner not found"));

        // 기존 Pet 찾기 및 수정
        Pet updatedPet = petService.updatePet(owner, petId, petDto);

        return ResponseEntity.ok(PetDto.from(updatedPet));
    }



}
