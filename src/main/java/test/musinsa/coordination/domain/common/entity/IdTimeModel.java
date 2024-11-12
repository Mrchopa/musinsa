package test.musinsa.coordination.domain.common.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * 자동 생성형 BIGINT 타입 ID 컬럼과 레코드 생성일시와 수정일시 컬럼을 갖는 추상 Entity 클래스.
 */
@MappedSuperclass
public abstract class IdTimeModel extends BaseTimeModel {
    /**
     * 자동 생성형 BIGINT 타입 ID 컬럼.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * id 값을 반환 한다.
     * 
     * @return ID
     */
    public Long getId() {
        return id;
    }
    
    public void setId(long id) {
    	this.id = id;
    }
}
