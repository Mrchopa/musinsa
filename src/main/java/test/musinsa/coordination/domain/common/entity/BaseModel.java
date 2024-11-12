package test.musinsa.coordination.domain.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

/**
 * 자동 생성형 BIGINT 타입 ID 컬럼과 레코드 생성일시와 수정일시 컬럼, 활성상태 여부 컬럼을 갖는 추상 Entity 클래스.
 */
@MappedSuperclass
public abstract class BaseModel extends IdTimeModel {
    /** 레코드 활성화 여부 */
    @Column(nullable = false)
    private Boolean isActive;
    
    /**
     * 레코드 활성화 여부를 반환 한다.
     * 
     * @return 레코드 활성화 여부
     */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * 레코드 활성화 여부를 설정 한다.
	 * 
	 * @param isActive 레코드 활성화 여부
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@PrePersist
	public void prePersist() {
		if (isActive == null) {
			isActive = true;
		}
	}
    
}
