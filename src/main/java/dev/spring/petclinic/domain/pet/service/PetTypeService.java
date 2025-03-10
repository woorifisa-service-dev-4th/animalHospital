package dev.spring.petclinic.domain.pet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.spring.petclinic.domain.pet.domain.PetType;
import dev.spring.petclinic.domain.pet.repository.PetTypeRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PetTypeService {
    private final PetTypeRepository petTypeRepository;

    @Transactional(readOnly = true)
    public List<PetType> findAll() {
        return petTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PetType> findByName(String name) {
        return petTypeRepository.findByName(name);
    }
}
