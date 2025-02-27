package test.musinsa.coordination.domain.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.musinsa.coordination.domain.product.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
	Optional<Brand> findByName(String name);
}
