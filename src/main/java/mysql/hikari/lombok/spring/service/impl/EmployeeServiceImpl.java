package mysql.hikari.lombok.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import mysql.hikari.lombok.spring.dao.EmployeeDAO;
import mysql.hikari.lombok.spring.model.Employee;
import mysql.hikari.lombok.spring.service.EmployeeService;

/**
 * Service Implementation for application business layer.
 * 
 * @author metanoia
 * @version 1.0
 * 
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeDAO<Employee> employeeDAO;

	@Override
	public List<Employee> findAllEmployees() {

		List<Employee> empList = employeeDAO.getAllEmployees();

		if (empList == null || empList.size() == 0) {
			LOGGER.info(" No employees found in database ");
			empList = new ArrayList<Employee>();
		} else {
			LOGGER.info(" Size of Employee List  " + empList.size());
		}

		return empList;
	}

	@Override
	public Employee findEmployeeSalary(int employeeId) {
		Employee employee = employeeDAO.getEmpJobSalary(employeeId);

		if (ObjectUtils.isEmpty(employee)) {
			LOGGER.info("No Records found for employee number " + employeeId);
			employee = new Employee();
		} else {
			LOGGER.info("Employee Number " + employeeId + " records "
					+ employee.toString());
		}

		return employee;
	}

	@Override
	public Employee findEmployeeManager(int employeeId) {

		String departmentId = employeeDAO.getDepartmentId(employeeId);

		Employee employee = employeeDAO.getEmpDepartment(employeeId, departmentId);

		if (ObjectUtils.isEmpty(employee)) {
			LOGGER.info("No Records found for employee number " + employeeId);
			employee = new Employee();
		} else {
			LOGGER.info(String.format(
					"Employee Number %d , Department Number %s records - %s ",
					employeeId, departmentId, employee.toString()));
		}

		return employee;
	}

	@Override
	public Employee findEmployeeDetails(int employeeId) {
		Employee employee = employeeDAO.getEmployeeDetails(employeeId);

		if (ObjectUtils.isEmpty(employee)) {
			LOGGER.info("No Records found for employee number " + employeeId);
			employee = new Employee();
		} else {
			LOGGER.info("Employee Number " + employeeId + " records "
					+ employee.toString());
		}

		return employee;
	}

}
