package dev.spring.petclinic.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import java.util.List;

@Getter
@AllArgsConstructor
public class CustomPageResDto<T> {
	private List<T> content;
	private PageInfo pageable;
	private int totalPages;
	private long totalElements;

	public static <T> CustomPageResDto<T> fromPage(Page<T> page) {
		return new CustomPageResDto<>(
			page.getContent(),
			new PageInfo(
				page.getNumber() + 1,  //1부터 시작하는 페이지 변환
				page.getSize()
			),
			page.getTotalPages(),
			page.getTotalElements()
		);
	}
}
