package dev.spring.petclinic.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageInfo {
	private int pageNumber;
	private int pageSize;
}
