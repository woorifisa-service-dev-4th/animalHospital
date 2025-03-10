package dev.spring.petclinic.domain.veterinarian.domain;

import dev.spring.petclinic.domain.vet.domain.Vet;
import dev.spring.petclinic.domain.specialties.domain.Specialty;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "vet_specialties")
@Entity
public class VetSpecialty {

    @EmbeddedId
    private VetSpecialtyId id;


    @MapsId("vetId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_id", nullable = false)
    private Vet vet;


    @MapsId("specialtyId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;

}
