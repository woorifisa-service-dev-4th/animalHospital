package dev.spring.petclinic.domain.pet.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;

import lombok.*;

@Entity
@Table(name = "types")
@Getter
@NoArgsConstructor
public class PetType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 80, nullable = false)
	private String name;

	@Builder
	public PetType(String name) {
		this.name = name;
	}
}

