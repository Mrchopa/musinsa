package test.musinsa.coordination.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 서버의 에러 처리를 위한 공통 Exception 클래스.
 */
public class ApplicationRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -1272132593346187240L;
	
	private final HttpStatus status;

	public ApplicationRuntimeException(HttpStatus status, String message) {
		super(message);
		
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
}
