package dev.spring.petclinic.domain.veterinarian.repository;

import dev.spring.petclinic.domain.veterinarian.domain.VetSpecialty;
import dev.spring.petclinic.domain.veterinarian.domain.VetSpecialtyId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VeterinarianRepository extends JpaRepository<VetSpecialty, VetSpecialtyId> {
    @EntityGraph(attributePaths = {"vet", "specialty"})
    @Query("SELECT vs " +
            "FROM VetSpecialty vs " +
            "JOIN FETCH vs.vet v " +
            "JOIN FETCH vs.specialty s " +
            "ORDER BY s.name ASC")
    List<VetSpecialty> findAll();



    @Query("SELECT vs FROM VetSpecialty vs JOIN vs.vet v JOIN vs.specialty s")
    List<VetSpecialty> findAllWithJoin();
}
