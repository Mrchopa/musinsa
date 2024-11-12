package test.musinsa.coordination.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 카테고리별 브랜드와 상품 가격 DTO 클래스.
 */
public class CategoryBrandPriceDTO {
	@JsonProperty("카테고리")
	private String category;

	@JsonProperty("브랜드")
	private String brand;

	@JsonProperty("가격")
	private int price;

	public CategoryBrandPriceDTO(String category, String brand, int price) {
		this.category = category;
		this.brand = brand;
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
