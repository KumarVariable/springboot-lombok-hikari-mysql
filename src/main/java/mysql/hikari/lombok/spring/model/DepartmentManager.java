package mysql.hikari.lombok.spring.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Manager of a Department.
 * 
 * @author metanoia
 * @version 1.0
 * 
 */

@Data
public class DepartmentManager {

	private int managerId;
	private String departmentId;

	private String managerName;
	private String managerFromDate;
	private String managerToDate;

}
