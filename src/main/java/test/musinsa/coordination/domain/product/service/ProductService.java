package test.musinsa.coordination.domain.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.musinsa.coordination.common.exception.NotFoundException;
import test.musinsa.coordination.domain.product.dto.BrandPriceDTO;
import test.musinsa.coordination.domain.product.dto.CategoryBrandPriceDTO;
import test.musinsa.coordination.domain.product.dto.CategoryPriceDTO;
import test.musinsa.coordination.domain.product.dto.CategoryPriceRangeDTO;
import test.musinsa.coordination.domain.product.dto.LowestPriceBrandProductsDTO;
import test.musinsa.coordination.domain.product.dto.LowestPriceProductsDTO;
import test.musinsa.coordination.domain.product.dto.ProductDTO;
import test.musinsa.coordination.domain.product.entity.Product;
import test.musinsa.coordination.domain.product.repository.ProductRepository;

/**
 * 상품 서비스 클래스.
 */
@Service
public class ProductService {
	private final ProductRepository productRepo;
	
	public ProductService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	/**
	 * 카테고리별 최저가 브랜드 상품 조회 및 총액 조회.
	 * 
	 * @return LowestPriceProductsDTO 카테고리별 최저가 브랜드 상품 목록과 총액
	 */
	public LowestPriceProductsDTO getLowestPriceProductsByCategory() {
		List<Product> productList = productRepo.findLowestPriceProductsEachCategoryOrderByDisplayOrder();
		
		List<CategoryBrandPriceDTO> dtoList = new ArrayList<>(productList.size());
		int totalAmount = 0;
		
		for (Product product : productList) {
			dtoList.add(
				new CategoryBrandPriceDTO(
					product.getCategory().getName(),
					product.getBrand().getName(),
					product.getPrice()
				)
			);
			
			totalAmount += product.getPrice();
		}
		
		LowestPriceProductsDTO result = new LowestPriceProductsDTO(dtoList, totalAmount);
		
		return result;
	}
	
	/**
	 * 카테고리별 최저가 브랜드 상품 목록을 반환 한다.
	 * 
	 * @return 최저가 브랜드 상품 목록
	 */
	public Map<String, LowestPriceBrandProductsDTO> getLowestPriceBrandProducts() {
		List<Product> productList = productRepo.findLowestPriceBrandProductsOrderByDisplayOrder();
		
		// 모든 카테고리 상품이 존재하는 브랜드가 한개도 없는 경우
		if (productList.size() == 0) {
			throw new NotFoundException("카테고리별 최저가 브랜드 상품을 찾을 수 없습니다.");
		}
		
		Map<String, LowestPriceBrandProductsDTO> result = new HashMap<>();
		List<CategoryPriceDTO> dtoList = new ArrayList<>(productList.size());
		int totalAmount = 0;
		
		for (Product product : productList) {
			dtoList.add(
				new CategoryPriceDTO(
					product.getCategory().getName(),
					product.getPrice()
				)
			);
			
			totalAmount += product.getPrice();
		}
		
		
		LowestPriceBrandProductsDTO resultDto = new LowestPriceBrandProductsDTO(
			productList.get(0).getBrand().getName(),
			dtoList,
			totalAmount
		);
		
		result.put("최저가", resultDto);
		
		return result;
	}
	
	/**
	 * 지정된 카테고리의 최저 가격과 최고 가격 제품 정보를 반환 한다.
	 * 
	 * @param categoryName 카테고리 이름
	 * @return 지정된 카테고리의 최저와 최고 가격 정보 {@link CategoryPriceRangeDTO} 객체
	 */
	public CategoryPriceRangeDTO getCategoryPriceRange(String categoryName) {
		List<Product> lowestList = productRepo.findLowestPriceProductByCategoryName(categoryName);
		
		if (lowestList.size() == 0) {
			throw new NotFoundException("카테고리에 상품이 없습니다.");
		}
		
		List<Product> highestList = productRepo.findHighestPriceProductByCategoryName(categoryName);
		
		List<BrandPriceDTO> lowestDtoList = new ArrayList<>(lowestList.size());
		List<BrandPriceDTO> highestDtoList = new ArrayList<>(highestList.size());
		
		for (Product product : lowestList) {
			lowestDtoList.add(
				new BrandPriceDTO(product.getBrand().getName(), product.getPrice())
			);
		}
		
		for (Product product : highestList) {
			highestDtoList.add(
				new BrandPriceDTO(product.getBrand().getName(), product.getPrice())
			);
		}
		
		return new CategoryPriceRangeDTO(categoryName, lowestDtoList, highestDtoList);
	}
	
	/**
	 * 새롭게 작성된 상품 entity 를 flush 한다.
	 * 
	 * @param product 상품 entity
	 * @return flush 된 {@link Product} entity
	 */
	@Transactional
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}
	
	/**
	 * 상품 entity를 반환 한다.
	 * 
	 * @param id 상품 ID
	 * @return 조회된 Optional {@link Product} entity
	 */
	public Optional<Product> getProduct(long id) {
		return productRepo.findById(id);
	}
	
	/**
	 * 상품 entity를 flush 한다.
	 * 
	 * @param product 상품 entity
	 * @return flush 된 {@link Product} entity
	 */
	@Transactional
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}
	
	 /**
	  * 상품을 삭제 한다.
	  * 상품이 삭제 되어도 삭제된 상품의 연관 정보 관리를 위해 논리 삭제 처리함.
	  * 
	  * @param id 삭제할 상품 ID
	  * @return 삭제된 상품 정보 {@link ProductDTO}
	  */
	@Transactional
	public ProductDTO deleteProduct(long id) {
		Product product = productRepo.findById(id).orElseThrow(
				() -> new NotFoundException(String.format("%d ID 상품이 존재하지 않습니다.", id)));
		
		// 일반적으로 상품이 삭제 되어도 구매내역 등 연관 데이터가 필요하므로 논리 삭제함.
		product.setActive(false);
		
		return product.toDTO();
	}
}
