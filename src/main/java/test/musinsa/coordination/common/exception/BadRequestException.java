package test.musinsa.coordination.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 클라이언트의 잘못된 요청으로 인한 에러 (HttpStatus 400) Exception 클래스.
 */
public class BadRequestException extends ApplicationRuntimeException {
	private static final long serialVersionUID = 4274366560443496450L;

	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}
}
