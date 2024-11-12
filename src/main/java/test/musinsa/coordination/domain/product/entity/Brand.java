package test.musinsa.coordination.domain.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import test.musinsa.coordination.domain.common.entity.BaseModel;

@Entity
@Table(name = "brands")
public class Brand extends BaseModel {
    private String name;
    
    public Brand() {
    	super();
    }
  
    public Brand(String name) {
    	super();
    	
		this.name = name;
	}

	public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
