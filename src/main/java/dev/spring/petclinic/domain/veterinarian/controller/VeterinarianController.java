package dev.spring.petclinic.domain.veterinarian.controller;


import dev.spring.petclinic.domain.veterinarian.dto.response.VeterinarianInfoDto;
import dev.spring.petclinic.domain.veterinarian.service.VeterinarianService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@AllArgsConstructor
@Controller
public class VeterinarianController {

    private final VeterinarianService veterinarianService;

    @GetMapping("/vets")
    public String veterinarian(Model model) {
        List<VeterinarianInfoDto> veterinarians = veterinarianService.findVeterinarians();
        model.addAttribute("listVets", veterinarians);
        return "vets/vetList.html";
    }

}
