package dev.spring.petclinic.domain.veterinarian.service;

import dev.spring.petclinic.domain.veterinarian.domain.VetSpecialty;
import dev.spring.petclinic.domain.veterinarian.dto.response.VeterinarianInfoDto;
import dev.spring.petclinic.domain.veterinarian.repository.VeterinarianRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class VeterinarianService {
    private final VeterinarianRepository veterinarianRepository;
    public List<VeterinarianInfoDto> findVeterinarians() {

        // repo에서 찾은 name과 specialty
        Map<String, List<String>> map = new HashMap<>();
        veterinarianRepository.findAll().forEach((e) ->
            map.computeIfAbsent(e.getVet().getFullName(), k -> new ArrayList<>())
                    .add(e.getSpecialty().getName())
        );

        // view에 뿌려줄 list 형태로 변환
        List<VeterinarianInfoDto> list = new ArrayList<>();
        map.forEach((k, v) -> {
            list.add(new VeterinarianInfoDto(k, v));
        });


        list.forEach(e->log.info(e.toString()));
        return list;
    }
}
