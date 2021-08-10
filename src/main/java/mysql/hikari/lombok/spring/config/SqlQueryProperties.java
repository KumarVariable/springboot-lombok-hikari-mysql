package mysql.hikari.lombok.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class to bind external properties (from sqlQuery.properties) to load SQL
 * queries(commands) for database operations in persistence layer.
 * 
 * Annotate with @ConfigurationProperties, the prefix of the properties to bind.
 * 
 * @author metanoia
 * @version 1.0
 */

@Configuration
@PropertySource("classpath:sqlQuery.properties")
@ConfigurationProperties(prefix = "sql.query")
@Getter
@Setter
@NoArgsConstructor
public class SqlQueryProperties {

	private String allEmployees;
	private String jobDetails;
	private String departmentDetails;
	private String departmentId;

}
