package test.musinsa.coordination.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 요청한 리소스가 존재하지 않아 발생한 에러 (HttpStatus 404) Exception 클래스.
 */
public class NotFoundException extends ApplicationRuntimeException {
	private static final long serialVersionUID = 7755231535103333317L;

	public NotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
}
