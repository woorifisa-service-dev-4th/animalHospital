package dev.spring.petclinic.domain.veterinarian.domain;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class VetSpecialtyId implements Serializable {

    @Column(name = "vets_id", nullable = false)
    private int vetId;

    @Column(name = "specialties_id", nullable = false)
    private int specialtyId;


}
