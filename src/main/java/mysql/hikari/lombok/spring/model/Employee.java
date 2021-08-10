package mysql.hikari.lombok.spring.model;

import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class represents an Employee.
 * 
 * @author metanoia
 * @version 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Employee {

	private int employeeId;

	private String employeeFirstName;
	private String employeeLastName;

	private String employeeFullName;
	private String employeeGender;

	private String employeeDateOfBirth;
	private String employeeHiringDate;

	private Map<Integer, EmployeeDepartment> empDepttDetails;
	private Map<Integer, EmployeeSalaryDetails> empSalaryDetails;
	private Map<Integer, DepartmentManager> empManagerDetails;
	private Map<Integer, JobTitle> empJobDetails;

}
