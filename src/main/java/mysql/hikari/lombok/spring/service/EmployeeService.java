package mysql.hikari.lombok.spring.service;

import java.util.List;

import mysql.hikari.lombok.spring.model.Employee;

/**
 * 
 * Service API to provide business logic related to Employee(s).
 * 
 * @author metanoia
 * @version 1.0
 */
public interface EmployeeService {

	/**
	 * Find all available employees.
	 * 
	 * @return List<Employee>
	 */
	public List<Employee> findAllEmployees();

	/**
	 * Find details of an employee's salary.
	 * 
	 * @param employeeId
	 * 
	 * @return Employee
	 */
	public Employee findEmployeeSalary(int employeeId);

	/**
	 * Find department and department's manager details of a employee
	 * 
	 * @param employeeId,
	 *            departmentId
	 * 
	 * @return Employee
	 */
	public Employee findEmployeeManager(int employeeId);

	/**
	 * Find Employee's information on salary, job title, department manager
	 * 
	 * @param employeeId
	 * 
	 * @return Employee
	 */
	public Employee findEmployeeDetails(int employeeId);

}
