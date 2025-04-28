package baekgwa.suhoserver.global.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * PackageName : baekgwa.suhoserver.global.response
 * FileName    : ErrorCode
 * Author      : Baekgwa
 * Date        : 2025-04-22
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-22     Baekgwa               Initial creation
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

	//Project: 0001 ~ 1000
	PROJECT_VERSION_NOT_FOUND(HttpStatus.NOT_FOUND, "0001", "유효하지 않은 version 정보 입니다."),
	PROJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "0002", "프로젝트를 찾을 수 없습니다."),
	
	//Branch: 1000 ~ 1999
	BRANCH_BOM_REGISTER_FAIL_BOM_FILE_MISS_MATCH(HttpStatus.BAD_REQUEST, "1000", "잘못된 Branch BOM 리스트입니다."),
	BRANCH_BOM_REGISTER_FAIL_BRANCH_NOT_FOUND(HttpStatus.BAD_REQUEST, "1001", "해당 프로젝트에 분기레일을 찾을 수 없습니다."),

	//Common: 9001 ~ 9999
	NOT_FOUND_URL(HttpStatus.NOT_FOUND, "9001", "요청하신 URL 을 찾을 수 없습니다."),
	NOT_SUPPORTED_METHOD(HttpStatus.METHOD_NOT_ALLOWED, "9002", "요청 메서드를 찾을 수 없습니다."),
	VALIDATION_FAIL_ERROR(HttpStatus.BAD_REQUEST, "9003", ""),
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "9004", "올바르지 않은 입력값입니다."),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "9005", "올바르지 않은 HTTP 메서드입니다."),
	ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "9006", "값을 찾지 못했습니다."),
	HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "9007", "요청이 거부되었습니다."),
	METHOD_ARGUMENT_TYPE_MISS_MATCH(HttpStatus.BAD_REQUEST, "9008", "요청 파라미터 타입 불일치. API 문서 확인해주세요."),
	VALIDATION_FAIL_BRANCH_ITEM_TYPE(HttpStatus.BAD_REQUEST, "9009", "허용되지 않은 품목구분입니다."),
	VALIDATION_FAIL_BRANCH_ITEM_UNIT(HttpStatus.BAD_REQUEST, "9010", "허용되지 않은 단위입니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "9999", "서버 내부 오류 발생했습니다");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
