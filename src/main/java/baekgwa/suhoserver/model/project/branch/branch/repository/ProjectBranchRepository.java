package baekgwa.suhoserver.model.project.branch.branch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import baekgwa.suhoserver.model.project.branch.branch.entity.ProjectBranch;
import baekgwa.suhoserver.model.project.project.entity.Project;

/**
 * PackageName : baekgwa.suhoserver.model.project.branch.branch.repository
 * FileName    : ProjectBranchRepository
 * Author      : Baekgwa
 * Date        : 2025-04-21
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-21     Baekgwa               Initial creation
 */
public interface ProjectBranchRepository extends JpaRepository<ProjectBranch, Long> {

	List<ProjectBranch> findAllByProject(Project project);

	@Query("SELECT p FROM ProjectBranch p where p.project.id = :projectId")
	List<ProjectBranch> findAllByProjectId(@Param("projectId") Long projectId);

	@Query("SELECT p FROM ProjectBranch p where p.project.id = :projectId AND p.code = :branchCode")
	Optional<ProjectBranch> findByProjectIdAndBranchCode(@Param("projectId") Long projectId,
		@Param("branchCode") Integer branchCode);
}
