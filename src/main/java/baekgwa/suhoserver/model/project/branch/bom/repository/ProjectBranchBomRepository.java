package baekgwa.suhoserver.model.project.branch.bom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import baekgwa.suhoserver.model.project.branch.bom.entity.ProjectBranchBom;

/**
 * PackageName : baekgwa.suhoserver.model.project.branch.bom.repository
 * FileName    : ProjectBranchBomRepository
 * Author      : Baekgwa
 * Date        : 2025-04-21
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-21     Baekgwa               Initial creation
 */
public interface ProjectBranchBomRepository extends JpaRepository<ProjectBranchBom, Long> {

	List<ProjectBranchBom> findAllByProjectBranchIdIn(List<Long> projectBranchIds);
}
