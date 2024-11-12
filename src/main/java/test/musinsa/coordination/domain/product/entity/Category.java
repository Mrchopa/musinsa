package test.musinsa.coordination.domain.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import test.musinsa.coordination.domain.common.entity.BaseModel;

/**
 * Category 엔티티 클래스.
 */
@Entity
@Table(name = "categories")
public class Category extends BaseModel {
    private String name;
    private int displayOrder;
    
    public Category() {
    	super();
    }
    
    public Category(String name, int displayOrder) {
    	super();
    	
		this.name = name;
		this.displayOrder = displayOrder;
	}

	public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getDisplayOrder() {
        return displayOrder;
    }
    
    public void setDisplayOrder(int displayOrder) { 
        this.displayOrder = displayOrder;
    }
}
