package dev.spring.petclinic.domain.owner.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import dev.spring.petclinic.domain.pet.domain.Pet;
import lombok.*;

@Entity // 어노테이션이 붙은 클래스는 JPA가 관리하며, 해당 클래스의 인스턴스는 DB의 레코드(행, row)와 연결됨.
@Table(name = "owners", indexes = {
	@Index(name = "idx_last_name", columnList = "last_name") //  last_name에 대한 인덱스
})
@Getter //클래스의 모든 필드에 대한 Getter 메서드를 자동 생성하는 Lombok 어노테이션.
@NoArgsConstructor //기본 생성자(파라미터가 없는 생성자)를 자동으로 생성하는 Lombok 어노테이션.
public class Owner {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", length = 30)
	private String firstName;

	@Column(name = "last_name", length = 30)
	private String lastName;

	@Column(length = 255)
	private String address;

	@Column(length = 80)
	private String city;

	@Column(length = 20)
	private String telephone;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Pet> pets;



	@Builder
	public Owner(String firstName, String lastName, String address, String city, String telephone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.telephone = telephone;
	}


	// setter 메서드를 사용하지 않고, 도메인 로직(Owner 엔티티)에서 변경 가능하도록 메서드를 추가
	public void updateOwnerInfo(String firstName, String lastName, String address, String city, String telephone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.telephone = telephone;
	}

}

