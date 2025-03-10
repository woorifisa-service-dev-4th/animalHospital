package dev.spring.petclinic.domain.pet.controller;

import dev.spring.petclinic.domain.owner.domain.Owner;
import dev.spring.petclinic.domain.owner.service.OwnerService;
import dev.spring.petclinic.domain.pet.domain.Pet;
import dev.spring.petclinic.domain.pet.domain.PetType;
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
        Pet pet = new Pet();
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        model.addAttribute("owner", owner);
        model.addAttribute("new", true);
        return "pets/createOrUpdatePetForm";
    }

    // Add New Pet 저장 처리
    @PostMapping("/new")
    public String processCreationForm(@PathVariable("ownerId") Long ownerId,
                                      @ModelAttribute("pet") @Valid Pet pet,
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
        pet.setOwner(owner);
        petService.save(pet);
        return "redirect:/owners/" + ownerId;
    }

    // Edit Pet 저장 처리
    @PostMapping("/{petId}/edit")
    public String processUpdateForm(@PathVariable("petId") Long petId,
                                    @ModelAttribute("pet") @Valid Pet pet,
                                    BindingResult result,
                                    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("types", petTypeService.findAll());
            model.addAttribute("owner", pet.getOwner());
            return "pets/createOrUpdatePetForm";
        }

        Pet existingPet = petService.findById(petId);
        pet.setOwner(existingPet.getOwner());
        petService.save(pet);
        return "redirect:/owners/" + existingPet.getOwner().getId();
    }
}
