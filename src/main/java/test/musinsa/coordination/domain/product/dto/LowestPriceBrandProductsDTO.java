package test.musinsa.coordination.domain.product.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 최저가 브랜드 상품 정보 DTO 클래스
 */
public class LowestPriceBrandProductsDTO {
	@JsonProperty("브랜드")
	private String brand;
	
	@JsonProperty("카테고리")
	private List<CategoryPriceDTO> categoryPrices;
	
	@JsonProperty("총액")
	private int totalAmountPrice;

	public LowestPriceBrandProductsDTO(String brand, List<CategoryPriceDTO> categoryPrices, int totalAmountPrice) {
		this.brand = brand;
		this.categoryPrices = categoryPrices;
		this.totalAmountPrice = totalAmountPrice;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public List<CategoryPriceDTO> getCategoryPrices() {
		return categoryPrices;
	}

	public void setCategoryPrices(List<CategoryPriceDTO> categoryPrices) {
		this.categoryPrices = categoryPrices;
	}

	public int getTotalAmountPrice() {
		return totalAmountPrice;
	}

	public void setTotalAmountPrice(int totalAmountPrice) {
		this.totalAmountPrice = totalAmountPrice;
	}
}
