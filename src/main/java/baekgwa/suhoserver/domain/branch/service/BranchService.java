package baekgwa.suhoserver.domain.branch.service;

import static baekgwa.suhoserver.domain.branch.excel.BranchExcelColumn.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import baekgwa.suhoserver.domain.branch.dto.BranchResponseDto;
import baekgwa.suhoserver.domain.branch.excel.BranchExcelColumn;
import baekgwa.suhoserver.global.exception.GlobalException;
import baekgwa.suhoserver.global.response.ErrorCode;
import baekgwa.suhoserver.domain.branch.excel.BranchBom;
import baekgwa.suhoserver.model.project.branch.bom.entity.ProjectBranchBom;
import baekgwa.suhoserver.model.project.branch.bom.repository.ProjectBranchBomRepository;
import baekgwa.suhoserver.model.project.branch.bom.type.BranchItemType;
import baekgwa.suhoserver.model.project.branch.bom.type.BranchItemUnit;
import baekgwa.suhoserver.model.project.branch.branch.entity.ProjectBranch;
import baekgwa.suhoserver.model.project.branch.branch.repository.ProjectBranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * PackageName : baekgwa.suhoserver.domain.branch.service
 * FileName    : BranchService
 * Author      : Baekgwa
 * Date        : 2025-04-26
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-26     Baekgwa               Initial creation
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BranchService {

	private final ProjectBranchRepository projectBranchRepository;
	private final ProjectBranchBomRepository projectBranchBomRepository;

	@Transactional
	public void updateBranchBom(MultipartFile file, Long projectId, Integer branchCode) {
		ProjectBranch findProjectBranch = projectBranchRepository.findByProjectIdAndBranchCode(projectId, branchCode)
			.orElseThrow( () -> new GlobalException(ErrorCode.BRANCH_BOM_REGISTER_FAIL_BRANCH_NOT_FOUND));

		List<BranchBom> branchBomList = extractBranchBomFromExcel(file);

		List<ProjectBranchBom> projectBranchBomList = branchBomList.stream().map(
			data -> ProjectBranchBom.from(findProjectBranch, data)).toList();

		projectBranchBomRepository.saveAll(projectBranchBomList);
	}

	private List<BranchBom> extractBranchBomFromExcel(MultipartFile file) {
		List<BranchBom> bomItems = new ArrayList<>();

		try (InputStream inputStream = file.getInputStream();
			 Workbook workbook = new XSSFWorkbook(inputStream)) {

			Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트

			// 1. 헤더 매핑
			Row headerRow = sheet.getRow(2); // C3행 (row index = 2)
			if (headerRow == null) {
				throw new GlobalException(ErrorCode.BRANCH_BOM_REGISTER_FAIL_BOM_FILE_MISS_MATCH);
			}

			// 헤더명 → 컬럼 인덱스 매핑
			Map<BranchExcelColumn, Integer> columnMapping = new HashMap<>();

			for (Cell cell : headerRow) {
				String headerName = getCellValue(cell);

				// 헤더명으로 BranchExcelColumn 찾기
				BranchExcelColumn column = BranchExcelColumn.findByHeaderName(headerName);
				if (column != null) {
					columnMapping.put(column, cell.getColumnIndex());
				}
			}

			int totalRows = sheet.getPhysicalNumberOfRows();

			for (int rowNum = 3; rowNum < totalRows; rowNum++) { // Row 0~2는 무시, 3부터 데이터
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}

				String number = getCellValue(row.getCell(columnMapping.get(NUMBER)));
				String unitNo = getCellValue(row.getCell(columnMapping.get(UNIT_NO)));
				if (number.isBlank() && unitNo.isBlank()) {
					break; // 데이터가 없는 Row를 만나면 종료
				}

				BranchItemType branchItemType = BranchItemType.fromDescription(
					getCellValue(row.getCell(columnMapping.get(BRANCH_ITEM_TYPE)))
				);
				String drawingCode = getCellValue(row.getCell(columnMapping.get(DRAWING_CODE)));
				String itemName = getCellValue(row.getCell(columnMapping.get(ITEM_NAME)));
				String specification = getCellValue(row.getCell(columnMapping.get(SPECIFICATION)));
				Integer quantityPerUnit = Integer.parseInt(
					getCellValue(row.getCell(columnMapping.get(QUANTITY_PER_UNIT)))
				);
				BranchItemUnit branchItemUnit = BranchItemUnit.fromDescription(
					getCellValue(row.getCell(columnMapping.get(BRANCH_ITEM_UNIT)))
				);

				String note = getCellValue(row.getCell(columnMapping.get(NOTE)));
				Boolean consignMaterial = isConsignMaterial(note);

				BranchBom newBranchBom = BranchBom.builder()
					.branchItemType(branchItemType)
					.drawingCode(drawingCode)
					.itemName(itemName)
					.specification(specification)
					.quantityPerUnit(quantityPerUnit)
					.branchItemUnit(branchItemUnit)
					.isConsignMaterial(consignMaterial)
					.build();

				bomItems.add(newBranchBom);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException(ErrorCode.BRANCH_BOM_REGISTER_FAIL_BOM_FILE_MISS_MATCH);
		}
		return bomItems;
	}

	private String getCellValue(Cell cell) {
		if (cell == null)
			return "";
		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue().trim();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf((long)cell.getNumericCellValue());
		} else {
			return "";
		}
	}

	private Boolean isConsignMaterial(String note) {
		if (note.contains("사급"))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	@Transactional(readOnly = true)
	public List<BranchResponseDto.BranchBomList> findBranchBomList(Long projectId) {

		// 1. ProjectBranch 조회
		List<ProjectBranch> findProjectBranchList = projectBranchRepository.findAllByProjectId(projectId);

		Map<Long, Integer> branchIdToQuantityMap = findProjectBranchList.stream()
			.collect(Collectors.toMap(ProjectBranch::getId, ProjectBranch::getQuantity));

		// 2. ProjectBranchBom 조회
		List<ProjectBranchBom> findProjectBranchBomList = projectBranchBomRepository.findAllByProjectBranchIdIn(
			findProjectBranchList.stream().map(ProjectBranch::getId).toList()
		);

		// 3. drawingCode 기준으로 바로 DTO 생성 + quantity 누적
		Map<String, BranchResponseDto.BranchBomList> aggregatedMap = new HashMap<>();

		for (ProjectBranchBom bom : findProjectBranchBomList) {
			Long branchId = bom.getProjectBranch().getId();
			Integer branchQuantity = branchIdToQuantityMap.getOrDefault(branchId, 0);

			int totalQuantity = bom.getQuantityPerUnit() * branchQuantity;

			aggregatedMap.compute(bom.getDrawingCode(), (drawingCode, existing) -> {
				if (existing == null) {
					return BranchResponseDto.BranchBomList.builder()
						.branchItemType(bom.getBranchItemType())
						.drawingCode(bom.getDrawingCode())
						.itemName(bom.getItemName())
						.specification(bom.getSpecification())
						.quantity(totalQuantity)
						.branchItemUnit(bom.getBranchItemUnit())
						.isConsignMaterial(bom.getIsConsignMaterial())
						.build();
				} else {
					return BranchResponseDto.BranchBomList.builder()
						.branchItemType(existing.getBranchItemType())
						.drawingCode(existing.getDrawingCode())
						.itemName(existing.getItemName())
						.specification(existing.getSpecification())
						.quantity(existing.getQuantity() + totalQuantity)
						.branchItemUnit(existing.getBranchItemUnit())
						.isConsignMaterial(existing.getIsConsignMaterial())
						.build();
				}
			});
		}

		return new ArrayList<>(aggregatedMap.values());
	}
}
