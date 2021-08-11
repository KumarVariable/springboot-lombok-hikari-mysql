package mysql.hikari.lombok.spring.model;

import lombok.Data;

/**
 * Current Salary Details of an Employee.
 * 
 * @author metanoia
 * @version 1.0
 * 
 */
@Data
public class EmployeeSalaryDetails {

	private int employeeId;
	private int employeeSalary;

	private String employeeSalaryFromDate;
	private String employeeSalaryToDate;
	private String salaryInCurrency;

}
