package test.musinsa.coordination.api;

import java.time.ZonedDateTime;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 표준 에러 메세지 클래스.
 */
public class ErrorResponse {
	private ZonedDateTime timestamp;
	private int status;
	private String message;
	private String uri;
	
	//TODO: 서비스의 세부 운영 요건에 따라 표준 에러 메세지에 상세 정보 추가
	
	public ErrorResponse(int status, String message, HttpServletRequest request) {
		this.timestamp = ZonedDateTime.now();
		this.status = status;
		this.message = message;
		this.uri = request.getRequestURI();
	}
	
	public ZonedDateTime getTimestamp() {
		return timestamp;
	}
	
	public int getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getUri() {
		return uri;
	}
}
