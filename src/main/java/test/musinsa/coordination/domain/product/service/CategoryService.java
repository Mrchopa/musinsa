package test.musinsa.coordination.domain.product.service;

import org.springframework.stereotype.Service;

import test.musinsa.coordination.common.exception.NotFoundException;
import test.musinsa.coordination.domain.product.entity.Category;
import test.musinsa.coordination.domain.product.repository.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepo;

	public CategoryService(CategoryRepository categoryRepo) {
		this.categoryRepo = categoryRepo;
	}
	
	public Category getCategory(String name) {
		return categoryRepo.findByNameAndIsActiveIsTrue(name).orElseThrow(
				() -> new NotFoundException(String.format("%s 카테고리가 존재하지 않습니다.", name)));
	}
}
