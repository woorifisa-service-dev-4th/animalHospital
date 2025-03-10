package dev.spring.petclinic.domain.pet.controller;

import dev.spring.petclinic.domain.owner.domain.Owner;
import dev.spring.petclinic.domain.owner.service.OwnerService;
import dev.spring.petclinic.domain.pet.domain.Pet;
import dev.spring.petclinic.domain.pet.domain.PetType;
import dev.spring.petclinic.domain.pet.dto.PetDto;
import dev.spring.petclinic.domain.pet.service.PetService;
import dev.spring.petclinic.domain.pet.service.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import javax.validation.Valid;

@Controller
@RequestMapping("owners/{ownerId}/pets")
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    // Add New Pet 폼 페이지 반환
    @GetMapping("/new")
    public String initCreationForm(@PathVariable("ownerId") Long ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        PetDto pet = new PetDto();
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        model.addAttribute("owner", owner);
        model.addAttribute("isNew", true);
        return "pets/createOrUpdatePetForm";
    }

    // Add New Pet 저장 처리
    @PostMapping("/new")
    public String processCreationForm(@PathVariable("ownerId") Long ownerId,
                                      @ModelAttribute("pet") @Valid PetDto pet,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            model.addAttribute("types", petTypeService.findAll());
            model.addAttribute("owner", ownerService.findById(ownerId)
                    .orElseThrow(() -> new RuntimeException("Owner not found")));
            return "pets/createOrUpdatePetForm";
        }

        Owner owner = ownerService.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        Pet petEnity = petService.convertPetEntity(pet);
        petEnity.setOwner(owner);

        petService.save(petEnity);
        return "redirect:/owners/" + ownerId;
    }

    // Edit Pet 저장 처리
    @GetMapping("/{petId}/edit")
    public String initUpdateForm(@PathVariable("ownerId") Long ownerId,
                                 @PathVariable("petId") Long petId,
                                 Model model) {
        try {
            // Owner와 Pet 데이터를 조회
            Owner owner = ownerService.findById(ownerId)
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            Pet pet = petService.findById(petId);

            PetDto petDto = PetDto.from(pet);

            // 모델에 필요한 데이터 추가
            model.addAttribute("pet", petDto);
            model.addAttribute("owner", owner);

            model.addAttribute("types", petTypeService.findAll());
            model.addAttribute("isNew", false);

            return "pets/createOrUpdatePetForm";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; // 에러 페이지 반환
        }
    }

    @PostMapping("/{petId}/edit")
    public String processUpdateForm(@PathVariable("ownerId") Long ownerId,
                                    @PathVariable("petId") Long petId,
                                    @ModelAttribute("pet") @Valid PetDto petDto,
                                    BindingResult result,
                                    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("types", petTypeService.findAll());
            model.addAttribute("owner", ownerService.findById(ownerId)
                    .orElseThrow(() -> new RuntimeException("Owner not found")));
            return "pets/createOrUpdatePetForm";
        }

        try {
            Owner owner = ownerService.findById(ownerId)
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            Pet existingPet = petService.findById(petId);

            // Pet 업데이트 로직
            existingPet.setName(petDto.getName());
            existingPet.setBirthDate(petDto.getBirthDate());

            // PetType ID로 조회
            try {
                Long typeId = Long.parseLong(petDto.getType());
                PetType petType = petTypeService.findById(typeId)
                        .orElseThrow(() -> new RuntimeException("Invalid Pet Type ID: " + typeId));
                existingPet.setType(petType);
            } catch (NumberFormatException e) {
                model.addAttribute("errorMessage", "Invalid Pet Type ID. Please enter a valid number.");
                return "pets/createOrUpdatePetForm";
            }

            existingPet.setOwner(owner);

            petService.save(existingPet); // 업데이트된 Pet 저장

            return "redirect:/owners/" + ownerId;
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; // 에러 페이지 반환
        }
    }
}
