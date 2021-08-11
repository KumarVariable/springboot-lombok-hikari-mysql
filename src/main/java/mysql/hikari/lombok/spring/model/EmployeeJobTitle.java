package mysql.hikari.lombok.spring.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Current job status of an Employee.
 * 
 * @author metanoia
 * @version 1.0
 * 
 */
@Data
@Accessors(fluent = true)
public class EmployeeJobTitle {

	private int employeeId;
	private String jobTitle;

	private String jobTitleFromDate;
	private String jobTitleToDate;

}
