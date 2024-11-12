package test.musinsa.coordination.api.v1.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import test.musinsa.coordination.api.v1.V1Path;
import test.musinsa.coordination.application.service.ProductApplicationService;
import test.musinsa.coordination.common.exception.BadRequestException;
import test.musinsa.coordination.domain.product.dto.CategoryPriceRangeDTO;
import test.musinsa.coordination.domain.product.dto.LowestPriceBrandProductsDTO;
import test.musinsa.coordination.domain.product.dto.LowestPriceProductsDTO;
import test.musinsa.coordination.domain.product.dto.ProductCreationDTO;
import test.musinsa.coordination.domain.product.dto.ProductDTO;
import test.musinsa.coordination.domain.product.dto.ProductUpdateDTO;
import test.musinsa.coordination.domain.product.service.ProductService;

/**
 * Product 리소스에 관련한 RestController 클래스.
 */
@Tag(name = "Product", description = "Product API")
@RestController
@RequestMapping(V1Path.ROOT_PATH + "/products")
public class ProductController {
	private final ProductService productService;
	
	private final ProductApplicationService productAppService;
    
    public ProductController(ProductService productService, ProductApplicationService productAppService) {
		this.productService = productService;
		this.productAppService = productAppService;
	}

	/**
     * 
     * @return
     */
	@Operation(summary = "카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회", tags = {"구현 1"})
    @GetMapping("/all-categories/lowest-price")
    public LowestPriceProductsDTO getAllCategoriesLowestPriceProducts() {
        return productService.getLowestPriceProductsByCategory();
    }
	
	@Operation(summary = "단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회", tags = {"구현 2"})
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(example = """
				{
				  "최저가": {
				    "브랜드": "D",
				    "카테고리": [
				      {
				        "카테고리": "상의",
				        "가격": 10100
				      },
				      {
				        "카테고리": "아우터",
				        "가격": 5100
				      },
				      {
				        "카테고리": "바지",
				        "가격": 3000
				      },
				      {
				        "카테고리": "스니커즈",
				        "가격": 9500
				      },
				      {
				        "카테고리": "가방",
				        "가격": 2500
				      },
				      {
				        "카테고리": "모자",
				        "가격": 1500
				      },
				      {
				        "카테고리": "양말",
				        "가격": 2400
				      },
				      {
				        "카테고리": "액세서리",
				        "가격": 2000
				      }
				    ],
				    "총액": 36100
				  }
				}
			""")))
    @GetMapping("/all-categories/lowest-price-brand")
    public Map<String, LowestPriceBrandProductsDTO> getAllCategoriesLowestPriceBrandProducts() {
		return productService.getLowestPriceBrandProducts();
    }

	@Operation(summary = "카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회", tags = {"구현 3"})
    @GetMapping("/{categoryName}/price-range")
    public CategoryPriceRangeDTO getCategoryPriceRange(
    		@Parameter(description = "카테고리 이름") @PathVariable("categoryName") String categoryName) {
    	return productService.getCategoryPriceRange(categoryName);
    }
	
	@Operation(summary = "브랜드 및 상품을 추가", tags = {"구현 4"})
    @PostMapping("")
    public ProductDTO addProduct(@RequestBody ProductCreationDTO product) {
		return productAppService.registerProduct(product);
    }
	
	@Operation(summary = "브랜드 및 상품을 업데이트", tags = {"구현 4"})
    @PatchMapping("/{productId}")
    public ProductDTO updateProduct(
    		@Parameter(description = "상품 ID") @PathVariable("productId") long productId,
    		@RequestBody ProductUpdateDTO product) {
		if (productId != product.getId()) throw new BadRequestException("상품 ID가 일치하지 않습니다.");
		
		return productAppService.updateProduct(product);
		
    }
	
	@Operation(summary = "브랜드 및 상품을 삭제", tags = {"구현 4"})
    @DeleteMapping("/{productId}")
    public ProductDTO deleteProduct(
    		@Parameter(description = "상품 ID") @PathVariable("productId") long productId) {
		return productService.deleteProduct(productId);
    }
}
