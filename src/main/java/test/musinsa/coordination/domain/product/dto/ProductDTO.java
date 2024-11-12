package test.musinsa.coordination.domain.product.dto;

import java.time.LocalDateTime;

/**
 * 상품 DTO 클래스.
 */
public class ProductDTO {
	private long id;

	private String category;

	private String brand;

	private Integer price;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private Boolean isActive;

	public ProductDTO(long id, String category, String brand, Integer price, LocalDateTime createdAt,
			LocalDateTime updatedAt, Boolean isActive) {
		this.id = id;
		this.category = category;
		this.brand = brand;
		this.price = price;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isActive = isActive;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
