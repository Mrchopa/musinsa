package test.musinsa.coordination.domain.product.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 카테고리의 가격 범위 DTO 클래스.
 */
public class CategoryPriceRangeDTO {
	@JsonProperty("카테고리")
	private String category;
	
	@JsonProperty("최저가")
	private List<BrandPriceDTO> lowest;
	
	@JsonProperty("최고가")
	private List<BrandPriceDTO> highest;

	public CategoryPriceRangeDTO(String category, List<BrandPriceDTO> lowest, List<BrandPriceDTO> highest) {
		this.category = category;
		this.lowest = lowest;
		this.highest = highest;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<BrandPriceDTO> getLowest() {
		return lowest;
	}

	public void setLowest(List<BrandPriceDTO> lowest) {
		this.lowest = lowest;
	}

	public List<BrandPriceDTO> getHighest() {
		return highest;
	}

	public void setHighest(List<BrandPriceDTO> highest) {
		this.highest = highest;
	}
}
