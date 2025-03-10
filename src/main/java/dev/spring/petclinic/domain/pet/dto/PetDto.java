package dev.spring.petclinic.domain.pet.dto;

import dev.spring.petclinic.domain.owner.domain.Owner;
import dev.spring.petclinic.domain.pet.domain.Pet;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
    private Long id;
    private String name;
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private Owner owner; // ✅ owner 필드 추가

    public static PetDto of(Long id, String name, String type, LocalDate birthDate, Owner owner) {
        return PetDto.builder()
                .id(id)
                .name(name)
                .type(type)
                .birthDate(birthDate)
                .owner(owner) // ✅ owner 추가
                .build();
    }

    public static PetDto from(Pet pet) {
        return PetDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .type(pet.getType().getName())
                .birthDate(pet.getBirthDate())
                .owner(pet.getOwner()) // ✅ owner 추가
                .build();
    }
}