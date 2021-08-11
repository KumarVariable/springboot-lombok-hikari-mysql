package mysql.hikari.lombok.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO<Employee> employeeDAO;

	@Override
	public List<Employee> findAllEmployees() {

		List<Employee> empList = employeeDAO.getAllEmployees();

		if (empList == null || empList.size() == 0) {
			log.info(" No employees found in database ");
			empList = new ArrayList<Employee>();
		} else {
			log.info(" Size of Employee List  " + empList.size());
		}

		return empList;
	}

	@Override
	public Employee findEmployeeSalary(int employeeId) {
		Employee employee = employeeDAO.getEmpJobSalary(employeeId);

		if (ObjectUtils.isEmpty(employee)) {
			log.info("No Records found for employee number = {} ", employeeId);
			employee = new Employee();
		} else {
			log.info("Employee Number {} record => {} ", employeeId,
					employee.toString());
		}

		return employee;
	}

	@Override
	public Employee findEmployeeManager(int employeeId) {

		String departmentId = employeeDAO.getDepartmentId(employeeId);

		Employee employee = employeeDAO.getEmpDepartment(employeeId,
				departmentId);

		if (ObjectUtils.isEmpty(employee)) {
			log.info("No Records found for employee number {} ", employeeId);
			employee = new Employee();
		} else {
			log.info(String.format(
					"Employee Number %d , Department Number %s records - %s ",
					employeeId, departmentId, employee.toString()));
		}

		return employee;
	}

	@Override
	public Employee findEmployeeDetails(int employeeId) {
		Employee employee = employeeDAO.getEmployeeDetails(employeeId);

		if (ObjectUtils.isEmpty(employee)) {
			log.info("No Records found for employee number ", employeeId);
			employee = new Employee();
		} else {
			log.info("Employee Number " + employeeId + " records "
					+ employee.toString());
		}

		return employee;
	}

}
