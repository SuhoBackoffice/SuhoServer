package baekgwa.suhoserver.model.project.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import baekgwa.suhoserver.domain.project.dto.ProjectResponseDto;
import baekgwa.suhoserver.model.project.branch.bom.entity.QProjectBranchBom;
import baekgwa.suhoserver.model.project.branch.branch.entity.QProjectBranch;
import baekgwa.suhoserver.model.project.project.entity.Project;
import baekgwa.suhoserver.model.project.project.entity.QProject;
import baekgwa.suhoserver.model.project.version.entity.QProjectVersion;
import lombok.RequiredArgsConstructor;

/**
 * PackageName : baekgwa.suhoserver.model.project.project.repository
 * FileName    : ProjectRepositoryImpl
 * Author      : Baekgwa
 * Date        : 2025-04-26
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-26     Baekgwa               Initial creation
 */
@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	private static final QProject project = QProject.project;
	private static final QProjectBranch projectBranch = QProjectBranch.projectBranch;
	private static final QProjectBranchBom projectBranchBom = QProjectBranchBom.projectBranchBom;
	private static final QProjectVersion projectVersion = QProjectVersion.projectVersion;

	@Override
	public Optional<ProjectResponseDto.GetProjectDetail> findProjectWithBranches(Long projectId) {
		// 프로젝트 정보 조회
		Project findProject = queryFactory
			.selectFrom(project)
			.join(project.projectVersion, projectVersion).fetchJoin()
			.where(project.id.eq(projectId))
			.fetchOne();

		if (findProject == null) {
			return Optional.empty();
		}

		// 브랜치 정보 조회
		List<ProjectResponseDto.ProjectBranchInfo> branches = queryFactory
			.select(Projections.constructor(
				ProjectResponseDto.ProjectBranchInfo.class,
				projectBranch.code,
				projectBranch.quantity,
				projectBranch.id,
				JPAExpressions.selectOne()
					.from(projectBranchBom)
					.where(projectBranchBom.projectBranch.id.eq(projectBranch.id))
					.exists()
			))
			.from(projectBranch)
			.where(projectBranch.project.eq(findProject))
			.fetch();

		// GetProjectDetail 객체 생성 및 반환
		ProjectResponseDto.GetProjectDetail projectDetail = ProjectResponseDto.GetProjectDetail.from(findProject, branches);
		return Optional.of(projectDetail);
	}
}
