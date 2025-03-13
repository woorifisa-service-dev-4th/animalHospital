package dev.spring.petclinic.domain.owner.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomPageResponse {
	private List<OwnerResDto> content;
	private int pageNumber;
	private int pageSize;
	private int totalPages;
	private long totalElements;
}
