package dev.spring.petclinic.domain.specialties.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Table(name = "specialties")
@Entity
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "name")
    private String name;



}
