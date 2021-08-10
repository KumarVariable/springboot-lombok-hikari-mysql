package mysql.hikari.lombok.spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class represents previous and current job title of an Employee.
 * 
 * @author metanoia
 * @version 1.0
 * 
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeeJobTitle {

	private int employeeNumber;
	private String jobTitle;
	private String jobTitleFromDate;
	private String jobTitleToDate;

}
