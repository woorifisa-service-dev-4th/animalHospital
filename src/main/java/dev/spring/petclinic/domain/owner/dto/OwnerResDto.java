package dev.spring.petclinic.domain.owner.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import dev.spring.petclinic.domain.pet.domain.Pet;
import dev.spring.petclinic.domain.pet.dto.PetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerResDto {
	private Long id;

	private String firstName;


	private String lastName;

	private String address;

	private String city;


	private String telephone;

	private List<PetDto> pets;

	// OwnerResDto로 변환
	public static OwnerResDto of(Long id, String firstName, String lastName, String address, String city, String telephone, List<Pet> pets) {
		return OwnerResDto.builder()
			.id(id)
			.firstName(firstName)
			.lastName(lastName)
			.address(address)
			.city(city)
			.telephone(telephone)
			.pets(pets.stream().map(PetDto::from).collect(Collectors.toList()))
			.build();
	}


}
