package baekgwa.suhoserver.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

/**
 * PackageName : baekgwa.suhoserver.global.config
 * FileName    : QuerydslConfig
 * Author      : Baekgwa
 * Date        : 2025-04-22
 * Description :
 * =====================================================================================================================
 * DATE          AUTHOR               NOTE
 * ---------------------------------------------------------------------------------------------------------------------
 * 2025-04-22     Baekgwa               Initial creation
 */
@Configuration
public class QuerydslConfig {

	@Bean
	public JPAQueryFactory jpaQueryFactory(final EntityManager em) {
		return new JPAQueryFactory(em);
	}
}
