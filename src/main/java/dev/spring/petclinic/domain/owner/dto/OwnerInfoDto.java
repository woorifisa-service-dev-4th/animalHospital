package dev.spring.petclinic.domain.owner.dto;


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
public class OwnerInfoDto {

	private Long id;

	private String firstName;

	private String lastName;

	private String address;

	private String city;


	private String telephone;

	public static OwnerInfoDto of(Long id, String firstName, String lastName, String address, String city, String telephone) {
		return OwnerInfoDto.builder()
			.id(id)
			.firstName(firstName)
			.lastName(lastName)
			.address(address)
			.city(city)
			.telephone(telephone)
			.build();
	}
}
