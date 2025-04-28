package baekgwa.suhoserver.domain.branch.excel;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * PackageName : baekgwa.suhoserver.domain.branch.excel
 * FileName    : BranchExcelColumn
 * Author      : Baekgwa
 * Date        : 2025-04-26
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-26     Baekgwa               Initial creation
 */
@Getter
public enum BranchExcelColumn {
	@Deprecated EMPTY(),
	NUMBER("번호"),
	UNIT_NO("UNIT NO"),
	BRANCH_ITEM_TYPE("품목구분"),
	DRAWING_CODE("도번"),
	ITEM_NAME("품명"),
	SPECIFICATION("규격"),
	@Deprecated MAKER("MAKER"),
	QUANTITY_PER_UNIT("수량", "원수량"),
	@Deprecated BUY_QUANTITY("구매수량"),
	BRANCH_ITEM_UNIT("단위"),
	@Deprecated BUY_REASON("구매사유"),
	NOTE("비고");

	private final List<String> headerNames;

	BranchExcelColumn(String... headerNames) {
		this.headerNames = List.of(headerNames);
	}

	public static BranchExcelColumn findByHeaderName(String headerName) {
		for (BranchExcelColumn column : values()) {
			if (column.headerNames.contains(headerName)) {
				return column;
			}
		}
		return null;
	}
}
