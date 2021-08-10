package mysql.hikari.lombok.spring.dao;

import java.util.List;

/**
 * Abstract APIs to persist records to database.
 * 
 * @author metanoia
 * @version 1.0
 * @param <T>
 */

public interface EmployeeDAO<T> {

	/**
	 * Fetch department number of an employee.
	 * 
	 * @return department number.
	 */
	public String getDepartmentId(int employeeId);

	/**
	 * Fetch all employees
	 * 
	 * @return <T> List of Employee(s)
	 */
	public List<T> getAllEmployees();

	/**
	 * Fetch selected employee's salary details.
	 * 
	 * @param employeeId
	 * 
	 * @return <T> An Employee
	 * 
	 */
	public T getEmpJobSalary(int employeeId);

	/**
	 * Fetch selected employee's department and manager information.
	 * 
	 * @param employeeId,
	 *            departmentId
	 * 
	 * @return <T> An Employee
	 */
	public T getEmpDepartment(int employeeId, String departmentId);

	/**
	 * Fetch selected employee's details (salary, department, manager)
	 * 
	 * @param employeeId
	 * 
	 * @return <T> an Employee
	 */
	public T getEmployeeDetails(int employeeId);

}
