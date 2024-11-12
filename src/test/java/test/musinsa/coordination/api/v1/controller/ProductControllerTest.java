package test.musinsa.coordination.api.v1.controller;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import test.musinsa.coordination.api.v1.V1Path;
import test.musinsa.coordination.application.service.ProductApplicationService;
import test.musinsa.coordination.domain.product.dto.ProductCreationDTO;
import test.musinsa.coordination.domain.product.dto.ProductUpdateDTO;
import test.musinsa.coordination.domain.product.service.ProductService;

@SpringBootTest
@Transactional
class ProductControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext context;

    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductApplicationService productAppService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @BeforeEach
    void setUp() {
    	mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    @DisplayName("구현1 테스트")
    void getAllCategoriesLowestPriceProducts() throws Exception {
    	//given
    	
    	//when
    	ResultActions actions = mockMvc.perform(
    			get(V1Path.ROOT_PATH+"/products/all-categories/lowest-price")
    			.accept(MediaType.APPLICATION_JSON)
    			.contentType(MediaType.APPLICATION_JSON)
    	);
    	
    	//then
    	actions
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$.총액").value(34100));
    }
    
    @Test
    @DisplayName("구현2 테스트")
    void getAllCategoriesLowestPriceBrandProducts() throws Exception {
    	//given
    	
    	//when
    	ResultActions actions = mockMvc.perform(
    			get(V1Path.ROOT_PATH+"/products/all-categories/lowest-price-brand")
    			.accept(MediaType.APPLICATION_JSON)
    			.contentType(MediaType.APPLICATION_JSON)
    	);
    	
    	//then
    	actions
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$.최저가.총액").value(36100))
    	.andExpect(jsonPath("$.최저가.브랜드").value("D"))
    	.andExpect(jsonPath("$.최저가.카테고리").isArray())
    	.andExpect(jsonPath("$.최저가.카테고리.[0]").exists())
    	.andExpect(jsonPath("$.최저가.카테고리.[1]").exists())
    	.andExpect(jsonPath("$.최저가.카테고리.[2]").exists())
    	.andExpect(jsonPath("$.최저가.카테고리.[3]").exists())
    	.andExpect(jsonPath("$.최저가.카테고리.[4]").exists())
    	.andExpect(jsonPath("$.최저가.카테고리.[5]").exists())
    	.andExpect(jsonPath("$.최저가.카테고리.[6]").exists())
    	.andExpect(jsonPath("$.최저가.카테고리.[7]").exists());
    }
    
    @ParameterizedTest
    @DisplayName("구현3 테스트")
    @ValueSource(strings = {"상의", "바지", "양말"})
    void getCategoryPriceRange(String category) throws Exception {
    	//given
    	
    	//when
    	ResultActions actions = mockMvc.perform(
    			get(V1Path.ROOT_PATH+"/products/{categoryName}/price-range", category)
    			.accept(MediaType.APPLICATION_JSON)
    			.contentType(MediaType.APPLICATION_JSON)
    	);
    	
    	//then
    	actions
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$.카테고리").value(category))
    	.andExpect(jsonPath("$.최저가.[0].가격").exists())
    	.andExpect(jsonPath("$.최고가.[0].가격").exists());
    }
    
    @Test
    @DisplayName("구현4 테스트 - 1")
    void addProduct() throws Exception {
    	// given
        ProductCreationDTO cDto = new ProductCreationDTO();
        cDto.setCategory("상의");
        cDto.setBrand("A");
        cDto.setPrice(20000);

        // when
        ResultActions actions = mockMvc.perform(post(V1Path.ROOT_PATH+"/products")
        		.accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cDto)));
        
        // then
        actions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(notNullValue()))
        .andExpect(jsonPath("$.category").value(equalTo("상의")))
        .andExpect(jsonPath("$.brand").value(equalTo("A")))
        .andExpect(jsonPath("$.price").value(equalTo(20000)));
    }
    
    @Test
    @DisplayName("구현4 테스트 - 2")
    void updateProduct() throws Exception {
    	// given
    	long id = 1L;
    	
        ProductUpdateDTO uDto = new ProductUpdateDTO();
        uDto.setId(id);
        uDto.setBrand("C");
        uDto.setIsActive(false);

        // when
        ResultActions actions = mockMvc.perform(patch(V1Path.ROOT_PATH+"/products/{productId}", id)
        		.accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(uDto)));
        
        // then
        actions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(equalTo(1)))
        .andExpect(jsonPath("$.brand").value(equalTo("C")))
        .andExpect(jsonPath("$.isActive").value(equalTo(false)));
    }
    
    @Test
    @DisplayName("구현4 테스트 - 3")
    void deleteProduct() throws Exception {
    	// given
    	long id = 1L;

        // when
        ResultActions actions = mockMvc.perform(delete(V1Path.ROOT_PATH+"/products/{productId}", id)
        		.accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
        
        // then
        actions
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(equalTo(1)))
        .andExpect(jsonPath("$.isActive").value(equalTo(false)));
    }
}
