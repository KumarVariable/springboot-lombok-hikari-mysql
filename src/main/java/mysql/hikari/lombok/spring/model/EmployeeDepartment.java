package mysql.hikari.lombok.spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class represents department information of an Employee.
 * 
 * @author metanoia
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeeDepartment {

	private int employeeId;
	
	private String deptJoiningDate;
	private String deptLeavingDate;

	private Departments departments;

}
