package test.musinsa.coordination.domain.product.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 카테고리별 최저가 브랜드 상품 정보 DTO 클래스.
 */
public class LowestPriceProductsDTO {
	@JsonProperty("카테고리별 최저가 브랜드 상품 가격")
	private List<CategoryBrandPriceDTO> categoryBrandPrices;
	
	@JsonProperty("총액")
	private int totalAmountPrice;

	public LowestPriceProductsDTO(List<CategoryBrandPriceDTO> categoryBrandPrices, int totalAmountPrice) {
		this.categoryBrandPrices = categoryBrandPrices;
		this.totalAmountPrice = totalAmountPrice;
	}

	public List<CategoryBrandPriceDTO> getCategoryBrandPrices() {
		return categoryBrandPrices;
	}

	public void setCategoryBrandPrices(List<CategoryBrandPriceDTO> categoryBrandPrices) {
		this.categoryBrandPrices = categoryBrandPrices;
	}

	public int getTotalAmountPrice() {
		return totalAmountPrice;
	}

	public void setTotalAmountPrice(int totalAmountPrice) {
		this.totalAmountPrice = totalAmountPrice;
	}
}
