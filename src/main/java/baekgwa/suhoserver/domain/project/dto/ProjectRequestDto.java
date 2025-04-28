package baekgwa.suhoserver.domain.project.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * PackageName : baekgwa.suhoserver.domain.project.dto
 * FileName    : ProjectRequestDto
 * Author      : Baekgwa
 * Date        : 2025-04-21
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-21     Baekgwa               Initial creation
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectRequestDto {

	@Getter
	public static class CreateProject {
		@Size(min = 1, max = 50)
		private final String projectName;
		private final Long versionId;
		private final LocalDate startDate;
		@Valid private final List<BranchInfo> rows;

		@Builder
		private CreateProject(String projectName, Long versionId, LocalDate startDate, List<BranchInfo> rows) {
			this.projectName = projectName;
			this.versionId = versionId;
			this.startDate = startDate;
			this.rows = rows;
		}
	}

	@Getter
	public static class BranchInfo {
		@Min(value = 1, message = "분기 코드는 1 이상이어야 합니다.")
		@Max(value = 10_000, message = "분기 코드는 10_000 이하이어야 합니다.")
		private final Integer productCode;

		@Min(value = 1, message = "수량은 1 이상이어야 합니다.")
		@Max(value = 1000, message = "수량은 1000 이하이어야 합니다.")
		private final Integer quantity;

		@Builder
		private BranchInfo(Integer productCode, Integer quantity) {
			this.productCode = productCode;
			this.quantity = quantity;
		}
	}
}
