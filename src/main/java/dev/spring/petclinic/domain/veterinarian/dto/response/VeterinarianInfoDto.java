package dev.spring.petclinic.domain.veterinarian.dto.response;

import lombok.ToString;

import java.util.List;

@ToString
public class VeterinarianInfoDto {

    public String name;
    public List<String> specialties;

    public VeterinarianInfoDto(String name, List<String> specialties) {
        this.name = name;
        this.specialties = specialties;
    }

}
