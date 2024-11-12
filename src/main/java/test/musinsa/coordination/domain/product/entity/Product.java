package test.musinsa.coordination.domain.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import test.musinsa.coordination.domain.common.entity.BaseModel;
import test.musinsa.coordination.domain.product.dto.ProductDTO;

/**
 * Product 도메인 모델 클래스(entity).
 */
@Entity
@Table(name = "products")
public class Product extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
    
    private int price;
    
    public Product() {
    	super();
    }
    
    public Product(Category category, Brand brand, int price) {
    	super();
    	
		this.category = category;
		this.brand = brand;
		this.price = price;
	}

	public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public Brand getBrand() {
        return brand;
    }
    
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public ProductDTO toDTO() {
    	return new ProductDTO(
    			getId(),
    			getCategory().getName(),
    			getBrand().getName(),
    			getPrice(),
    			getCreatedAt(),
    			getUpdatedAt(),
    			isActive()
    		);
    }
}
