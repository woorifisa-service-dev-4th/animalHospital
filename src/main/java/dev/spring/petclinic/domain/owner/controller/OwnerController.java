package dev.spring.petclinic.domain.owner.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.validation.Valid;

import dev.spring.petclinic.domain.owner.domain.Owner;
import dev.spring.petclinic.domain.owner.dto.OwnerResDto;
import dev.spring.petclinic.domain.owner.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {
	private final OwnerService ownerService;

	@GetMapping("/new")
	public String showCreateOwnerForm(Model model) {
		model.addAttribute("owner", new OwnerResDto()); // 빈 Owner 객체 전달
		return "owners/createOrUpdateOwnerForm"; // 폼 페이지 반환
	}

	@PostMapping("/new")
	public String createOwner(@ModelAttribute @Valid OwnerResDto owner, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("owner", owner);
			return "owners/createOrUpdateOwnerForm"; // 유효성 검사 실패 시 다시 폼으로 이동
		}
		Owner ownerEntity = Owner.builder()
			.address(owner.getAddress())
			.city(owner.getCity())
			.firstName(owner.getFirstName())
			.lastName(owner.getLastName())
			.telephone(owner.getTelephone())
			.build();
		Owner savedOwner = ownerService.save(ownerEntity);
		return "redirect:/owners/" + savedOwner.getId(); // 저장 후 상세 페이지로 리다이렉트
	}

	@GetMapping("/find")
	public String showFindOwnersForm(Model model) {
		model.addAttribute("owner", new Owner());  // 빈 Owner 객체 전달
		return "owners/findOwners"; // Thymeleaf 뷰 파일 경로
	}
	@GetMapping
	public String findOwners(@RequestParam(value = "lastName", required = false) String lastName,
		@RequestParam(value = "page", defaultValue = "1") int page,
		Model model) {

		if (!model.containsAttribute("owner")) {
			model.addAttribute("owner", new Owner());
		}

		int pageSize = 5;
		Page<Owner> ownerPage = (lastName == null || lastName.isEmpty())
			? ownerService.findPaginatedAllOwners(page, pageSize)
			: ownerService.findPaginatedByLastName(lastName, page, pageSize);

		if (ownerPage.isEmpty()) {
			model.addAttribute("errorMessage", "No owners found with last name: " + lastName);
			return "owners/findOwners";
		}

		model.addAttribute("listOwners", ownerPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", ownerPage.getTotalPages());

		return "owners/ownersList";
	}

	@GetMapping("/{id}")
	public String showOwnerDetail(@PathVariable Long id, Model model) {
		OwnerResDto owner = ownerService.getOwnerById(id);
		model.addAttribute("owner", owner);
		return "owners/ownerDetails";
	}
	@PutMapping("/{id}/edit")
	public String updateOwner(@PathVariable Long id, @ModelAttribute @Valid Owner updatedOwner) {
		ownerService.updateOwner(id, updatedOwner);
		return "redirect:/owners/" + id;
	}

}
