package baekgwa.suhoserver.model.project.version.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * PackageName : baekgwa.suhoserver.model.project.version.entity
 * FileName    : GetProjectVersion
 * Author      : Baekgwa
 * Date        : 2025-04-22
 * Description : 
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-22     Baekgwa               Initial creation
 */
@Getter
@Entity
@Table(name = "project_version")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectVersion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Builder(access = AccessLevel.PRIVATE)
	private ProjectVersion(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public static ProjectVersion create(Long id, String name) {
		return ProjectVersion
			.builder()
			.id(id)
			.name(name)
			.build();
	}
}
