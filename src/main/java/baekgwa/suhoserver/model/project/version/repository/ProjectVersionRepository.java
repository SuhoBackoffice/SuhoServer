package baekgwa.suhoserver.model.project.version.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import baekgwa.suhoserver.model.project.version.entity.ProjectVersion;

/**
 * PackageName : baekgwa.suhoserver.model.project.version.repository
 * FileName    : ProjectVersionRepository
 * Author      : Baekgwa
 * Date        : 2025-04-22
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-22     Baekgwa               Initial creation
 */
public interface ProjectVersionRepository extends JpaRepository<ProjectVersion, Long> {
}
