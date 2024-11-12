package test.musinsa.coordination.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 카테고리별 상품 가격 DTO 클래스.
 */
public class CategoryPriceDTO {
	@JsonProperty("카테고리")
	private String category;

	@JsonProperty("가격")
	private int price;

	public CategoryPriceDTO(String category, int price) {
		this.category = category;
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
