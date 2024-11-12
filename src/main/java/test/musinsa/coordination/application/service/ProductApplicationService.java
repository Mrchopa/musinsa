package test.musinsa.coordination.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.musinsa.coordination.common.exception.NotFoundException;
import test.musinsa.coordination.domain.product.dto.ProductCreationDTO;
import test.musinsa.coordination.domain.product.dto.ProductDTO;
import test.musinsa.coordination.domain.product.dto.ProductUpdateDTO;
import test.musinsa.coordination.domain.product.entity.Brand;
import test.musinsa.coordination.domain.product.entity.Category;
import test.musinsa.coordination.domain.product.entity.Product;
import test.musinsa.coordination.domain.product.service.BrandService;
import test.musinsa.coordination.domain.product.service.CategoryService;
import test.musinsa.coordination.domain.product.service.ProductService;

@Service
public class ProductApplicationService {
	private final ProductService productService;
	
	private final BrandService brandService;
	
	private final CategoryService categoryService;

	public ProductApplicationService(
			ProductService productService, 
			BrandService brandService, 
			CategoryService categoryService) {
		this.productService = productService;
		this.brandService = brandService;
		this.categoryService = categoryService;
	}
	
	@Transactional
	public ProductDTO registerProduct(ProductCreationDTO creationDto) {
		Category category = categoryService.getCategory(creationDto.getCategory());
		Brand brand = brandService.getBrandWithCreationIfNotExist(creationDto.getBrand());
		
		Product product = new Product(category, brand, creationDto.getPrice());
		
		product = productService.addProduct(product);
		
		return product.toDTO();
	}
	
	@Transactional
	public ProductDTO updateProduct(ProductUpdateDTO updateDto) {
		Product product = productService.getProduct(updateDto.getId()).orElseThrow(
				() -> new NotFoundException(String.format("%s ID 상품이 존재하지 않습니다.", updateDto.getId())));
		
		if (updateDto.getCategory() != null && !updateDto.getCategory().equals(product.getCategory().getName())) {
			Category category = categoryService.getCategory(updateDto.getCategory());
			product.setCategory(category);
		}
		
		if (updateDto.getBrand() != null && !updateDto.getBrand().equals(product.getBrand().getName())) {
			Brand brand = brandService.getBrandWithCreationIfNotExist(updateDto.getBrand());
			product.setBrand(brand);
		}
		
		if (updateDto.getPrice() != null) {
			product.setPrice(updateDto.getPrice());
		}
		
		if (updateDto.getIsActive() != null) {
			product.setActive(updateDto.getIsActive());
		}
		
		product = productService.saveProduct(product);
		
		return product.toDTO();
	}
}
