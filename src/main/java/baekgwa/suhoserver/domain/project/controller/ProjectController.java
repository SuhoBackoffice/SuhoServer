package baekgwa.suhoserver.domain.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baekgwa.suhoserver.domain.project.dto.ProjectRequestDto;
import baekgwa.suhoserver.domain.project.dto.ProjectResponseDto;
import baekgwa.suhoserver.domain.project.service.ProjectService;
import baekgwa.suhoserver.global.response.BaseResponse;
import baekgwa.suhoserver.global.response.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * PackageName : baekgwa.suhoserver.domain.project.controller
 * FileName    : ProjectController
 * Author      : Baekgwa
 * Date        : 2025-04-21
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-21     Baekgwa               Initial creation
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {

	private final ProjectService projectService;

	@PostMapping
	public BaseResponse<ProjectResponseDto.CreateProject> createProject(
		@Valid @RequestBody ProjectRequestDto.CreateProject createProject
	) {
		ProjectResponseDto.CreateProject response = projectService.saveNewProject(createProject);
		return BaseResponse.success(SuccessCode.PROJECT_CREATE_SUCCESS, response);
	}

	@GetMapping("/version")
	public BaseResponse<List<ProjectResponseDto.GetProjectVersion>> getProjectVersions() {
		List<ProjectResponseDto.GetProjectVersion> response = projectService.getProjectVersionList();
		return BaseResponse.success(SuccessCode.PROJECT_VERSION_FIND_SUCCESS, response);
	}

	@GetMapping("{projectId}")
	public BaseResponse<ProjectResponseDto.GetProjectDetail> getProjectInfo(
		@PathVariable(name = "projectId") Long projectId
	) {
		ProjectResponseDto.GetProjectDetail response = projectService.getProjectDetail(projectId);
		return BaseResponse.success(SuccessCode.PROJECT_FIND_SUCCESS, response);
	}
}
