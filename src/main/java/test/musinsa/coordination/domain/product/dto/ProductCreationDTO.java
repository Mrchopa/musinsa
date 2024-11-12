package test.musinsa.coordination.domain.product.dto;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

/**
 * 상품 추가 DTO 클래스
 */
public class ProductCreationDTO {
	@JsonProperty("카테고리")
	@NotNull
	private String category;
	
	@JsonProperty("브랜드")
	@NotNull
	private String brand;
	
	@JsonProperty("가격")
	@NonNull
	private int price;

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
