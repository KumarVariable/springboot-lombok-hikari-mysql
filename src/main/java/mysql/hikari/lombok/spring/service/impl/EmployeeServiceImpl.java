package mysql.hikari.lombok.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;
import mysql.hikari.lombok.spring.dao.EmployeeDAO;
import mysql.hikari.lombok.spring.exception.NoRecordsFoundsException;
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

			String exceptionLocation = "EmployeeServiceImpl.findAllEmployees()";
			String exMsg = String.format(
					"No records found in Database, Exception raised at %s",
					exceptionLocation);

			throw new NoRecordsFoundsException(exMsg);

		}
		log.info("EmployeeServiceImpl.findAllEmployees() called .");
		return empList;

	}

	@Override
	public Employee findEmployeeSalary(int employeeId) {
		Employee employee = employeeDAO.getEmpJobSalary(employeeId);

		if (ObjectUtils.isEmpty(employee)) {
			String exceptionLocation = "EmployeeServiceImpl.findEmployeeSalary()";
			String exMsg = String.format(
					"No records found for employee %d , Exception raised at %s",
					employeeId, exceptionLocation);

			throw new NoRecordsFoundsException(exMsg);
		}
		log.info("EmployeeServiceImpl.findEmployeeSalary() called .");
		return employee;
	}

	@Override
	public Employee findEmployeeManager(int employeeId) {

		String departmentId = employeeDAO.getDepartmentId(employeeId);

		if (ObjectUtils.isEmpty(departmentId)) {
			String msgformat = String.format(
					"Department Number not found for employee %d ", employeeId);
			throw new NoRecordsFoundsException(msgformat);
		}

		Employee employee = employeeDAO.getEmpDepartment(employeeId,
				departmentId);

		if (ObjectUtils.isEmpty(employee)) {
			String exceptionLocation = "EmployeeServiceImpl.findEmployeeManager()";
			String exMsg = String.format(
					"No records found for employee %d , Exception raised at %s",
					employeeId, exceptionLocation);

			throw new NoRecordsFoundsException(exMsg);
		}
		log.info("EmployeeServiceImpl.findEmployeeManager() called .");
		return employee;
	}

	@Override
	public Employee findEmployeeDetails(int employeeId) {
		log.info("EmployeeServiceImpl.findEmployeeDetails() called .");
		Employee employee;
		try {
			employee = employeeDAO.getEmployeeDetails(employeeId);

			if (ObjectUtils.isEmpty(employee)) {
				String exceptionLocation = "EmployeeServiceImpl.findEmployeeDetails()";
				String exMsg = String.format(
						"No records found for employee  %d , Exception raised at %s",
						employeeId, exceptionLocation);
				throw new NoRecordsFoundsException(exMsg);
			}

		} catch (Exception ex) {
			String exceptionLocation = "EmployeeServiceImpl.findEmployeeDetails()";
			String exMsg = String.format(
					"No records found for employee  %d , Exception raised at %s",
					employeeId, exceptionLocation);
			throw new NoRecordsFoundsException(exMsg);
		}

		return employee;
	}

}
