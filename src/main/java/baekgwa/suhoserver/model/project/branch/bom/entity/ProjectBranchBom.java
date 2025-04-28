package baekgwa.suhoserver.model.project.branch.bom.entity;

import java.util.List;

import baekgwa.suhoserver.domain.branch.excel.BranchBom;
import baekgwa.suhoserver.model.BaseEntity;
import baekgwa.suhoserver.model.project.branch.bom.type.BranchItemType;
import baekgwa.suhoserver.model.project.branch.bom.type.BranchItemUnit;
import baekgwa.suhoserver.model.project.branch.branch.entity.ProjectBranch;
import baekgwa.suhoserver.model.project.version.entity.ProjectVersion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * PackageName : baekgwa.suhoserver.model.project.branch.bom.entity
 * FileName    : ProjectBranchBom
 * Author      : Baekgwa
 * Date        : 2025-04-21
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-21     Baekgwa               Initial creation
 */
@Getter
@Entity
@Table(name = "project_branch_bom")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectBranchBom extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_branch_id", nullable = false)
	private ProjectBranch projectBranch;

	@Enumerated(EnumType.STRING)
	@Column(name = "item_type", nullable = false)
	private BranchItemType branchItemType;

	@Column(name = "drawing_code", nullable = false)
	private String drawingCode;

	@Column(name = "item_name", nullable = false)
	private String itemName;

	@Column(name = "specification", columnDefinition = "TEXT", nullable = false)
	private String specification;

	@Column(name = "quantity_per_unit", nullable = false)
	private Integer quantityPerUnit;

	@Enumerated(EnumType.STRING)
	@Column(name = "unit", nullable = false)
	private BranchItemUnit branchItemUnit;

	@Column(name = "is_consign_material", nullable = false)
	private Boolean isConsignMaterial;

	@Builder
	private ProjectBranchBom(ProjectBranch projectBranch, BranchItemType branchItemType,
		String drawingCode, String itemName, String specification, Integer quantityPerUnit,
		BranchItemUnit branchItemUnit,
		Boolean isConsignMaterial) {
		this.projectBranch = projectBranch;
		this.branchItemType = branchItemType;
		this.drawingCode = drawingCode;
		this.itemName = itemName;
		this.specification = specification;
		this.quantityPerUnit = quantityPerUnit;
		this.branchItemUnit = branchItemUnit;
		this.isConsignMaterial = isConsignMaterial;
	}

	public static ProjectBranchBom from(ProjectBranch projectBranch, BranchBom branchBom) {
		return ProjectBranchBom
			.builder()
			.projectBranch(projectBranch)
			.branchItemType(branchBom.getBranchItemType())
			.drawingCode(branchBom.getDrawingCode())
			.itemName(branchBom.getItemName())
			.specification(branchBom.getSpecification())
			.quantityPerUnit(branchBom.getQuantityPerUnit())
			.branchItemUnit(branchBom.getBranchItemUnit())
			.isConsignMaterial(branchBom.getIsConsignMaterial())
			.build();
	}
}
