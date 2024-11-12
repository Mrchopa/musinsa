package test.musinsa.coordination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 어플리케이션 메인 클래스.
 */
@SpringBootApplication
public class CoordinationApplication {
    
    /**
     * 어플리케이션 진입 메서드.
     * 
     * @param args CLI String arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CoordinationApplication.class, args);
    }

}
