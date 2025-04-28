package baekgwa.suhoserver.model.project.project.entity;

import java.time.LocalDate;

import baekgwa.suhoserver.model.BaseEntity;
import baekgwa.suhoserver.model.project.version.entity.ProjectVersion;
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
 * PackageName : baekgwa.suhoserver.model.project.project.entity
 * FileName    : Project
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
@Table(name = "project")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_version_id", nullable = false)
	private ProjectVersion projectVersion;

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@Builder(access = AccessLevel.PRIVATE)
	private Project(String name, ProjectVersion projectVersion, LocalDate startDate) {
		this.name = name;
		this.projectVersion = projectVersion;
		this.startDate = startDate;
	}

	public static Project create(String name, ProjectVersion projectVersion, LocalDate startDate) {
		return Project
			.builder()
			.name(name)
			.projectVersion(projectVersion)
			.startDate(startDate)
			.build();
	}
}
