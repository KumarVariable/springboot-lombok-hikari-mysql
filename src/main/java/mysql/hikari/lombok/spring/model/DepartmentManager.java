package mysql.hikari.lombok.spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class represents information of previous and current manager(s) of department
 * in an organization.
 * 
 * @author metanoia
 * @version 1.0
 * 
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DepartmentManager {

	private int managerId;
	private String departmentId;

	private String managerName;
	private String managerFromDate;
	private String managerToDate;

}
