package baekgwa.suhoserver.model.project.branch.branch.entity;

import baekgwa.suhoserver.model.BaseEntity;
import baekgwa.suhoserver.model.project.project.entity.Project;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
 * PackageName : baekgwa.suhoserver.model.project.branch.branch.entity
 * FileName    : ProjectBranch
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
@Table(name = "project_branch")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectBranch extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	@Column(name = "branch_code", nullable = false)
	private Integer code;

	@Column(name = "branch_quantity", nullable = false)
	private Integer quantity;

	@Builder(access = AccessLevel.PRIVATE)
	private ProjectBranch(Project project, Integer code, Integer quantity) {
		this.project = project;
		this.code = code;
		this.quantity = quantity;
	}

	public static ProjectBranch create(
		Project project, Integer code, Integer quantity) {
		return ProjectBranch
			.builder()
			.project(project)
			.code(code)
			.quantity(quantity)
			.build();
	}
}
