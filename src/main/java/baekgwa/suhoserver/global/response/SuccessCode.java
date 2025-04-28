package baekgwa.suhoserver.global.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * PackageName : baekgwa.suhoserver.global.response
 * FileName    : SuccessCode
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
public enum SuccessCode {

	//Project
	PROJECT_CREATE_SUCCESS(HttpStatus.CREATED, "신규 프로젝트 생성 완료."),
	PROJECT_VERSION_FIND_SUCCESS(HttpStatus.OK, "프로젝트 버전 조회 완료."),
	PROJECT_FIND_SUCCESS(HttpStatus.OK, "프로젝트 정보 조회 성공."),

	//Branch
	BRANCH_BOM_UPDATE_SUCCESS(HttpStatus.CREATED, "프로젝트 분기 BOM 업데이트 완료."),
	BRANCH_BOM_GET_SUCCESS(HttpStatus.OK, "프로젝트 분기 BOM 불러오기 완료."),

	//Common
	REQUEST_SUCCESS(HttpStatus.OK, "요청 응답 성공.");

	private final HttpStatus status;
	private final String message;
}

