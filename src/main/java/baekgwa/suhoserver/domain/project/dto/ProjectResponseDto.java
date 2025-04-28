package baekgwa.suhoserver.domain.project.dto;

import static lombok.AccessLevel.*;

import java.time.LocalDate;
import java.util.List;

import baekgwa.suhoserver.model.project.branch.branch.entity.ProjectBranch;
import baekgwa.suhoserver.model.project.project.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * PackageName : baekgwa.suhoserver.domain.project.dto
 * FileName    : ProjectResponseDto
 * Author      : Baekgwa
 * Date        : 2025-04-22
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-22     Baekgwa               Initial creation
 */
@NoArgsConstructor(access = PRIVATE)
public class ProjectResponseDto {

	@Getter
	@Builder(access = PRIVATE)
	@AllArgsConstructor(access = PRIVATE)
	public static class CreateProject {
		private final Long projectId;

		public static CreateProject of(Long projectId) {
			return new CreateProject(projectId);
		}
	}

	@Getter
	@Builder(access = PRIVATE)
	@AllArgsConstructor(access = PRIVATE)
	public static class GetProjectVersion {
		private final Long versionId;
		private final String versionName;

		public static GetProjectVersion from(Long versionId, String versionName) {
			return GetProjectVersion
				.builder()
				.versionId(versionId)
				.versionName(versionName)
				.build();
		}
	}

	@Getter
	@Builder(access = PRIVATE)
	@AllArgsConstructor(access = PRIVATE)
	public static class GetProjectDetail {
		private final Long projectId;
		private final String projectName;
		private final LocalDate startDate;
		private final String versionName;
		private final List<ProjectBranchInfo> branches;

		public static GetProjectDetail from(Project project, List<ProjectBranchInfo> branches) {
			return GetProjectDetail.builder()
				.projectId(project.getId())
				.projectName(project.getName())
				.startDate(project.getStartDate())
				.versionName(project.getProjectVersion().getName())
				.branches(branches)
				.build();
		}
	}

	@Getter
	public static class ProjectBranchInfo {
		private final Integer branchCode;
		private final Integer branchQuantity;
		private final Long branchId;
		private final Boolean isRegistered;

		public ProjectBranchInfo(Integer branchCode, Integer branchQuantity, Long branchId, Boolean isRegistered) {
			this.branchCode = branchCode;
			this.branchQuantity = branchQuantity;
			this.branchId = branchId;
			this.isRegistered = isRegistered;
		}
	}
}
