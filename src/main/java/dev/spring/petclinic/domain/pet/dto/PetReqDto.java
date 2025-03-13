package dev.spring.petclinic.domain.pet.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetReqDto {

	private String name;
	private String type;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	public static PetReqDto of(String name, String type, LocalDate birthDate) {
		PetReqDto petReqDto = new PetReqDto();
		petReqDto.name = name;
		petReqDto.type = type;
		petReqDto.birthDate = birthDate;
		return petReqDto;
	}

}
