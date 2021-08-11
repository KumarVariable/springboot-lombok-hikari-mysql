package mysql.hikari.lombok.spring.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Available departments in current organization.
 * 
 * @author metanoia
 * @version 1.0
 * 
 */
@Data
@Accessors(fluent = true)
public class Departments {

	private String departmentId;
	private String departmentName;

}
