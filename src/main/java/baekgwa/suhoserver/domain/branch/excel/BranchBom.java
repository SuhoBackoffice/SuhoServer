package baekgwa.suhoserver.domain.branch.excel;

import baekgwa.suhoserver.model.project.branch.bom.type.BranchItemType;
import baekgwa.suhoserver.model.project.branch.bom.type.BranchItemUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * PackageName : baekgwa.suhoserver.model.project.branch.bom.dto
 * FileName    : BranchBom
 * Author      : Baekgwa
 * Date        : 2025-04-26
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-26     Baekgwa               Initial creation
 */
@Getter
@Builder
@AllArgsConstructor
public class BranchBom {

	private final BranchItemType branchItemType;
	private final String drawingCode;
	private final String itemName;
	private final String specification;
	private final Integer quantityPerUnit;
	private final BranchItemUnit branchItemUnit;
	private final Boolean isConsignMaterial;
}
