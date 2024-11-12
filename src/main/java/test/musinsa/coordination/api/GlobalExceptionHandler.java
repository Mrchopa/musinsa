package test.musinsa.coordination.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import test.musinsa.coordination.common.exception.ApplicationRuntimeException;
import test.musinsa.coordination.common.exception.ServerException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
		if (e instanceof ApplicationRuntimeException are) {
			ErrorResponse response = new ErrorResponse(
				are.getStatus().value(), 
				are.getMessage(), 
				request
			);
			
			if (e instanceof ServerException) {
				logger.warn(request.getRequestURI(), e);
			}
			
			return new ResponseEntity<>(response, are.getStatus());
		}
		else {
			ErrorResponse response = new ErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				"예기치 못한 오류입니다.", 
				request
			);
			
			logger.warn(request.getRequestURI(), e);
			
			//TODO: 예기치 못한 에러 처리를 위한 상세 정보 로깅
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
