package mysql.hikari.lombok.spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class represents salary details of an Employee.
 * 
 * @author metanoia
 * @version 1.0
 * 
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeeSalaryDetails {

	private int employeeId;
	private int employeeSalary;

	private String employeeSalaryFromDate;
	private String employeeSalaryToDate;
	private String salaryInCurrency;

}
