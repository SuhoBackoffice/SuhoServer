package baekgwa.suhoserver.model.project.project.repository;

import java.util.Optional;

import baekgwa.suhoserver.domain.project.dto.ProjectResponseDto;

/**
 * PackageName : baekgwa.suhoserver.model.project.project.repository
 * FileName    : ProjectRepositoryCustom
 * Author      : Baekgwa
 * Date        : 2025-04-26
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-26     Baekgwa               Initial creation
 */
public interface ProjectRepositoryCustom {
	Optional<ProjectResponseDto.GetProjectDetail> findProjectWithBranches(Long projectId);
}
