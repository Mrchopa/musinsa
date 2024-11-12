package test.musinsa.coordination.domain.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import test.musinsa.coordination.domain.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByCategoryId(Long categoryId);

	List<Product> findByBrandId(Long brandId);

	List<Product> findByCategoryIdAndBrandId(Long categoryId, Long brandId);
	
	
	
	/**
	 * 카테고리별 최저가 브랜드 상품 검색.
	 * 정렬: 카테고리 displayOrder
	 * 
	 * @return 카테고리별 최저가 상품 {@link Product} 리스트
	 */
	@Query(nativeQuery = true, value = """
		    WITH ranked_products AS (
		        SELECT p.*, ROW_NUMBER() OVER (PARTITION BY p.category_id ORDER BY p.price ASC) AS row_num
		        FROM products p
		        JOIN brands b ON p.brand_id = b.id AND b.is_active = true
		        JOIN categories c ON p.category_id = c.id AND c.is_active = true
		        WHERE p.price = (SELECT MIN(p2.price) FROM products p2 WHERE p2.category_id = p.category_id)
					  AND p.is_active = true
		    )
		    SELECT p.*
		    FROM ranked_products p
		    JOIN categories c ON p.category_id = c.id
		    WHERE p.row_num = 1
		    ORDER BY c.display_order
		""")
	List<Product> findLowestPriceProductsEachCategoryOrderByDisplayOrder();
	
	/**
	 * 모든 카테고리의 상품 금액 합계가 가장 낮은 브랜드의 상품 검색.
	 * 정렬: 카테고리 displayOrder
	 * 
	 * @return 금액이 가장 저렴한 브랜드의 상품 {@link Product} 리스트
	 */
	@Query(nativeQuery = true, value = """
		    WITH ActiveCategoryCount AS (
		    SELECT COUNT(id) AS active_category_count
			    FROM categories
			    WHERE is_active = true
			),
			LowestPricePerCategory AS (
			    SELECT brand_id, category_id, MIN(price) AS lowest_price
			    FROM products
			    WHERE is_active = true
			    GROUP BY brand_id, category_id
			)
			SELECT r.*
			FROM products r
			JOIN categories c ON c.id = r.category_id
			JOIN brands b ON b.id = r.brand_id
			JOIN LowestPricePerCategory lppc 
				ON r.category_id = lppc.category_id
				AND r.brand_id = lppc.brand_id
				AND r.price = lppc.lowest_price
			WHERE r.brand_id = (
				SELECT lowest_brand.brand_id
				FROM (
					SELECT p.brand_id,
					       SUM(p.price) AS total_lowest_price_sum
					FROM products p
					JOIN LowestPricePerCategory lpc
					    ON p.brand_id = lpc.brand_id
					    AND p.category_id = lpc.category_id
					    AND p.price = lpc.lowest_price
					JOIN ActiveCategoryCount acc ON 1 = 1  -- 활성화된 카테고리 수와 매칭
					WHERE p.is_active = true
					GROUP BY p.brand_id
					HAVING COUNT(DISTINCT p.category_id) = acc.active_category_count
					ORDER BY total_lowest_price_sum ASC
					LIMIT 1
				) lowest_brand
			)
			ORDER BY c.display_order
		""")
	List<Product> findLowestPriceBrandProductsOrderByDisplayOrder();
	
	/**
	 * 특정 카테고리의 가장 저렴한 상품 검색.
	 * 
	 * @param categoryName 카테고리 이름
	 * @return 카테고리에 해당하는 최저가 상품 {@link Product} 리스트
	 */
	@Query("SELECT p FROM Product p WHERE p.category.name = :categoryName AND p.price = " +
	           "(SELECT MIN(p2.price) FROM Product p2 WHERE p2.category = p.category AND p2.isActive = true)")
	List<Product> findLowestPriceProductByCategoryName(@Param("categoryName") String categoryName);
	
	/**
	 * 특정 카테고리의 가장 비싼 상품 검색.
	 * 
	 * @param categoryName 카테고리 이름
	 * @return 카테고리에 해당하는 최고가 상품 {@link Product} 리스트
	 */
	@Query("SELECT p FROM Product p WHERE p.category.name = :categoryName AND p.price = " +
	           "(SELECT MAX(p2.price) FROM Product p2 WHERE p2.category = p.category AND p2.isActive = true)")
	List<Product> findHighestPriceProductByCategoryName(@Param("categoryName") String categoryName);
}
