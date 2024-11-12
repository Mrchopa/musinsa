package test.musinsa.coordination.domain.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import test.musinsa.coordination.common.exception.NotFoundException;
import test.musinsa.coordination.domain.product.dto.CategoryPriceRangeDTO;
import test.musinsa.coordination.domain.product.dto.LowestPriceBrandProductsDTO;
import test.musinsa.coordination.domain.product.dto.LowestPriceProductsDTO;
import test.musinsa.coordination.domain.product.dto.ProductDTO;
import test.musinsa.coordination.domain.product.entity.Brand;
import test.musinsa.coordination.domain.product.entity.Category;
import test.musinsa.coordination.domain.product.entity.Product;
import test.musinsa.coordination.domain.product.repository.ProductRepository;

class ProductServiceTest {
	@Mock
    private ProductRepository productRepo;

    @InjectMocks
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Mock Product data
        product1 = new Product(new Category("category1", 0), new Brand("brand1"), 1000);
        product1.setId(1L);
        product2 = new Product(new Category("category1", 1), new Brand("brand2"), 2000);
        product2.setId(2L);
    }

    @Test
    void getLowestPriceProductsByCategory() {
        // Given
        List<Product> productList = Arrays.asList(product1, product2);
        when(productRepo.findLowestPriceProductsEachCategoryOrderByDisplayOrder()).thenReturn(productList);

        // When
        LowestPriceProductsDTO result = productService.getLowestPriceProductsByCategory();

        // Then
        assertNotNull(result);
        assertEquals(2, result.getCategoryBrandPrices().size());
        assertEquals(3000, result.getTotalAmountPrice());
    }

    @Test
    void getLowestPriceBrandProducts() {
        // Given
        List<Product> productList = Arrays.asList(product1);
        when(productRepo.findLowestPriceBrandProductsOrderByDisplayOrder()).thenReturn(productList);

        // When
        Map<String, LowestPriceBrandProductsDTO> result = productService.getLowestPriceBrandProducts();

        // Then
        assertNotNull(result);
        assertTrue(result.containsKey("최저가"));
        assertEquals(1, result.get("최저가").getCategoryPrices().size());
        assertEquals(1000, result.get("최저가").getTotalAmountPrice());
    }

    @Test
    void getCategoryPriceRange() {
        // Given
        List<Product> lowestList = Arrays.asList(product1);
        List<Product> highestList = Arrays.asList(product2);
        when(productRepo.findLowestPriceProductByCategoryName("category1")).thenReturn(lowestList);
        when(productRepo.findHighestPriceProductByCategoryName("category1")).thenReturn(highestList);

        // When
        CategoryPriceRangeDTO result = productService.getCategoryPriceRange("category1");

        // Then
        assertNotNull(result);
        assertEquals("category1", result.getCategory());
        assertEquals(1000, result.getLowest().get(0).getPrice());
        assertEquals(2000, result.getHighest().get(0).getPrice());
    }

    @Test
    void addProduct() {
        // Given
        when(productRepo.save(any(Product.class))).thenReturn(product1);

        // When
        Product result = productService.addProduct(product1);

        // Then
        assertNotNull(result);
        assertEquals(1000, result.getPrice());
    }

    @Test
    void getProduct() {
        // Given
        when(productRepo.findById(1L)).thenReturn(Optional.of(product1));

        // When
        Optional<Product> result = productService.getProduct(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(1000, result.get().getPrice());
    }

    @Test
    void saveProduct() {
        // Given
        when(productRepo.save(any(Product.class))).thenReturn(product1);

        // When
        Product result = productService.saveProduct(product1);

        // Then
        assertNotNull(result);
        assertEquals(1000, result.getPrice());
    }

    @Test
    void deleteProduct() {
        // Given
        when(productRepo.findById(1L)).thenReturn(Optional.of(product1));
        doNothing().when(productRepo).flush();

        // When
        ProductDTO result = productService.deleteProduct(1L);

        // Then
        assertNotNull(result);
        assertFalse(product1.isActive());
    }

    @Test
    void deleteProduct_NotFound() {
        // Given
        when(productRepo.findById(1L)).thenReturn(Optional.empty());

        // Then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> productService.deleteProduct(1L));
        assertEquals("1 ID 상품이 존재하지 않습니다.", exception.getMessage());
    }
}
