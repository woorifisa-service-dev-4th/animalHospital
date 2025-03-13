package dev.spring.petclinic.domain.pet.domain;


import dev.spring.petclinic.domain.owner.domain.Owner;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity // 어노테이션이 붙은 클래스는 JPA가 관리하며, 해당 클래스의 인스턴스는 DB의 레코드(행, row)와 연결됨.
@Table(name = "pets", indexes = {
	@Index(name = "idx_pet_name", columnList = "name") // ✅ name 인덱스 추가
})//기본적으로 @Entity가 선언된 클래스의 이름이 테이블명으로 자동 매핑되지만, 이를 직접 지정하고 싶을 때 사용.
@Getter //클래스의 모든 필드에 대한 Getter 메서드를 자동 생성하는 Lombok 어노테이션.
@Setter
@NoArgsConstructor //기본 생성자(파라미터가 없는 생성자)를 자동으로 생성하는 Lombok 어노테이션.
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 30)
	private String name;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@Schema(description = "Pet 종류 (예: dog, bird)", example = "bird")
	@JoinColumn(name = "type_id", nullable = false)
	private PetType type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private Owner owner;
	public void setOwner(Owner owner) {
		this.owner = owner;
	}


	@Builder
	public Pet(String name, LocalDate birthDate, PetType type, Owner owner) {
		this.name = name;
		this.birthDate = birthDate;
		this.type = type;
		this.owner = owner;
	}

}