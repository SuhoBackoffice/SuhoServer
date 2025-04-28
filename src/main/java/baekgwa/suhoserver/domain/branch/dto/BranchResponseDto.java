package baekgwa.suhoserver.domain.branch.dto;

import baekgwa.suhoserver.model.project.branch.bom.type.BranchItemType;
import baekgwa.suhoserver.model.project.branch.bom.type.BranchItemUnit;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * PackageName : baekgwa.suhoserver.domain.branch.dto
 * FileName    : BranchResponseDto
 * Author      : Baekgwa
 * Date        : 2025-04-27
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-27     Baekgwa               Initial creation
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BranchResponseDto {

	@Getter
	@Builder
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class BranchBomList {
		private final BranchItemType branchItemType;
		private final String drawingCode;
		private final String itemName;
		private final String specification;
		private final Integer quantity;
		private final BranchItemUnit branchItemUnit;
		private final Boolean isConsignMaterial;
	}
}
