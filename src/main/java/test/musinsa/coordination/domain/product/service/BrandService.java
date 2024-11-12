package test.musinsa.coordination.domain.product.service;

import org.springframework.stereotype.Service;

import test.musinsa.coordination.common.exception.BadRequestException;
import test.musinsa.coordination.domain.product.entity.Brand;
import test.musinsa.coordination.domain.product.repository.BrandRepository;

/**
 * 브랜드 서비스 클래스.
 */
@Service
public class BrandService {
	private final BrandRepository brandRepo;

	public BrandService(BrandRepository brandRepo) {
		this.brandRepo = brandRepo;
	}
	
	/**
	 * 브랜드를 조회 하고 브랜드가 없으면 신규 entity 객체를 작성 후 반환 한다.
	 * 반환 하는 객체는 fetch 된(select 된) 객체 또는 flush 되지 않은(신규로 작성 된) 객체 이다.
	 * 
	 * @param name 브랜드 이름
	 * @return 이미 존재 하거나 신규로 작성된 {@link Brand} entity
	 */
	private Brand getOrMakeEntity(String name) {
		Brand brand = brandRepo.findByName(name).orElseGet(() -> {
			return new Brand(name);
		});
		
		return brand;
	}
	
	/**
	 * 브랜드를 생성 한다.
	 * 이미 존재하는 브랜드 이름인 경우 {@link BadRequestException}을 throw 하고,
	 * 이미 존재하지만 inactive 상태인 경우 active 시킨다.
	 * 
	 * @param name 브랜드 이름
	 * @return 생성된 {@link Brand} entity
	 */
	public Brand createBrand(String name) {
		Brand brand = getOrMakeEntity(name);
			
		if (brand.getId() != null) {
			if (brand.isActive()) {
				throw new BadRequestException("이미 존재하는 브랜드 입니다."); 
			}
			else {
				brand.setActive(true);
			}
		}
		
		brandRepo.save(brand);
		
		return brand;
	}
	
	/**
	 * name에 해당 하는 브랜드를 조회 하고 없으면 생성 후 반환 한다.
	 * 
	 * @param name 브랜드 이름
	 * @return 이미 존재 하거나 신규로 생성되어 flush 된 {@link Brand} entity
	 */
	public Brand getBrandWithCreationIfNotExist(String name) {
		Brand brand = getOrMakeEntity(name);
		
		if (brand.getId() == null) {
			brandRepo.save(brand);
		}
		
		return brand;
	}
}
