package test.musinsa.coordination.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 예기치 못한 서버 에러 (HttpStatus 500) Exception 클래스.
 */
public class ServerException extends ApplicationRuntimeException {
	private static final long serialVersionUID = 3515270375256471703L;

	public ServerException(String message) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}
}
