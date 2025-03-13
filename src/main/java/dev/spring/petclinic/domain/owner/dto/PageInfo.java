package dev.spring.petclinic.domain.owner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageInfo {
	private int pageNumber;
	private int pageSize;
}
