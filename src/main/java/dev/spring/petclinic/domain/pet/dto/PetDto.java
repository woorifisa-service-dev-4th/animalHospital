package dev.spring.petclinic.domain.pet.dto;

import dev.spring.petclinic.domain.owner.domain.Owner;
import dev.spring.petclinic.domain.pet.domain.Pet;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {

    private String name;
    private String type;
    private LocalDate birthDate;
    private Owner owner;


    public static PetDto of(String name, String type,LocalDate birthDate ,Owner owner) {
        return PetDto.builder().name(name).type(type).birthDate(birthDate).owner(owner).build();
    }

}
