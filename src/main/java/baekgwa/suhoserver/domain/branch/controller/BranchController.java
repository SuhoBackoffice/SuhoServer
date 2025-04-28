package baekgwa.suhoserver.domain.branch.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import baekgwa.suhoserver.domain.branch.dto.BranchResponseDto;
import baekgwa.suhoserver.domain.branch.service.BranchService;
import baekgwa.suhoserver.global.response.BaseResponse;
import baekgwa.suhoserver.global.response.SuccessCode;
import lombok.RequiredArgsConstructor;

/**
 * PackageName : baekgwa.suhoserver.domain.branch.controller
 * FileName    : BranchController
 * Author      : Baekgwa
 * Date        : 2025-04-26
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-26     Baekgwa               Initial creation
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {

	private final BranchService branchService;

	@PostMapping("/{projectId}/{branchCode}")
	public BaseResponse<Void> updateBranchBom(
		@PathVariable("projectId") Long projectId,
		@PathVariable("branchCode") Integer branchCode,
		@RequestPart("file") MultipartFile file
	) {
		branchService.updateBranchBom(file, projectId, branchCode);
		return BaseResponse.success(SuccessCode.BRANCH_BOM_UPDATE_SUCCESS);
	}

	@GetMapping("/{projectId}")
	public BaseResponse<List<BranchResponseDto.BranchBomList>> getBranchBomList(
		@PathVariable("projectId") Long projectId
	) {
		List<BranchResponseDto.BranchBomList> result = branchService.findBranchBomList(projectId);
		return BaseResponse.success(SuccessCode.BRANCH_BOM_GET_SUCCESS, result);
	}
}
