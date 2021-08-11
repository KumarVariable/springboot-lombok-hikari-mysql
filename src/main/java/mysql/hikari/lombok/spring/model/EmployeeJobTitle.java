package mysql.hikari.lombok.spring.model;

import lombok.Data;

/**
 * Current job status of an Employee.
 * 
 * @author metanoia
 * @version 1.0
 * 
 */
@Data
public class EmployeeJobTitle {

	private int employeeId;
	private String jobTitle;

	private String jobTitleFromDate;
	private String jobTitleToDate;

}
