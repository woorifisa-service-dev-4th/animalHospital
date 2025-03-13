package dev.spring.petclinic.domain.owner.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
public class OwnerReqDto {


	@NotBlank(message = "First name is required")
	private String firstName;

	@NotBlank(message = "Last name is required")
	private String lastName;

	@NotBlank(message = "Address is required")
	private String address;

	@NotBlank(message = "City is required")
	private String city;


	@NotBlank(message = "Telephone is required")
	@Pattern(
		regexp = "010-\\d{4}-\\d{4}|010\\d{8}",
		message = "Telephone must be in the format 010-XXXX-XXXX or 010XXXXXXXX"
	)
	private String telephone;

	public static OwnerReqDto of( String firstName, String lastName, String address, String city, String telephone) {
		return OwnerReqDto.builder()
			.firstName(firstName)
			.lastName(lastName)
			.address(address)
			.city(city)
			.telephone(telephone)
			.build();
	}

}
