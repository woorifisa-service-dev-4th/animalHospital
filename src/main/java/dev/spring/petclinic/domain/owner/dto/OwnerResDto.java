package dev.spring.petclinic.domain.owner.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import dev.spring.petclinic.domain.pet.domain.Pet;
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

	//@NotBlank(message = "First name is required")
	private String firstName;

	//@NotBlank(message = "Last name is required")
	private String lastName;

	//@NotBlank(message = "Address is required")
	private String address;

	//@NotBlank(message = "City is required")
	private String city;

	//@NotBlank(message = "Telephone is required")
	private String telephone;
	private List<Pet> pets;

	// OwnerResDto로 변환
	public static OwnerResDto of(Long id, String firstName, String lastName, String address, String city, String telephone, List<Pet> pets) {
		return OwnerResDto.builder()
			.id(id)
			.firstName(firstName)
			.lastName(lastName)
			.address(address)
			.city(city)
			.telephone(telephone)
			.pets(pets)
			.build();
	}
}
