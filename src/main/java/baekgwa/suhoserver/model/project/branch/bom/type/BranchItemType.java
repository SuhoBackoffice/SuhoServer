package baekgwa.suhoserver.model.project.branch.bom.type;

import baekgwa.suhoserver.global.exception.GlobalException;
import baekgwa.suhoserver.global.response.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * PackageName : baekgwa.suhoserver.model.project.branch.bom.type
 * FileName    : BranchItemType
 * Author      : Baekgwa
 * Date        : 2025-04-21
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-21     Baekgwa               Initial creation
 */
@Getter
@RequiredArgsConstructor
public enum BranchItemType {

	PROCESSED("가공품"),
	PURCHASED("구매품"),
	INJECTION("사출품");

	public static BranchItemType fromDescription(String description) {
		for (BranchItemType type : BranchItemType.values()) {
			if (type.getDescriptionKo().equals(description)) {
				return type;
			}
		}
		throw new GlobalException(ErrorCode.VALIDATION_FAIL_BRANCH_ITEM_TYPE);
	}

	private final String descriptionKo;
}
