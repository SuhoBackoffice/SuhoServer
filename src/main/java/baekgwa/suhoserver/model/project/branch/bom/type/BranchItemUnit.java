package baekgwa.suhoserver.model.project.branch.bom.type;

import baekgwa.suhoserver.global.exception.GlobalException;
import baekgwa.suhoserver.global.response.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * PackageName : baekgwa.suhoserver.model.project.branch.bom.type
 * FileName    : BranchItemUnit
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
public enum BranchItemUnit {

	EA("EA"),
	BOX("BOX"),
	ROLL("ROLL");

	public static BranchItemUnit fromDescription(String description) {
		for (BranchItemUnit unit : BranchItemUnit.values()) {
			if (unit.getDescription().equals(description)) {
				return unit;
			}
		}
		throw new GlobalException(ErrorCode.VALIDATION_FAIL_BRANCH_ITEM_UNIT);
	}

	private final String description;
}
