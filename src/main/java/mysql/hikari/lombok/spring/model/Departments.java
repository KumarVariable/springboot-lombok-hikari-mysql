package mysql.hikari.lombok.spring.model;

import lombok.Data;

/**
 * Available departments in current organization.
 * 
 * @author metanoia
 * @version 1.0
 * 
 */
@Data
public class Departments {

	private String departmentId;
	private String departmentName;

}
