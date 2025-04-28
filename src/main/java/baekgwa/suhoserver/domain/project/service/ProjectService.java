package baekgwa.suhoserver.domain.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import baekgwa.suhoserver.domain.project.dto.ProjectRequestDto;
import baekgwa.suhoserver.domain.project.dto.ProjectResponseDto;
import baekgwa.suhoserver.global.exception.GlobalException;
import baekgwa.suhoserver.global.response.ErrorCode;
import baekgwa.suhoserver.model.project.branch.bom.repository.ProjectBranchBomRepository;
import baekgwa.suhoserver.model.project.branch.branch.entity.ProjectBranch;
import baekgwa.suhoserver.model.project.branch.branch.repository.ProjectBranchRepository;
import baekgwa.suhoserver.model.project.project.entity.Project;
import baekgwa.suhoserver.model.project.project.repository.ProjectRepository;
import baekgwa.suhoserver.model.project.version.entity.ProjectVersion;
import baekgwa.suhoserver.model.project.version.repository.ProjectVersionRepository;
import lombok.RequiredArgsConstructor;

/**
 * PackageName : baekgwa.suhoserver.domain.project.service
 * FileName    : ProjectService
 * Author      : Baekgwa
 * Date        : 2025-04-21
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-21     Baekgwa               Initial creation
 */
@Service
@RequiredArgsConstructor
public class ProjectService {

	private final ProjectRepository projectRepository;
	private final ProjectBranchRepository projectBranchRepository;
	private final ProjectBranchBomRepository projectBranchBomRepository;
	private final ProjectVersionRepository projectVersionRepository;

	@Transactional
	public ProjectResponseDto.CreateProject saveNewProject(
		final ProjectRequestDto.CreateProject createProject
	) {
		// 프로젝트 version 조회
		ProjectVersion findProjectVersion = findProjectVersion(createProject.getVersionId());

		// 신규 프로젝트 생성
		Project saveProject = createAndSaveNewProject(createProject, findProjectVersion);

		// 신규 프로젝트의 분기 레일 정보 생성
		List<ProjectBranch> saveProjectBranch = createAndSaveNewProjectBranch(createProject, saveProject);

		// 분기 레일의 BOM 정보 업데이트
		updateBranchBOM(saveProjectBranch, findProjectVersion);

		return ProjectResponseDto.CreateProject.of(saveProject.getId());
	}

	/**
	 * 현재 프로젝트에서 작업할 분기 레일의 Id
	 * @param projectBranchList
	 */
	// todo : 추가 업데이트
	// 동일한 버전의 프로젝트에서 이미 진행한 분기 레일의 BOM 리스트 중, 가장 최신 내용을 먼저 검색
	// 만약 있다면, DEFAULT 로 가져와서 적용시키면 됨
	// 이유는, 한번 제작 완료한 분기 번호들은, Revision 잘 되지 않음.
	private void updateBranchBOM(List<ProjectBranch> projectBranchList, ProjectVersion projectVersion) {
	}

	private List<ProjectBranch> createAndSaveNewProjectBranch(ProjectRequestDto.CreateProject createProject,
		Project saveProject) {
		List<ProjectBranch> branches = createProject.getRows().stream()
			.map(item -> ProjectBranch.create(saveProject, item.getProductCode(), item.getQuantity()))
			.toList();

		return projectBranchRepository.saveAll(branches);
	}

	private Project createAndSaveNewProject(ProjectRequestDto.CreateProject createProject,
		ProjectVersion projectVersion) {
		Project newProject = Project.create(createProject.getProjectName(), projectVersion,
			createProject.getStartDate());
		return projectRepository.save(newProject);
	}

	private ProjectVersion findProjectVersion(Long versionId) {
		return projectVersionRepository.findById(versionId)
			.orElseThrow(() -> new GlobalException(ErrorCode.PROJECT_VERSION_NOT_FOUND));
	}

	/**
	 * 모든 Project Version 정보를 찾습니다.
	 * @return List<ProjectResponseDto.GetProjectVersion>
	 */
	@Transactional(readOnly = true)
	public List<ProjectResponseDto.GetProjectVersion> getProjectVersionList() {
		return projectVersionRepository.findAll().stream()
			.map(version -> ProjectResponseDto.GetProjectVersion.from(version.getId(), version.getName()))
			.toList();
	}

	/**
	 * 프로젝트의 상세 정보를 반환합니다.
	 * @param projectId 프로젝트 ID
	 * @return 프로젝트 상세 정보
	 */
	@Transactional(readOnly = true)
	public ProjectResponseDto.GetProjectDetail getProjectDetail(Long projectId) {
		return projectRepository.findProjectWithBranches(projectId)
			.orElseThrow(() -> new GlobalException(ErrorCode.PROJECT_NOT_FOUND));
	}
}
