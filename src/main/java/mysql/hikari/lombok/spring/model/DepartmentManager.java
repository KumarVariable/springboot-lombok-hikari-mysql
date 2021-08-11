package mysql.hikari.lombok.spring.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Manager of a Department.
 * 
 * @author metanoia
 * @version 1.0
 * 
 */

@Data
@Accessors(fluent = true)
public class DepartmentManager {

	private int managerId;
	private String departmentId;

	private String managerName;
	private String managerFromDate;
	private String managerToDate;

}
