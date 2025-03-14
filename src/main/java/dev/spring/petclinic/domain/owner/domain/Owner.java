package dev.spring.petclinic.domain.owner.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

import dev.spring.petclinic.domain.pet.domain.Pet;

@Entity
@Table(name = "owners", indexes = {
	@Index(name = "idx_last_name", columnList = "last_name")
})
@Getter
@NoArgsConstructor
@Schema(description = "반려동물 주인(Owner) 정보")
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "소유자 ID", example = "1") //Swagger에서 API 응답 예시를 제공하기 위한 설명
	private Long id;

	@Column(name = "first_name", length = 30)
	@Schema(description = "소유자의 이름", example = "John")
	private String firstName;

	@Column(name = "last_name", length = 30)
	@Schema(description = "소유자의 성", example = "Doe")
	private String lastName;

	@Column(length = 255)
	@Schema(description = "주소", example = "123 Main St")
	private String address;

	@Column(length = 80)
	@Schema(description = "도시", example = "New York")
	private String city;

	@Column(length = 20)
	@Schema(description = "전화번호", example = "123-456-7890")
	private String telephone;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Schema(description = "반려동물 목록")
	private List<Pet> pets;

	@Builder
	public Owner(String firstName, String lastName, String address, String city, String telephone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.telephone = telephone;
	}

	public void updateOwnerInfo(String firstName, String lastName, String address, String city, String telephone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.telephone = telephone;
	}
}
