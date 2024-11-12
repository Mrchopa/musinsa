package test.musinsa.coordination.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 브랜드 상품 가격 DTO 클래스.
 */
public class BrandPriceDTO {
	@JsonProperty("브랜드")
	private String brand;
	
	@JsonProperty("가격")
	private int price;

	public BrandPriceDTO(String brand, int price) {
		this.brand = brand;
		this.price = price;
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
